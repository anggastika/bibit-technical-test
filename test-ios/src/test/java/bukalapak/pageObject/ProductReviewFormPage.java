package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.RAGEData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.lang3.RandomUtils;

import java.time.Duration;
import java.util.Random;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class ProductReviewFormPage extends BasePage {

    public ProductReviewFormPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePage();
        if (isElementVisible("update_review_back_button")) {
            tapElement("update_review_back_button");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void reviewListPage(){
        verifyElementExist("ulas_barang_page_title");
        verifyElementExist("ulas_barang_back_button");
        verifyElementExist("ulas_barang_information_message");
    }

    public void isOnDetailPembelianPage(){
        verifyElementExist("detail_pembelian_page_title");
        verifyElementExist("detail_pembelian_alamat_pengiriman_section");
        verifyElementExist("update_review_product_rating");
    }

    public void clickUbahUlasan(){
        waitForVisibilityOf("ulas_barang_ubah_ulasan_btn_txt", 15);
        tapElement("ulas_barang_ubah_ulasan_btn_txt");
    }

    public void assertReviewForm(){
        //butuh delay untuk dapat interaksi dengan element tersebut
        waitFor(5);
        waitForVisibilityOf("update_review_title", 30);
        waitForVisibilityOf("update_review_back_button", 30);
        HelperData.setLastActionPage(new ProductReviewFormPage(iosDriver));
    }

    public void tapRating(){
        waitForVisibilityOf("update_review_product_rating", 15);
        tapElement("update_review_product_rating");
    }

    public void clearTextField() {
        IOSElement element = getElement("update_review_topic_text_field", 5);
        TouchAction action = new TouchAction(iosDriver);
        action.longPress(longPressOptions().withElement(element(element)).withDuration(Duration.ofMillis(9000)));
        action.release().perform();
        verifyElementExist("select_all_button");
        tapElement("select_all_button");
        waitForVisibilityOf("cut_button", 10);
        verifyElementExist("cut_button");
        tapElement("cut_button");
    }

    public void  chooseReviewTopic(String topic){
        waitForVisibilityOf("update_review_topic_title", 15);
        switch (topic) {
            case "Harga":
                tapElement("update_review_topic_harga");
                break;
            case "Kualitas":
                tapElement("update_review_topic_kualitas");
                break;
            case "Sesuai":
                tapElement("update_review_topic_sesuai");
                break;
            case "Awet":
                tapElement("update_review_topic_awet");
                break;
            case "Aroma":
                tapElement("update_review_topic_aroma");
                break;
            case "Rasa":
                tapElement("update_review_topic_rasa");
                break;
            default:
                break;
        }
    }

    public void assertTopicSelected(){
        waitForVisibilityOf("ulas_barang_review_text", 15);
    }
    public void uploadImage(){
        waitForVisibilityOf("update_review_tambahkan_foto_btn", 15);
        tapElement("update_review_tambahkan_foto_btn");

        if (isElementVisible("update_review_access_camera_ok_btn")) {
            tapElement("update_review_access_camera_ok_btn");
        } else {
            LogUtil.info("access camera permission not display");
        }
        if (isElementVisible("update_review_access_photos_ok_btn")) {
            tapElement("update_review_access_photos_ok_btn");
        } else {
            LogUtil.info("access photo permission not display");
        }

        //butuh delay untuk dapat interaksi dengan element tersebut
        waitFor(7);
        //click image
        waitForVisibilityOf("update_review_topic_upload_image_btn", 30);
        tapElement("update_review_topic_upload_image_btn");

        //choose image
        waitForVisibilityOf("update_review_topic_upload_image_checkbox", 30);
        tapElement("update_review_topic_upload_image_checkbox");

        //click lanjut button
        waitForVisibilityOf("update_review_topic_upload_image_lanjut_btn", 30);
        tapElement("update_review_topic_upload_image_lanjut_btn");

        //click loading state after upload image
        waitForVisibilityOf("update_review_loader_img", 30);
        tapElement("update_review_loader_img");
    }

    public void deleteImage(){
        waitForVisibilityOf("update_review_img_upload", 15);
        tapElement("update_review_img_upload");
        waitForVisibilityOf("update_review_delete_img_btn", 15);
        tapElement("update_review_delete_img_btn");
        assertReviewForm();
    }

    public void tapOnAnonymousCheckBox(){
        waitForVisibilityOf("update_review_anonym_checkbox", 15);
        tapElement("update_review_anonym_checkbox");
    }

    public void clickSelesaibtn() {
        verifyElementExist("update_review_selesai_button");
        tapElement("update_review_selesai_button");
    }

    public void tapOnKirimButton(){
        waitForVisibilityOf("update_review_kirim_button", 15);
        tapElement("update_review_kirim_button");
    }

    public void userTapUlasanBtn(){
        waitForVisibilityOf("ulas_barang_ubah_ulasan_btn", 15);
        tapElement("ulas_barang_ubah_ulasan_btn");
    }

    public String generateRandomDesc(){
        String s, desc;
        int randomNum = RandomUtils.nextInt(100000, 999999);
        String randomize = String.valueOf(randomNum);
        String names[] = {"Harga Murah ", "Kualitas Bagus ", "Pengiriman cepat ", "Keren abis ", "Recommended! ", "Kualitas bintang 5 ", "Tjakep ", "Original ", "Produk bagus ", "Sesuai gambar "};
        Random dice = new Random();
        int n = dice.nextInt(10);
        s = names[n];

        desc = s + randomize;

        return desc.toLowerCase();
    }

    public void typeOnReviewDescEditText(String desc) {
        RAGEData.setReviewDescription(desc);
        typeAndEnterValueWithTimeOut("update_review_topic_text_field", desc);
        verifyElementExist("update_review_selesai_button");
        tapElement("update_review_selesai_button");
    }
}
