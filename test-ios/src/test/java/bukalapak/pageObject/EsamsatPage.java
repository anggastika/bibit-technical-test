package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriverException;

public class EsamsatPage extends PostpaidBasePage {

    public EsamsatPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/bl/esamsat");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void verifyPageDisplayed() {
        verifyElementExist("postpaid_esamsat_no_rangka_field");
        verifyElementExist("postpaid_esamsat_no_ktp_field");
        verifyElementExist("postpaid_esamsat_next_button");
        HelperData.setLastActionPage(new EsamsatPage(iosDriver));
    }

    public void chooseArea() {
        waitForVisibilityOf("postpaid_esamsat_choose_area_dropdown", 10);
        tapElement("postpaid_esamsat_choose_area_dropdown");
        tapElement("postpaid_esamsat_terapkan_button");
    }

    public void typeVehicleNumber(String vehicleNumber) {
        String vehicleFrame;

        if (vehicleNumber.equals("valid")) {
            vehicleFrame = generateEsamsatRangkaNumber("ESAMSAT_RANGKA");
        } else {
            vehicleFrame= fakerRangkaNumber();
        }
        PostpaidData.setRangkaNumber(vehicleFrame);
        typeAndEnterValueWithTimeOut("postpaid_esamsat_no_rangka_field", vehicleFrame );
    }

    public void typeNIKNumber(String nikNumber) {
        typeAndEnterValueWithTimeOut("postpaid_esamsat_no_ktp_field", nikNumber);
    }

    public void submitDataEsamsat() {
        tapElement("postpaid_esamsat_next_button");
    }

    public void validateDetailInfo() {
        waitForVisibilityOf("postpaid_esamsat_rincian_tagihan", 30);
        validateDisplayed("postpaid_esamsat_pokok_pajak");
        validateDisplayed("postpaid_esamsat_denda_pajak");
        validateDisplayed("postpaid_esamsat_total_tagihan_field");
    }

    public void clickBayarButton() {
        swipeUpToElement("postpaid_esamsat_bayar_button");
        tapElement("postpaid_esamsat_bayar_button");
    }

    public void validateOnCheckoutPage() {
        waitForVisibilityOf("postpaid_esamsat_checkout_page_title", 30);
        validateDisplayed("postpaid_esamsat_checkout_page_title");
    }

    public void validateErrorPage() {
        waitForVisibilityOf("postpaid_esamsat_error_page", 15);
        validateDisplayed("postpaid_esamsat_error_page");
        HelperData.setLastActionPage(new EsamsatPage(iosDriver));
    }

    public void goToWidget() {
        tapElement("postpaid_esamsat_tnc_widget_button");
    }

    public void goToLink() {
        tapElement("postpaid_esamsat_tnc_link_button");
    }

    public void validateTnCPage() {
        waitForVisibilityOf("postpaid_esamsat_panduan_bayar", 10);
        validateDisplayed("postpaid_esamsat_panduan_pengesahan");
        tapElement("postpaid_esamsat_panduan_pengesahan");
        validateDisplayed("postpaid_esamsat_syarat_dan_ketentuan");
        tapElement("postpaid_esamsat_syarat_dan_ketentuan");
        HelperData.setLastActionPage(new EsamsatPage(iosDriver));
    }
}
