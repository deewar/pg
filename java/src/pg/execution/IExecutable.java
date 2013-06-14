package pg.execution;

public interface IExecutable {
    public void solve(ExecutionResult result) throws  Exception;

    void resetGame() throws  Exception;
}
