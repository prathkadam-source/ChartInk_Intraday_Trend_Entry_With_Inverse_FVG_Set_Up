package qa.testCases;

import qa.base.BaseTest;
import qa.commonfuctions.Constants;
import qa.commonfuctions.DateTimeFunctions;
import qa.commonfuctions.FileAndFolderFunctions;
import qa.commonfuctions.NewTabsSetUp;
import qa.pages.AlertPage;
import qa.pages.LoginPage;
import qa.pages.WatchlistPage;
import qa.utils.ReportUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static qa.utils.ReportUtil.finalizeReport;

public class Strategy_2_Test_Cases extends BaseTest {

    LoginPage loginPage = new LoginPage();
    NewTabsSetUp newTabsSetUp = new NewTabsSetUp();

    AlertPage alertPage = new AlertPage();

    WatchlistPage watchlistPage = new WatchlistPage();

    public void Prerequisite_To_Login_And_Set_Sub_Tabs_Urls() throws InterruptedException {

        ReportUtil.report( true, "INFO", "-- Function -- Starting -- Prerequisite_To_Login_And_Set_Sub_Tabs_Urls",  "");

        try {
            loginPage.loginToApplication();
            newTabsSetUp.setUpTabsForParallelExecution();

            // <editor-fold desc="Folder and Text file creation ">
            //To Create a output folder
            Constants.FOLDER_MAIN_OUTPUT = FileAndFolderFunctions.Create_A_Folder(Constants.OUTPUT_FOLDER_PATH, Constants.TODAYSDATE_YYYY_MM_DD);
            Constants.FOLDER_SUB_OUTPUT = FileAndFolderFunctions.Create_A_Folder(Constants.FOLDER_MAIN_OUTPUT + "\\" , Constants.TODAYSDATE_YYYY_MM_DD_HH_MM_SS);

            //To Create a textfile to log details in output folder

            // Textfile to Log updates when ST2_Cndt2-watchlists are updated for alerts received for ST2_Cndt1
            // this file is use to record the out details
            Constants.TEXTFILE_PATH_ST2_CNDT2_WATCHLIST_UPDATES = FileAndFolderFunctions.Create_A_TextFile(
                    Constants.FOLDER_SUB_OUTPUT+ "\\", Constants.TEXTFILE_NAME_ST2_CNDT2_WATCHLIST_UPDATES);

            // Textfile to Log updates when ST2_Cndt3-watchlist is updated for alerts received for ST2_Cndt2
            // this file is use to record the out details
            Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_UPDATES = FileAndFolderFunctions.Create_A_TextFile(
                    Constants.FOLDER_SUB_OUTPUT+ "\\", Constants.TEXTFILE_NAME_ST2_CNDT3_WATCHLIST_UPDATES);

            // Textfile to Log updates when ST2_Cndt2-watchlists are updated with removing stocks for avoiding duplicate alerts in final scan
            // this file is use to record the out details
            Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_STOCKS_REMOVED_TO_AVOID_DUPLICATE_ALERTS = FileAndFolderFunctions.Create_A_TextFile(
                    Constants.FOLDER_SUB_OUTPUT+ "\\", Constants.TEXTFILE_NAME_ST2_CNDT3_WATCHLIST_STOCKS_REMOVED_TO_AVOID_DUPLICATE_ALERTS);

            // Textfile to Log updates when ST2_Cndt3-watchlist is updated with removing stocks for invalid condition
            // this file is use to record the out details
            Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_STOCKS_REMOVED_FOR_INVALID_CONDITION = FileAndFolderFunctions.Create_A_TextFile(
                    Constants.FOLDER_SUB_OUTPUT+ "\\", Constants.TEXTFILE_NAME_ST2_CNDT3_WATCHLIST_STOCKS_REMOVED_FOR_INVALID_CONDITION);

            //Textfile for copying and pasting alerts in Data folder at runtiem for ST2_Cndt1
            Constants.TEXTFILE_PATH_FOR_COPY_PASTED_ALERTS_OUTPUT_OF_ST2_CNDT1 = FileAndFolderFunctions.Create_A_TextFile(
                    Constants.DATA_FOLDER_PATH, Constants.TEXTFILE_NAME_COPY_PASTED_ALERTS_OUTPUT_OF_ST2_CNDT1);

            //Textfile for copying and pasting alerts in Data folder at runtiem for ST1_Cndt2
            Constants.TEXTFILE_PATH_FOR_COPY_PASTED_ALERTS_OUTPUT_OF_ST2_CNDT2 = FileAndFolderFunctions.Create_A_TextFile(
                    Constants.DATA_FOLDER_PATH, Constants.TEXTFILE_NAME_COPY_PASTED_ALERTS_OUTPUT_OF_ST2_CNDT2);

            //Textfile for ST2_Cndt2 for next day carry forward stocks
            Constants.TEXTFILE_PATH_ST2_CNDT2_ALERTS_FOR_NEXYDAY_CARRY_FORWARD_SCENARIO = FileAndFolderFunctions.Create_A_TextFile(
                    Constants.DATA_FOLDER_PATH, Constants.TEXTFILE_NAME_FOR_NEXYDAY_CARRY_FORWARD_STOCKS_OF_ST2_CNDT2);

            //        Commenting below for time being , to be work on later
            //        Constants.TEXTFILE_PATH_ST1_CNDT2_ALERTS = FileAndFolderFunctions.Create_A_TextFile(Constants.FOLDER_SUB_OUTPUT+ "\\", Constants.TEXTFILE_NAME_ST1_CNDT2_ALERTS);
            //        Constants.TEXTFILE_PATH_ST1_CNDT3_ALERTS = FileAndFolderFunctions.Create_A_TextFile(Constants.FOLDER_SUB_OUTPUT+ "\\", Constants.TEXTFILE_NAME_ST1_CNDT3_ALERTS);

            // </editor-fold>

            // To empty all watchlist for Strategy_2
            if ("true".equalsIgnoreCase(prop.getProperty("empty_All_Watchlists_For_Strategy_2").trim())) {
                watchlistPage.empty_All_Watchlists_For_Strategy_2(Constants.TEXTFILE_PATH_FOR_ST2_CNDT2_WATCHLISTS_NAME_AND_URL);

                // Add previous day shortlisted stocks to ST1_Cndt3 watchlist at strat of the day for further validation for today
                if ("true".equalsIgnoreCase(prop.getProperty("to_Consider_Previous_Day_Filtered_Stocks_For_Today").trim())){
                    // GNavigate to ST_1_Cndt_3_Watchlist
                    newTabsSetUp.navigateToTab(Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist);

                    //Upload Stocks text file
                    watchlistPage.upload_Stock_List_TextFile(Constants.TEXTFILE_PATH_ST2_CNDT2_ALERTS_FOR_NEXYDAY_CARRY_FORWARD_SCENARIO);

                    // Clear the text file for today's run
                    FileAndFolderFunctions.clear_Text_File(Constants.TEXTFILE_PATH_ST2_CNDT2_ALERTS_FOR_NEXYDAY_CARRY_FORWARD_SCENARIO);
                }
            }

            // To collect All failed steps and report them at end of the test case ,
            // it is created to avoid hlt in between test case execution
            finalizeReport();

        }catch (Exception e) {

            System.out.println("Prerequisite_To_Login_And_Set_Sub_Tabs_Urls: " + e.getMessage());
            ReportUtil.report( false, "FAIL", "Prerequisite_To_Login_And_Set_Sub_Tabs_Urls, ",  e.getMessage());
        }

        ReportUtil.report( true, "INFO", "-- Function -- Ending -- Prerequisite_To_Login_And_Set_Sub_Tabs_Urls",  "");

    }
    public void Execute_Strategy_2_All_Conditions() throws InterruptedException {

        ReportUtil.report( true, "INFO", "-- Function -- Starting -- Execute_Strategy_2_All_Conditions",  "");

        // <editor-fold desc="Variables">

        int loop_Count = 75;
        String ST2_CNDT1_Alerts_Stock_Names = "";
        String ST2_CNDT1_latest_Alert_TimeStamp = "";

        String ST2_CNDT2_Alerts_Stock_Names = "";

        // </editor-fold>

        // <editor-fold desc="Time related setting">
        String Interval = "";
        LocalTime cutoffTime = LocalTime.of(15, 35); // 3:35 PM
        LocalTime endTime = LocalTime.of(10, 30); // 10.35 AM ;  this variable is used for next day carry forward loop to end
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // </editor-fold>

        try {

            // Start loop at specific time i.e 9.15.05 AM
            if ("true".equalsIgnoreCase(prop.getProperty("Start_Loop_At_Specific_Time").trim())) {
                LocalTime start_Time = LocalTime.of(9, 15, 25);
                DateTimeFunctions.start_For_Loop_At_Specific_Time(start_Time);
            }

            // Executing loop for 75 times or till 3.30 PM in interval of 5 mins
            for (int i = 1; i <= loop_Count; i++) {

                Interval = "Executing iteration " + i + " at " + LocalDateTime.now().format(formatter);
                System.out.println(Interval);
                ReportUtil.report(true, "INFO", Interval, "");

                // Step 1: Navigate to  ST2_CNDT1_Alerts tab and get an alerts and update them to ST2_CNDT2 watchlist
                ReportUtil.report(true, "INFO", "-- STEP 1 -- Navigate to ST2_CNDT1_Alerts page and get an alerts and update them to ST2_CNDT2 watchlist", "");

                // If new alert displayed for strategy : ST1_Cndt1 then add it to watchlist of strategy : ST1_Cndt2
                if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_ST2_Cndt1()) {

                    ST2_CNDT1_Alerts_Stock_Names = Constants.ST2_CNDT1_LATEST_ALERT_STOCK_NAMES;
                    ST2_CNDT1_latest_Alert_TimeStamp = Constants.ST2_CNDT1_LATEST_ALERT_TIMESTAMP;

                    // Condition 2
                    // Update Stock Alert to textfile
                    FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_ST2_CNDT2_WATCHLIST,
                            ST2_CNDT1_Alerts_Stock_Names);

                    // Get and update Watchlist_Url_For_ST2_CNDT2_Statergy in constant variables
                    watchlistPage.get_Watchlist_Url_For_ST2_CNDT2_Statergy(ST2_CNDT1_latest_Alert_TimeStamp);
                    watchlistPage.navigate_to_Particular_Watchlist(Constants.TAB_DEFAULT_WATCHLIST_PAGE, Constants.ST2_CNDT2_CURRENT_RUN_WATCHLIST_URL,
                            Constants.ST2_CNDT2_CURRENT_RUN_WATCHLIST_NAME);

                    //Upload Stocks text file
                    watchlistPage.upload_Stock_List_TextFile_Using_Robot(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_ST2_CNDT2_WATCHLIST);

                    // Update Stock Alerts to  output textfile for end of the day validation
                    FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT2_WATCHLIST_UPDATES,
                            Constants.ACTION_STOCKS_ADDED, Constants.ST2_CNDT2_CURRENT_RUN_WATCHLIST_NAME,
                            Constants.ST2_CNDT2_CURRENT_RUN_WATCHLIST_URL, ST2_CNDT1_Alerts_Stock_Names);

                    //Condition 3
                    // Update Stock Alert to textfile:
                    FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_ST2_CNDT3_WATCHLIST, ST2_CNDT1_Alerts_Stock_Names);

                    // GNavigate to ST_2_Cndt_3_Watchlist
                    newTabsSetUp.navigateToTab(Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist);

                    //Upload Stocks text file
                    watchlistPage.upload_Stock_List_TextFile_Using_Robot(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_ST2_CNDT3_WATCHLIST);

                    // Update Stock Alert to  output textfile for end of the day validation
                    FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_UPDATES,
                            Constants.ACTION_STOCKS_ADDED,Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist,
                            prop.getProperty("ST2_Cndt3_Watchlist_Page_Url"), ST2_CNDT1_Alerts_Stock_Names);
                }


                // <editor-fold desc=" Step 2">
                // Step 2: Navigate to  ST2_CNDT2_Alerts tab and get an alerts and delete the stocks from watchlist of condition 2 and condition 3
                // Todo : write a logic to delete stocks from condition 2, time being not mandatory

                ReportUtil.report(true, "INFO", "-- STEP 2 -- Navigate to  ST1_CNDT2_Alerts tab and get an alerts and delete the stocks from watchlist of condition 2 and 3", "");

                // If new alert displayed for strategy : ST1_Cndt2 then add it to watchlist of strategy : ST1_Cndt3
                if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_ST2_Cndt2()) {

                    ST2_CNDT2_Alerts_Stock_Names = Constants.ST1_CNDT2_LATEST_ALERT_STOCK_NAMES;
                    String[] stocks;

                    // Create an array to pass to delete_Stock_From_Watchlist function
                    if (ST2_CNDT2_Alerts_Stock_Names.contains(",")) {
                        stocks = ST2_CNDT2_Alerts_Stock_Names.split(",");
                    }else {
                        stocks = new String[]{ST2_CNDT2_Alerts_Stock_Names};
                    }

                    // Delete Stocks from "ST2_Cndt_3_Watchlist"
                    watchlistPage.delete_Stock_From_Watchlist(Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist,
                            prop.getProperty("ST2_Cndt3_Watchlist_Page_Name"),
                            prop.getProperty("ST2_Cndt3_Watchlist_Page_Url"),stocks);

                    // Update Stock Alert to  output textfile for end of the day validation
                    FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_STOCKS_REMOVED_FOR_INVALID_CONDITION,
                            Constants.ACTION_STOCKS_REMOVED_FOR_INVALID_CONDITION,Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist,
                            prop.getProperty("ST2_Cndt3_Watchlist_Page_Url"), ST2_CNDT2_Alerts_Stock_Names);

                    // Add stocks from 1 pm onward to text file for next day carry forward validation
                    // Regardless of flag condition "to_Consider_Previous_Day_Filtered_Stocks_For_Today"
                    // commenting out below condition as it is based on flag to_Consider_Previous_Day_Filtered_Stocks_For_Today
//                    LocalTime now = LocalTime.now();
//                    if (now.isAfter(LocalTime.of(13, 00))) {
//                        // Upadte Stock Alerst to textfile
//                        FileAndFolderFunctions.append_To_Text_File(Constants.TEXTFILE_PATH_ST2_CNDT2_ALERTS_FOR_NEXYDAY_CARRY_FORWARD_SCENARIO, ST2_CNDT2_Alerts_Stock_Names);
//                    }

                }
                // </editor-fold>

                // <editor-fold desc=" Step 3">
                // Step 3: Navigate to  ST2_CNDT3_Alerts tab and get an alerts and delete the stocks from watchlist of condition 3 "ST2_Cndt_3_Watchlist" to avoid duplicate alerts in final scan
                // adding this condition to avoid duplicate alerts by removing them from ST2_Cndt_3_Watchlist watchlist once alert is given
                // TODO: To be worked on later based on how scan works
//                ReportUtil.report(true, "INFO", "-- STEP 3 -- Navigate to  ST2_CNDT3_Alerts tab and get an alerts and delete the stocks from " +
//                        "watchlist of condition 3 'ST2_Cndt_3_Watchlist' to avoid duplicate alerts in final scan", "");
//
//                // If new alert displayed for strategy : ST1_Cndt3 then delete that stcok from watchlist of
//                // condition 3 'ST2_Cndt_3_Watchlist' to avoid duplicate alerts in final scan
//                if ("true".equalsIgnoreCase(prop.getProperty("to_Remove_Alerted_Stocks_From_Final_Scan_Watchlist").trim())) {
//                    if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_ST2_Cndt2()) {
//
//                        ST2_CNDT2_Alerts_Stock_Names = Constants.ST1_CNDT2_LATEST_ALERT_STOCK_NAMES;
//                        String[] stocks;
//
//                        // Create an array to pass to delete_Stock_From_Watchlist function
//                        if (ST2_CNDT2_Alerts_Stock_Names.contains(",")) {
//                            stocks = ST2_CNDT2_Alerts_Stock_Names.split(",");
//                        } else {
//                            stocks = new String[]{ST2_CNDT2_Alerts_Stock_Names};
//                        }
//
//                        // Delete Stocks from "ST2_Cndt_3_Watchlist"
//                        watchlistPage.delete_Stock_From_Watchlist(Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist,
//                                prop.getProperty("ST2_Cndt3_Watchlist_Page_Name"),
//                                prop.getProperty("ST2_Cndt3_Watchlist_Page_Url"), stocks);
//
//                        // Update Stock Alert to  output textfile for end of the day validation
//                        FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_STOCKS_REMOVED_FOR_INVALID_CONDITION,
//                                Constants.ACTION_STOCKS_REMOVED_FOR_INVALID_CONDITION, Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist,
//                                prop.getProperty("ST2_Cndt3_Watchlist_Page_Url"), ST2_CNDT2_Alerts_Stock_Names);
//
//
//                    }
//                }
                // </editor-fold>

                // <editor-fold desc="Step 4">
                // Step 3: Navigate to  ST1_CNDT2_NextDay_Carry_Forward Alerts tab and get an alerts and update them to ST1_CNDT3 watchlist
                // to_Consider_Previous_Day_Filtered_Stocks_For_Todays validation
//                if ("true".equalsIgnoreCase(prop.getProperty("to_Consider_Previous_Day_Filtered_Stocks_For_Today").trim())) {
//                    LocalTime now = LocalTime.now();
//
//                    if (now.isBefore(endTime))  {   // For next day carry forward loop to end if time is after 10.35 AM
//
//                        ReportUtil.report(true, "INFO", "-- STEP 3 -- Navigate to ST1_CNDT2_NextDay_Carry_Forward alert page and get an alerts and update them to ST1_CNDT3 watchlist", "");
//
//                        // If new alert displayed for strategy : ST1_Cndt2 then add it to watchlist of strategy : ST1_Cndt3
//                        if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_ST1_Cndt2_NextDay_Carry_Forward_Scenario()) {
//
//                            ST2_CNDT2_Alerts_Stock_Names = Constants.ST1_CNDT2_LATEST_ALERT_STOCK_NAMES;
//
//                            // Update Stock Alerts to textfile
//                            FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_ST2_CNDT3_WATCHLIST, ST2_CNDT2_Alerts_Stock_Names);
//
//                            // GNavigate to ST_1_Cndt_3_Watchlist
//                            newTabsSetUp.navigateToTab(Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist);
//
//                            //Upload Stocks text file
//                            watchlistPage.upload_Stock_List_TextFile(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_ST2_CNDT3_WATCHLIST);
//
//                            // Update Stock Alert to  output textfile for end of the day validation
//                            FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_UPDATES,
//                                    Constants.ACTION_STOCKS_ADDED,Constants.TAB_WATCHLISTPAGE_NAME_ST_2_Cndt_3_Watchlist,
//                                    prop.getProperty("ST2_Cndt3_Watchlist_Page_Url"), ST2_CNDT2_Alerts_Stock_Names);
//                        }
//                    } else {
//
//                        System.out.println("Iteration : '" + i + "' , Skipping , stocks for next day carry forward loop ");
//                        ReportUtil.report( true, "INFO", "Iteration : '" + i + "' , Skipping ",  "Step 3: Navigate to  ST1_CNDT2_NextDay_Carry_Forward Alerts tab and get an alerts and update them to ST1_CNDT3 watchlist");
//                    }
//                }
                // </editor-fold>

                //Deleting previous day stocks details from watchlist on 1 pm onwards so new details can be added for current run
                if ("true".equalsIgnoreCase(prop.getProperty("to_Consider_Previous_Day_Filtered_Stocks_For_Today").trim())) {
                    LocalTime now = LocalTime.now();
                    if (now.isAfter(LocalTime.of(12, 55)) && now.isBefore(LocalTime.of(13, 00))){
                        watchlistPage.empty_ST1_Cndt2_Watchlists_From_1_PM_Onwards(Constants.TEXTFILE_PATH_FOR_ST2_CNDT2_WATCHLISTS_NAME_AND_URL);
                    }
                }

                // To ensure for loop starts at the next multiple of 5 minutes 25 seconds,
                // such as 9:25:05, even if the program is started at 9:23:00
                if (i < loop_Count && ("true".equalsIgnoreCase(prop.getProperty("Wait_For_Precise_5min_25Sec_Time_Interval").trim()))) {
                    DateTimeFunctions.loop_At_Precise_Time_Intervals();
                }

                // Break loop if current time is past 3:35 PM
                if ("true".equalsIgnoreCase(prop.getProperty("Break_For_Loop_After_3_35_PM").trim())){
                    LocalTime now = LocalTime.now();

                    if (now.isAfter(cutoffTime)) {
                        System.out.println("Breaking loop as time exceeded 3:35 PM.");
                        break;
                    }
                }

                // it is created to avoid halt in between test case execution, updating all details at final reports
                finalizeReport();

            }

        }catch (Exception e) {

            System.out.println("Execute_Strategy_2_All_Conditions: " + e.getMessage());
            ReportUtil.report( false, "FAIL", "Execute_Strategy_2_All_Conditions, ",  e.getMessage());
        }

        ReportUtil.report( true, "INFO", "-- Function -- Ending -- Execute_Strategy_2_All_Conditions",  "");

    }

}
