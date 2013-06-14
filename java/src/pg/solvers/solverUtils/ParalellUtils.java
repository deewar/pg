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
                game.incrementNoOfAbandonedNodes();
                game.deleteNode(n);
                game.addToWinningRegion(1 - n.getOwner(), n);
            }
        }
    }

}
