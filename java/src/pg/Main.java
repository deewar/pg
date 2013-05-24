package pg;

import pg.core.Game;
import pg.core.Parser;

public class Main   {
    public static  void main (String args[])throws  Exception{
        Game g = Parser.parsePsolGame("games/modelCheckerLadder/modelCheckerLadder.game");
        System.out.println(g);

    }

}
