package pg.solvers.PsolB;

import pg.core.Node;
import pg.core.PsolBGame;
import pg.solvers.solverUtils.Round;
import pg.solvers.solverUtils.SolverUtils;

import java.util.HashSet;
import java.util.Set;

public class MarkingSolverThread extends Thread {

    private MarkingPsoBParallel markingPsoBParallel;
    private Round round = new Round();
    private PsolBGame psolBGame;

    public Set<Node> winningRegion0 = new HashSet<Node>();
    public Set<Node> winningRegion1 = new HashSet<Node>();

    public MarkingSolverThread(MarkingPsoBParallel markingPsoBParallel, PsolBGame psolBGame) {

        this.markingPsoBParallel = markingPsoBParallel;
        this.psolBGame = psolBGame;
    }

    @Override
    public void run() {
        Set<Node> nodes = markingPsoBParallel.getNextNodes(psolBGame, round);
        Set<Node> cache = new HashSet<Node>();
        Set<Node> nodesCopy = new HashSet<Node>();
        while (nodes != null) {
            nodesCopy.addAll(nodes);
            while (!nodesCopy.isEmpty() && !nodesCopy.equals(cache)) {
                cache.addAll(nodesCopy);
                Set<Node> monotone = SolverUtils.generateMonotoneAttractor(nodesCopy, round.colour);
                if (monotone.containsAll(nodesCopy)) {
                    Set<Node> attr = SolverUtils.generateAttractor(monotone, round.colour);
                    int player = round.colour % 2;
                    StringBuilder sb = new StringBuilder();

                    boolean allMarked = true;
                    for (Node n : attr) {
                        allMarked = allMarked && n.isMarked();
                        if (n.mark()) {
                            if (player == 0) {
                                winningRegion0.add(n);
                            } else if (player == 1) {
                                winningRegion1.add(n);
                            } else {
                                assert (false);
                            }
                        }
                     }
                    if (allMarked){
                        //System.out.println("ignoring restart of computation");
                         break;
                    }else {
                        //System.out.println("requesting restart of computation");
                        markingPsoBParallel.restartComputation(round.round);
                        break;
                    }

                } else {
                    nodesCopy.retainAll(monotone);
                }
            }
            nodesCopy.clear();
            cache.clear();
            nodes = markingPsoBParallel.getNextNodes(psolBGame, round);
        }
    }
}
