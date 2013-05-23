package pg.solvers;

import pg.core.Game;
import pg.core.Node;

import java.util.HashSet;
import java.util.Iterator;


public class PSol implements ISolver {
    @Override
    public void solve(Game game) {
        HashSet<Node> nodes = new HashSet<Node>();
        for (Node k : game.getSortedNodes()) {
            nodes.add(k);
            HashSet<Node> attr = SolverUtils.generateMonotoneAttractor(game, nodes, k.getPriority());
            if (attr.contains(k)) {
                //fatal attractor found
                attr = SolverUtils.generateAttractor(game, attr, k.getPriority());
                for (Node n : attr) {
                    game.deleteNode(n);
                    int player = k.getPriority() % 2;
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
