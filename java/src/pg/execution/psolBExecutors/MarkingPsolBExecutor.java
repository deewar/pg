package pg.execution.psolBExecutors;

import pg.execution.ExecutionResult;
import pg.solvers.PsolB.MarkingPsolBParallel;

public class MarkingPsolBExecutor extends BasePsolBExecutor {
    private int nThreads;

    public MarkingPsolBExecutor(String game, int nThreads) throws Exception{
        super(game);
        this.nThreads = nThreads;

    }

    @Override
    public void solve(ExecutionResult result) throws Exception {
        new MarkingPsolBParallel(nThreads).solve(psolBGame);
        super.solve(result);
    }

}
