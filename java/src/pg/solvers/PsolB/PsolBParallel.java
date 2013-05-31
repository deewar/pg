package pg.solvers.PsolB;

import pg.core.Node;
import pg.core.PsolBGame;
import pg.solvers.solverUtils.ParalellUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class PsolBParallel {

    private int nThreads;

    public PsolBParallel(int nThreads) {

        this.nThreads = nThreads;
    }


    public void solve(PsolBGame game) throws Exception {

        TreeMap<Integer, Set<Node>> nodeMap = game.getNodeMap();
        if (game.getNodes().isEmpty()) return;

        SolverThread[] threads = new SolverThread[nThreads];
        int index = 0;
        for (Integer color : nodeMap.keySet()) {

            if (index == nThreads) {
                //execute and process
                boolean attractorFound = executeInParallel(game, threads);
                if (attractorFound) {
                    this.solve(game);
                    return;
                }
                //clear the array
                index = 0;
                for (int i = 0; i < nThreads; i++) {
                    threads[i] = null;
                }

            }

            //build batch
            Set<Node> nodes = new HashSet<Node>();
            nodes.addAll(nodeMap.get(color));
            threads[index] = new SolverThread(nodes, color);
            //System.out.println("adding nodes to batch of color " + color);
            index++;


        }
        boolean attractorFound = executeInParallel(game, threads);
        if (attractorFound) {
            this.solve(game);
            return;
        }
    }

    private boolean executeInParallel(PsolBGame game, SolverThread[] threads) throws InterruptedException {
        //System.out.println("executing a batch");

        for (SolverThread t : threads) {
            if (t != null) t.start();
        }
        for (SolverThread t : threads) {
            if (t != null) t.join();
        }

        Set<Node> predecessors = new HashSet<Node>();
        boolean attractorFound = false;
        for (SolverThread t : threads) {
            if (t != null && t.attractorFound) {
                attractorFound = true;
                int player = t.color % 2;
                for (Node n : t.attractor) {
                    for (Node p : n.getPredecessors()) {
                        if (!t.attractor.contains(p)) {
                            predecessors.add(p);
                        }
                    }
                    game.deleteNode(n);
                    game.addToWinningRegion(player, n);
                }
            }
        }

        ParalellUtils.handleSuccessorLessNodes(game, predecessors);
        return attractorFound;
    }


}
