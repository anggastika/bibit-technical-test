package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.vp.postpaid.PajakDaerahData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PajakDaerahInvoiceDetailPage extends BasePage {

    public PajakDaerahInvoiceDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void validatePajakDaerahInvoice(String province) {
        switch (province) {
            case "Jawa Barat":
                swipeDownToElement("pajak_daerah_detail_year");
                verifyElementExist("pajak_daerah_detail_nop");
                verifyElementExist("pajak_daerah_detail_province");
                verifyElementExist("pajak_daerah_detail_city");
                verifyElementExist("pajak_daerah_detail_name");
                verifyElementExist("pajak_daerah_detail_address");
                verifyElementExist("pajak_daerah_detail_year");
                break;
            case "Sumatera Utara":
                swipeDownToElement("pajak_daerah_detail_year_sumut");
                verifyElementExist("pajak_daerah_detail_nop_sumut");
                verifyElementExist("pajak_daerah_detail_province_sumut");
                verifyElementExist("pajak_daerah_detail_city_sumut");
                verifyElementExist("pajak_daerah_detail_name_sumut");
                verifyElementExist("pajak_daerah_detail_address_sumut");
                verifyElementExist("pajak_daerah_detail_year_sumut");
                break;
            default:
                LogUtil.info("Provinsi tidak ditemukan");
                break;
        }

        verifyElementExist("pajak_daerah_detail_type");
        HelperData.setLastActionPage(new PajakDaerahInvoiceDetailPage(iosDriver));
    }

    public void validateCustomerData() {
        swipeUpToElement("pajak_daerah_invoice_tax_year_text");
        validateElementWithText("pajak_daerah_invoice_customer_number", PajakDaerahData.getCustomerNumber());
        validateElementWithText("pajak_daerah_invoice_tax_type_text", "PBB");
        validateElementWithText("pajak_daerah_invoice_province", PajakDaerahData.getProvince());
        validateElementWithText("pajak_daerah_invoice_customer_name", PajakDaerahData.getCustomerName());
        validateElementWithText("pajak_daerah_invoice_tax_year_text", String.valueOf(PajakDaerahData.getTaxYear()));
        HelperData.setLastActionPage(new PajakDaerahInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
