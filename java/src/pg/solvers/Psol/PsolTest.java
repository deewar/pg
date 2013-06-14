package pg.solvers.Psol;

import org.junit.Test;
import pg.core.Parser;
import pg.core.PsolGame;
import pg.core.ResultParser;
import pg.core.Results;
import pg.solvers.solverUtils.SolverUtilsTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static junit.framework.Assert.assertTrue;


public class PsolTest {

    @Test
    public void testElevatorSmall() throws Exception {
        String path ="games/elevator/elevator.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.subSet(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.subSet(g.getWinningRegion0(), results.winningRegion0));


    }


    @Test
    public void testJurdzinskiSmall10() throws Exception {
        String path ="games/jurdzinski/jurdzinski10.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), results.winningRegion0));
        assertTrue(g.getNodes().isEmpty());

    }

    @Test
    public void testJurdzinskiSmall() throws Exception {
        String path ="games/jurdzinski/jurdzinski.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), results.winningRegion0));
        assertTrue(g.getNodes().isEmpty());

    }

    @Test
    public void testLadderSmall() throws Exception {
        String path ="games/ladder/ladder.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), results.winningRegion0));
        assertTrue(g.getNodes().isEmpty());

    }

    @Test
    public void testModelCheckerLadderSmall() throws Exception {
        String path ="games/modelCheckerLadder/modelCheckerLadder.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), results.winningRegion0));
        assertTrue(g.getNodes().isEmpty());

    }

    @Test
    public void testRecursiveLadderSmall() throws Exception {
        String path ="games/recursiveLadder/recursiveLadder.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
       /* Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), results.winningRegion0));*/
        assertTrue(g.getNodes().isEmpty());

    }

    @Test
    public void testTowersOfHanoiSmall() throws Exception {
        String path ="games/towersOfHanoi/towersOfHanoi.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), results.winningRegion0));
        assertTrue(g.getNodes().isEmpty());
    }

    @Test
    public void testClique300() throws Exception {
        String path ="games/clique/clique3000.game";
        PsolGame g = Parser.parsePsolGame(path);

        Long startTime = System.nanoTime();
        new Psol().solve(g);
        Long endTime = System.nanoTime();
        Long duration = endTime -startTime;
        System.out.println(duration);
        Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), results.winningRegion0));
        assertTrue(g.getNodes().isEmpty());
    }


    @Test
    public void testCliqueSmall() throws Exception {
        String path ="games/clique/clique.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        Integer[] wi0 = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50,
                52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100,
                102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122, 124, 126, 128, 130, 132, 134, 136, 138, 140,
                142, 144, 146, 148, 150, 152, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180,
                182, 184, 186, 188, 190, 192, 194, 196, 198};
        Integer[] wi1 =  {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29,
                31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81,
                83, 85, 87, 89, 91, 93, 95, 97, 99, 101, 103, 105, 107, 109, 111, 113, 115, 117, 119, 121, 123, 125,
                127, 129, 131, 133, 135, 137, 139, 141, 143, 145, 147, 149, 151, 153, 155, 157, 159, 161, 163, 165, 167,
                169, 171, 173, 175, 177, 179, 181, 183, 185, 187, 189, 191, 193, 195, 197, 199};
        List<Integer> w0 = Arrays.asList(wi0);
        List<Integer> w1 = Arrays.asList(wi1) ;
        Results results= ResultParser.parseResults(path);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(), results.winningRegion1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), results.winningRegion0));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), w0));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(),w1));
    }


    @Test
    public void testSolveBasic() throws Exception {
        String path ="games/game.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        HashSet<Integer> w0 = new HashSet<Integer>();
        HashSet<Integer> w1 = new HashSet<Integer>();
        w0.add(2);
        w0.add(4);
        w0.add(6);
        w1.add(1);
        w1.add(3);
        w1.add(5);
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(), w0));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(),w1));
    }


    @Test
    public void testSolvePapersFatalAttractorsPresentation1() throws Exception {
        String path ="games/papers/fatal_attractors_presentation1.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        HashSet<Integer> w0 = new HashSet<Integer>();
        HashSet<Integer> w1 = new HashSet<Integer>();
        w1.add(0);
        w1.add(2);
        w1.add(9);
        w1.add(14);
        w1.add(6);
        w1.add(15);
        w1.add(4);
        w1.add(8);
        w1.add(12);
        w1.add(11);

        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(),w1));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(),w0));
    }

    @Test
    public void testSolvePapersFatalAttractors2() throws Exception {
        String path ="games/papers/fatal_attractors2.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
        HashSet<Integer> w0 = new HashSet<Integer>();
        HashSet<Integer> w1 = new HashSet<Integer>();

        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion0(),w0));
        assertTrue(SolverUtilsTest.equalSets(g.getWinningRegion1(),w1));
    }

    @Test
    public void testSolvePapersFatalAttractors1() throws Exception {
        String path ="games/papers/fatal_attractors1.game";
        PsolGame g = Parser.parsePsolGame(path);
        new Psol().solve(g);
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
