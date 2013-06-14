package pg.solvers.Psol;

import pg.core.Node;
import pg.core.PsolGame;
import pg.solvers.solverUtils.ParalellUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PsolParallel {

    private int nThreads;

    public PsolParallel(int nThreads) {
        this.nThreads = nThreads;
    }

    private Set<Node>[] executeBatch(Node[] nodes) throws Exception {
        Set<Node>[] attractors = new HashSet[nThreads];
        SolverThread[] solverThreads = new SolverThread[nThreads];

        for (int i = 0; i < nThreads; i++) {
            solverThreads[i] = new SolverThread(nodes[i]);
            solverThreads[i].start();
        }
        for (SolverThread t : solverThreads) {
            t.join();
        }

        for (int i = 0; i < nThreads; i++) {
            attractors[i] = solverThreads[i].attractor;
        }
        return attractors;
    }

    public void solve(PsolGame game) throws Exception {
        if (game.getNodes().isEmpty()) return;
        List<Node> nodeList = game.getSortedNodes();
        int size = nodeList.size();
        int iterations = size / nThreads;
        Node[] nodes = new Node[nThreads];

        //System.out.println(String.format("Size of nodelist %d", size));
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < nThreads; j++) {
                nodes[j] = nodeList.get(i * nThreads + j);
            }
            Set<Node>[] attractors = executeBatch(nodes);
            boolean attractorFound = handleAttractors(attractors, nodes, game);

            if (attractorFound) {
                if (!nodeList.isEmpty()) {
                    solve(game);
                }
                return;
            }
        }

        for (int i = 0; i < nThreads; i++) {
            nodes[i] = null;
        }
        for (int i = iterations * nThreads, j = 0; i < size; i++, j++) {
            nodes[j] = nodeList.get(i);
        }
        Set<Node>[] attractors = executeBatch(nodes);
        if (handleAttractors(attractors, nodes, game)){
            if (!nodeList.isEmpty()) {
                solve(game);
            }
            return;
        }


    }


    private boolean handleAttractors(Set<Node>[] attractors, Node[] nodes, PsolGame game) {
        boolean fatal = false;
        Set<Node> predecessors = new HashSet<Node>();
        for (int i = 0; i < nThreads; i++) {
            Node node = nodes[i];
            if (node == null) continue;
            Set<Node> attractor = attractors[i];
            if (attractor.isEmpty()) continue;


            if (attractor.contains(node)) {
                fatal = true;
                game.incrementFatalAttractorCount();
                int player = node.getPriority() % 2;
                for (Node n : attractor) {
                    for (Node pred : n.getPredecessors()) {
                        if (!attractor.contains(pred)) {
                            predecessors.add(pred);
                        }
                    }
                    game.deleteNode(n);
                    game.addToWinningRegion(player, n);
                }

            } else {
                if (!attractor.isEmpty()) {
                    Iterator<Node> iterator = node.getSuccessors().iterator();

                    while (iterator.hasNext()) {
                        Node succ = iterator.next();
                        if (attractor.contains(succ)) {
                            iterator.remove();
                            succ.removeFromPredecessors(node);
                        }
                    }

                }
            }
        }
        ParalellUtils.handleSuccessorLessNodes(game, predecessors);
        return fatal;
    }


}
