import helpers.DataProviders;
import helpers.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;

import java.io.IOException;
import java.util.List;

import static helpers.DriverSingleton.getDriver;


public class TwitterTest {
    public static final String BASE_URL = "https://twitter.com/signup";
    @BeforeMethod(alwaysRun = true)
    public void setup(){
        getDriver().get(BASE_URL);
    }
    @AfterMethod(alwaysRun = true)
    public void teardown() {
        DriverSingleton.quit();
    }

    @Test
    public void signUpTest1(){
        RegisterPage.signUp("qwerty", "qwerty");
        List<WebElement>validations= RegisterPage.getValidationMessages();
        Assert.assertEquals(validations.size(),1);
        WebElement validation =getDriver().findElement(RegisterPage.ACTIVE_EMAIL_VALIDATION);
        Assert.assertTrue(validation.isDisplayed());
        Assert.assertEquals(validation.getText(), "");


//        Assert.assertEquals(validations.get(0).findElement(By.xpath(".//parent::div[@data-fieldname]")));
        Assert.assertEquals(RegisterPage.getValidationMessages().size(),1);
    }
    @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
    public void signUpTes(String email, String pass, String emailValidation, String passValidation) throws IOException {
        RegisterPage.signUp(email, pass);
        int validations = 0;

        if (emailValidation.length() > 2) {
            validations++;
            WebElement emailValidationElement = getDriver().findElement(RegisterPage.ACTIVE_EMAIL_VALIDATION);
            Assert.assertTrue(emailValidationElement.isDisplayed());
            Assert.assertEquals(emailValidationElement.getText(), emailValidation);
        }

        if (passValidation.length() > 2) {
            validations++;
            WebElement passValidationMessage = getDriver().findElement(RegisterPage.ACTIVE_PASS_VALIDATION);
            Assert.assertTrue(passValidationMessage.isDisplayed());
            Assert.assertEquals(passValidationMessage.getText(), passValidation);
        }

        Assert.assertEquals(RegisterPage.getValidationMessages().size(), validations);
    }
}
