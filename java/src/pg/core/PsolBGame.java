package pg.core;


import java.util.*;

public class PsolBGame extends Game {
    private final TreeMap<Integer, Set<Node>> nodeMap = new TreeMap<Integer, Set<Node>>(comparator);

    private static final Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return  o1.compareTo(o2)*-1;
        }
    };

    public TreeMap<Integer, Set<Node>> getNodeMap() {
        return nodeMap;
    }


    @Override
    void initialize() {

    }

    @Override
    public void addNode(Node node) {
        int priority = node.getPriority();
        if ( nodeMap.containsKey(priority) ){
            Set<Node> nodes = nodeMap.get(priority);
            nodes.add(node);
        }else {
            Set<Node> nodes = new HashSet<Node>();
            nodes.add(node);
            nodeMap.put(priority,nodes);
        }
        super.addNode(node);
    }

    @Override
    public void deleteNode(Node n) {
        int priority = n.getPriority();
        //System.out.println("deleting node " + n);

        if (nodeMap.containsKey(priority)){
            Set<Node> nodes = nodeMap.get(priority);
            if (! nodes.remove(n)){
                //throw new RuntimeException("node did not exist");
            }
            if(nodes.isEmpty()){
                nodeMap.remove(priority);
            }
        }else {
            //throw new RuntimeException("node did not exist" +n);
        }
        super.deleteNode(n);
    }
}
