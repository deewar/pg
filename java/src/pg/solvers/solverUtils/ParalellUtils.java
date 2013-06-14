package pg.solvers.solverUtils;

import pg.core.Game;
import pg.core.Node;
import pg.core.PsolGame;

import java.util.HashSet;
import java.util.Set;

public class ParalellUtils {

    public static void handleSuccessorLessNodes(Game game, Set<Node> predecessors) {
        for (Node n : predecessors) {
            if (n.getSuccessors().isEmpty()) {
                if (game.getWinningRegion0().contains(n) || game.getWinningRegion1().contains(n)) {
                    continue;
                }
                //System.out.println(n.getId() + " going to " + (1 - n.getOwner()));
                //with attractors
                /*  Set<Node> nod = new HashSet<Node>();
                    nod.add(n);
                  nod = SolverUtils.generateAttractor(nod,n.getPriority());
                  for ( Node k : nod){
                      game.deleteNode(n);
                      game.addToWinningRegion(1 - n.getOwner(), n);
                  }*/
                //
                game.deleteNode(n);
                game.addToWinningRegion(1 - n.getOwner(), n);
            }
        }
    }

    public static Set<Node> handleSuccessorLessNodesByMarking(Game game, Set<Node> predecessors) {
        Set<Node> nodes = new HashSet<Node>();
        for (Node n : predecessors) {

            boolean atleastOneSuccessor = false;
            for (Node k : n.getSuccessors()) {
                if (!k.isMarked()) {
                    atleastOneSuccessor = true;
                }
            }
            if (!atleastOneSuccessor) {
                // this node has been abandoned.. add to winning region
                nodes.add(n);
                /*if (n.mark()) {
                    System.out.println("found childless " + n + " for " + node);
                    int player = 1 - n.getOwner();
                    if (player == 0) {
                        HashSet<Node> winningRegion0 = game.getWinningRegion0();
                        synchronized (winningRegion0) {
                            if (!winningRegion0.contains(n)) {
                                winningRegion0.add(n);
                            }
                        }
                    } else {
                        HashSet<Node> winningRegion1 = game.getWinningRegion1();
                        synchronized (winningRegion1) {
                            if (!winningRegion1.contains(n)) {
                                winningRegion1.add(n);
                            }
                        }
                    }
                }*/
            }
        }
        return nodes;
    }
}
