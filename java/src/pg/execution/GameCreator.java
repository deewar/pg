package pg.execution;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import pg.core.Parser;
import pg.core.PsolGame;
import pg.solvers.Psol.Psol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;

public class GameCreator {

    private static final long timeout = 1200000;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#");

    //('cliquegame', 'laddergame' , jurdzinskigame 'modelcheckerladder' , 'towersofhanoi')
    //jurdszinski
    public static double calibratePsol(String gameName) throws Exception {
        double prevRound = 2;
        double round = 2;
        long time = 0;
        while (time < timeout) {
            ByteArrayOutputStream byteArrayOutputStream =    execute(gameName, "" + decimalFormat.format(round));
            StringReader gameFile = new StringReader(byteArrayOutputStream.toString());
            System.out.println("game genrated");
            PsolGame game = Parser.parsePsolGame(gameFile);
            System.out.println("game parsed");

            long startTime = System.currentTimeMillis();
            new Psol().solve(game);
            long endTime = System.currentTimeMillis();
            time = endTime - startTime;
            System.out.println(String.format("game solved in [%d]ms", time));

            prevRound = round;
            round = round * 2;
        }

        return prevRound;

    }


    public static ByteArrayOutputStream execute(String command, String... args) throws IOException {
        //
        // ExecuteWatchdog watchdog = new ExecuteWatchdog(timeout);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommandLine commandline = CommandLine.parse(command);
        for (String i : args) {
            //System.out.println("argument[" + i + "]");
            commandline.addArgument(i);
        }
        DefaultExecutor exec = new DefaultExecutor();
        //exec.setWatchdog(watchdog);
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        exec.setStreamHandler(streamHandler);
        exec.execute(commandline);

        return outputStream;
    }

}
