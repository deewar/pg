package pg.solvers;

import org.junit.Test;
import pg.core.Game;
import pg.core.Parser;

import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: deewar
 * Date: 23/05/13
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class PSolTest {
    @Test
    public void testSolveBasic() throws Exception {
        String path ="games/game.game";
        Game g = Parser.parseGame(path);
        new PSol().solve(g);
        HashSet<Integer> w0 = new HashSet<Integer>();
        HashSet<Integer> w1 = new HashSet<Integer>();
        w0.add(2);
        w0.add(4);
        w0.add(6);
        w1.add(1);
        w1.add(3);
        w1.add(5);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(),w0));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(),w1));
    }

    @Test
    public void testSolvePapersFatalAttractors1() throws Exception {
        String path ="games/papers/fatal_attractors1.game";
        Game g = Parser.parseGame(path);
        new PSol().solve(g);
        HashSet<Integer> w0 = new HashSet<Integer>();
        HashSet<Integer> w1 = new HashSet<Integer>();
        w0.add(0);
        w0.add(1);
        w0.add(2);
        w0.add(4);
        w0.add(6);
        w0.add(8);
        w0.add(9);
        w0.add(10);
        w0.add(11);

        w1.add(3);
        w1.add(5);
        w1.add(7);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(),w0));
        assertTrue(SolverUtilsTest.equalSets(g.getSortedNodes(),w1));
    }
}
