package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory extends DriverAbstract{

protected WebDriver driver;

    @Override
    public WebDriver startDriver(){
        ChromeOptions options = new ChromeOptions();
        driver=new ChromeDriver(options);
        return driver;
    }
}
