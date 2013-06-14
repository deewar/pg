package pg.solvers.Psol;

import pg.core.Node;
import pg.core.PsolGame;
import pg.solvers.solverUtils.Round;
import pg.solvers.solverUtils.SolverUtils;

import java.util.HashSet;
import java.util.Set;

public class MarkingSolverThread extends Thread {
    public int attractosFound = 0;
    private PsolGame game;
    Round round = new Round();
    private MarkingPsolParallel markingPsolParallel;

    public Set<Node> winningRegion0 = new HashSet<Node>();
    public Set<Node> winningRegion1 = new HashSet<Node>();

    public MarkingSolverThread(PsolGame game, MarkingPsolParallel markingPsolParallel) {
        this.game = game;
        this.markingPsolParallel = markingPsolParallel;
    }

    @Override
    public void run() {
        Node node = markingPsolParallel.getNextNode(game, round);
        //System.out.println("solving " + node.getId());
        Set<Node> nodes = new HashSet<Node>();
        Set<Node> attractor = null;

        while (node != null) {
            //System.out.println("Processing " + node.getId() );
            nodes.clear();
            nodes.add(node);
            attractor = SolverUtils.generateMonotoneAttractor(nodes, node.getPriority());
            if (attractor.contains(node)) {

                attractosFound++;
                attractor = SolverUtils.generateAttractor(attractor, node.getPriority());
                handleAttractor(attractor, node, game);
                markingPsolParallel.restartComputation(round.round);
            } else {
                //System.out.println("attractor NOT  found for node " + node.getId() );

                /*  if (!attractor.isEmpty()) {
                    Iterator<Node> iterator = node.getSuccessors().iterator();

                    while (iterator.hasNext()) {
                        Node succ = iterator.next();
                        if (attractor.contains(succ)) {
                            iterator.remove();
                            succ.removeFromPredecessors(node);
                            //System.out.println("deleting edge for node " + node.getId() + " -> " + succ.getId());
                        }
                    }
                }*/
            }

            node = markingPsolParallel.getNextNode(game, round);
        }
        //markingPsolParallel.dyingThread();

    }



    private void handleAttractor(Set<Node> attractor, Node node, PsolGame game) {

        //Set<Node> predecessors = new HashSet<Node>();
        //Set<Node> wonNodes = new HashSet<Node>();
        int player = node.getPriority() % 2;
        for (Node n : attractor) {
            /*for (Node pred : n.getPredecessors()) {
                if (!attractor.contains(pred)) {
                    predecessors.add(pred);
                }
            }*/
            if (n.mark()){
                if( player == 0){
                    winningRegion0.add(n);
                }else if (player ==1)  {
                    winningRegion1.add(n);
                }else {
                    assert (false);
                }

            } /*else{
                //System.out.println("node " +node.getId()+ " lost  "+ n.getId());
            }*/

        }
        //StringBuilder sb = new StringBuilder();
        //sb.append("node " +node.getId()+ " won " ) ;

/*        for (Node n : wonNodes) {
            //sb.append(n.getId() + " ,");
            if ( player == 0 ){
               // HashSet<Node> winningRegion0 = game.getWinningRegion0();
              *//*  synchronized (winningRegion0) {
                    if (!winningRegion0.contains(n)){
                        winningRegion0.add(n);
                    }
                }*//*
                //if(!winningRegion0.contains(n)){
                    winningRegion0.add(n);
                //}
            } else{
               *//* HashSet<Node> winningRegion1 = game.getWinningRegion1();
                synchronized (winningRegion1) {
                    if (!winningRegion1.contains(n)){
                        winningRegion1.add(n);
                    }
                }*//*
                //if ( !winningRegion1.contains(n)){
                    winningRegion1.add(n);
                //}
            }*/
        }


        //System.out.println(sb.toString());

        /*for( Node n :ParalellUtils.handleSuccessorLessNodesByMarking(game, predecessors )){
            markingPsolParallel.depositNodeForRetest(n);
        }*/



}