package pages;

import driverFactory.Driver;
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

    public ContactUsPage checkThatGetInTouchIsDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(getInTouchTitle));
        Assert.assertEquals(driver.element().getTextOf(getInTouchTitle), "GET IN TOUCH");
        return this;
    }

    public ContactUsPage checkThatContactUsPageIsLoadedSuccessfully(){
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/contact_us"));
        return this;
    }

    public ContactUsPage checkThatContactUsFormIsSubmittedSuccessfully(){
        Assert.assertEquals(driver.element().getTextOf(successMessage),"Success! Your details have been submitted successfully.");
        return this;
    }

    /*********************************  Actions  *****************************************************/

    public ContactUsPage fillInContactUsForm(String name, String email, String subject, String message) {
       driver.element().fillFiled(nameField,name);
        driver.element().fillFiled(emailField,email);
        driver.element().fillFiled(subjectField,subject);
        driver.element().fillFiled(messageField,message);
        return this;
    }

    public ContactUsPage clickOnSubmitButton() {
        driver.element().click(submitButton);
        driver.get().switchTo().alert().accept();
        return this;
    }

    public HomePage clickOnHomeButton(){
        driver.element().click(homeButton);
        return new HomePage(driver);
    }

}
