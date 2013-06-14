package pg.execution;

import pg.core.Game;

public class ExecutionResult {
    public long runTime;
    public Game game;
    public boolean validSolution = true;
    public int noOfFatalAttactors;
    public int noOfAbandonedNodes;
    public int noOfSubGameAttractors;
    public boolean timeOut;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BENCHMARK Results\n");
        sb.append("RunTime ");
        sb.append(runTime);
        sb.append('\t');
        sb.append("Number of FatalAttractors ");
        sb.append(noOfFatalAttactors);
        sb.append('\t');
        sb.append("Number of Abandoned Nodes ");
        sb.append(noOfAbandonedNodes);
        sb.append('\t');
        sb.append("Number of sub game Fatal Attractors ");
        sb.append(noOfSubGameAttractors);
        sb.append('\t');
        sb.append("timeout? ");
        sb.append(timeOut ? 'y' : 'n');
        sb.append("\t");
        sb.append("Valid Solution? ");
        sb.append(validSolution ? 'y' : 'n');
        sb.append("\n\n");
        return sb.toString();
    }
}
