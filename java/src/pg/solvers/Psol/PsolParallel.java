package pg.solvers.Psol;

import pg.core.Node;
import pg.core.PsolGame;
import pg.solvers.SolverUtils;

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
                //System.out.println("attractor found for node " + node.getId() + " of size " + attractor.size());
                //fatal attractor
                fatal = true;
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
                            //System.out.println("deleting edge for node " + node.getId() + " -> " + succ.getId());
                        }
                    }
                    // think about this
                    // if (node.getSuccessors().isEmpty())
                }
            }
        }
        for (Node n : predecessors) {
            if (n.getSuccessors().isEmpty()) {
                if ( game.getWinningRegion0().contains(n) || game.getWinningRegion1().contains(n)){
                    continue;
                }
                //System.out.println(n.getId() + " going to " + (1 - n.getOwner()));
                //with attractors
                  Set<Node> nod = new HashSet<Node>();
                    nod.add(n);
                  nod = SolverUtils.generateAttractor(nod,n.getPriority());
                  for ( Node k : nod){
                      game.deleteNode(n);
                      game.addToWinningRegion(1 - n.getOwner(), n);
                  }
                //
                //game.deleteNode(n);
                //game.addToWinningRegion(1 - n.getOwner(), n);
            }
        }
        return fatal;
    }


}
