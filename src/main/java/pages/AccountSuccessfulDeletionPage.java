package pages;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountSuccessfulDeletionPage {
    private Driver driver;

    By deleteTitle = By.xpath("//h2[@class=\"title text-center\"]");
    By continueButton = By.xpath("//a[@data-qa=\"continue-button\"]");

    public AccountSuccessfulDeletionPage(Driver driver) {
        this.driver = driver;
    }

    @Step("check That Delete Account Page Is Loaded Successfully")

    public AccountSuccessfulDeletionPage checkThatDeleteAccountPageIsLoadedSuccessfully() {
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/delete_account"));
        Assert.assertEquals(driver.element().getTextOf(deleteTitle), "ACCOUNT DELETED!");
        return this;
    }

    @Step("click On Continue Button")
    public HomePage clickOnContinueButton() {
        driver.element().click(continueButton);
        return new HomePage(driver);
    }
}
