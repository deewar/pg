package pg.execution.psolExecutors;


import pg.execution.ExecutionResult;
import pg.execution.psolExecutors.BasePsolExecutor;
import pg.solvers.Psol.PsolParallel;

public class PsolParallelExecuter extends BasePsolExecutor {
    private int nThreads;

    public PsolParallelExecuter(String game, int nThreads) throws Exception{
        super(game);
        this.nThreads = nThreads;
    }

    @Override
    public void solve(ExecutionResult result) throws Exception {
        new PsolParallel(nThreads).solve(psolGame);
        super.solve(result);
    }

}
