package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ChooseCategoryPage extends BasePage {


    public ChooseCategoryPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnChooseCategoryPage() {
        waitForVisibilityOf("choose_category_title_text", 10);
        verifyElementExist("choose_category_title_text");
        HelperData.setLastActionPage(new ChooseCategoryPage(iosDriver));
    }

    public void chooseCategory(String category) {
        tapElement(constructLocator("choose_category_radio_button", category));
        tapElement("choose_category_pilih_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
