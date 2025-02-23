package pages;

import driverFactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RegistrationPage {

    private Driver driver;

    public RegistrationPage(Driver driver) {
        this.driver = driver;
    }

    By passwordField = By.xpath("//input[@data-qa=\"password\"]");
    By daysDropDown = By.id("days");
    By monthDropDown = By.id("months");
    By yearDropDown = By.id("years");
    By newsLetter = By.id("newsletter");
    By receiveSpecialOffers = By.id("optin");
    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By firstAddress = By.id("address1");
    By country = By.id("country");
    By state = By.xpath("//input[@name=\"state\"]");
    By city = By.xpath("//input[@data-qa=\"city\"]");
    By zipCode = By.id("zipcode");
    By mobileNumber = By.id("mobile_number");
    By submitButton = By.xpath("(//button[@type=\"submit\"])[1]");
    By registrationFormTitle = By.xpath("(//h2[@class=\"title text-center\"])[1]");

    /*********************************  Assertions  *****************************************************/

    public RegistrationPage checkThaRegistrationPageIsLoadedSuccessfully() {
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/signup"));
        Assert.assertEquals(driver.element().getTextOf(registrationFormTitle), "ENTER ACCOUNT INFORMATION");
        return this;
    }


    /*********************************  Actions  *****************************************************/

    public RegistrationSuccessPage fillInregistrationform() {

        driver.element().fillFiled(passwordField, "Password@123");
        driver.element().selectByIndex(daysDropDown, 4);
        driver.element().selectByIndex(monthDropDown, 10);
        driver.element().selectByValue(yearDropDown, "1986");
        driver.element().click(newsLetter);
        driver.element().click(receiveSpecialOffers);
        driver.element().fillFiled(firstName, "Maro");
        driver.element().fillFiled(lastName, "Moss");
        driver.element().fillFiled(firstAddress, "Alex");
        driver.element().selectByValue(country, "Canada");
        driver.element().fillFiled(state, "Alex");
        driver.element().fillFiled(city, "Alexandria");
        driver.element().fillFiled(zipCode, "12222");
        driver.element().fillFiled(mobileNumber, "0121212141");
        driver.element().click(submitButton);

        return new RegistrationSuccessPage(driver);
    }
}
