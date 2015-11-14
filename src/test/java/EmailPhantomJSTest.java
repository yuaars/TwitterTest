import helpers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static helpers.DriverSingleton.getDriver;

public class EmailPhantomJSTest {
    public static final String BASE_URL = "http://the-internet.herokuapp.com";
    public  static final String STATIC_EMAIL = "wtpvnugr@sharklasers.com";
    public  static final String EMAIL_SUBJECT = "Forgot Password from the-internet";

    @BeforeMethod(alwaysRun = true)
    public void setup(){

        getDriver().get(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {

        DriverSingleton.quit();
    }

    @Test
    public void forgotPasswordTest(){

        getDriver().findElement(By.linkText("Forgot Password")).click();
        String email = "wtpvnugr@sharklasers.com";
        getDriver().findElement(By.id("email")).sendKeys(email);
        WebElement submitButton = getDriver().findElement(By.id("form_submit"));
        submitButton.click();
        verifyForgotPasswordEmailGotTest(STATIC_EMAIL,EMAIL_SUBJECT);


    }

    //headless browser test - PhantomJS
    public void verifyForgotPasswordEmailGotTest(String email, String subject){

        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://www.guerrillamail.com/ru/inbox");

        List <WebElement> mails = getDriver().findElements(By.cssSelector("#email_list tr"));
        WebElement emailRow = null;
       for (WebElement mail : mails){
           if (mail.getText().contains("no-reply@the-internet.herokuapp.com ")){
               emailRow = mail;
               break;
           }
       }
        emailRow.click();

        WebElement header = getDriver().findElement(By.cssSelector("#display_email h3"));
        Assert.assertEquals(header.getText(),"Forgot Password from the-internet");

    }
}
