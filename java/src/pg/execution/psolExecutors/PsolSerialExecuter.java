package pg.execution.psolExecutors;

import pg.execution.ExecutionResult;
import pg.execution.psolExecutors.BasePsolExecutor;
import pg.solvers.Psol.Psol;

public class PsolSerialExecuter extends BasePsolExecutor {
    public PsolSerialExecuter(String game) throws Exception {
        super(game);
    }

    @Override
    public void solve(ExecutionResult result) throws Exception {
        new Psol().solve(psolGame);
        super.solve(result);
    }

}
