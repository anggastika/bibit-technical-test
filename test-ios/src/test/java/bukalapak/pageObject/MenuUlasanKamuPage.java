package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.RAGEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import java.util.List;


public class MenuUlasanKamuPage extends BasePage {

    public MenuUlasanKamuPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void userOnMenuUlasanKamuPage(){
        verifyElementExist("menu_ulasan_kamu_belum_diulas_tab");
        verifyElementExist("menu_ulasan_kamu_sudah_diulas_tab");
        HelperData.setLastActionPage(new MenuUlasanKamuPage(iosDriver));
    }

    public void validateTotalNotificationmatchwithTotalProduct(){
        List<IOSElement> products = getElements("menu_ulasan_kamu_product_name");
        int product = products.size();
        String totalProduct = Integer.toString(product);
        Assert.assertEquals(getElementValue("notification_bubble_review"), totalProduct);
        HelperData.setLastActionPage(new MenuUlasanKamuPage(iosDriver));
    }

    public void verifyNotificationBubbleMenuUlasanKamu(){
        verifyElementExist("menu_ulasan_kamu_belum_diulas_tab");
        verifyElementExist("notification_bubble_review");
    }

    public void validateTotalProductBelumDiulasMatch(){
        Assert.assertEquals(getElementValue("notification_bubble_review"), RAGEData.getTotalProductBelumDiulas());
    }

    public void verifyProductReviewInfo(){
        verifyElementExist("menu_ulasan_kamu_belum_diulas_tab");
        verifyElementExist("menu_ulasan_kamu_seller_name");
        verifyElementExist("menu_ulasan_kamu_seller_picture");
        verifyElementExist("menu_ulasan_kamu_product_name");
        verifyElementExist("menu_ulasan_kamu_review_date");
        verifyElementExist("menu_ulasan_kamu_rating");
    }

    public void tabSudahDiulas(){
        verifyElementExist("menu_ulasan_kamu_sudah_diulas_tab");
        tapElement("menu_ulasan_kamu_sudah_diulas_tab");
    }

    public void verifyUlasanUpdated(){
        verifyElementExist("menu_ulasan_kamu_review_desc");
        assertEquals(getElementValue("menu_ulasan_kamu_review_desc"), "Rasa");
    }

    public void verifyEmptystateSudahDiulas(){
        verifyElementExist("menu_ulasan_kamu_empty_state_sudah_diulas");
        verifyElementExist("menu_ulasan_kamu_sudah_diulas_tab");
        HelperData.setLastActionPage(new MenuUlasanKamuPage(iosDriver));
    }

    public void clickUbahUlasanButton(){
        verifyElementExist("ulas_barang_ubah_ulasan_btn");
        tapElement("ulas_barang_ubah_ulasan_btn");
    }

    public void clickRating(){
        waitForVisibilityOf("menu_ulasan_kamu_rating", 10);
        verifyElementExist("menu_ulasan_kamu_rating");
        tapElement("menu_ulasan_kamu_rating");
    }

    public void verifyLengkapiUlasanButton(){
        verifyElementExist("menu_ulasan_kamu_lengkapi_ulasan");
    }

    public void clickLengkapiUlasan(){
        tapElement("menu_ulasan_kamu_lengkapi_ulasan");
    }

    public void verifyUlasanMovedtoSudahDiulasTab(){
        verifyElementExist("menu_ulasan_kamu_sudah_diulas_tab");
        tapElement("menu_ulasan_kamu_sudah_diulas_tab");
        verifyElementExist("menu_ulasan_kamu_product_name");
        assertEquals(getElementValue("menu_ulasan_kamu_review_desc"),RAGEData.getReviewDescription());
        HelperData.setLastActionPage(new MenuUlasanKamuPage(iosDriver));
    }

    public void verifyEmptystateBelumDiulas(){
        verifyElementExist("menu_ulasan_kamu_empty_state_belum_diulas");
        verifyElementExist("menu_ulasan_kamu_belum_diulas_tab");
        HelperData.setLastActionPage(new MenuUlasanKamuPage(iosDriver));
    }
}
