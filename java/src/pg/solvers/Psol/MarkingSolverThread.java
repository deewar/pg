package pg.solvers.Psol;

import pg.core.Node;
import pg.core.PsolGame;
import pg.solvers.solverUtils.Round;
import pg.solvers.solverUtils.SolverUtils;

import java.util.HashSet;
import java.util.Set;

public class MarkingSolverThread extends Thread {
    public int attractosFound = 0;
    private PsolGame game;
    Round round = new Round();
    private MarkingPsolParallel markingPsolParallel;

    public Set<Node> winningRegion0 = new HashSet<Node>();
    public Set<Node> winningRegion1 = new HashSet<Node>();

    public MarkingSolverThread(PsolGame game, MarkingPsolParallel markingPsolParallel) {
        this.game = game;
        this.markingPsolParallel = markingPsolParallel;
    }

    @Override
    public void run() {
        Node node = markingPsolParallel.getNextNode(game, round);
        Set<Node> nodes = new HashSet<Node>();
        Set<Node> attractor = null;

        while (node != null) {
            nodes.clear();
            nodes.add(node);
            attractor = SolverUtils.generateMonotoneAttractor(nodes, node.getPriority());
            if (attractor.contains(node)) {

                attractosFound++;
                attractor = SolverUtils.generateAttractor(attractor, node.getPriority());
                handleAttractor(attractor, node, game);
                markingPsolParallel.restartComputation(round.round);
            } else {

            }

            node = markingPsolParallel.getNextNode(game, round);
        }

    }


    private void handleAttractor(Set<Node> attractor, Node node, PsolGame game) {

        int player = node.getPriority() % 2;
        for (Node n : attractor) {
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

    }


}
