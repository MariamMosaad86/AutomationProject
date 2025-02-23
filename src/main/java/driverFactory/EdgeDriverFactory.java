package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverFactory extends DriverAbstract {

    protected WebDriver driver;

    public WebDriver startDriver() {
        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(options);
        return driver;
    }
}
