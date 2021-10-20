package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class EditProfilPage extends BasePage {
    public EditProfilPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnEditProfilPage() {
        verifyElementExist("edit_profil_title", 5, "Edit Profil page not displays title page");
        HelperData.setLastActionPage(new EditProfilPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void typeOnNameTextField(String name) {
        typeAndEnterValue("edit_profil_name_textfield", name);
    }

    public void typeOnNotesTextField(String notes) {
        waitForVisibilityOf("edit_profil_notes_textfield", 5);
        typeAndEnterValue("edit_profil_notes_textfield", notes);
    }

    public void typeOnDescriptionTextField(String description) {
        typeAndEnterValue("edit_profil_description_textfield", description);
    }

    public void tapSimpanButtton() {
        swipeUp();
        tapElement("edit_profil_simpan_button", 5);
        validateNotExist("edit_profil_simpan_button", 10);
    }

    public void validateName(String name) {
        validateElementContainsText("edit_profil_name_textfield", name);
    }

    public void validateNotes(String notes) {
        validateElementContainsText("edit_profil_notes_textfield", notes);
    }

    public void validateDescription(String description) {
        validateElementContainsText("edit_profil_description_textfield", description);
    }

}
