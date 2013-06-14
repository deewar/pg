package pg.solvers.PsolB;

import pg.core.Node;
import pg.solvers.solverUtils.SolverUtils;

import java.util.HashSet;
import java.util.Set;

public class SolverThread extends  Thread{
    public Set<Node> attractor = null;
    public Set<Node> nodes;
    public int color;
    public Set<Node> cache;
    public boolean attractorFound;


    public SolverThread( Set<Node> nodes , int color) {
        this.nodes = nodes;
        this.color = color;
        attractorFound = false;
    }

    @Override
    public void run() {
        if (nodes == null) {
            attractor = null;
            return;
        }
        //System.out.println("solving " + node.getId());
        cache = new HashSet<Node>();
        while (!nodes.isEmpty() && !nodes.equals(cache)) {
            cache.addAll(nodes);
            Set<Node> monotone = SolverUtils.generateMonotoneAttractor(nodes, color);
            if (monotone.containsAll(nodes)) {
                attractorFound = true;
                attractor = SolverUtils.generateAttractor(monotone, color);
                return;
            } else {
                nodes.retainAll(monotone);
            }
        }

    }
}
