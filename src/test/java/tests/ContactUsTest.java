package tests;

import driverFactory.Driver;

import org.testng.annotations.*;
import pages.DataTest;
import pages.HomePage;

public class ContactUsTest {
//    public Driver driver;

  public ThreadLocal<Driver> driver ; //1


    @BeforeClass
    @Parameters(value = {"browserName"})
    public void setUp(@Optional("CHROME") String browserName) {
        driver= new ThreadLocal<>(); //2
        driver.set(new Driver(browserName)); //3
///        driver = new Driver(browserName);
        driver.get().browser().navigateToURL("https://automationexercise.com/"); //4
        driver.get().browser().maximizeWindow();



    }

    @Test
    public void ContactusTest() {
//        logger.info("*************** User Can Contact Us **************");

        new HomePage(driver.get())
                .checkThatUserNavigatedToHomePageSuccessfully()
                .checkThatContactUsLinkIsDisplayed()
                .clickOnContactUsLink()
                .checkThatContactUsPageIsLoadedSuccessfully()
                .checkThatGetInTouchIsDisplayed()
               .fillInContactUsForm("Mariam", DataTest.fakerEmail, "Hello", "Hi")
                .clickOnSubmitButton()
                .checkThatContactUsFormIsSubmittedSuccessfully()
                .clickOnHomeButton();

    }


    @AfterClass
    public void tearDown() {
        driver.get().browser().deleteAllCookies();
        driver.get().quit();
    }

}

