package pg.execution.psolBExecutors;

import pg.execution.ExecutionResult;
import pg.execution.psolBExecutors.BasePsolBExecutor;
import pg.solvers.PsolB.PsolB;

public class PsolBSerialExecutor extends BasePsolBExecutor {
    public PsolBSerialExecutor(String game) throws Exception {
        super(game);
    }

    @Override
    public void solve(ExecutionResult result) throws Exception {
        new PsolB().solve(psolBGame);
        super.solve(result);
    }

}
