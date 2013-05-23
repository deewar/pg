package pg.solvers;


import pg.core.Game;
import pg.core.Node;

import java.util.ArrayList;
import java.util.HashSet;

public class SolverUtils {

    public static HashSet<Node> generateMonotoneAttractor(Game game ,HashSet<Node> nodes, int color ){
        return generateAttractor(game,nodes,color,true);
    }

    public static HashSet<Node> generateAttractor(Game game , HashSet<Node> nodes , int color) {
        return generateAttractor(game,nodes,color,false);
    }

    private static HashSet<Node> generateAttractor(Game game, HashSet<Node> nodes, int color , boolean coloured) {
        HashSet<Node> attractorSet = new HashSet<Node>();
        HashSet<Node> nodesToTest = new HashSet<Node>();
        HashSet<Node> nodesChecked = new HashSet<Node>();
        HashSet<Node> nodesForNextIteration = new HashSet<Node>();

        int player = color % 2;

        for (Node node : nodes) {
            for (Node n : node.getPredecessors()){
                nodesToTest.add(n);
            }

            if(!coloured){
               attractorSet.add(node);
            }
        }


        boolean attractorSetModified = false;

        do {
            attractorSetModified = false;
            for (Node n : nodesToTest) {

                nodesChecked.add(n);

                if (coloured){
                    if (n.getPriority() < color) continue;
                }

                if (n.getOwner() == player) {
                    addNodeToAttractor(attractorSet, nodesChecked, nodesForNextIteration, n);
                    attractorSetModified = true;
                } else {
                    boolean allLinks = true;
                    for (Node succ : n.getSuccessors()) {
                        if (!attractorSet.contains(succ)) {
                            //atleast one escape available.try this node later
                            allLinks = false;
                            break;
                        }
                    }

                    if (allLinks) {
                        addNodeToAttractor(attractorSet, nodesChecked, nodesForNextIteration, n);
                        attractorSetModified = true;
                    } else{
                        nodesForNextIteration.add(n);
                    }
                }
            }
            HashSet<Node> tmp = nodesToTest;
            nodesToTest =  nodesForNextIteration;
            nodesForNextIteration = tmp;
            nodesForNextIteration.clear();

        } while (attractorSetModified);


        return attractorSet;
    }

    private static void addNodeToAttractor(HashSet<Node> attractorSet, HashSet<Node> nodesChecked, HashSet<Node> nodeForNextIteration, Node n) {
        attractorSet.add(n);
        for (Node pred : n.getPredecessors()) {
            if (!nodesChecked.contains(pred) && !attractorSet.contains(pred)) {
                nodeForNextIteration.add(pred);
            }
        }
    }
}
