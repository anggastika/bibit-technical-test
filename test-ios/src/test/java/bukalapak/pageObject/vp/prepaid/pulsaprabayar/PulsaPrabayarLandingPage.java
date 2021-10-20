package bukalapak.pageObject.vp.prepaid.pulsaprabayar;

import bukalapak.data.HelperData;
import bukalapak.data.UserData;
import bukalapak.data.vp.prepaid.PulsaPrabayarData;
import bukalapak.pageObject.vp.base.VpBasePage;
import bukalapak.utils.Constants;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;

/**
 * Created by sekarayukarindra on 24/02/20.
 */
public class PulsaPrabayarLandingPage extends VpBasePage {

    public PulsaPrabayarLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    private List<IOSElement> getDenominations() {
        validateDenomination(true);

        List<IOSElement> denominations = getMultipleElement().withLocator("pulsa_prabayar_denomination_name");

        LogUtil.info("Total denominations: " + denominations.size());
        return denominations;
    }

    private void skipOnboarding() {
        if (!PulsaPrabayarData.isOnboarded()) {
            if (!UserData.isLoggedIn()) typeOnFieldPhoneNumber("082257365724");
            waitForVisibilityOf("onboarding_lanjut_button", MEDIUM_TIMEOUT);
            tapElement("onboarding_lanjut_button");
            PulsaPrabayarData.setOnboarded(true);
        }
    }

    public void validateOnPage() {
        skipOnboarding();
        waitForVisibilityOf("pulsa_prabayar_number_field", 25);
        HelperData.setLastActionPage(new PulsaPrabayarLandingPage(iosDriver));
    }

    public void typeOnFieldPhoneNumber(String number) {
        swipeDownToElement("pulsa_prabayar_number_field");
        PulsaPrabayarData.setPhoneNumber(number.replaceAll("[^0-9]", ""));
        typeAndEnterValue("pulsa_prabayar_number_field", number);
    }

    public void tapOnDenomination(int order) {
        int priceOrder = order; // handle static analyzer (dont direct change to parameter)
        priceOrder--;

        swipeDownToElement("pulsa_prabayar_number_field");
        waitForVisibilityOf("pulsa_prabayar_denomination_name", VERY_LONG_TIMEOUT);

        int totalDenomination = getElements("pulsa_prabayar_denomination_name").size();
        int nameOrder = order == 1 ? totalDenomination - 1 : priceOrder - 1; // name & price element's order not equals

        PulsaPrabayarData.setPackageName(getText("pulsa_prabayar_denomination_name", nameOrder));
        PulsaPrabayarData.setPackagePrice(getText("pulsa_prabayar_denomination_price", priceOrder));

        LogUtil.info(PulsaPrabayarData.getPackageName());
        LogUtil.info(PulsaPrabayarData.getPackagePrice());
        tapElement(constructLocator("pulsa_prabayar_dynamic_denomination_price", order), Direction.UP);
    }

    public void tapOnBayarButton() {
        if (!isElementVisible("checkout_non_marketplace_alchemy_title_text",5)) {
            tapElement("pulsa_prabayar_bayar_button", Direction.UP);
        }
    }

    public void validateRincianInformasi(boolean isDisplayed) {
        if (isDisplayed) {
            verifyElementDisplayed("pulsa_prabayar_text_rincian_informasi_no_handphone");
            String phoneNumber = getText("pulsa_prabayar_text_rincian_informasi_no_handphone");

            validateValue().equals(phoneNumber.replaceAll("-", "").replaceAll(" ", ""), PulsaPrabayarData.getPhoneNumber());
            validateElementWithText("pulsa_prabayar_text_rincian_informasi_pulsa", PulsaPrabayarData.getPackageName());
            validateElementWithText("pulsa_prabayar_text_total", PulsaPrabayarData.getPackagePrice());
        } else {
            validateNotExist("pulsa_prabayar_text_total", 5);
        }
    }

    public void validateFieldPhoneNumber(String number) {
        validateElementWithText("pulsa_prabayar_number_field", number);
    }

    public void validateTextFieldValidation(String text) {
        waitForVisibilityOf("pulsa_prabayar_text_field_validation");
        validateElementWithText("pulsa_prabayar_text_field_validation", text);
    }

    public void importContactNumber() {
        PulsaPrabayarData.setPhoneNumber(getElementValue("pulsa_prabayar_number_field"));
        tapElement("pulsa_prabayar_contact_icon");
        tapElement("pulsa_prabayar_pilih_kontak_menu");
        tapElement("pulsa_prabayar_contact_name");
        tapElement("pulsa_prabayar_first_contact_phone_number");
    }

    public void importProfileNumber() {
        PulsaPrabayarData.setPhoneNumber(getElementValue("pulsa_prabayar_number_field"));
        tapElement("pulsa_prabayar_contact_icon");
        tapElement("pulsa_prabayar_nomor_saya_menu");
    }

    public void validateDenomination(boolean isDisplayed) {
        if (isDisplayed) {
            waitForVisibilityOf("pulsa_prabayar_denomination_name", MEDIUM_TIMEOUT);
            verifyElementDisplayed("pulsa_prabayar_denomination_name");
        } else validateNotDisplayed("pulsa_prabayar_denomination_name");
    }

    public void selectDenom(String denom) {
        waitForVisibilityOf(constructLocator("vp_general_dynamic_label", denom), 30);
        tapElement(constructLocator("vp_general_dynamic_label", denom));
    }

    public void tapAvailableDenomination() {
        int totalDenomination = getDenominations().size();

        for (int i = 1; i < totalDenomination; i++) {
            tapOnDenomination(i);
            if (isElementExist("pulsa_prabayar_text_rincian_informasi_no_handphone", VERY_SHORT_TIMEOUT)) break;
            else LogUtil.error("denomination is unavailable");
        }
    }
}
