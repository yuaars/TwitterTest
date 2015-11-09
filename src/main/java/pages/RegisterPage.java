package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static helpers.DriverSingleton.getDriver;
import static helpers.Locators.get;

public class RegisterPage {

    public static final By USER_FULL_NAME_FIELD = get("signUp.userFullNameField");
    public static final By EMAIL_FIELD = get("signUp.EmailField");
    public static final By PASSWORD_FIELD = get("signUp.PasswordField");
    public static final By SIGN_UP_BUTTON = get("signUp.signUpButton");
    public static final By ALLERT = get("signUp.ActiveValidationMessages");
    public static final By ACTIVE_EMAIL_VALIDATION = get("signUp.emailValidationMessage");
    public static final By ACTIVE_PASS_VALIDATION = get("signUp.passValidationMessage");


    public static void signUp(String user, String email, String pass) {
        fillForm(user, email, pass);
        getDriver().findElement(SIGN_UP_BUTTON).click();
    }

    public static void fillForm(String user, String email, String pass) {
        getDriver().findElement(USER_FULL_NAME_FIELD).sendKeys(user);
        getDriver().findElement(EMAIL_FIELD).sendKeys(email);
        getDriver().findElement(PASSWORD_FIELD).sendKeys(pass);
    }

    public static List<WebElement> getValidationMessages(){
        return getDriver().findElements(ALLERT);
    }
}
