package qa.commonfuctions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadWithRobot {
    public static void uploadFile(String filePath) throws AWTException, InterruptedException {

        // Build path automatically (no need to escape backslashes)
        // Normalize mixed slashes and split
        String[] parts = filePath.replace("\\", "/").split("/");
        Path path = Paths.get(parts[0], java.util.Arrays.copyOfRange(parts, 1, parts.length));
        String NewfilePath = path.toString();

        // Copy file path to clipboard
        StringSelection selection = new StringSelection(NewfilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();
        robot.setAutoDelay(100); // Small delay between actions

        // Wait a moment to ensure dialog is ready
        Thread.sleep(1000);

        // Press CTRL+V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(500); // Wait for text to appear

        // Press ENTER
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
