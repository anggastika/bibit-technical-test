package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.STRIPEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class BelumDikirimPage extends BasePage {

    public BelumDikirimPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void typeOnSearchButton() {
        String numberTransaction = STRIPEData.getNomorTransaksi();
        waitForVisibilityOf("cari_tagihan_button");
        typeAndEnterValueWithTimeOut("cari_tagihan_button", numberTransaction);
    }

    public void clickOnBelumDikirim() {
        // tried another waitfor and locator still can not access
        waitFor(5);
        tapElement("proses_kirim_sekaligus_belum_dikirim_button");
    }

    public void validationProductName() {
        assertEquals(getElementValue("product_name_belum_dikirim_text"), STRIPEData.getProductName());
    }

    public void clickProses() {
        tapElement("proses_kirim_sekaligus_proses_button");
        // tried another waitfor and locator still can not access
        waitFor(15);
    }

    public void validationInvalidResiNumber() {
        assertEquals(getElementValue("input_resi_invalid"), "Shipping code terlalu pendek (minimum 8 karakter)");
        HelperData.setLastActionPage(new InputResiPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
    }
}
