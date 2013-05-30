package pg.solvers.Psol;

import pg.core.Node;
import pg.core.PsolGame;
import pg.solvers.SolverUtils;

import java.util.HashSet;
import java.util.Iterator;


public class Psol {

    public void solve(PsolGame game) {
        if (game.getNodes().isEmpty() ) return;
        HashSet<Node> nodes = new HashSet<Node>();
        for (Node k : game.getSortedNodes()) {
            nodes.add(k);
            HashSet<Node> attr = SolverUtils.generateMonotoneAttractor(nodes, k.getPriority());
            if (attr.contains(k)) {
                //fatal attractor found

                attr = SolverUtils.generateAttractor(attr, k.getPriority());
                System.out.println("attractor found for node " +k.getId() + "of size " + attr.size());
                int player = k.getPriority() % 2;
                for (Node n : attr) {
                    game.deleteNode(n);
                    game.addToWinningRegion(player, n);
                }
                this.solve(game);
                return;
            }
            if (!attr.isEmpty()) {
                Iterator<Node> iterator = k.getSuccessors().iterator();

                while (iterator.hasNext()) {
                    Node succ = iterator.next();
                    if (attr.contains(succ)) {
                        iterator.remove();
                        succ.removeFromPredecessors(k);
                    }
                }
            }
            nodes.remove(k);
        }

    }
}
