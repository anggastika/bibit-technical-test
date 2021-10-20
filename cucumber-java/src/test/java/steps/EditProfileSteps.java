package steps;

import cucumber.api.java.en.*;
import static pages.EditProfile.*;


import static pages.Login.clickEntryPointLogin;

public class EditProfileSteps {

    @When("user click entry point edit profile")
    public void entryPointProfile() {
        clickEntryPointProfile();
    }

    @Then("user click public profile")
    public void editProfile() {
        clickPublicProfile();
    }

    @Then("user click edit button in my public profile page")
    public void goToEdit() {
        clickEditButton();
    }

    @Then("user edit biography profile \"(.*?)\"")
    public void editBiography(String biography) {
        inputBiography(biography);
    }

    @Then("user choose location \"(.*?)\"")
    public void editLocation(String location) {
        inputLocation(location);
    }

    @Then("user click save button edit profile")
    public void clickSaveButtonEditProfile() {
        clickSaveEditProfile();
    }
}
