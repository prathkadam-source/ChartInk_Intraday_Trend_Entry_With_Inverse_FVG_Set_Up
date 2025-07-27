package qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import qa.commonfuctions.TelegramBotSender;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] Test Case Passed: " + result.getName());
        Reporter.log("[PASS] Test Case Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] Test Case Failed: " + result.getName());
        Reporter.log("[FAIL] Test Case Failed: " + result.getName());

        try {
            TelegramBotSender.sendTelegramMessage("[Test Case Failed] ChartInk Execution Stopped");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED] Test Case Skipped: " + result.getName());
        Reporter.log("[SKIPPED] Test Case Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
    }
}
