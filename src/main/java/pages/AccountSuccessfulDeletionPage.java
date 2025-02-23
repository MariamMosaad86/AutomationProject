package pages;

import driverFactory.Driver;
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

    public AccountSuccessfulDeletionPage checkThatDeleteAccountPageIsLoadedSuccessfully(){
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/delete_account"));
        Assert.assertEquals(driver.element().getTextOf(deleteTitle),"ACCOUNT DELETED!");
        return this;
    }

    public HomePage clickOnContinueButton(){
        driver.element().click(continueButton);
        return new HomePage(driver);
    }
}
