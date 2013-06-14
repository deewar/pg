package pg.execution;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

public abstract class BaseExecutor implements  IExecutable{
    protected ByteArrayOutputStream byteArrayOutputStream;
    protected StringReader gameFile ;
    private String game;


    public BaseExecutor(String game)  throws  Exception{
        this.game = game;
        resetGame();
    }

    @Override
    public void resetGame() throws  Exception{
         gameFile = new StringReader(game);
    }
}
