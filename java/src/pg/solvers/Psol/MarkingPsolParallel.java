package pg.solvers.Psol;

import pg.core.Game;
import pg.core.Node;
import pg.core.PsolGame;
import pg.solvers.solverUtils.ParalellUtils;
import pg.solvers.solverUtils.Round;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MarkingPsolParallel {

    private int nThreads;

    public MarkingPsolParallel(int nThreads) {
        this.nThreads = nThreads;
    }

    private  int round =0;
    private int index = 0;


    synchronized public Node getNextNode(PsolGame game , Round round){
        round.round = this.round;
        if (game.getNodes().isEmpty()) return  null;
        if ( index >= game.getSortedNodes().size()) return  null;
        Node n;
        do{
            if ( index >= game.getSortedNodes().size()) {
                n = null;
            }else{
                n =  game.getSortedNodes().get(index);
                index++;
            }

        }while( n != null && n.isMarked());

        if ( n == null ||  n.isMarked() ) return null; //all nodes processed
        //System.out.println("Assigning" + n.getId());
        return  n;
    }

    synchronized public void restartComputation(int round){
        if ( round < this.round) {
            //System.out.println("round rejected"+ round);
            return;
        }
        //System.out.println("round "+ round);
        this.round++;
        index = 0;
    }

    public void solve(PsolGame game) throws Exception {
        MarkingSolverThread[] markingSolverThreads = new MarkingSolverThread[nThreads];
        for ( int i  = 0 ; i < nThreads ; i ++){
            markingSolverThreads[i] = new MarkingSolverThread(game, this);
            markingSolverThreads[i].start();
        }
        for( int i = 0 ; i < nThreads ; i++ ){
            markingSolverThreads[i].join();
        }

        for (Node n : game.getSortedNodes()){
            if (n.isMarked()) continue;
            boolean atleastOneSuccessor = false;
            for (Node k : n.getSuccessors()) {
                if (!k.isMarked()) {
                    atleastOneSuccessor = true;
                }
            }
            if (!atleastOneSuccessor) {
                // this node has been abandoned.. add to winning region

                if (n.Mark()) {
                    System.out.println("found childless " + n );
                    int player = 1 - n.getOwner();
                    if (player == 0) {
                        HashSet<Node> winningRegion0 = game.getWinningRegion0();
                        synchronized (winningRegion0) {
                            if (!winningRegion0.contains(n)) {
                                winningRegion0.add(n);
                            }
                        }
                    } else {
                        HashSet<Node> winningRegion1 = game.getWinningRegion1();
                        synchronized (winningRegion1) {
                            if (!winningRegion1.contains(n)) {
                                winningRegion1.add(n);
                            }
                        }
                    }
                }
            }
        }


    }




    private List<Node> nodesToTest =  new ArrayList<Node>();

    public void depositNodeForRetest(Node node){
       synchronized (nodesToTest){
        nodesToTest.add(node);
       }
    }





}
