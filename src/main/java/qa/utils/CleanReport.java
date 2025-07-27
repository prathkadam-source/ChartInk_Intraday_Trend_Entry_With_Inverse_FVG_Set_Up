package qa.utils;

import org.testng.annotations.BeforeSuite;

import java.io.File;

public class CleanReport {

    @BeforeSuite
    public void deleteOldReports() {
        File reportDir = new File("test-output");
        deleteDirectory(reportDir);
    }

    private void deleteDirectory(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
            dir.delete();
        }
    }
}