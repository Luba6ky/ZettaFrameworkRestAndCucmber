package api.utils.listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class RetryListener implements TestExecutionExceptionHandler, TestWatcher {

    private int count = 0;
    private static final int maxTry = 3;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (count < maxTry) {
            count++;
            System.out.println("Retrying test " + context.getDisplayName() + " for the " + count + " time.");
            throw throwable;
        } else {
            throw throwable;
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        count = 0;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (count >= maxTry) {
            count = 0;
        }
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        count = 0;
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        count = 0;
    }
}
