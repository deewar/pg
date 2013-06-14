package pg.execution;

import org.apache.commons.io.FileUtils;
import pg.core.*;
import pg.execution.psolBExecutors.MarkingPsolBExecutor;
import pg.execution.psolBExecutors.PsolBParallelExecutor;
import pg.execution.psolBExecutors.PsolBSerialExecutor;
import pg.execution.psolExecutors.MarkingPsolParallelExecuter;
import pg.execution.psolExecutors.PsolParallelExecuter;
import pg.execution.psolExecutors.PsolSerialExecuter;
import pg.solvers.solverUtils.SolverUtilsTest;

import java.io.File;
import java.io.StringReader;

public class Main {
    public static void main(String args[]) throws Exception {

        if (args.length < 1) {
            printUsage();
            return;
        }


        switch (args[0].toLowerCase()) {
            case "clique": {
                handleSingleParameters("cliquegame", 11);
                break;
            }
            case "ladder": {
                handleSingleParameters("laddergame", 19);
                break;
            }
            case "recursive": {
                handleSingleParameters("recursiveladder", 5);
                break;
            }
            case "model": {
                handleSingleParameters("modelcheckerladder", 13);
                break;
            }
            case "tower": {
                handleSingleParameters("towersofhanoi", 3);
                break;
            }
            case "jurdzinski": {
                handleJurdzinski("jurdzinskigame");
                break;
            }
            case "random": {
                handleRandom("jurdzinskigame");
                break;
            }
            default: printUsage();

        }


    }

    private static void handleRandom(String game) {

    }

    private static void handleJurdzinski(String gameName) throws Exception {
        LaunchStrartup();

        System.out.println("Runnin n 10");

        int size = 1;
        for (int i = 1; i <= 5/*11*/ ; i++) {
            size = size * 2;
            System.out.println("performing benchmark for size " + size);
            String game = GameCreator.execute(gameName, "" + size, "" + 10).toString();
            String filename = gameName + System.currentTimeMillis();
            File file = new File(filename);
            FileUtils.writeStringToFile(file, game);
            String result = GameCreator.execute("pgsolver", "-global", "recursive", "--printsolonly", filename).toString();
            //System.out.println(result);
            file.delete();
            Results results = ResultParser.parseResults(new StringReader(result));
            runAllSolvers(game, results);
            System.gc();
        }


        System.out.println("Runnin 10 n");
        size = 1;
        for (int i = 1; i <= 5/*11*/ ; i++) {
            size = size * 2;
            System.out.println("performing benchmark for size " + size);
            String game = GameCreator.execute(gameName, "" + 10, "" + size).toString();
            String filename = gameName + System.currentTimeMillis();
            File file = new File(filename);
            FileUtils.writeStringToFile(file, game);
            String result = GameCreator.execute("pgsolver", "-global", "recursive", "--printsolonly", filename).toString();
            //System.out.println(result);
            file.delete();
            Results results = ResultParser.parseResults(new StringReader(result));
            runAllSolvers(game, results);
            System.gc();
        }

        System.out.println("Runnin n n");
        size = 1;
        for (int i = 1; i <= 2/*7 */; i++) {
            size = size * 2;
            System.out.println("performing benchmark for size " + size);
            String game = GameCreator.execute(gameName, "" + size, "" + size).toString();
            String filename = gameName + System.currentTimeMillis();
            File file = new File(filename);
            FileUtils.writeStringToFile(file, game);
            String result = GameCreator.execute("pgsolver", "-global", "recursive", "--printsolonly", filename).toString();
            //System.out.println(result);
            file.delete();
            Results results = ResultParser.parseResults(new StringReader(result));
            runAllSolvers(game, results);
            System.gc();
        }

    }

    private static void printUsage() {
        System.out.println("usage : java -jar game.jar <NAME OF GAME> where possible games are clique,ladder,recursive" +
                ",model,tower,jurdzinski,random.");
    }

    private static void handleSingleParameters(String gameName, int maxRounds) throws Exception {
        LaunchStrartup();


        int size = 1;
        for (int i = 1; i <= maxRounds; i++) {
            size = size * 2;
            System.out.println("performing benchmark for size " + size );
            System.out.println("---------------------------------------------\n");
            String game = GameCreator.execute(gameName, "" + size).toString();
            String filename = gameName + System.currentTimeMillis();
            File file = new File(filename);
            FileUtils.writeStringToFile(file, game);
            String result = GameCreator.execute("pgsolver", "-global", "recursive", "--printsolonly", filename).toString();
            //System.out.println(result);
            file.delete();
            Results results = ResultParser.parseResults(new StringReader(result));
            runAllSolvers(game, results);
            System.gc();
        }
    }

    private static void LaunchStrartup() throws Exception {
        //warmup
        System.out.println("Warmup Started");
        String warmupGame = GameCreator.execute("cliquegame", "" + 128).toString();
        warmup(new PsolSerialExecuter(warmupGame));
        warmup(new PsolBSerialExecutor(warmupGame));
        warmup(new PsolParallelExecuter(warmupGame,8));
        warmup(new PsolBParallelExecutor(warmupGame,8));
        warmup(new MarkingPsolBExecutor(warmupGame,8));
        warmup(new MarkingPsolParallelExecuter(warmupGame,8));
        System.out.println("warmup complete");
    }

    private static void runAllSolvers(String game, Results results) throws Exception {
        System.out.println("Running Psol Bench Mark");
        System.out.println(benchmark(new PsolSerialExecuter(game), results));
        int nthreads = 1;
        for (int i = 0; i <= 5; i++) {
            nthreads = (int) Math.pow(2, i);
            System.out.println("Running PsolParallel " + nthreads + " Bench Mark");
            System.out.println(benchmark(new PsolParallelExecuter(game, nthreads), results));
        }
        for (int i = 0; i <= 5; i++) {
            nthreads = (int) Math.pow(2, i);
            System.out.println("Running MarkingPsolParallel " + nthreads + " Bench Mark");
            System.out.println(benchmark(new MarkingPsolParallelExecuter(game, nthreads), results));
        }

        System.out.println("Running PsolB Bench Mark");
        System.out.println(benchmark(new PsolBSerialExecutor(game), results));

        for (int i = 0; i <= 5; i++) {
            nthreads = (int) Math.pow(2, i);
            System.out.println("Running PsolBParallel " + nthreads + " Bench Mark");
            System.out.println(benchmark(new PsolBParallelExecutor(game, nthreads), results));
        }
        for (int i = 0; i <= 5; i++) {
            nthreads = (int) Math.pow(2, i);
            System.out.println("Running MarkingPsolBParallel " + nthreads + " Bench Mark");
            System.out.println(benchmark(new MarkingPsolBExecutor(game, nthreads), results));
        }


    }


    private static boolean validateGame(Results results, Game game) throws Exception {

        return SolverUtilsTest.equalSets(game.getWinningRegion0(), results.winningRegion0) &&
                SolverUtilsTest.equalSets(game.getWinningRegion1(), results.winningRegion1);
    }


    private static void warmup(IExecutable executable) throws  Exception{
        ExecutionResult result = new ExecutionResult();

        for (int i = 0; i < 20; i++) {
            timedRun(executable,result);
            executable.resetGame();
            System.gc();
        }
    }
    private static ExecutionResult benchmark(IExecutable executable, Results results) throws Exception {
        ExecutionResult result = new ExecutionResult();

        int roundsSkipped = 0;
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            if (!timedRun(executable, result)){
               roundsSkipped++;
               continue;
            }
            long endTime = System.nanoTime();
            result.runTime += endTime - startTime;
            result.validSolution = result.validSolution && validateGame(results, result.game);
            System.gc();
        }
        result.runTime = result.runTime / (5-roundsSkipped);
        return result;
    }

    private static boolean timedRun(final IExecutable executable, final ExecutionResult result) throws  Exception{
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    executable.solve(result);
                } catch (Exception e) {
                    System.out.println("dang" + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        t.start();

        t.join(1200000);
        if (t.isAlive()){
            System.out.println("timeout");
            System.out.flush();
            result.timeOut = true;
            t.interrupt();
            return false;
        }
        return  true;
    }

}
