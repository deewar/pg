package pg.execution;

import org.junit.internal.runners.*;
import org.junit.runner.notification.*;
import org.junit.runner.*;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;

public class RunUntilFailure extends Runner {

    private static final int MAX_RUN_COUNT = Integer.MAX_VALUE;

    private JUnit4 runner;

    @SuppressWarnings("unchecked")
    public RunUntilFailure(final Class testClass) throws Exception {
        runner = new JUnit4(testClass);
    }

    @Override
    public Description getDescription() {
        final Description description = Description.createSuiteDescription("Run many times until failure");
        description.addChild(runner.getDescription());
        return description;
    }

    @Override
    public void run(final RunNotifier notifier) {
        class L extends RunListener {
            boolean shouldContinue = true;
            int runCount = 0;

            @Override
            public void testFailure(@SuppressWarnings("unused") final Failure failure) throws Exception {
                shouldContinue = false;
            }

            @Override
            public void testFinished(@SuppressWarnings("unused") Description description) throws Exception {
                runCount++;

                shouldContinue = (shouldContinue && runCount < MAX_RUN_COUNT);
            }
        }

        final L listener = new L();
        notifier.addListener(listener);

        while (listener.shouldContinue) {
            runner.run(notifier);
        }
    }

}