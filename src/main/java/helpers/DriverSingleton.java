package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static WebDriver driver;
    private DriverSingleton(){}


    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver("phantomjs");
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static void initDriver(String browser) {
        String browserName =System.getProperty("browser", browser);
        String remote = System.getProperty("remote", null);

        if (remote == null){
            switch (browserName) {
                case "chrome":
                default:
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    break;
                case "htmlunit":
                    driver = new HtmlUnitDriver();
                case "phantomjs":
                    driver = new PhantomJSDriver();
                    break;

            }
            }else {
            DesiredCapabilities capabilities;
            switch (browserName) {
                case "firefox":
                    capabilities = DesiredCapabilities.firefox();
                    break;
                case "ie":
                    capabilities = DesiredCapabilities.internetExplorer();
                    break;
                case "chrome":
                    capabilities = DesiredCapabilities.chrome();
                    break;
                case "htmlunit":
                    capabilities = DesiredCapabilities.htmlUnit();
                    break;
                case "phantomjs":
                    capabilities = DesiredCapabilities.phantomjs();
                    break;
                default:
                    capabilities = DesiredCapabilities.firefox();
                    break;
            }
            try {
                driver=new RemoteWebDriver(new URL(remote),capabilities);
            } catch (MalformedURLException ex) {
                // do nothing
            }
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

}
