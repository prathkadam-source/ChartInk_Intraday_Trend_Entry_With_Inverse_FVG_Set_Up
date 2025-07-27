package qa.commonfuctions;

import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;

public class ClipboardToFile {

    public static void pasteDataToFile() {
        try {
            // Get clipboard content
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String clipboardData = (String) clipboard.getData(DataFlavor.stringFlavor);

            // Write to file
            FileWriter writer = new FileWriter("C:/Users/prath/IdeaProjects/ChartInk/src/main/resources/data/pasted_output.txt", true);
            writer.write(clipboardData + "\n");
            writer.close();

            System.out.println("Clipboard data written to file.");

        } catch (UnsupportedFlavorException | IOException e) {
            System.out.println("Failed to get clipboard data.");
            e.printStackTrace();
        }
    }
}
