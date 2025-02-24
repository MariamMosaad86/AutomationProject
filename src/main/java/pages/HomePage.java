package pages;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    private Driver driver;
    FluentWait wait;

    By logoutLink = By.xpath("//a[@href=\"/logout\"]");
    By deleteAccountLink = By.xpath("//a[@href=\"/delete_account\"]");
    By loginLink = By.xpath("//a[@href=\"/login\"]");
    By userLoggedInName = By.xpath("(//li/a)[10]");
    By contactUsLink = By.xpath("//a[@href=\"/contact_us\"]");

    public HomePage(Driver driver) {
        this.driver = driver;
        wait = new FluentWait<>(this.driver);
        wait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);

    }

    /*********************************  Assertions  *****************************************************/
    @Step("checkThatLogoutLinkIsDisplayed")
    public HomePage checkThatLogoutLinkIsDisplayed() {
//        wait.until(ExpectedConditions.visibilityOf(driver.get().findElement(logoutLink)));
        Assert.assertTrue(driver.element().isDisplayed(logoutLink));
        return this;
    }

    @Step("checkThatDeleteAccountLinkIsDisplayed")
    public HomePage checkThatDeleteAccountLinkIsDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(deleteAccountLink));
        return this;
    }

    @Step("checkThatLoginLinkIsDisplayed")
    public HomePage checkThatLoginLinkIsDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(loginLink));
        return this;
    }

    @Step("checkThatContactUsLinkIsDisplayed")
    public HomePage checkThatContactUsLinkIsDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(contactUsLink));
        return this;
    }

    @Step("checkThatUserNavigatedToHomePageSuccessfully")
    public HomePage checkThatUserNavigatedToHomePageSuccessfully() {
        Assert.assertEquals(driver.browser().getCurrentUrl(), "https://automationexercise.com/");
        return this;
    }

    @Step("checkThatLoggedInAsUsernameEnteredIsDisplayed")
    public HomePage checkThatLoggedInAsUsernameEnteredIsDisplayed(String userName) {
        Assert.assertEquals(driver.element().getTextOf(userLoggedInName), "Logged in as " + userName);
        return this;
    }

    /*********************************  Actions  *****************************************************/
    @Step("clickOnLoginLink")
    public LoginSignupPage clickOnLoginLink() {
        driver.element().click(loginLink);
        return new LoginSignupPage(driver);
    }

    @Step("clickOnLogoutLink")
    public LoginSignupPage clickOnLogoutLink() {
        driver.element().click(logoutLink);
        return new LoginSignupPage(driver);
    }

    @Step("clickOnDeleteAccount")
    public AccountSuccessfulDeletionPage clickOnDeleteAccount() {
        driver.element().click(deleteAccountLink);
        return new AccountSuccessfulDeletionPage(driver);
    }

    @Step("clickOnContactUsLink")
    public ContactUsPage clickOnContactUsLink() {
        driver.element().click(contactUsLink);
        return new ContactUsPage(driver);
    }
}
