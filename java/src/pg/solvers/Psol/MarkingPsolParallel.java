package pg.solvers.Psol;

import pg.core.Node;
import pg.core.PsolGame;
import pg.solvers.solverUtils.MarkingSolverUtils;
import pg.solvers.solverUtils.Round;
import pg.solvers.solverUtils.SolverUtils;

import java.util.*;

public class MarkingPsolParallel {

    private int nThreads;
    private PsolGame game;

    public MarkingPsolParallel(int nThreads) {
        this.nThreads = nThreads;
    }

    private int round = 0;
    private int index = 0;


    synchronized public Node getNextNode(PsolGame game, Round round) {
        round.round = this.round;
        if (game.getNodes().isEmpty()) return null;
        if (index >= game.getSortedNodes().size()) return null;
        Node n;
        do {
            if (index >= game.getSortedNodes().size()) {
                n = null;
            } else {
                n = game.getSortedNodes().get(index);
                index++;
            }

        } while (n != null && n.isMarked());

        if (n == null || n.isMarked()) return null; //all nodes processed
        //System.out.println("Assigning" + n.getId());
        return n;
    }

    synchronized public void restartComputation(int round) {
        if (round < this.round) {
            //System.out.println("round rejected"+ round);
            return;
        }
        //System.out.println("round "+ round);
        this.round++;


        /*  for (int i = 0; i < deadThreads; i++) {
            Thread t = new MarkingSolverThread(game, this);
            newThreads.add(t);
        }*/
        index = 0;
    }

    //private List<Thread> newThreads = new ArrayList<Thread>();

    public void solve(PsolGame game) throws Exception {
        this.game = game;
        MarkingSolverThread[] markingSolverThreads = new MarkingSolverThread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            markingSolverThreads[i] = new MarkingSolverThread(game, this);
            markingSolverThreads[i].start();
        }
        for (int i = 0; i < nThreads; i++) {
            markingSolverThreads[i].join();
        }


        Set<Node> winningRegion0 = game.getWinningRegion0();
        Set<Node> winningRegion1 = game.getWinningRegion1();

        for (MarkingSolverThread t : markingSolverThreads) {
            game.incrementFatalAttractorCount(t.attractosFound);
            for (Node n : t.winningRegion0) {
                winningRegion0.add(n);
            }
            for (Node n : t.winningRegion1) {
                winningRegion1.add(n);
            }

        }
        /*  while (!newThreads.isEmpty()) {
            newThreads.get(0).join();
        }*/

        for (Node n : game.getSortedNodes()) {
            if (n.isMarked()) continue;
            boolean atleastOneSuccessor = false;
            for (Node k : n.getSuccessors()) {
                if (!k.isMarked()) {
                    atleastOneSuccessor = true;
                }
            }
            if (!atleastOneSuccessor) {
                // this node has been abandoned.. add to winning region

                if (n.mark()) {
                    //System.out.println("found childless " + n);
                    int player = 1 - n.getOwner();
                    if (player == 0) {

                        if (!winningRegion0.contains(n)) {
                            winningRegion0.add(n);
                        }

                    } else {

                        if (!winningRegion1.contains(n)) {
                            winningRegion1.add(n);
                        }

                    }
                }
            }
        }

        psolve(game);

    }


    public static void psolve(PsolGame game) {

        HashSet<Node> nodes = new HashSet<Node>();
        for (Node k : game.getSortedNodes()) {
            if (k.isMarked()) continue;
            nodes.add(k);
            HashSet<Node> attr = MarkingSolverUtils.generateMonotoneAttractor(nodes, k.getPriority());
            if (attr.contains(k)) {
                //fatal attractor found

                attr = MarkingSolverUtils.generateAttractor(attr, k.getPriority());
                //System.out.println("attractor found for node " +k.getId() + "of size " + attr.size());
                int player = k.getPriority() % 2;
                for (Node n : attr) {
                    n.mark();
                    game.addToWinningRegion(player, n);
                }
                psolve(game);
                return;
            }

            nodes.remove(k);
        }

    }

    /*Integer deadThreads = 0;

    synchronized public void dyingThread() {
        System.out.println("dying thread");
        deadThreads++;

    }*/
}
