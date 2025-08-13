package qa.pages;

import org.openqa.selenium.By;
import qa.base.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Properties;
import qa.helperClass.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import qa.utils.ReportUtil;

public class LoginPage extends BaseTest {

    SeleniumHelper helper;

    //By WebElement_Login_Header_Link = By.xpath("//*[@href='/login']");
    By WebElement_Login_Header_Link = By.xpath("//*[text()='Sign in to your account']");

//    By WebElement_Email_Textbox = By.id("login-email");
    By WebElement_Email_Textbox = By.id("login-email");
    By WebElement_chartInkWebpage = By.xpath("//*[@href='https://chartink.com/']");
    By WebElement_Password_Textbox = By.id("login-password");
    By WebElement_Login_Button = By.xpath("//button//*[text()='Log in']");
    By WebElement_After_Login_Notification_Cancel_Button = By.xpath("//*[@data-callback='onSubmitloginform']");
    By WebElement_Header_ChartInk_Link = By.xpath("(//img[@alt='Chartink Logo'])[1]");
    By WebElement_Header_Dashboard_Link= By.xpath("(//*[@href='/scan_dashboard' and text()='Dashboard'])[1]");
    By WebElement_Header_Sign_In_Link= By.xpath("(//*[@href='/login' and text()='Sign in'])[1]");

//    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//        helper = new SeleniumHelper(driver);
//    }
    public LoginPage() {
        PageFactory.initElements(driver, this);
        helper = new SeleniumHelper();
    }

    public boolean isElementHeaderLoginLinkVisible() {
        return helper.isElementVisible(WebElement_Login_Header_Link);
    }

    public boolean isElementEmailLoginFieldVisible() {
        return helper.isElementVisible(WebElement_Email_Textbox);
    }

    public boolean isElementHeaderDashboardLinkVisible() {
        return helper.isElementVisible(WebElement_Header_Dashboard_Link);
    }

    public boolean is_WebElement_SignIn_Link_Visible() {
        return helper.safeFindElement(WebElement_Header_Sign_In_Link, 0);
    }

    public boolean isElementHeaderChartInkLinkVisible() {
        return helper.isElementVisible(WebElement_Header_ChartInk_Link);
    }

    public boolean isElementSendNotificationPopUpVisible() {
        return helper.isElementVisible(WebElement_After_Login_Notification_Cancel_Button);
    }

    // actions or functions on the Login Page
    public boolean verifyChartInkWebpageDisplayed() {

        if (this.isElementHeaderChartInkLinkVisible()){
            ReportUtil.report( true, "PASS", "Verify ChartInk Webpage Displayed, ",  "ChartInk Webpage Displayed");
            return true;
        } else {
            ReportUtil.report( true, "FAIL", "Verify ChartInk Webpage Displayed, ",  "ChartInk Webpage NOT Displayed");
            return false;
        }
    }

    public boolean verifyUserIsLoggedIn() {

        if (this.is_WebElement_SignIn_Link_Visible()==false){
            ReportUtil.report( true, "PASS", "verify User Is Logged In, ",  "User Is Logged In");
            return true;
        } else {
            ReportUtil.report( true, "INFO", "verify User Is Logged In, ",  "User Is NOT Logged In");
            return false;
        }
    }

    public boolean verifyLoginPageDisplayed() {

        if (this.isElementEmailLoginFieldVisible()){
            ReportUtil.report( true, "PASS", "Verify Login page Displayed, ",  "Login page Displayed");
            return true;
        } else {
            ReportUtil.report( true, "INFO", "Verify Login page Displayed, ",  "Login page NOT Displayed");
            return false;
        }
    }

    public boolean verifySendNotificationPopUpDisplayed() {

        if (this.isElementSendNotificationPopUpVisible()){
            ReportUtil.report( true, "PASS", "Verify After login ,Send Notification Pop Up Displayed",  "Send Notification Pop Up Displayed");
            return true;
        } else {
            ReportUtil.report( true, "INFO", "Verify After login ,Send Notification Pop Up Displayed",  "Send Notification Pop Up NOT Displayed");
            return false;
        }
    }

    // function for logging in to application which will return a Home Page
    public boolean loginToApplication() throws InterruptedException {

        ReportUtil.report( true, "INFO", "-- Function -- Starting -- loginToApplication function",  "");

        if (verifyChartInkWebpageDisplayed()){
            //helper.safeClick(WebElement_Login_Header_Link);
            if (verifyLoginPageDisplayed()){
                helper.sendKeysSafe(WebElement_Email_Textbox,prop.getProperty("emailid") );
                Thread.sleep(1000);
                helper.sendKeysSafe(WebElement_Password_Textbox,prop.getProperty("pwd") );
                helper.safeClick(WebElement_Login_Button);
                Thread.sleep(3000);

//                if (verifySendNotificationPopUpDisplayed()){
//                    helper.safeClick(loginNotificationCancelButton);
//                }

                if (!verifyUserIsLoggedIn()){
                    ReportUtil.report( false, "FAIL", "verify User Is Logged In, ",  "User Is NOT Logged In");
                } else {
                    ReportUtil.report( true, "PASS", "verify User Is Logged In, ",  "User Is Logged In");
                }
            }

        }
        ReportUtil.report( true, "INFO", "-- Function -- Ending -- loginToApplication function",  "");
        return true;
    }
//        // function to navigate to User Register Page which will return a Register Page
//    public UserRegisterPage navigateToRegisterPage() {
//        createAccountLink.click();
//        return new UserRegisterPage();
//    }
}
