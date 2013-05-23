package pg;

import com.sun.javafx.scene.paint.GradientUtils;
import pg.core.Game;
import pg.core.Parser;

public class Main   {
    public static  void main (String args[])throws  Exception{
        Game g = Parser.parseGame("games/modelCheckerLadder/modelCheckerLadder.game");
        System.out.println(g);

    }

}
