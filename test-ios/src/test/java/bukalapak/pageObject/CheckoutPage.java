package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckoutPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(CheckoutPage.class);
    private static boolean alchemyStatus = false;

    public CheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public boolean getStatusAlchemy() {
        return alchemyStatus;
    }

    private synchronized void setAlchemyStatus(boolean alchemystatus) {
        alchemyStatus = alchemystatus;
    }

    public void userOnCheckoutPage() {
        allowPopup();
        if (isElementVisible("checkout_alchemy_page_title")) {
            verifyElementExist("checkout_alchemy_page_title");
            //alchemyStatus = true;
            setAlchemyStatus(true);
        } else {
            waitForVisibilityOf("checkout_title", 30);
            //alchemyStatus = false;
            setAlchemyStatus(false);
        }
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void choosePaymentMethod(String payment_method) {
        if (payment_method.equals("DANA")) {
            tapElement("checkout_metode_bayar_name");
            tapElement("checkout_page_dana_option_card_payment_version");

            if (isElementVisible("use_this_method_button")) {
                tapElement("use_this_method_button");
            }
        } else {
            String payment_option = "checkout_page_" + payment_method.toLowerCase() + "_option";

            swipeDownToElement(payment_option);
            tapElement(payment_option);
        }
    }

    public void tapOnPayHalfWithBukaDompet(String mixPayment) {
        String balance = "2500";

        if (mixPayment.equals("can")) {
            try {
                tapElement("mix_payment_bukadompet");
                typeAndEnterValueWithTimeOut("bukadompet_balance", balance);
            } catch (Exception e) {
                LogUtil.info("Akun pengguna tidak memiliki saldo BukaDompet");
            }
        } else if (mixPayment.equals("can not")) {
            try {
                verifyElementNotExist("mix_payment_bukadompet");
            } catch (AssertionError e) {
                LogUtil.info("Akun pengguna masih memiliki saldo BukaDompet");
            }
        }
    }

    public void tapPayButton() {
        try {
            swipeDownToElement("payment_button");
            tapElement("payment_button");
        } catch (Exception e) {
            LogUtil.info("DANA balance is not enough");
        }
    }

    public void chooseNewPaymentMethod(String payment_method, String payment_choose) {
        tapElement("checkout_metode_bayar_icon");
        waitFor(3);
        String payment_option = "checkout_page_" + payment_method.toLowerCase() + "_option";

        swipeDownToElement(payment_option);
        tapElement(payment_option);

        waitFor(2);
        String payment_choosen = "checkout_page_" + payment_choose.toLowerCase() + "_choosen";
        tapElement(payment_choosen);
    }

    public void submitForm() {
        swipeDownToElement("checkout_payment_button");
        assertTrue(TransactionData.getTotalPrice().equals(getElementValue("checkout_total_price_text")));
        tapElement("checkout_payment_button");
    }

    public Double getBiayaAdmin() {
        swipeToLocator("checkout_page_biaya_admin_text");
        String biayaAdmin = getTextFromElement("checkout_page_biaya_admin_fee").replace(".", "").replace("Rp", "");
        return Double.parseDouble(biayaAdmin);
    }

    public void chooseAnotherPaymentMethod() {
        try {
            swipeDownToElement("choose_other_method_button");
            tapElement("choose_other_method_button");
        } catch (Exception e) {
            swipeToDirection(Direction.DOWN);
            if (isElementVisible("payment_next_button")) {
                tapElement("payment_next_button");
            }
        }
    }

    public void topUpDana() {
        tapElement("checkout_page_topup_dana");
    }

    public void tapDropshipperCheckbox() {
        waitFor(4);
        tapElement("dropshipper_checkbox");
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void checkingDANAUnbindCheckout() {
        verifyElementExist("checkout_hubungkan_akun_id");
        tapElement("checkout_markeplcace_back_btn_id");
        //alchemyStatus = true;
        setAlchemyStatus(true);
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void verifyDropshipperPopup() {
        assertTrue(isElementVisible("dropshipper_text"));
        assertTrue(isElementVisible("nama_pengirim_text"));
        assertTrue(isElementVisible("no_telp_pengirim_text"));
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void dropshipperAs(String dropshipperType) {
        String tmpDropshipperType = dropshipperType.toUpperCase();
        try {
            typeAndEnterValueWithTimeOut("nama_pengirim_field", TestInstrument.dotenv.get(tmpDropshipperType + "_DROPSHIPPER_NAME"));
            typeAndEnterValueWithTimeOut("no_telp_pengirim_field", TestInstrument.dotenv.get(tmpDropshipperType + "_DROPSHIPPER_DETAIL"));
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Type of " + tmpDropshipperType + " is not listed in data.env file.");
        }
        tapElement("dropshipper_simpan_button");
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void tapCatanLink() {
        waitFor(2);
        swipeDownToElement("checkout_voucher_text");
        tapElement("catatan_link");
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void verifyCatatanPopup() {
        assertTrue(isElementVisible("catatan_for_pelapak_text"));
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void writeCatatanForSeller(String notes) {
        String tmpNotes = notes.toUpperCase();
        try {
            typeAndEnterValueWithTimeOut("catatan_for_pelapak_field", TestInstrument.dotenv.get(tmpNotes + "_NOTES"));
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Type of " + tmpNotes + " is not listed in data.env file.");
        }
        tapElement("simpan_catatan_button");
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void tapBayarSekarangButton() {
        tapElement("checkout_bayar_sekarang_button");
        HelperData.setLastActionPage(new CheckoutPage(iosDriver));
    }

    public void setTotalPayment() {
        TransactionData.setTotalPrice(getElementValue("checkout_total_price_text"));
    }

    public void editQtyItems(String qty) {
        typeAndEnterValueWithTimeOut("checkout_quantity_item_textfield", qty);
    }

    public void goToHomePage() {
        //modif start (don't delete old version)
        //modif by Core (Sekar) because of error in Last Action Page
        /*if (alchemyStatus) {
            tapElement("checkout_markeplcace_back_btn_id");
        } else {
            tapElement("base_back_button");
            if (isElementVisible("base_back_button"))
                tapElement("base_back_button");
        }
        if (isElementVisible("popup_alert_ya_button")) {
            tapElement("popup_alert_ya_button");
        }

        if (isElementVisible("popup_alert_ya_keluar_button")) {
            tapElement("popup_alert_ya_keluar_button");
        }

        for (int i = 0; i < 5; i++) {
            if (isElementVisible("home_blhome_tab")) {
                tapElement("home_blhome_tab");
                break;
            }
            try {
                tapElement("base_back_button");
            } catch (Exception e) {
                if (isElementVisible("checkout_markeplcace_back_btn_id")) {
                    tapElement("checkout_markeplcace_back_btn_id");
                }
            }
        }*/
        backToHomePageViaDeeplink();
        //modif end
        setAlchemyStatus(false);
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
