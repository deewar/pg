package pg.solvers.PsolB;

import pg.core.Node;
import pg.core.PsolBGame;
import pg.core.PsolGame;
import pg.solvers.Psol.MarkingPsolParallel;
import pg.solvers.solverUtils.MarkingSolverUtils;
import pg.solvers.solverUtils.ParalellUtils;
import pg.solvers.solverUtils.Round;
import pg.solvers.solverUtils.SolverUtils;

import java.util.*;

public class MarkingPsoBParallel {

    private int nThreads;

    public MarkingPsoBParallel(int nThreads) {
        this.nThreads = nThreads;
    }


    private int round = 0;
    private int index = 0;
    private TreeMap<Integer, Set<Node>> nodeMap;
    Set<Integer> coloursCompleted;
    List<Integer> colours;
    HashMap<Integer,Integer> colourRound = new HashMap<Integer, Integer>();
    synchronized public Set<Node> getNextNodes(PsolBGame game, Round round) {
        round.round = this.round;
        if (game.getNodes().isEmpty()) return null;
        if (index >= colours.size()) return null;
        Set<Node> ret;
        do {
            if (index >= colours.size()) {
                ret = null;
            } else {
                int colour = colours.get(index);
                ret = nodeMap.get(colour);
                round.colour = colour;
                index++;
            }
        } while (ret != null && checkAllMarked(ret) );
        if (ret == null || checkAllMarked(ret)) return null;
        colourRound.put(round.colour,round.round);
        //System.out.println("assigning colour " + round.colour);
        return ret;
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

    private boolean checkAllMarked(Set<Node> nodes) {
        if (nodes.isEmpty()) return true;
        boolean allMarked = true;
        int colour = nodes.iterator().next().getPriority();
        if (coloursCompleted.contains(colour)) return true;
        for (Node n : nodes) {
            if (!n.isMarked()) {
                allMarked = false;
                break;
            }
        }
        if (allMarked) {
            coloursCompleted.add(colour);
        }
        return allMarked;
    }

    public void solve(PsolBGame game) throws Exception {
        if (game.getNodes().isEmpty()) return;

        nodeMap = game.getNodeMap();
        coloursCompleted = new HashSet<Integer>();
        colours = new ArrayList<Integer>(nodeMap.keySet().size());
        for (int i : nodeMap.keySet()) {
            colours.add(i);
        }

        MarkingSolverThread[] markingSolverThreads = new MarkingSolverThread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            markingSolverThreads[i] = new MarkingSolverThread(this, game);
            markingSolverThreads[i].start();
        }
        for (int i = 0; i < nThreads; i++) {
            markingSolverThreads[i].join();
        }

        Set<Node> winningRegion0 = game.getWinningRegion0();
        Set<Node> winningRegion1 = game.getWinningRegion1();
        for (MarkingSolverThread t : markingSolverThreads) {
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

        for (Node n : game.getNodes().values()) {
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
                    System.out.println("found childless " + n);
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

        pBsolve(game);

    }

    public void pBsolve(PsolBGame game) {

        Set<Node> nodes = new HashSet<Node>();
        Set<Node> cache = new HashSet<Node>();
        for (Set<Node> k : nodeMap.values()) {
            if (checkAllMarked(k)) continue;



            nodes.addAll(k);
            int colour = nodes.iterator().next().getPriority();
            while (!nodes.isEmpty() && !nodes.equals(cache)) {
                cache.addAll(nodes);
                Set<Node> monotone = MarkingSolverUtils.generateMonotoneAttractor(nodes, colour);
                if (monotone.containsAll(nodes)) {
                    Set<Node> attr = MarkingSolverUtils.generateAttractor(monotone, colour);
                    int player = colour % 2;
                    for (Node n : attr) {
                        //game.deleteNode(n);
                        n.mark();
                        game.addToWinningRegion(player, n);
                    }
                    this.pBsolve(game);
                    return;
                } else {
                    nodes.retainAll(monotone);
                }
            }
            nodes.clear();
            cache.clear();


        }

    }


}
