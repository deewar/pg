package pg.solvers.PsolB;

import pg.core.Node;
import pg.core.PsolBGame;
import pg.solvers.solverUtils.SolverUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;


public class PsolB {

    public void solve(PsolBGame game){

        TreeMap<Integer,Set<Node>> nodeMap = game.getNodeMap();
        if (game.getNodes().isEmpty() ) return;

        Set<Node> nodes = new HashSet<Node>();
        Set<Node> cache = new HashSet<Node>();
        for (Integer color : nodeMap.keySet()){
            nodes.addAll(nodeMap.get(color));
            while(!nodes.isEmpty() && !nodes.equals(cache)){
                cache.addAll(nodes);
                Set<Node> monotone = SolverUtils.generateMonotoneAttractor(nodes, color);
                if(monotone.containsAll(nodes)) {
                    game.incrementFatalAttractorCount();
                    Set<Node> attr = SolverUtils.generateAttractor(monotone, color);
                    int player = color % 2;
                    for (Node n : attr) {
                        game.deleteNode(n);
                        game.addToWinningRegion(player, n);
                    }
                    this.solve(game);
                    return;
                } else {
                    nodes.retainAll(monotone);
                }
            }
            nodes.clear();
            cache.clear();
        }

    }
}
