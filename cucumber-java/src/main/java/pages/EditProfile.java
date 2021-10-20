package pages;

import org.testng.Assert;

public class EditProfile extends BasePage {

    public static void clickEntryPointProfile() {
        clickButton("ENTRY_POINT_PROFILE");
    }

    public static void clickPublicProfile() {
        clickButtonByIndex("LIST_MENU_PROFILE", 0);

        String title = getText("TITLE_PUBLIC_PROFILE");
        String expectedTitle = dotenv.get("TITLE_PUBLIC_PROFILE");
        assertEquals(expectedTitle, title);
    }

    public static void clickEditButton() {
        clickButton("PUBLIC_PROFILE_EDIT_BUTTON");
    }

    public static void inputBiography(String biography) {
        inputText("EDIT_BIOGRAPHY_PUBLIC_PROFILE", biography);
    }

    public static void inputLocation(String location) {
        chooseDropdown("EDIT_LOCATION_PUBLIC_PROFILE", location);
    }

    public static void clickSaveEditProfile() {
        clickButton("SAVA_EDIT_PUBLIC_PROFILE");
        String alert = getText("ALERT_TEXT");
        assertEquals("Profile Updated", alert);
    }
}
