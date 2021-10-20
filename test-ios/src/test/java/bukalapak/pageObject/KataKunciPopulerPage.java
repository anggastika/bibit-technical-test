package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KataKunciPopulerPage extends BasePage {
    public KataKunciPopulerPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnKataKunciPopulerPage() {
        waitForVisibilityOf("kata_kunci_populer_periode_txt", 5);
        verifyElementExist("kata_kunci_populer_title");
        verifyElementExist("kata_kunci_populer_kata_kunci_list");
        HelperData.setLastActionPage(new KataKunciPopulerPage(iosDriver));
    }

    public void verifyAmountListKataKunci(int amountListKataKunci) {
        validateValue().equalsTrue(isElementVisible("kata_kunci_populer_no_txt"));
        validateValue().equalsTrue(isElementVisible("kata_kunci_populer_katakunci_txt"));
        validateValue().equalsTrue(isElementVisible("kata_kunci_populer_pencarian_txt"));
        validateValue().equals(amountListKataKunci, getElements("kata_kunci_populer_kata_kunci_list").size());
        validateValue().equals(amountListKataKunci, getElements("kata_kunci_populer_kata_kunci_jmlh_pencarian").size());
        HelperData.setLastActionPage(new KataKunciPopulerPage(iosDriver));
    }

    public void verifyCategoryField(String categoryContainText) {
        waitForVisibilityOf("kata_kunci_populer_category_dropdown", 10);
        validateValue().equalsTrue(getElementValue("kata_kunci_populer_category_dropdown").contains(categoryContainText));
        HelperData.setLastActionPage(new KataKunciPopulerPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
