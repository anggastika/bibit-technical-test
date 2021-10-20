package bukalapak.pageObject.vp.prepaid.paketdata;

import bukalapak.data.HelperData;
import bukalapak.data.vp.prepaid.PaketDataData;
import bukalapak.pageObject.vp.base.VpBasePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;

public class PaketDataLandingPage extends VpBasePage {

    public PaketDataLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    private List<IOSElement> getDenominations() {
        validateDenomination(true);

        List<IOSElement> denominations = getMultipleElement().withLocator("paket_data_denomination_text_title");

        LogUtil.info("Total denominations: " + denominations.size());
        return denominations;
    }

    public void validateOnPage() {
        waitForVisibilityOf("paket_data_image_banner", VERY_LONG_TIMEOUT);
        HelperData.setLastActionPage(new PaketDataLandingPage(iosDriver));
    }

    public void typeOnFieldNomorTelepon(String number) {
        PaketDataData.setPhoneNumber(number.replaceAll("[^0-9]", ""));
        typeAndEnterValue("paket_data_field_nomor_telepon", number);
    }

    public void validateDenomination(boolean isDisplayed) {
        if (isDisplayed) {
            waitForVisibilityOf("paket_data_denomination_text_title", VERY_LONG_TIMEOUT);
            validateExist("paket_data_tab_reguler");
            validateExist("paket_data_denomination_text_description");
            validateExist("paket_data_denomination_text_price");
            validateExist("paket_data_denomination_button_lihat_detail");
        } else {
            validateNotDisplayed("paket_data_denomination_text_title");
        }
    }

    public void tapOnTabRoaming() {
        tapElement("paket_data_tab_roaming");
    }

    public void tapOnTabReguler() {
        tapElement("paket_data_tab_reguler");
    }

    public void tapAvailableDenomination() {
        int totalDenomination = getDenominations().size();

        for (int i = 1; i < totalDenomination; i++) {
            tapOnDenomination(i);
            if (isElementExist("paket_data_detail_text_modal_title", VERY_SHORT_TIMEOUT)) break;
            else LogUtil.error("denomination is unavailable");
        }
    }

    public void tapOnDenomination(int order) {
        if (isElementVisible("paket_data_detail_text_modal_title", VERY_SHORT_TIMEOUT)) tapElement("paket_data_detail_button_close");

        int tempOrder = order;
        tempOrder--;

        swipeToLocator(constructLocator("paket_data_denomination_area_constructible", order));
        LogUtil.info("swiped to denomination: " + order);
        PaketDataData.setDenominationName(getText("paket_data_denomination_text_title", tempOrder));
        PaketDataData.setDenominationPrice(getText("paket_data_denomination_text_price", tempOrder));
        tapElement(constructLocator("paket_data_denomination_area_constructible", order));
    }

    public void validateDenominationDetails(boolean isDisplayed) {
        if (isDisplayed) {
            waitForVisibilityOf("paket_data_detail_text_modal_title", MEDIUM_TIMEOUT);
            validateElementWithText("paket_data_detail_text_denomination_title", PaketDataData.getDenominationName());
            validateElementWithText("paket_data_detail_text_denomination_price", PaketDataData.getDenominationPrice());
            validateExist("paket_data_detail_button_bayar");
        } else {
            validateNotDisplayed("paket_data_detail_text_modal_title");
        }
    }

    public void tapOnButtonBayar() {
        tapElement("paket_data_detail_button_bayar");
    }

    public void validateInputtedNumber(String number) {
        validateElementWithText("paket_data_field_nomor_telepon", number);
    }

    public void validateTextFieldValidation(String text) {
        waitForVisibilityOf("paket_data_text_field_validation");
        validateElementWithText("paket_data_text_field_validation", text);
    }
}
