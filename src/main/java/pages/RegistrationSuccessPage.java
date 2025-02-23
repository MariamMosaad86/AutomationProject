package pages;

import driverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationSuccessPage {

    private Driver driver;
    private WebDriverWait wait;

    By continueButton = By.xpath("//a[@data-qa=\"continue-button\"]");
    By successMessage = By.xpath("//h2[@data-qa=\"account-created\"]");

    public RegistrationSuccessPage(Driver driver) {
        this.driver = driver;
//        wait=new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    /*********************************  Assertions  *****************************************************/

    public RegistrationSuccessPage checkThatRegistrationTitleIsDisplayed() {
//        wait.until(ExpectedConditions.urlContains("/account_created"));

        wait.until(ExpectedConditions.visibilityOf(driver.get().findElement(successMessage)));

        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/account_created"));
        Assert.assertTrue(driver.element().isDisplayed(successMessage));
//        Assert.assertEquals(driver.element().getTextOf(successMessage), "ACCOUNT CREATED!");
        return this;
    }

    /*********************************  Actions  *****************************************************/
    public HomePage clickOnContinueButton() {
        driver.element().click(continueButton);
        return new HomePage(driver);
    }
}

