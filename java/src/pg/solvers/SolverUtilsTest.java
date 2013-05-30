package pg.solvers;

import org.junit.Test;
import pg.core.Game;
import pg.core.Node;
import pg.core.Parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertTrue;

public class SolverUtilsTest {

    @Test
    public void testFatalAtrractorPDF1_Attr() throws Exception {
        String path ="games/papers/fatal_attractors1.game";
        Game g = Parser.parsePsolGame(path);
        HashSet<Integer> attractorIds = new HashSet<Integer>();
        attractorIds.add(6);
        attractorIds.add(4);
        attractorIds.add(8);
        assertTrue(equalSets(testAttr(6, g),attractorIds));
    }

    @Test
    public void testFatalAtrractorPDF1_Monotone() throws Exception {
        String path ="games/papers/fatal_attractors1.game";
        Game g = Parser.parsePsolGame(path);
        HashSet<Integer> attractorIds = new HashSet<Integer>();
        assertTrue(equalSets(testMonotone(6, g),attractorIds));
    }

    @Test
    public void testGenerateAttractor() throws Exception {
        String path ="games/game.game";
        Game g = Parser.parsePsolGame(path);

        HashSet<Integer> attractorIds = new HashSet<Integer>();
        attractorIds.add(1);
        attractorIds.add(3);
        attractorIds.add(5);
        assertTrue(equalSets(testAttr(1, g),attractorIds));
    }

    @Test
    public void testGenerateMonotoneAttractor() throws Exception {
        String path ="games/game.game";
        Game g = Parser.parsePsolGame(path);

        HashSet<Integer> attractorIds = new HashSet<Integer>();
        attractorIds.add(1);
        attractorIds.add(3);
        attractorIds.add(5);
        assertTrue(equalSets(testMonotone(1,g),attractorIds));


    }





    public  static boolean equalSets(Set<Node> attr , List<Integer> ids){
        if (attr.size()!= ids.size()) return false;
        for(Node node : attr){
            if (!ids.contains(node.getId())) return false;
        }
        return  true;
    }

    public  static boolean equalSets(List<Node> attr , Set<Integer> ids){
        if (attr.size()!= ids.size()) return false;
        for(Node node : attr){
            if (!ids.contains(node.getId()))
                return false;
        }
        return  true;
    }
    public  static boolean equalSets(Set<Node> attr , Set<Integer> ids){
        if (attr.size()!= ids.size()) return false;
        for(Node node : attr){
           if (!ids.contains(node.getId())) {
               System.out.println("faulty node  " + node);
               return false;
           }
        }
        return  true;
    }

    public  static boolean subSet(Set<Node> attr , Set<Integer> ids){
        if (attr.size()== ids.size()) return equalSets(attr,ids);
        for(Node node : attr){
            if (!ids.contains(node.getId())) {
                System.out.println("faulty node  " + node);
                return false;
            }
        }
        return  true;
    }
    private static HashSet<Node> testAttr( int node , Game game)throws  Exception{
        Node n = game.getNodes().get(node);
        HashSet<Node> hashSet = new HashSet<Node>();
        hashSet.add(n);
        return  SolverUtils.generateAttractor(hashSet,n.getPriority());
    }

    private  static HashSet<Node> testMonotone( int node , Game game)throws Exception{
        Node n = game.getNodes().get(node);
        HashSet<Node> hashSet = new HashSet<Node>();
        hashSet.add(n);
        return SolverUtils.generateMonotoneAttractor(hashSet,n.getPriority());
    }

}
