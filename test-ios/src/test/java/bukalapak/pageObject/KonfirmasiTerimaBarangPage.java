package bukalapak.pageObject;

import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KonfirmasiTerimaBarangPage extends BasePage {

    public KonfirmasiTerimaBarangPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userTapOkBtn(){
        if (isElementVisible("konfirmasi_terima_barang_on_boarding_ok_button")) {
            tapElement("konfirmasi_terima_barang_on_boarding_ok_button");
        } else {
            LogUtil.info("OK button not display on the screen");
        }
    }

    public void userTapNextBtn(){
        if (isElementVisible("konfirmasi_terima_barang_on_boarding_next_button")) {
            tapElement("konfirmasi_terima_barang_on_boarding_next_button");
        } else {
            LogUtil.info("Next button not display on the screen");
        }
    }

    public void userOnKTBPage(){
        verifyElementExist("konfirmasi_terima_barang_page_title");
        verifyElementExist("konfirmasi_terima_barang_back_button");
        verifyElementExist("konfirmasi_terima_barang_feed_back_title");
    }

    public void userTapFeedbackBtn(String feedback){
        //need delay for interact with element
        waitFor(3);

        if (feedback.equalsIgnoreCase("positif")) {
            waitForVisibilityOf("konfirmasi_terima_barang_positive_feed_back_button", 15);
            tapElement("konfirmasi_terima_barang_positive_feed_back_button");
        } else {
            waitForVisibilityOf("konfirmasi_terima_barang_negative_feed_back_button", 15);
            tapElement("konfirmasi_terima_barang_negative_feed_back_button");
        }
    }

    public void userInputComment(String comment){
        waitForVisibilityOf("konfirmasi_terima_barang_modal_teruskan_uang_title",15);
        waitForVisibilityOf("konfirmasi_terima_barang_modal_pendapat_textfield",15);
        typeAndEnterValue("konfirmasi_terima_barang_modal_pendapat_textfield", comment);
        tapElement("konfirmasi_terima_barang_modal_teruskan_uang_button");
    }

    public void verifySuccesMessage(){
        waitFor(5);
        verifyElementNotExist("konfirmasi_terima_barang_success_message");
    }

    public void userTapBackBtn(){
        waitForVisibilityOf("konfirmasi_terima_barang_back_button", 15);
        tapElement("konfirmasi_terima_barang_back_button");
    }

    public void userTapCloseModalBtn(){
        waitForVisibilityOf("ulas_barang_modal_close_btn", 15);
        tapElement("ulas_barang_modal_close_btn");
    }

    public void verifyUlasBarangPage(){
        verifyElementExist("ulas_barang_page_title");
        verifyElementExist("ulas_barang_back_button");
        verifyElementExist("ulas_barang_information_message");
    }

    public void userTapUlasBtn(){
        waitForVisibilityOf("ulas_barang_ulas_button", 15);
        tapElement("ulas_barang_ulas_button");
    }

    public void userInputRating(){
        waitForVisibilityOf("ulas_barang_modal_rating",15);
        tapElement("ulas_barang_modal_rating");
    }

    public void userInputUlasBarangRate(){
        waitForVisibilityOf("konfirmasi_terima_barang_ulasan_rating",15);
        tapElement("konfirmasi_terima_barang_ulasan_rating");
    }

    public void userTapSimpanButton(){
        waitForVisibilityOf("ulas_barang_modal_simpan_btn",15);
        tapElement("ulas_barang_modal_simpan_btn");
    }

    public void userTapProductRate(){
        waitForVisibilityOf("konfirmasi_terima_barang_ulasan_rating",15);
        tapElement("konfirmasi_terima_barang_ulasan_rating");
    }

    public void userTapRatingAndTeruskanBtn(){
        waitForVisibilityOf("ulas_barang_ulas_button", 15);
        tapElement("ulas_barang_ulas_button");
    }

    public void assertReviewOnKtbPage(){
        verifyElementExist("konfirmasi_terima_barang_ulasan_barang_txt");
        verifyElementExist("konfirmasi_terima_barang_ulasan_product_image");
        verifyElementExist("konfirmasi_terima_barang_ulasan_product_name");
        verifyElementExist("konfirmasi_terima_barang_ulasan_rating");
    }

    public void userTapLengkapiUlasanBtn(){
        waitForVisibilityOf("konfirmasi_terima_barang_lengkapi_ulasan_button", 15);
        tapElement("konfirmasi_terima_barang_lengkapi_ulasan_button");
    }
}
