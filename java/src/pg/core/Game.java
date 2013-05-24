package pg.core;

import java.util.*;

public abstract class Game {

    private int parity;
    private final HashMap<Integer, Node> nodes = new HashMap<Integer, Node>(200);
    private final HashSet<Node> winningRegion1 = new HashSet<Node>(100);
    private final HashSet<Node> winningRegion0 = new HashSet<Node>(100);


    public HashMap<Integer, Node> getNodes() {
        return nodes;
    }


    public HashSet<Node> getWinningRegion1() {
        return winningRegion1;
    }


    public HashSet<Node> getWinningRegion0() {
        return winningRegion0;
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
        sb.append(getParity());
        sb.append(";\n");


        for (Node n : nodes.values()) {
            sb.append(n.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    abstract void initialize();

    public void deleteNode(Node n) {
       //System.out.println("deleting node " + n);
        if (nodes.remove(n.getId()) == null )
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

    public void addNode(Node node){
        nodes.put(node.getId(),node);
    }
}
