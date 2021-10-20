package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.XPRData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaGlobalPage extends BasePage {

    public BukaGlobalPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnBukaGlobalPage() {
        waitForVisibilityOf("bukaglobal_title", 20);
        verifyElementExist("bukaglobal_title");
        verifyElementExist("bukaglobal_kirim_ke_Luar_Negeri_text");
        verifyElementExist("bukaglobal_kirim_barang_ke_text");
    }

    public void tapKirimKeNegara(String country) {
        swipeDownToElement("bukaglobal_kirim_barang_ke_text");
        tapElement("bukaglobal_pilih_negara_dropdown");
        tapElement(constructLocator("bukaglobal_negara_text", country));
    }

    public void tapLihatSemua() {
        waitForVisibilityOf("bukaglobal_lihat_semua_button", 20);
        swipeDownToElement("bukaglobal_lihat_semua_button");
        tapElement("bukaglobal_lihat_semua_button");
    }

    public void validateCategoryBukaGlobal(String category) {
        verifyElementExist(constructLocator("bukaglobal_category_title", category));
        validateValue().equals(XPRData.getCategoryProduct(), getTextFromElement(constructLocator("bukaglobal_category_title", category)));
        HelperData.setLastActionPage(new BukaGlobalPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
