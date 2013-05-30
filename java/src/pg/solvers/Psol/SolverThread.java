package pg.solvers.Psol;

import pg.core.Node;
import pg.solvers.SolverUtils;

import java.util.HashSet;

public class SolverThread extends Thread {
    public HashSet<Node> attractor = null;
    public Node node;

    public SolverThread(Node node) {

        this.node = node;
    }

    @Override
    public void run() {
        if (node == null) {
            attractor = null;
            return;
        }
        //System.out.println("solving " + node.getId());
        HashSet<Node> nodes = new HashSet<Node>();
        nodes.add(node);
        attractor = SolverUtils.generateMonotoneAttractor(nodes, node.getPriority());
        if (attractor.contains(node)) {
            attractor = SolverUtils.generateAttractor(attractor, node.getPriority());
        }else {
            attractor.clear();
        }
    }
}
