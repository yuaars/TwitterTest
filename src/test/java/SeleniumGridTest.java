import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridTest {

    @Test
    public void simpleGridTest() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("firefox");
        URL hubAddress = new URL("http://192.168.37.174:4444/wd/hub");
        WebDriver driver = new RemoteWebDriver(hubAddress,caps);


        driver.get("http://the-internet.herokuapp.com/");
        System.out.println(driver.getTitle());

    }

}
