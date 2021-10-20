package bukalapak.pageObject.vp.insurance;

import bukalapak.pageObject.BasePage;
import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by Aditya Putra on 13/10/20.
 */
public class AsuransiTambahanPage extends BasePage {

    public AsuransiTambahanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        changeContext().toWebview();
        verifyElementExist("ASURANSI_TAMBAHAN_LANDING_PAGE_TITLE_TEXT");
        verifyElementExist("ASURANSI_TAMBAHAN_LANDING_PAGE_CARA_KLAIM_BUTTON");
    }

    public void tapOnCheckbox() {
        tapElement("ASURANSI_TAMBAHAN_LANDING_PAGE_CHECKBOX");
    }

    public void tapOnLindungiSekarangButton() {
        tapElement("ASURANSI_TAMBAHAN_LANDING_PAGE_LINDUNGI_SEKARANG_BUTTON");
    }

    public void tapOnSnkButton() {
        waitForVisibilityOf("ASURANSI_TAMBAHAN_LANDING_PAGE_SNK_BUTTON", 10);
        tapElement("ASURANSI_TAMBAHAN_LANDING_PAGE_SNK_BUTTON");
    }

    public void tapOnBanner() {
        tapElement("ASURANSI_TAMBAHAN_LANDING_PAGE_BANNER_BUTTON");
    }

    public void validateManfaatSection(boolean isDisplayed) {
        verifyElementExist("ASURANSI_TAMBAHAN_MANFAAT_TITLE_TEXT");
        verifyElementExist( "ASURANSI_TAMBAHAN_GENERAL_ELEKTRONIK_TEXT");
        verifyElementExist( "ASURANSI_TAMBAHAN_GENERAL_BARANG_TEXT");
        verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_DIDUKUNG_TEXT");
        if (isDisplayed) {
            verifyElementExist("ASURANSI_TAMBAHAN_MANFAAT_ASURANSI_ELEKTRONIK_CONTENT_TAB_TEXT");
        } else {
            verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_BARANG_TEXT");
            verifyElementExist("ASURANSI_TAMBAHAN_MANFAAT_ASURANSI_BARANG_CONTENT_TAB_TEXT");
        }
    }

    public void tapOnCaraKlaimButton() {
        tapElement("ASURANSI_TAMBAHAN_LANDING_PAGE_CARA_KLAIM_BUTTON");
    }

    public void validateCaraKlaimSection(boolean isDisplayed) {
        verifyElementExist("ASURANSI_TAMBAHAN_CARA_KLAIM_TITLE");
        verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_ELEKTRONIK_TEXT");
        verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_BARANG_TEXT");
        verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_DIDUKUNG_TEXT");
        if (isDisplayed) {
            verifyElementExist("ASURANSI_TAMBAHAN_CARA_KLAIM_ELEKTRONIK_CONTENT_TAB_TEXT");
        } else {
            verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_BARANG_TEXT");
            verifyElementExist("ASURANSI_TAMBAHAN_CARA_KLAIM_BARANG_CONTENT_TAB_TEXT");
        }
        HelperData.setLastActionPage(new AsuransiTambahanPage(iosDriver));
    }

    public void tapOnTncButton() {
        tapElement("ASURANSI_TAMBAHAN_LANDING_PAGE_TNC_BUTTON");
    }

    public void validateTncSection(boolean isDisplayed) {
        verifyElementExist("ASURANSI_TAMBAHAN_TNC_ASURANSI_TITLE");
        verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_ELEKTRONIK_TEXT");
        verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_BARANG_TEXT");
        if (isDisplayed) {
            verifyElementExist("ASURANSI_TAMBAHAN_TNC_ASURANSI_BARANG_CONTENT_TAB_TEXT");
        } else {
            verifyElementExist("ASURANSI_TAMBAHAN_GENERAL_ELEKTRONIK_TEXT");
            verifyElementExist("ASURANSI_TAMBAHAN_TNC_ASURANSI_ELEKTRONIK_CONTENT_TAB_TEXT");
        }
        tapElement("ASURANSI_TAMBAHAN_TNC_ASURANSI_SAYA_MENGERTI_BUTTON");
        validateOnPage();
        HelperData.setLastActionPage(new AsuransiTambahanPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
