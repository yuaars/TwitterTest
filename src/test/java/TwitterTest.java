import helpers.DataProviders;
import helpers.DriverSingleton;
import helpers.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;


import java.io.IOException;


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



    @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
    public void signUpTest(String user, String email, String pass, String emailValidation, String passValidation) throws IOException {
        RegisterPage.fillForm(user, email, pass);
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


    @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
    public void weakPasswordTest (String user, String email, String pass, String weakPasswordValidationMessage){
        RegisterPage.fillForm(user, email, pass);
        WebElement weakPassValidationElement = getDriver().findElement(RegisterPage.WEAK_PASS_VALIDATION);
        Assert.assertTrue(weakPassValidationElement.isDisplayed());
        Assert.assertEquals(weakPassValidationElement.getText(),weakPasswordValidationMessage);

    }


    @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
    public void shortPasswordTest(String user, String email, String pass, String shortPasswordValidationMessage){
        RegisterPage.fillForm(user, email, pass);
        WebElement shortPassValidationElement = getDriver().findElement(RegisterPage.SHORT_PASS_VALIDATION);
        Assert.assertTrue(shortPassValidationElement.isDisplayed());
        Assert.assertEquals(shortPassValidationElement.getText(),shortPasswordValidationMessage);
    }


    @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
    public void phoneNumberTest(String user, String email, String pass, String phoneNumberValidation){
        RegisterPage.fillForm(user, email, pass);
        WebElement phoneNumberValidationElement = getDriver().findElement(RegisterPage.ACTIVE_PHONE_VALIDATION);
        Assert.assertTrue(phoneNumberValidationElement.isDisplayed());
        Assert.assertEquals(phoneNumberValidationElement.getText(),phoneNumberValidation);
    }


    @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
    public void invalidEmailTest(String user, String email, String pass, String invalidMailValidation) throws IOException {
        RegisterPage.fillForm(user, email, pass);
        WebElement invalidEmailValidationElement = getDriver().findElement(RegisterPage.INVALID_EMAIL_VALIDATION);
        //Helper.saveScreenShot("D://PVT/projects/autoScreen.png");
        Helper.saveScreenShot(RegisterPage.ACTIVE_EMAIL_VALIDATION,"D://PVT/projects/autoScreen.png");
        Assert.assertTrue(invalidEmailValidationElement.isDisplayed());
        Assert.assertEquals(invalidEmailValidationElement.getText(),invalidMailValidation);
    }


    @Test(dataProvider = "registrationData", dataProviderClass = DataProviders.class)
    public void alreadyBusyEmainTest(String user, String email, String pass, String alreadyTakenEmailValidation){
        RegisterPage.fillForm(user, email, pass);
        WebElement alreaTakenEmailValidationElement = getDriver().findElement(RegisterPage.ALREADY_TAKEN_EMAIL_VALIDATION);
        Assert.assertTrue(alreaTakenEmailValidationElement.isDisplayed());
        Assert.assertEquals(alreaTakenEmailValidationElement.getText(),alreadyTakenEmailValidation);
    }


}
