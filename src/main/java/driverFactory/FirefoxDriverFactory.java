package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static utilities.properties.PropertiesManager.WebConfig;

public class FirefoxDriverFactory extends DriverAbstract {

    protected WebDriver driver;

    public WebDriver startDriver(){
        FirefoxOptions options = new FirefoxOptions();
        if (WebConfig.getProperty("HeadlessMode").equalsIgnoreCase("true")){
            options.addArguments("--headless");
        }
        driver = new FirefoxDriver(options);
        return driver;
    }
}
