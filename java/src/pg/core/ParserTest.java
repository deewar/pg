package pg.core;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

import static junit.framework.Assert.assertTrue;


public class ParserTest {
    @Test
    public void testParseSimpleGame() throws Exception {
        String path ="games/game.game";
        PsolGame g = Parser.parsePsolGame(path);
        checkLength(path,g);
    }
    @Test
    public void testParseCliqueSmall() throws Exception {
        String path ="games/clique/clique.game";
        PsolGame g = Parser.parsePsolGame(path);
        checkLength(path,g);
    }
    @Test
    public void testParseElevatorSmall() throws Exception {
        String path ="games/elevator/elevator.game";
        PsolGame g = Parser.parsePsolGame(path);
        checkLength(path,g);
    }
    @Test
         public void testParseJurdzinskiSmall() throws Exception {
        String path ="games/jurdzinski/jurdzinski.game";
        PsolGame g = Parser.parsePsolGame(path);
        checkLength(path,g);
    }
    @Test
    public void testParseLadderSmall() throws Exception {
        String path ="games/ladder/ladder.game";
        PsolGame g = Parser.parsePsolGame(path);
        checkLength(path,g);
    }
    @Test
    public void testParseModelCheckerLadderSmall() throws Exception {
        String path ="games/modelCheckerLadder/modelCheckerLadder.game";
        PsolGame g = Parser.parsePsolGame(path);
        checkLength(path,g);
    }
    @Test
    public void testParseRecursiveLadderSmall() throws Exception {
        String path ="games/recursiveLadder/recursiveLadder.game";
        PsolGame g = Parser.parsePsolGame(path);
        checkLength(path,g);
    }
    @Test
    public void testParseTowersOfHanoiSmall() throws Exception {
        String path ="games/towersOfHanoi/towersOfHanoi.game";
        PsolGame g = Parser.parsePsolGame(path);
        checkLength(path,g);
    }

    private boolean checkLength(String path,PsolGame game) throws  Exception{
        LineNumberReader lnr = new LineNumberReader(new FileReader(new File(path)));
        int lines = 0;
        String line;
        while((line = lnr.readLine()) != null)    {
            if(line.isEmpty()) continue;
            lines++;
        }
        lines --;
        assertTrue(lines == game.getNodes().size());
        assertTrue(lines == game.getSortedNodes().size());
        return true;
    }
}
