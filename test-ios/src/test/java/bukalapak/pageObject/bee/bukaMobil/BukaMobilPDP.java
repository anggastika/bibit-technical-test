package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.BeeData;
import bukalapak.data.BukaMobilData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilPDP extends BasePage {
    public BukaMobilPDP(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validatePDPBukaMobil() {
        waitForVisibilityOf("BUKAMOBIL_PDP_SLIDE_IMAGES", 10);
        verifyElementDisplayed("BUKAMOBIL_PDP_CAR_NAME");
        verifyElementDisplayed("BUKAMOBIL_PDP_CAR_MODEL");
        verifyElementDisplayed("BUKAMOBIL_PDP_CAR_PRICE");
        verifyElementDisplayed("BUKAMOBIL_PDP_CAR_LOCATION");
        verifyElementDisplayed("BUKAMOBIL_PDP_VARIANT_DROPDOWN");
        HelperData.setLastActionPage(new BukaMobilPDP(iosDriver));
    }

    public void selectTunaiRadioBtn() {
        swipeUpToElement("BUKAMOBIL_PDP_TUNAI_RADIO_BTN");
        tapElement("BUKAMOBIL_PDP_TUNAI_RADIO_BTN");
        validateValue().equals("true", getElementValue("BUKAMOBIL_PDP_TUNAI_RADIO_BTN"));
    }

    public void validateSchemaPaymentInfo(boolean isTunai) {
        if (isTunai) {
            validateNotDisplayed("BUKAMOBIL_PDP_KREDIT_DP_INFO");
            validateNotDisplayed("BUKAMOBIL_PDP_KREDIT_TENOR_INFO");
        } else {
            verifyElementDisplayed("BUKAMOBIL_PDP_KREDIT_DP_INFO");
            verifyElementDisplayed("BUKAMOBIL_PDP_KREDIT_TENOR_INFO");
            validateExist("BUKAMOBIL_PDP_KREDIT_GANTI_SKEMA_BUTTON");
        }
        verifyElementDisplayed(constructLocator("BUKAMOBIL_PDP_HARGA_INFO", BukaMobilData.getLokasi()));
        validateExist("BUKAMOBIL_PDP_BOOKING_FEE_INFO");
    }

    public void selectKreditRadioBtn() {
        swipeUpToElement("BUKAMOBIL_PDP_KREDIT_RADIO_BTN");
        tapElement("BUKAMOBIL_PDP_KREDIT_RADIO_BTN");
        validateValue().equals("true", getElementValue("BUKAMOBIL_PDP_KREDIT_RADIO_BTN"));
    }

    public void tapLanjutkanBtn() {
        tapElement("BUKAMOBIL_PDP_LANJUTKAN_BTN");
        HelperData.setLastActionPage(new BukaMobilPDP(iosDriver));
    }
}
