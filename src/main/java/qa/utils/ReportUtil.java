package qa.utils;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportUtil {

    // Store failure messages per thread (parallel-safe)
    private static final ThreadLocal<List<String>> failureMessages = ThreadLocal.withInitial(ArrayList::new);

    public static String details = "";
    public static void report(boolean condition , String status, String stepDetails , String description ) {
        details = " - Step : " + stepDetails + " , " + description ;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        switch (status.toUpperCase()) {
            case "PASS":
                if (condition) {
                    Reporter.log(now.format(formatter) + " ✅ [PASS] " + details + "<br>");
                    System.out.println(now.format(formatter) + " ✅ [PASS] " + details );
                    Assert.assertTrue(condition);

                } else {
                    Reporter.log(now.format(formatter) + " ❌ [FAIL]  " + details + "<br>");
                    System.out.println(now.format(formatter) + " ❌ [FAIL] " + details+ "<br>");
                    //Assert.fail("Expected PASS but condition failed: " + description);
                    failureMessages.get().add("[FAIL] " + details);
                }
                break;

            case "FAIL":
                if (!condition) {
                    Reporter.log(now.format(formatter) + " ✅ [FAIL Confirmed] " + details + "<br>");
                    System.out.println(now.format(formatter) + " ✅ [FAIL Confirmed] " + details);
                    failureMessages.get().add("[FAIL] " + details);
                    Assert.fail("Expected PASS but condition failed: " + details);
                } else {
                    Reporter.log(now.format(formatter) + " ❌ [FAIL - Expected FAIL] but condition passed: " + details +"<br>");
                    System.out.println(now.format(formatter) + " ❌ [FAIL - Expected FAIL] but condition passed: " + details);
                    //Assert.fail("Expected FAIL but condition passed: " + description);
                    failureMessages.get().add("[FAIL] " + details);
                }
                break;

            case "SKIP":
                Reporter.log("⏭️ [SKIP] " + details +"<br>");
                System.out.println("⏭️ [SKIP] " + details);
                throw new SkipException("SKIP test: " + details);

            case "INFO":
                Reporter.log(now.format(formatter) + " ⏭️ [INFO] " + details +"<br>");
                System.out.println(now.format(formatter) + " ⏭️ [INFO] " + details);

//            default:
//                Reporter.log("❓ [UNKNOWN STATUS] " + details +"<br>");
//                System.out.println("❓ [UNKNOWN STATUS] " + details);
//                Assert.fail("Unknown status provided: " + status);
        }
    }

    public static void finalizeReport() {
        List<String> failures = failureMessages.get();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (!failures.isEmpty()) {
            Reporter.log(now.format(formatter) + "❌ Test had failures:" + "<br>");
            for (String msg : failures) {
                Reporter.log(msg + "<br>");
                System.out.println(msg);
                Assert.fail(msg);
            }
            failureMessages.remove();
            org.testng.Assert.fail("One or more steps failed. See log above.");
        }
    }

    private static void log(String message) {
        Reporter.log(message);
        System.out.println(message);
    }
}
