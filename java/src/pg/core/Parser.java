package pg.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Parser {

    public static  PsolBGame parsePsolBGame(String path) throws  Exception{
       PsolBGame game = new PsolBGame();
       parseGame(path,game);
       return game;
    }
    public  static PsolGame parsePsolGame(String path) throws  Exception{
        PsolGame game = new PsolGame();
        parseGame(path, game);
        return game;
    }
    private static void parseGame(String path, Game game) throws ParseFailureException{
        int lineNo = 0;
        BufferedReader file = null;
        try{
          file = new BufferedReader(new FileReader(path));
          String line = file.readLine();
          if ( !line.contains("parity")){
              throw new ParseFailureException("The file does not contain max parity");
          }
          int maxParity = Integer.parseInt(line.split(" ")[1].replace(";",""));
          if ((maxParity % 2) == 1) maxParity++;
          game.setParity(maxParity);

          line = file.readLine().replace(";","");
          while (line != null){
              //System.out.println(line);
              if(!line.isEmpty()){
                Node node = parseNode(line);
                node.setPriority(maxParity   - node.getPriority());
                game.addNode(node);

              }
              line = file.readLine();
              if( line!= null) line = line.replace(";","");
          }
           game.initialize();
           generateSuccessors(game);
           generatePredecessors(game);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ParseFailureException("could not open the file [" + path+"]");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParseFailureException("Failed to read line [" +  lineNo +"]");
        } finally {
          try{
              if (file!= null) file.close();
          }catch (Exception e){
              e.printStackTrace();
          }
        }

    }

    private static void generateSuccessors(Game game) {
        HashMap<Integer,Node> nodes = game.getNodes();
        for(Node node: game.getNodes().values()){
           HashSet<Node> successors = node.getSuccessors();
           for( Integer successor: node.getSuccessorsIds()){
               successors.add(nodes.get(successor));
           }
        }
    }

    private static void generatePredecessors(Game game) {
        for (Node node : game.getNodes().values()){
            for(Node successor : node.getSuccessors() ){
                successor.getPredecessors().add(node);
            }
        }
    }


    private static Node parseNode(String line){
       Node node = new Node();
       String[] parts = line.split(" ");

       node.setId(Integer.parseInt(parts[0]));

       node.setPriority(Integer.parseInt(parts[1]));

       node.setOwner(Integer.parseInt(parts[2]));

       String[] successors = parts[3].split(",");
       HashSet<Integer> nodeSuccessors = node.getSuccessorsIds();
       for( String s : successors){
        nodeSuccessors.add(Integer.parseInt(s));
       }

       if ( parts.length > 4){
          String name = parts[4];
          name =name.replaceAll("[\";]","");
          node.setName(name);
       }
       return  node;
    }



}
