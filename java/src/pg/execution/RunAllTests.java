package pg.execution;


import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pg.solvers.Psol.MarkingPsolParallelTest;
import pg.solvers.Psol.PsolParallelTest;
import pg.solvers.Psol.PsolTest;
import pg.solvers.PsolB.PsolBParallelTest;
import pg.solvers.PsolB.PsolBTest;

@RunWith(RunUntilFailure.class)
@Suite.SuiteClasses({MarkingPsolParallelTest.class})

/*({MarkingPsolParallelTest.class, PsolParallelTest.class , PsolTest.class , PsolBParallelTest.class ,
        PsolBTest.class})*/
public class RunAllTests {

}


