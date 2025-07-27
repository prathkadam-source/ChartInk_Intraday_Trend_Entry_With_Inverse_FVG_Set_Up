package qa.utils;

import org.testng.annotations.AfterSuite;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ReportOpener {

    //@AfterSuite
    public void openTestNGReport() {
        try {
            String propFilePath = System.getProperty("user.dir");
            File htmlFile = new File(propFilePath+ "/test-output/index.html");

            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println("📂 TestNG Report opened in browser.");
            } else {
                System.out.println("❌ Report file not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
