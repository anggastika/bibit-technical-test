package bukalapak.pageObject.vp.prepaid.listrikprabayar;

import bukalapak.data.HelperData;
import bukalapak.data.vp.prepaid.ListrikPrabayarData;
import bukalapak.pageObject.vp.base.VpBasePage;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assume;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ferawati Hartanti on 04/06/20.
 */
public class ListrikPrabayarLandingPage extends VpBasePage {

    public ListrikPrabayarLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    private boolean isDownTime() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(iosDriver.getDeviceTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int timeInHour = Integer.parseInt(new SimpleDateFormat("HH").format(date));
        int timeInMin = Integer.parseInt(new SimpleDateFormat("mm").format(date));
        return timeInHour > 22 || (timeInHour < 1 && timeInMin < 59);
    }

    public void checkDownTime() {
        Assume.assumeFalse("Product not available at 23-1", isDownTime()); // skip test if running at 23-1
    }

    public void validateOnPage() {
        if (isDownTime()) {
            if (isElementVisible("listrik_prabayar_button_lanjut", MEDIUM_TIMEOUT)) skipOnboarding();
            // else do nothing
        } else skipOnboarding();
        waitForVisibilityOf("listrik_prabayar_header", SHORT_TIMEOUT);
        HelperData.setLastActionPage(new ListrikPrabayarLandingPage(iosDriver));
    }

    public void skipOnboarding() {
        if (!ListrikPrabayarData.isOnboarded()) {
            waitForElementClickable("listrik_prabayar_button_lanjut", MEDIUM_TIMEOUT);
            tapElement("listrik_prabayar_button_lanjut");
            ListrikPrabayarData.setOnboarded(true);
        }
    }

    public void validateDenomination() {
        validateExist("listrik_prabayar_text_denomination_name", LONG_TIMEOUT);
    }

    public void typeOnFieldIdPelanggan(String customerId) {
        ListrikPrabayarData.setCustomerId(customerId.replaceAll("[^0-9]", ""));
        swipeToLocator("listrik_prabayar_field_id_pelanggan", Direction.DOWN);
        typeAndEnterValue("listrik_prabayar_field_id_pelanggan", customerId);
    }

    public void validateFieldIdPelanggan( String customerId) {
        validateElementWithText("listrik_prabayar_field_id_pelanggan", customerId);
    }

    public void tapOnDenomination(int order) {
        int priceOrder = order; // handle static analyzer (dont direct change to parameter)
        priceOrder--;
        int totalDenomination = getElements("listrik_prabayar_text_denomination_name").size();
        int nameOrder = order == 1 ? totalDenomination - 1 : priceOrder - 1; // name & price element's order not equals

        ListrikPrabayarData.setDenominationName(getText("listrik_prabayar_text_denomination_name", nameOrder));
        ListrikPrabayarData.setDenominationPrice(getText("listrik_prabayar_text_denomination_price", priceOrder));
        tapElements("listrik_prabayar_text_denomination_name", nameOrder);
    }

    public void validateRincianInformasi(boolean isDisplayed) {
        if (isDisplayed) {
            validateExist("listrik_prabayar_text_rincian_informasi", VERY_LONG_TIMEOUT);
            ListrikPrabayarData.setCustomerId(getText("listrik_prabayar_rincian_informasi_text_id_pelanggan"));
            ListrikPrabayarData.setMeterNumber(getText("listrik_prabayar_rincian_informasi_text_no_meter"));
            ListrikPrabayarData.setFullName(getText("listrik_prabayar_rincian_informasi_text_nama_lengkap"));
            ListrikPrabayarData.setPower(getText("listrik_prabayar_rincian_informasi_text_tarif_daya"));
            validateElementWithText("listrik_prabayar_rincian_informasi_text_nilai_token", ListrikPrabayarData.getDenominationName());
            validateElementWithText("listrik_prabayar_total_text_total", ListrikPrabayarData.getDenominationPrice());
        } else {
            validateNotDisplayed("listrik_prabayar_text_rincian_informasi");
        }
    }

    public void tapOnButtonBayar() {
        tapElement("listrik_prabayar_button_bayar");
    }
}
