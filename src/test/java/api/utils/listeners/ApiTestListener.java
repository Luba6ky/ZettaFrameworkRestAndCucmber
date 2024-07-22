package api.utils.listeners;

import api.utils.logging.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class ApiTestListener implements TestWatcher, BeforeEachCallback {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    static {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReports.html");
        extent.attachReporter(spark);
        Log.startLog("Test Suite started!");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ExtentTest extentTest = extent.createTest(context.getDisplayName());
        test.set(extentTest);
        Log.startLog(context.getDisplayName() + " started!");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        test.get().pass("Test passed");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        test.get().fail(cause);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        test.get().skip(cause);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        test.get().skip(reason.orElse("No reason provided"));
    }
}
