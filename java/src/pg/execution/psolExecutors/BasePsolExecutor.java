package pg.execution.psolExecutors;

import pg.core.Parser;
import pg.core.PsolGame;
import pg.execution.BaseExecutor;
import pg.execution.ExecutionResult;

import java.io.ByteArrayOutputStream;

public abstract class BasePsolExecutor extends BaseExecutor {
    protected PsolGame psolGame;

    public BasePsolExecutor(String game) throws Exception {
        super(game);
    }

    @Override
    public void solve(ExecutionResult result) throws Exception {
        result.noOfFatalAttactors = psolGame.getNoOfFatalAttractors();
        result.game = psolGame;
    }

    @Override
    public void resetGame() throws Exception {
        super.resetGame();
        psolGame = Parser.parsePsolGame(gameFile);
    }
}
