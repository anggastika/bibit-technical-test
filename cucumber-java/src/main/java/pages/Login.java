package pages;

import data.LoginData;
import org.testng.Assert;

public class Login extends BasePage {

    public static void clickEntryPointLogin() {
        clickButton("ENTRY_POINT_LOGIN");
    }

    public static void inputUsernameAndPassword(String username, String password) {
        inputText("FORM_INPUT_USERNAME", username);
        inputText("FORM_INPUT_PASSWORD", password);
    }

    public static void clickLoginButton() {
        clickButton("LOGIN_BUTTON");
    }

    public static void skipPopUpProfileAvatar() {
        clickButton("POPUP_CANCEL_BUTTON");
    }

    public static void clickProfile() {
        clickButton("PROFILE_BUTTON");
        getAccountUsername();
    }

    public static void validateProfileUsername(String expectedUsername) {
        assertEquals(expectedUsername, LoginData.getUsername());
    }

    public static void getAccountUsername() {
        String username = getText("USERNAME_TEXT");
        LoginData.setUsername(username);
    }

    public static void checkAlert(String error) {
        String alert = getText("ALERT_TEXT");
        assertEquals(error, alert);
    }

}
