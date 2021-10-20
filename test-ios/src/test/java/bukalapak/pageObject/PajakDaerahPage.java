package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.PajakDaerahData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class PajakDaerahPage extends BasePage {

    public PajakDaerahPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void inputInvalidNOP() {
        typeAndEnterValueWithTimeOut("pajak_daerah_field_nop", dotenv.get("PBB_INVALID_NOP"));
        tapElement("pajak_daerah_lanjut_button");
    }

    public void isDisplayed() {
        if (isElementExist("pajak_daerah_oke_button")) {
            tapElement("pajak_daerah_oke_button");
        }

        verifyElementExist("pajak_daerah_field_province");
        verifyElementExist("pajak_daerah_field_city");
        verifyElementExist("pajak_daerah_field_year");
        HelperData.setLastActionPage(new PajakDaerahPage(iosDriver));
    }

    public void inputValidProvince() {
        tapElement("pajak_daerah_field_province");
        PajakDaerahData.setProvince(dotenv.get("PAJAK_PBB_PROVINSI"));
        waitForVisibilityOf("pajak_daerah_field_province_jakarta");
        tapElement("pajak_daerah_field_province_jakarta");
        swipeUpToElement("pajak_daerah_button_apply");
        tapElement("pajak_daerah_button_apply");
    }

    public void inputValidCity() {
        tapElement("pajak_daerah_field_city");
        swipeUpToElement("pajak_daerah_button_apply");
        tapElement("pajak_daerah_button_apply");
    }

    public void inputValidNOP() {
        PajakDaerahData.setCustomerNumber(dotenv.get("PBB_VALID_NOP"));
        typeAndEnterValueWithTimeOut("pajak_daerah_field_nop", dotenv.get("PBB_VALID_NOP"));
        tapElement("pajak_daerah_lanjut_button");
    }

    public void inputValidSPPT(String year) {
        tapElement("pajak_daerah_field_year");
        PajakDaerahData.setTaxYear(Integer.parseInt(year));
        selectPickerWheel(year);
        tapElement("pajak_daerah_sppt_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateDetailInfo() {
        validateCustomerData();
        validateDisplayed("pajak_rincian_tagihan_label");
        validateDisplayed("pajak_rincian_tagihan_pokok_label");
    }

    private void validateCustomerData() {
        waitForVisibilityOf("pajak_rincian_data_label", 30);
        validateElementWithText("pajak_rincian_data_nop_label", PajakDaerahData.getCustomerNumber());
        validateElementWithText("pajak_rincian_data_tax_type_label", "PBB");
        validateElementWithText("pajak_rincian_data_province_label", PajakDaerahData.getProvince());
        validateElementWithText("pajak_rincian_data_tax_year_label", String.valueOf(PajakDaerahData.getTaxYear()));
    }

    private void setCustomerNameInfo() {
        waitForVisibilityOf("pajak_rincian_data_customer_name_label", 15);
        PajakDaerahData.setCustomerName(getTextFromElement("pajak_rincian_data_customer_name_label"));
    }

    public void submitDetailInfo() {
        setCustomerNameInfo();
        swipeUpToElement("pajak_daerah_bayar_button");
        tapElement("pajak_daerah_bayar_button");
    }
}
