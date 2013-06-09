package pg.execution.psolExecutors;


import pg.execution.ExecutionResult;
import pg.execution.psolExecutors.BasePsolExecutor;
import pg.solvers.Psol.MarkingPsolParallel;

public class MarkingPsolParallelExecuter extends BasePsolExecutor {
    private int nThreads;

    public MarkingPsolParallelExecuter(String game, int nThreads) throws Exception{
        super(game);
        this.nThreads = nThreads;
    }

    @Override
    public void solve(ExecutionResult result) throws Exception {
        new MarkingPsolParallel(nThreads).solve(psolGame);
        super.solve(result);
    }

}
