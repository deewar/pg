package pg.execution.psolBExecutors;

import pg.core.Parser;
import pg.core.PsolBGame;
import pg.execution.BaseExecutor;
import pg.execution.ExecutionResult;

public class BasePsolBExecutor extends BaseExecutor {

    protected PsolBGame psolBGame;

    public BasePsolBExecutor(String game) throws Exception {
        super(game);
    }

    @Override
    public void solve(ExecutionResult result) throws Exception {
        result.noOfFatalAttactors = psolBGame.getNoOfFatalAttractors();
        result.game = psolBGame;
    }

    @Override
    public void resetGame() throws Exception {
        super.resetGame();
        psolBGame = Parser.parsePsolBGame(gameFile);
    }
}
