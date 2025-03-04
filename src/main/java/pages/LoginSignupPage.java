package pages;

import driverFactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSignupPage {

    private Driver driver;

    By loginEmail = By.xpath("//input[@data-qa=\"login-email\"]");
    By loginPassword = By.name("password");
    By loginButton = By.xpath("//button[@data-qa=\"login-button\"]");

    By signUpName = By.name("name");
    By signUpEmail = By.xpath("//input[@data-qa=\"signup-email\"]");
    By signUpButton = By.xpath("//button[@data-qa=\"signup-button\"]");
    By signupFormTitle = By.xpath("(//h2)[3]");
    By loginTitle = By.xpath("(//h2)[1]");


    public LoginSignupPage(Driver driver) {
        this.driver = driver;
    }

    /*********************************  Assertions  *****************************************************/
    @Step("checkThatUserNavigatedToLoginSignUpPage")
    public LoginSignupPage checkThatUserNavigatedToLoginSignUpPage() {
        Assert.assertTrue(driver.get().getCurrentUrl().contains("/login"));
        return this;
    }

    @Step("checkThatNewUserSignUpTitleIsDisplayed")
    public LoginSignupPage checkThatNewUserSignUpTitleIsDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(signupFormTitle));
        Assert.assertEquals(driver.element().getTextOf(signupFormTitle), "New User Signup!");
        return this;
    }

    @Step("checkThatLoginTitleIsDisplayed")
    public LoginSignupPage checkThatLoginTitleIsDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(loginTitle));
        Assert.assertEquals(driver.element().getTextOf(loginTitle), "Login to your account");
        return this;
    }

    /*********************************  Actions  *****************************************************/
    @Step("fillInLoginEmailField")
    public LoginSignupPage fillInLoginEmailField(String email) {
        driver.element().fillFiled(loginEmail, email);
        return this;
    }

    @Step("fillInLoginPasswordField")
    public LoginSignupPage fillInLoginPasswordField(String password) {
        driver.element().fillFiled(loginPassword, password);
        return this;
    }

    @Step("clickOnLoginButton")
    public HomePage clickOnLoginButton() {
        driver.element().click(loginButton);
        return new HomePage(driver);
    }

    @Step("fillInNameField")
    public LoginSignupPage fillInNameField(String name) {
        driver.element().fillFiled(signUpName, name);
        return this;
    }

    @Step("fillInSinUpEmailField")
    public LoginSignupPage fillInSinUpEmailField(String email) {
        driver.element().fillFiled(signUpEmail, email);
        return this;
    }

    @Step("clickOnSignUpButton")
    public RegistrationPage clickOnSignUpButton() {
        driver.element().click(signUpButton);
        return new RegistrationPage(driver);
    }


}
