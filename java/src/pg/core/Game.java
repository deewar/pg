package pg.core;

import java.util.*;

public class Game {

    private int parity;
    private final HashMap<Integer, Node> nodes = new HashMap<Integer, Node>(200);
    private final HashSet<Node> winningRegion1 = new HashSet<Node>(100);
    private final HashSet<Node> winningRegion0 = new HashSet<Node>(100);
    private final ArrayList<Node> sortedNodes = new ArrayList<Node>();

    private static final Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.getPriority() < o2.getPriority()) return 1;
            if (o1.getPriority() == o2.getPriority()) return 0;
            return -1;
        }
    };


    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }


    public HashSet<Node> getWinningRegion1() {
        return winningRegion1;
    }


    public HashSet<Node> getWinningRegion0() {
        return winningRegion0;
    }


    public ArrayList<Node> getSortedNodes() {
        return sortedNodes;
    }


    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("game parity: ");
        sb.append(parity);
        sb.append(";\n");


        for (Node n : sortedNodes) {
            sb.append(n.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void sortNodes() {
        Collections.sort(sortedNodes, comparator);
    }

    public void deleteNode(Node n) {
        if (nodes.remove(n.getId()) == null || !sortedNodes.remove(n))
            throw new RuntimeException("node did not exist");
        n.deleteNode();
    }

    public void addToWinningRegion(int player, Node n) {
        if (player == 0) {
           winningRegion0.add(n);
        } else if (player == 1) {
           winningRegion1.add(n);
        } else {
            throw  new RuntimeException("how did this happen");
        }
    }
}
