package bukalapak.pageObject.vp.prepaid.uangelektronik;

import bukalapak.data.HelperData;
import bukalapak.data.UserData;
import bukalapak.data.vp.prepaid.UangElektronikData;
import bukalapak.pageObject.vp.base.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assume;
import org.openqa.selenium.NoSuchElementException;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by agustriawantiningtyas on 02/06/20.
 */

public class UangElektronikLandingPage extends VpBasePage {

    public UangElektronikLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    private void tapOnFieldJenisKartu() {
        // intermittent not displayed
        for (int i = 0; i <3; i++ ) {
            tapElement("uang_elektronik_dropdown_jenis_kartu");
            if (!isElementVisible("uang_elektronik_dropdown_jenis_kartu")) break;
        }
    }

    private void validateAutoFill(String number) {
        if (number.equals(dotenv.get("UANG_ELEKTRONIK_TRANSACTION_CARD_BRI"))) {
            waitFor(1); // wait for autofill
            tapOnFieldJenisKartu();
            validateValue().equalsTrue(Boolean.parseBoolean(getElementAttributeValue("uang_elektronik_checkbox_bri", "value")), "`Jenis Kartu` not autofilled!");
            tapElement("uang_elektronik_button_terapkan");
        }
    }

    public void validateOnPage() {
        waitForVisibilityOf("uang_elektronik_dropdown_jenis_kartu", MEDIUM_TIMEOUT);
        verifyElementDisplayed("uang_elektronik_field_nomor_kartu");
        HelperData.setLastActionPage(new UangElektronikLandingPage(iosDriver));
    }

    public void typeOnFieldNomorKartu(String number) {
        UangElektronikData.setCardNumber(number.replaceAll("[^0-9]", ""));
        typeAndEnterValue("uang_elektronik_field_nomor_kartu", number);
        validateAutoFill(number);
    }

    public void setOnFieldJenisKartu(String partner) {
        // reduce running time, already validate on typing method
        if (!UangElektronikData.getCardNumber().equals(dotenv.get("UANG_ELEKTRONIK_TRANSACTION_CARD_BRI"))) {
            tapOnFieldJenisKartu();
            tapElement("uang_elektronik_checkbox_" + partner.toLowerCase());
            tapElement("uang_elektronik_button_terapkan");
        }
    }

    public void validateFieldNomorKartu(String number) {
        String actualCardNumber = getText("uang_elektronik_field_nomor_kartu").replaceAll("[^0-9]", "");
        validateValue().equals(number, actualCardNumber);
    }

    public void validateFieldJenisKartu(String number) {
        String prefix = number.substring(0, 4);
        String partner;

        if (prefix.equals("6032")) partner = "mandiri";
        else if (prefix.equals("6013")) partner = "bri";
        else partner = "bni";

        tapOnFieldJenisKartu();
        validateValue().equalsTrue(Boolean.parseBoolean(getElementAttributeValue("uang_elektronik_checkbox_" + partner, "value")));
        tapElement("uang_elektronik_button_terapkan");
    }

    public void validateDenomination(boolean isDisplayed) {
        if (isDisplayed) {
            waitForVisibilityOf("uang_elektronik_denomination_text_name", VERY_LONG_TIMEOUT);
            verifyElementExist("uang_elektronik_denomination_text_price");
            verifyElementExist("uang_elektronik_text_pilih_nominal");
            verifyElementExist("uang_elektronik_text_maksimal_saldo");
        } else verifyElementNotExist("uang_elektronik_denomination_text_name");
    }

    public void tapOnDenomination(int order) {
        int priceOrder = order; // handle static analyzer (dont direct change to parameter)
        priceOrder--;
        int totalDenomination = getElements("uang_elektronik_denomination_text_name").size();
        int nameOrder = order == 1 ? totalDenomination - 1 : priceOrder - 1; // name & price element's order not equals

        UangElektronikData.setDenominationName(getTextFromElement("uang_elektronik_denomination_text_name", nameOrder));
        UangElektronikData.setDenominationPrice(getTextFromElement("uang_elektronik_denomination_text_price", priceOrder));
        tapElements("uang_elektronik_denomination_text_name", nameOrder);
    }

    public void validateTotalTagihan() {
        swipeUpToElement("uang_elektronik_button_bayar");
        try {
            validateElementWithText("uang_elektronik_text_total_tagihan", UangElektronikData.getDenominationPrice());
        } catch (NoSuchElementException e) {
            // intermittent screen reloading
            waitFor(1);
            validateElementWithText("uang_elektronik_text_total_tagihan", UangElektronikData.getDenominationPrice());
        }
    }

    public void skipUpdateBalanceInfo() {
        if (!UangElektronikData.isInfoSkipped() && UserData.isLoggedIn()) {
            tapElement("uang_elektronik_pop_up_checkbox_jangan_tampilkan");
            tapElement("uang_elektronik_button_lanjutkan");
            UangElektronikData.setInfoSkipped(true);
        }
    }

    public void tapOnButtonBayar() {
        tapElement("uang_elektronik_button_bayar");
        skipUpdateBalanceInfo();
        // Skip test if got limited
        if (UserData.isLoggedIn()) Assume.assumeFalse(isElementExist("uang_elektronik_snackbar_text_limited", VERY_SHORT_TIMEOUT));
    }

    public void tapOnButtonCaraUpdate() {
        tapElement("uang_elektronik_menu_cara_update_saldo");
    }

    public void validateRedirectedToWebview() {
        waitForVisibilityOf("uang_elektronik_webview_text_title", LONG_TIMEOUT);
        validateToolbarTitle("Update Saldo");
    }
}
