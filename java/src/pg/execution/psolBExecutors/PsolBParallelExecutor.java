package pg.execution.psolBExecutors;

import pg.execution.ExecutionResult;
import pg.execution.psolBExecutors.BasePsolBExecutor;
import pg.solvers.PsolB.PsolBParallel;

public class PsolBParallelExecutor extends BasePsolBExecutor {
    private int nThreads;

    public PsolBParallelExecutor(String game, int nThreads) throws Exception{
        super(game);
        this.nThreads = nThreads;
    }

    @Override
    public void solve(ExecutionResult result) throws Exception {
        new PsolBParallel(nThreads).solve(psolBGame);
        super.solve(result);
    }

}
