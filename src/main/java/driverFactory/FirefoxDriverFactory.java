package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory extends DriverAbstract {

    protected WebDriver driver;

    public WebDriver startDriver(){
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        return driver;
    }
}
