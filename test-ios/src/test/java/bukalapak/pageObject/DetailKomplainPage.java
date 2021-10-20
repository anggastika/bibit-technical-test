package bukalapak.pageObject;

import bukalapak.data.CSIData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class DetailKomplainPage extends BasePage {

    public DetailKomplainPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnDetailKomplainPage() {
        //wait for snackbar to disappear
        waitFor(5);
        if (isElementVisible("detail_komplain_oke_button", 10)) {
            tapOkeButton();
        }
        waitForVisibilityOf("detail_komplain_title", 5);
        waitForVisibilityOf("detail_komplain_info_komplain_tab", 5);
        waitForVisibilityOf("detail_komplain_diskusi_komplain_tab", 5);
        waitForVisibilityOf("detail_komplain_status_komplain_text1", 5);
        waitForVisibilityOf("detail_komplain_alasan_komplain_text1", 5);
        waitForVisibilityOf("detail_komplain_solusi_komplain_text1", 5);
        HelperData.setLastActionPage(new DetailKomplainPage(iosDriver));
    }

    public void userOnDetailKomplainBukaBantuan() {
        waitForVisibilityOf("detail_komplain_title", 5);
        waitForVisibilityOf("detail_komplain_kategori", 5);
        HelperData.setLastActionPage(new DetailKomplainPage(iosDriver));
    }

    public void validateNomorTransaksi(String transactionNumber) {
        String tmpTransactionNumber = transactionNumber;
        if (transactionNumber.equals("noTransaksi")) {
            tmpTransactionNumber = CSIData.getNomorTransaksi();
        }
        validateDisplayed(constructLocator("detail_komplain_nomor_transaksi", tmpTransactionNumber));
    }

    public void tapOkeButton() {
        tapElement("detail_komplain_oke_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnTransactionCard() {
        tapElement("detail_komplain_transaction_card");
    }

    public void tutupKomplain() {
        waitForVisibilityOf("detail_komplain_tutup_button", 15);
        tapElement("detail_komplain_tutup_button");
        typeAndEnterValue("tutup_komplain_alasan_textfield", "this is just a test");
        tapElement("tutup_komplain_send_button");
    }

    public void validateStatusKomplain(String statusKomplain) {
        //need to refresh page to load current state
        swipeToDirection(Direction.DOWN);
        assertEquals(statusKomplain, getTextFromElement("detail_komplain_status"));
    }

    public void validateNomorTagihan(String invoiceNumber) {
        validateDisplayed(constructLocator("detail_komplain_nomor_tagihan", invoiceNumber));
    }

    public void validateItemReturnStepper() {
        waitForVisibilityOf("detail_komplain_item_return_step_text");
        String firstStepText = getText("detail_komplain_item_return_step_text");
        firstStepText.equals("Tunggu pembeli mengirim barang yang ingin diganti");
    }

    public void validateStatusSolusi() {
        waitForVisibilityOf("detail_komplain_status_solusi_label",5);
        verifyElementDisplayed("detail_komplain_status_solusi_content");
    }

    public void validateResiItemReturn() {
        waitForVisibilityOf("detail_komplain_resi_text", 30);
        String resi = getText("detail_komplain_resi_text");
        resi.equals(dotenv.get("RETURN_COURIER") + " / " + dotenv.get("RETURN_RESI"));
        HelperData.setLastActionPage(new DetailKomplainPage(iosDriver));
    }

    public void inDetailKomplainRetur() {
        //wait for snackbar to disappear
        waitFor(5);
        if (isElementVisible("detail_komplain_oke_button", 10)) {
            tapOkeButton();
        }
        waitForVisibilityOf("detail_komplain_title", 3);
        waitForVisibilityOf("detail_komplain_solusi_label", 3);
        waitForVisibilityOf("detail_komplain_label", 3);
    }
}
