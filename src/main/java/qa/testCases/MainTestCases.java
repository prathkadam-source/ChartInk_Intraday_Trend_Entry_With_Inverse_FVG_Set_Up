package qa.testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import qa.base.BaseTest;
import org.testng.annotations.Listeners;
import qa.commonfuctions.NewTabsSetUp;
import qa.pages.AlertPage;
import qa.pages.LoginPage;
import qa.commonfuctions.*;
import qa.commonfuctions.Constants;
import qa.commonfuctions.FileAndFolderFunctions;
import qa.pages.WatchlistPage;
import qa.utils.ReportUtil;
import qa.commonfuctions.TelegramBotSender;

import java.io.IOException;

import static qa.utils.ReportUtil.finalizeReport;

@Listeners(qa.listeners.TestListener.class)
public class MainTestCases  extends BaseTest{

    LoginPage loginPage = new LoginPage();
    NewTabsSetUp newTabsSetUp = new NewTabsSetUp();

    AlertPage alertPage = new AlertPage();

    WatchlistPage watchlistPage = new WatchlistPage();

    Strategy_2_Test_Cases strategy_2_Test_Cases = new Strategy_2_Test_Cases();


    @Test(priority = 1)
    public void TestCase_1_Prerequisite_To_Login_And_Set_Sub_Tabs_Urls() throws InterruptedException {

        ReportUtil.report( true, "INFO", "-- TestCase -- Starting -- TestCase_1_Prerequisite_To_Login_And_Set_Sub_Tabs_Urls",  "");

        strategy_2_Test_Cases.Prerequisite_To_Login_And_Set_Sub_Tabs_Urls();

        ReportUtil.report( true, "INFO", "-- TestCase -- Ending -- TestCase_1_Prerequisite_To_Login_And_Set_Sub_Tabs_Urls",  "");

    }

    @Test(priority = 2, dependsOnMethods = "TestCase_1_Prerequisite_To_Login_And_Set_Sub_Tabs_Urls")
    public void TestCase_2_Execute_Strategy_2_All_Conditions() throws InterruptedException, IOException {

        ReportUtil.report( true, "INFO", "-- TestCase -- Starting -- TestCase_2_Execute_Strategy_2_All_Conditions",  "");

        strategy_2_Test_Cases.Execute_Strategy_2_All_Conditions();


        ReportUtil.report( true, "INFO", "-- TestCase -- Ending -- TestCase_2_Execute_Strategy_2_All_Conditions",  "");

    }


}
