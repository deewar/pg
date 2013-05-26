package pg.execution;

import pg.core.*;
import pg.solvers.Psol;
import pg.solvers.PsolB;
import pg.solvers.SolverUtilsTest;

public class Main {
    public static void main (String args[]) throws  Exception{

        System.out.println("generating game");
        System.out.println(GameCreator.execute("jurdzinski" , "3", "5"));
        System.out.println("generated  game");

        if (args.length != 2){
            System.out.println("usage : <solver> <gameFilePath> where solver = \"psol\" \"psolb\" \n The solver expects" +
                    "a result file in the same location as the game file");
        }

        long ret =  -1;
        if ("psol".equals(args[0])){
            ret = psol(args[1]);
        }else if ("psolb".equals(args[0])){
            ret  = psolB(args[1]);
        }
        System.out.println("Exec time " +ret);

    }

    private static long psolB(String arg) {
        try{
            PsolBGame game = Parser.parsePsolBGame(arg);
            PsolB psolB = new PsolB();
            Long startTime = System.currentTimeMillis();
            psolB.solve(game);
            Long endTime = System.currentTimeMillis();
            long solveTime = endTime-startTime;
            if (!validateGame(arg,game)){
                throw  new RuntimeException("results not valid");
            }
            return  solveTime;
        }   catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    private static long psol(String arg) {
        try{
            PsolGame game = Parser.parsePsolGame(arg);
            Psol psol = new Psol();
            Long startTime = System.currentTimeMillis();
            psol.solve(game);
            Long endTime = System.currentTimeMillis();
            long solveTime = endTime-startTime;
            if (!validateGame(arg,game)){
                throw  new RuntimeException("results not valid");
            }
            return  solveTime;
        }   catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    private static boolean validateGame(String arg, Game game)  throws Exception{
       Results results = ResultParser.parseResults(arg);
       return SolverUtilsTest.equalSets(game.getWinningRegion0(),results.winningRegion0) &&
              SolverUtilsTest.equalSets(game.getWinningRegion1(),results.winningRegion1);
    }

}
