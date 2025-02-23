package tests;

import driverFactory.ChromeDriverFactory;
import driverFactory.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.DataTest;
import pages.HomePage;
import pages.LoginSignupPage;

import java.time.Duration;

public class NewTest {

    public Driver driver;
    public Logger logger; // for logs



    @BeforeClass
    public void setUp() {
        driver = new Driver("CHROME");

        driver.browser().navigateToURL("https://automationexercise.com/");
        driver.browser().maximizeWindow();


//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // logs
        logger = LogManager.getLogger(this.getClass());
        logger.debug("debugging.....");
    }


    @Test(priority = 1)
    public void userCanRegisterSuccessfully() {
        logger.info("*************** User Can Register **************");
        new HomePage(driver)

                .checkThatUserNavigatedToHomePageSuccessfully()
                .clickOnLoginLink()
                .checkThatUserNavigatedToLoginSignUpPage()
                .checkThatNewUserSignUpTitleIsDisplayed()
                .fillInNameField("Mariam")
                .fillInSinUpEmailField(DataTest.fakerEmail)
                .clickOnSignUpButton()
                .checkThaRegistrationPageIsLoadedSuccessfully()
                .fillInregistrationform()
//                .checkThatRegistrationTitleIsDisplayed()
                .clickOnContinueButton();
        logger.info("*************** User is Registered SuccessFully  **************");

        driver.get().manage().deleteAllCookies();
    }

    @Test(priority = 2, dependsOnMethods = "userCanRegisterSuccessfully")
    public void checkThatUserCanLoginSuccessfully() {
        logger.info("*************** User Can Login And Navigate To HomePage **************");

        driver.get().navigate().to("https://automationexercise.com/login");
        new LoginSignupPage(driver)
                .fillInLoginEmailField(DataTest.fakerEmail)
                .fillInLoginPasswordField("Password@123")
                .clickOnLoginButton()
                .checkThatLogoutLinkIsDisplayed()
                .checkThatDeleteAccountLinkIsDisplayed();

        logger.info("*************** User is Logged In And Navigated To HomePage Successfully **************");
        driver.get().manage().deleteAllCookies();

    }

    @Test(priority = 3, dependsOnMethods = "checkThatUserCanLoginSuccessfully")
    public void checkThatUserCanLogoutSuccessfully() {
        logger.info("*************** User Can Logout **************");

        driver.get().navigate().to("https://automationexercise.com/");
        new HomePage(driver)
                .checkThatUserNavigatedToHomePageSuccessfully()
                .checkThatLoginLinkIsDisplayed()
                .clickOnLoginLink()
                .checkThatUserNavigatedToLoginSignUpPage()
                .checkThatLoginTitleIsDisplayed()
                .fillInLoginEmailField(DataTest.fakerEmail)
                .fillInLoginPasswordField("Password@123")
                .clickOnLoginButton()
                .checkThatLoggedInAsUsernameEnteredIsDisplayed("Mariam")
                .clickOnLogoutLink()
                .checkThatUserNavigatedToLoginSignUpPage();
        logger.info("*************** User is Logged out Successfully **************");

    }

    @Test(priority = 4, dependsOnMethods = "checkThatUserCanLogoutSuccessfully")
    public void checkThatUserCanDeleteAccountSuccessfully() {
        new LoginSignupPage(driver)
                .checkThatUserNavigatedToLoginSignUpPage()
                .checkThatLoginTitleIsDisplayed()
                .fillInLoginEmailField(DataTest.fakerEmail)
                .fillInLoginPasswordField("Password@123")
                .clickOnLoginButton()
                .checkThatUserNavigatedToHomePageSuccessfully()
                .checkThatDeleteAccountLinkIsDisplayed()
                .clickOnDeleteAccount()
                .checkThatDeleteAccountPageIsLoadedSuccessfully()
                .clickOnContinueButton()
                .checkThatUserNavigatedToHomePageSuccessfully();
    }

    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();

    }
}
