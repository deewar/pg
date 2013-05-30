package pg.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PsolGame extends Game {
    private final ArrayList<Node> sortedNodes = new ArrayList<Node>();

    private static final Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.getPriority() < o2.getPriority()) return 1;
            //if (o1.getPriority() == o2.getPriority()) return 0;
            if (o1.getPriority() == o2.getPriority()){
                return  Integer.compare(o1.getId(),o2.getId()) *-1;
            }

            return -1;
        }
    };

    public ArrayList<Node> getSortedNodes() {
        return sortedNodes;
    }

    public void sortNodes() {
        Collections.sort(sortedNodes, comparator);
    }

    @Override
    void initialize() {
       sortNodes();
    }

    @Override
    public void addNode(Node node){
        sortedNodes.add(node);
        super.addNode(node);
    }

    @Override
    public void deleteNode(Node n) {
        if (!sortedNodes.remove(n)) {
            //throw new RuntimeException("node did not exist");

        }
        super.deleteNode(n);
    }

}
