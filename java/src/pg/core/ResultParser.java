package pg.core;


import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ResultParser {

    public static Results parseResults(String path) throws ParseFailureException {
        path = path + ".result";

        try {
            return parseResults(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ParseFailureException("could not open the file [" + path + "]");
        }
    }

    public static Results parseResults(Reader reader) throws ParseFailureException {
        Results results = new Results();
        int lineNo = 1;
        BufferedReader file = new BufferedReader(reader);
        try {
            String line = file.readLine();
            if (!line.contains("paritysol")) {
                throw new ParseFailureException("The file does not contain max parity");
            }
            int maxParity = Integer.parseInt(line.split(" ")[1].replace(";", ""));
            //if ((maxParity % 2) == 1) maxParity++;

            lineNo++;
            line = file.readLine().replace(";", "");
            while (line != null) {
                //System.out.println(line);
                if (!line.isEmpty()) {
                    String[] parts = line.split(" ");
                    int id = Integer.parseInt(parts[0]);
                    int winner = Integer.parseInt((parts[1]));
                    if (winner == 0) {
                        results.winningRegion0.add(id);
                    } else if (winner == 1) {
                        results.winningRegion1.add(id);
                    } else {
                        throw new ParseFailureException("Why did this happen");
                    }
                }
                lineNo++;
                line = file.readLine();
                if (line != null) line = line.replace(";", "");
            }

            return results;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParseFailureException("Failed to read line [" + lineNo + "]");
        } finally {
            try {
                if (file != null) file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
