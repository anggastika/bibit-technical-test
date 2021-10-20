package steps;

import cucumber.api.java.en.*;
import static pages.Login.*;

public class LoginSteps {

    @When("user click entry point login")
    public void entryPointLogin() {
        clickEntryPointLogin();
    }

    @Then("^user input username \"(.*?)\" and password \"(.*?)\"$")
    public void fieldUsernameAndPassword(String username, String password) {
        String getUsername = dotenv.get(username);
        String getPassword = dotenv.get(password);
        inputUsernameAndPassword(getUsername, getPassword);
    }

    @Then("user click button login")
    public void loginButton() {
        clickLoginButton();
    }

    @Then("user skip popup avatar")
    public void skipPopUpAvatar() {
        skipPopUpProfileAvatar();
    }

    @Then("user click profile")
    public void goToProfile() {
        clickProfile();
    }

    @And("user check username \"(.*?)\"")
    public void validateUser(String username) {
        String getUsername = dotenv.get(username);
        validateProfileUsername(getUsername);
    }

    @And("user check alert message \"(.*?)\"")
    public void validateAlert(String error) {
        String getAlert = dotenv.get(error);
        checkAlert(getAlert);
    }

}
