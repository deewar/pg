package pg.solvers.solverUtils;

import pg.core.Game;
import pg.core.Node;
import pg.core.PsolGame;

import java.util.Set;

public class ParalellUtils {

    public static void handleSuccessorLessNodes(Game game, Set<Node> predecessors) {
        for (Node n : predecessors) {
            if (n.getSuccessors().isEmpty()) {
                if ( game.getWinningRegion0().contains(n) || game.getWinningRegion1().contains(n)){
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
}
