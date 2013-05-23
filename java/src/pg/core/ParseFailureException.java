package pg.core;

/**
 * Created with IntelliJ IDEA.
 * User: deewar
 * Date: 20/05/13
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class ParseFailureException extends Exception {
    public final String message;
    public ParseFailureException(String message){
        this.message = message;
    }
}
