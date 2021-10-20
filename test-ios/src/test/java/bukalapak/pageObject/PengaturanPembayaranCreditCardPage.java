package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PengaturanPembayaranCreditCardPage extends BasePage {

    public PengaturanPembayaranCreditCardPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPengaturanPembayaranCreditCardPage() {
        verifyElementExist("pengaturan_pembayaran_credit_card_title_text");
    }

    public void tapOnTambahKartuButton() {
        waitForVisibilityOf("pengaturan_pembayaran_credit_card_tambah_kartu_button", 15);
        tapElement("pengaturan_pembayaran_credit_card_tambah_kartu_button");
    }

    public void checkCreditCard(boolean condition, String number) {
        if (condition) {
            waitForVisibilityOf("pengaturan_pembayaran_credit_card_title_text",15);
            validateNotDisplayed(constructLocator("pengaturan_pembayaran_credit_card_number_text", number));
        } else {
            validateDisplayed(constructLocator("pengaturan_pembayaran_credit_card_number_text", number));
        }
    }

    public void tapOnSetUtamaSecondCCButton() {
        waitForVisibilityOf("pengaturan_pembayaran_credit_card_second_card_number_text", 15);
        TransactionData.setNomorKartu(getText("pengaturan_pembayaran_credit_card_second_card_number_text"));
        tapElement(constructLocator("pengaturan_pembayaran_credit_card_set_utama_second_card_button", TransactionData.getNomorKartu()));
        waitForVisibilityOf("pengaturan_pembayaran_credit_card_successfully_set_kartu_utama_text");
    }

    public void validateKartuUtama() {
        waitForVisibilityOf("pengaturan_pembayaran_credit_card_kartu_utama_button", 15);
        validateDisplayed(constructLocator("pengaturan_pembayaran_credit_card_kartu_utama_button", TransactionData.getNomorKartu()));
    }

    public void tapOnHapusButton(String number) {
        validateDisplayed(constructLocator("pengaturan_pembayaran_credit_card_number_text", number));
        tapElement(constructLocator("pengaturan_pembayaran_credit_card_hapus_button", number));
        tapElement("popup_alert_ya_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
