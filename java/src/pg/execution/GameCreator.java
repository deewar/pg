package pg.execution;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
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


    public static ByteArrayOutputStream execute(String command, String... args) throws IOException {
        //
        ExecuteWatchdog watchdog = new ExecuteWatchdog(timeout);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommandLine commandline = CommandLine.parse(command);
        for (String i : args) {
            //System.out.println("argument[" + i + "]");
            commandline.addArgument(i);
        }
        DefaultExecutor exec = new DefaultExecutor();
        exec.setWatchdog(watchdog);
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        exec.setStreamHandler(streamHandler);
        exec.execute(commandline);

        return outputStream;
    }

}
