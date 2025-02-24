package pages;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class ContactUsPage {

    private Driver driver;

    FluentWait fluentWait;

    By getInTouchTitle = By.xpath("(//h2[@class=\"title text-center\"])[2]");
    By nameField = By.xpath("(//input[@class=\"form-control\"])[1]");
    By emailField = By.xpath("(//input[@type=\"email\"])[1]");
    By subjectField = By.xpath("//input[@data-qa=\"subject\"]");
    By messageField = By.xpath("//textarea[@data-qa=\"message\"]");
    By submitButton = By.xpath("//input[@data-qa=\"submit-button\"]");
    By successMessage = By.cssSelector("div.status.alert.alert-success");
    By homeButton = By.xpath("//a[@class=\"btn btn-success\"]");

    public ContactUsPage(Driver driver) {
        this.driver = driver;
    }


    /*********************************  Assertions  *****************************************************/
    @Step("check That Get In Touch Is Displayed")
    public ContactUsPage checkThatGetInTouchIsDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(getInTouchTitle));
        Assert.assertEquals(driver.element().getTextOf(getInTouchTitle), "GET IN TOUCH");
        return this;
    }

    @Step("check That ContactUs Page Is LoadedSuccessfully")
    public ContactUsPage checkThatContactUsPageIsLoadedSuccessfully() {
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/contact_us"));
        return this;
    }

    @Step("checkThatContactUsFormIsSubmittedSuccessfully")
    public ContactUsPage checkThatContactUsFormIsSubmittedSuccessfully() {
        Assert.assertEquals(driver.element().getTextOf(successMessage), "Success! Your details have been submitted successfully.");
        return this;
    }

    /*********************************  Actions  *****************************************************/
    @Step("fillInContactUsForm")
    public ContactUsPage fillInContactUsForm(String name, String email, String subject, String message) {
        driver.element().fillFiled(nameField, name);
        driver.element().fillFiled(emailField, email);
        driver.element().fillFiled(subjectField, subject);
        driver.element().fillFiled(messageField, message);
        return this;
    }

    @Step("clickOnSubmitButton")
    public ContactUsPage clickOnSubmitButton() {
        driver.element().click(submitButton);
        driver.get().switchTo().alert().accept();
        return this;
    }

    @Step("clickOnHomeButton")
    public HomePage clickOnHomeButton() {
        driver.element().click(homeButton);
        return new HomePage(driver);
    }

}
