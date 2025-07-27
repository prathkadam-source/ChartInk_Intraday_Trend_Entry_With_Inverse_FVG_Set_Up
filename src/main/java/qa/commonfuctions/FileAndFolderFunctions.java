package qa.commonfuctions;

import qa.utils.ReportUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FileAndFolderFunctions {
    public static File folder_Details;

    public static File text_file_Details;

    public static String Create_A_Folder(String folder_Location, String folder_Name) {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- Create Folder function ", "");
        String folderPath ="";

        try {
            // Set folder path (you can customize the base directory)
            folderPath = folder_Location + folder_Name;
            // Create File object
            folder_Details = new File(folderPath);

            // Check and create folder
            if (!folder_Details.exists()) {
                boolean created = folder_Details.mkdirs();  // mkdirs() creates any missing parent directories as well
                if (created) {
                    System.out.println("Folder created: " + folderPath);
                    ReportUtil.report(true, "PASS", "Create a Folder, ", " " + folderPath + " Folder created successfully");

                } else {
                    System.out.println("Failed to create folder.");
                    ReportUtil.report(false, "FAIL", "Create a Folder , ", "Error: Failed to create folder '" + folderPath + "'");
                    folderPath = "";
                }
            } else {
                ReportUtil.report(true, "PASS", "Create a Folder, ", " '" + folderPath + "' Folder Already exist");
                System.out.println("Folder already exists: '" + folderPath + "'");
            }

        } catch (Exception e) {
            System.out.println("Error for Create Folder : " + e.getMessage());
            ReportUtil.report(false, "FAIL", "Create a Folder, ", "Error: " + e.getMessage());

        }

        ReportUtil.report(true, "INFO", "-- Function -- Ending -- Create a Folder", "");
        return folderPath;
    }

    public static String Create_A_TextFile(String file_Location, String file_Name) {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- Create TextFile function ", "");
        String filePath ="";

        try {
            // Set file path
            filePath = file_Location + file_Name;
            // Create File object
            text_file_Details = new File(filePath);

            // Check and create folder
            if (!text_file_Details.exists()) {
                boolean created = text_file_Details.createNewFile();
                if (created) {
                    System.out.println("File created: " + filePath);
                    ReportUtil.report(true, "PASS", "Create a Text File, ", " " + filePath + " File created successfully");

                } else {
                    System.out.println("Failed to create Text File.");
                    ReportUtil.report(false, "FAIL", "Create a Text File , ", "Error: Failed to create Text File '" + filePath + "'");
                    filePath = "";
                }
            } else {
                ReportUtil.report(true, "PASS", "Create a Text File, ", " '" + filePath + "' Text File Already exist");
                System.out.println("Text File already exists: '" + filePath + "'");
            }

        } catch (Exception e) {
            System.out.println("Error for Create Text File : " + e.getMessage());
            ReportUtil.report(false, "FAIL", "Create a Text File, ", "Error: " + e.getMessage());

        }

        ReportUtil.report(true, "INFO", "-- Function -- Ending -- Create a Text File", "");
        return filePath;
    }

    public static void Overwrite_To_Text_File(String textFilePath, String newContent) throws IOException {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- Overwrite_To_Text_File function ", "");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFilePath, false))) {
            writer.write(newContent);
            writer.close();
            System.out.println("File modified (overwritten).");
            ReportUtil.report(true, "INFO", "-- File modified (overwritten).", ", " +textFilePath + " overwritten");

        }    catch (IOException e) {

        System.out.println("Error for Overwrite_To_Text_File : " + e.getMessage());
        ReportUtil.report(false, "FAIL", "Overwrite_To_Text_File: "+ textFilePath, ", Error: " + e.getMessage());

    }
        ReportUtil.report(true, "INFO", "-- Function -- Ending -- Overwrite_To_Text_File function ", "");
    }

    // Append content
    public static void append_To_Text_File(String textFilePath, String contentToAppend) throws IOException {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- append_To_Text_File function ", "");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFilePath, true))) {
            writer.write(contentToAppend);
            writer.newLine(); // optional line break
            writer.close();
            System.out.println("Content appended to file.");
            ReportUtil.report(true, "INFO", "-- Content appended to file.", ", " + textFilePath );

        }catch (IOException e) {

            System.out.println("Error for Overwrite_To_Text_File : " + e.getMessage());
            ReportUtil.report(false, "FAIL", "append_To_Text_File: " + textFilePath, ", Error: " + e.getMessage());

        }
        ReportUtil.report(true, "INFO", "-- Function -- Ending -- append_To_Text_File function ", "");
    }

    // Clear file content
    public static void clear_Text_File(String textFilePath) throws IOException {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- clear_Text_File function ", "");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFilePath, false))) {
            writer.write(""); // Writing empty string clears content
            writer.close();
            System.out.println("File cleared.");
            ReportUtil.report(true, "PASS", "-- File cleared.", ", " + textFilePath );
        }catch (IOException e) {

            System.out.println("Error for clear_Text_File : " + e.getMessage());
            ReportUtil.report(false, "FAIL", "clear_Text_File: " + textFilePath, ", Error: " + e.getMessage());

        }
        ReportUtil.report(true, "INFO", "-- Function -- Ending -- clear_Text_File function ", "");

    }

    // Read file for verification
    public static String read_Text_File(String textFilePath, int number_Of_Lines_To_Be_Read) throws IOException {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- read_Text_File function ", "");
        String line="";
        String required_Text = "";
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(textFilePath))) {

            System.out.println("File content:");
            while ((line = reader.readLine()) != null && lineCount <= number_Of_Lines_To_Be_Read) {
                required_Text = required_Text + line +"\n";
                System.out.println("Line " + (lineCount + 1) + ": " + line);
                lineCount++;
            }

        }catch (IOException e) {

            System.out.println("Error for clear_Text_File : " + e.getMessage());
            ReportUtil.report(false, "FAIL", "read_Text_File: " + textFilePath, ", Error: " + e.getMessage());

        }
        ReportUtil.report(true, "INFO", "-- Function -- Ending -- read_Text_File function ", "");
        return required_Text;
    }

    public static void paste_Copied_Data_To_Text_File(String textFilePath) throws IOException {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- paste_Copied_Data_To_Text_File function ", "");

        try {
            // Get clipboard content
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String clipboardData = (String) clipboard.getData(DataFlavor.stringFlavor);

            // Write to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(textFilePath, false));
            writer.write(clipboardData + "\n");
            writer.close();

            System.out.println("Clipboard data written to file.");
            ReportUtil.report(true, "PASS", "-- Clipboard data written to file.", ", " + textFilePath );

        } catch (UnsupportedFlavorException | IOException e) {
            System.out.println("Failed to get clipboard data.");
            e.printStackTrace();
            ReportUtil.report(false, "FAIL", "paste_Copied_Data_To_Text_File : " + textFilePath, ", Error: " + e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Function -- Ending -- paste_Copied_Data_To_Text_File function ", "");

    }

    public static void save_Filtered_Alert_Stocks_to_TextFile(String textFilePath, String stocks) throws IOException {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- save_Filtered_Alert_Stocks_to_TextFile function ", "");

        try {
            // Write to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(textFilePath, false));
            writer.write(stocks);
            writer.close();

            System.out.println("Saved Filtered_Alert_Stocks_to_TextFile.");
            ReportUtil.report(true, "PASS", "-- Saved Filtered_Alert_Stocks_to_TextFile.", ", " + textFilePath );

        } catch (IOException e) {
            System.out.println("save_Filtered_Alert_Stocks_to_TextFile");
            e.printStackTrace();
            ReportUtil.report(false, "FAIL", "save_Filtered_Alert_Stocks_to_TextFile : " + textFilePath, ", Error: " + e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Function -- Ending -- save_Filtered_Alert_Stocks_to_TextFile function ", "");

    }

    // Update Stock Alert to  output textfile for end of the day validation
    public static void update_Output_Text_File_for_Alert_Results(String textFilePath, String action, String watchlist_name, String watchlist_Url, String stock_Names) throws IOException {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- update_Output_Text_File_for_Alert_Results function ", "");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {

            String text_To_Be_Updated =System.lineSeparator()+System.lineSeparator() +"Timestamp: " + now.format(formatter) + System.lineSeparator();
            text_To_Be_Updated = text_To_Be_Updated + "Action : " + action +  System.lineSeparator();
            text_To_Be_Updated = text_To_Be_Updated + "Watchlist name : " + watchlist_name +  System.lineSeparator();
            text_To_Be_Updated = text_To_Be_Updated + "Watchlist url : " + watchlist_Url +  System.lineSeparator();
            text_To_Be_Updated = text_To_Be_Updated + "Stocks Name  : " + stock_Names;

            FileAndFolderFunctions.append_To_Text_File(textFilePath, text_To_Be_Updated);

            System.out.println("update_Output_Text_File_for_Alert_Results.");
            ReportUtil.report(true, "PASS", "--  update_Output_Text_File_for_Alert_Results.", ", " + textFilePath );

        } catch (IOException e) {
            System.out.println("update_Output_Text_File_for_Alert_Results");
            e.printStackTrace();
            ReportUtil.report(false, "FAIL", "update_Output_Text_File_for_Alert_Results : " + textFilePath, ", Error: " + e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Function -- Ending -- update_Output_Text_File_for_Alert_Results function ", "");

    }


}

