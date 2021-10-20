package bukalapak.pageObject;

import bukalapak.data.TransactionData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 17/12/18.
 */
public class PilihCicilanKredivoPage extends BasePage {

    public PilihCicilanKredivoPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPilihCicilanKredivoPage() {
        verifyElementExist("pilih_cicilan_page_title");
        HelperData.setLastActionPage(new PilihCicilanKredivoPage(iosDriver));
    }

    public void verifyTotalPaymentIsMatch() {
        assertTrue(isElementVisible("pilih_cicilan_kredivo_total_pembayaran_text", 10));
        assertEquals(TransactionData.getTotalPrice(), getText("pilih_cicilan_kredivo_total_pembayaran_text"));
    }

    public void choosePaymentMethod(String arg0) {
        tapElement("pilih_cicilan_kredivo_tipe_pembayaran_dropdown");
        waitForVisibilityOf("pilih_cicilan_kredivo_tunda_30_hari_option",10);
        tapElement("pilih_cicilan_kredivo_tunda_30_hari_option");
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickOnYaPunyaDialogButton() {
        if (isElementVisible("pilih_cicilan_kredivo_ya_button", 5)) {
            tapElement("pilih_cicilan_kredivo_ya_button");
        }
    }
}
