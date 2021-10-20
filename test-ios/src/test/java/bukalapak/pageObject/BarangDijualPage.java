package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import bukalapak.data.PXData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;

/**
 * Created by Surya Thio on 27/12/18.
 */
public class BarangDijualPage extends BasePage {

    public BarangDijualPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnBarangDijualPage() {
        verifyElementExist("barang_dijual_title");
        verifyElementExist("barang_dijual_tambah_button");
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
    }

    public void goToBarangDijualWithDeeplink() {
        openDeepLink("https://www.bukalapak.com/my_products/stocked");
    }

    public void verifySuperSellerOffering() {
        validateDisplayed("barang_dijual_super_seller_modal_text");
        validateDisplayed("barang_dijual_super_seller_button");
    }

    public void clickOpsiProduk() {
        tapElement("opsi_prodcut_button");
    }

    public void goToUbahProduk() {
        tapElement("ubah_info_produk_button");
    }

    public void navigateToPushPage() {
        backToHomePage();
        swipeDownToElement("widget_push_product");
        tapElement("widget_push_product");
    }

    public void clickFirstPushButton() {
        verifyElementExist("barang_dijual_first_push_button");
        tapElement("barang_dijual_first_push_button");
    }

    public void validatePopupConfirmation() {
        waitForVisibilityOf("barang_dijual_push_popup_bd_title_text");
        validateDisplayed("barang_dijual_push_popup_bd_content_text");
    }

    public void tapButtonTidak() {
        waitForVisibilityOf("barang_dijual_tidak_button");
        tapElement("barang_dijual_tidak_button");
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void filterBarangDijual() {
        tapElement("barang_dijual_filter_button");
    }

    public void filterActiveDiscount() {
        tapElement("barang_dijual_filter_active_discount_button");
    }

    public void searchProdActiveDiscount() {
        typeAndEnterValueWithTimeOut("barang_dijual_search_button", PXData.getProductName());
    }

    public void verifyProdDiscNotFound() {
        // need waitFor to handle loading when fetching data
        waitFor(15);
        if (isElementVisible("product_detail_name", 10)) {
            assertEquals(PXData.getProductName(), getTextFromElement("product_detail_name"));
        } else {
            assertTrue(isElementVisible(getTextFromElement("edit_product_verify_name"), 10), "Product name doesn't match");
        }
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
    }

    public void searchProduct(String productName) {
        typeAndEnterValue("barang_dijual_text_field", productName);
        waitForVisibilityOf(constructLocator("barang_dijual_product_text", productName), 20);
    }

    public void selectProducts(String product) {
        if (product.contains("first")) {
            tapElement("barang_dijual_first_product_checkbox");
        }
        else if (product.contains("all")) {
            waitForVisibilityOf("barang_dijual_all_checkbox");
            tapElement("barang_dijual_all_checkbox");
            List<IOSElement> productElements = getElements("barang_dijual_product_checkbox");
            PROMData.setPushBulkTotal(productElements.size());
        }
        else {
            tapElement(constructLocator("barang_dijual_product_name_checkbox", product));
        }
    }

    public void clickOnPromotedPushButton(String productName) {
        tapElement(constructLocator("barang_dijual_promoted_push_button", productName));
    }

    public void clickOnBarangDijualThreeDotsButton(String productName) {
        waitForVisibilityOf(constructLocator("barang_dijual_three_dots", productName), 40);
        tapElement(constructLocator("barang_dijual_three_dots", productName));
    }

    // Region successful upload product
    public void checkUploadModal() {
        waitForVisibilityOf("barang_dijual_header_berhasil_unggah", 20);
        verifyElementDisplayed("barang_dijual_preview_barang");
        verifyElementDisplayed("barang_dijual_info_promoted_text");
        verifyElementDisplayed("barang_dijual_atur_promoted_text");
    }

    private void scrollDownToOption(String option, int maxScroll) {
        for (int i = 0; i < maxScroll; i++) {
            if (!isElementVisible(option)) {
                nativeSwipeUp();
            }
        }
    }

    public void tapOption(String option) {
        switch (option.toLowerCase()) {
            case "salin barang":
                scrollDownToOption("barang_dijual_salin_barang_option", 5);
                tapCenterOfElement("barang_dijual_salin_barang_option", 0);
                break;
            case "ubah":
                scrollDownToOption("barang_dijual_ubah_option", 10);
                tapElement("barang_dijual_ubah_option");
                break;
            default:
                LogUtil.warn("Not recognized option in Barang Dijual page");
                break;
        }
    }

    public void clickSortByTerlaris() {
        tapElement("barang_dijual_sort_button", 15);
        tapElement("barang_dijual_sort_terlaris_option_text", 15);
    }

    // pop  up menu lainnya section
    public void tapOnMenuLainnyaButton() {
        tapElement("barang_dijual_menu_lainnya_btn");
    }

    public void verifyPopUpMenuLainnya() {
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
        waitForVisibilityOf("barang_dijual_menu_lainnya_title", 10);
        validateDisplayed("barang_dijual_menu_lainnya_close_btn");
    }

    public void tapBtnInMenuLainnyaPopUp(String btnMenu) {
        waitForVisibilityOf(constructLocator("barang_dijual_btn_menu_lainnya_popup", btnMenu), 5);
        tapElement(constructLocator("barang_dijual_btn_menu_lainnya_popup", btnMenu), 3);
    }

    //pop up atur label barang
    public void verifyPopUpAturLabelBarang() {
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
        waitForVisibilityOf("atur_label_barang_popup_title", 30);
        validateDisplayed("atur_label_barang_popup_x_button");
    }

    public void tapTambahLabelBaruBtn() {
        swipeUpToElement("atur_label_barang_popup_tambah_label_baru_button", 10);
        tapElement("atur_label_barang_popup_tambah_label_baru_button", 10);
    }

    public void verifyOfferingSuperSeller() {
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
        waitForVisibilityOf("super_seller_offering_popup_title", 20);
        waitForVisibilityOf("super_seller_offering_popup_desc", 15);
        validateDisplayed("super_seller_offering_popup_pelajari_super_seller_btn");
    }

    public void tapPelajariSuperSeller() {
        tapElement("super_seller_offering_popup_pelajari_super_seller_btn", 5);
    }

    public void validateBulkPushUsingBukaDompetModal() {
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
        waitForVisibilityOf("barang_dijual_push_bd_title");
        verifyElementDisplayed("barang_dijual_push_bd_desc");
        verifyElementDisplayed("barang_dijual_push_bd_desc_1");
        verifyElementDisplayed(constructLocator("barang_dijual_total_push", PROMData.getPushBulkTotal()));
        validateValue().equals(PROMData.getPushBulkTotal() * PROMData.getPushPrice(),
                Math.abs(getIntFromRp(getText(constructLocator("barang_dijual_total_harga_push", PROMData.getPushBulkTotal())))));
        validateDisplayed("barang_dijual_push_bd_batal_btn");
        validateDisplayed("barang_dijual_push_bd_pinjam_btn");
    }

    public void tapBatalPushButton() {
        tapElement("barang_dijual_push_bd_batal_btn");
    }


    public void validateLinkLangsungBayarModal() {
        validateDisplayed("barang_dijual_link_langsung_bayar_title");
        validateDisplayed("barang_dijual_link_langsung_bayar_text");
        validateDisplayed("barang_dijual_link_langsung_bayar_url");
        validateDisplayed("barang_dijual_link_langsung_bayar_share_btn");
    }

    public void tapOnShareLinkLangsungBayar() {
        tapElement("barang_dijual_link_langsung_bayar_share_btn");
    }

    public void validateShareLinkLangsungBayarModal() {
        validateDisplayed("barang_dijual_link_langsung_bayar_share_modal");
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
    }

    public void tapOnDeleteBtn() {
        scrollDownToOption("edit_product_hapus_barang", 5);
    }

    public void tapPromotedPushOption() {
        waitForVisibilityOf("barang_dijual_promoted_push_option");
        tapElement("barang_dijual_promoted_push_option");
    }

    public void tapBulkPromotedPush() {
        waitForVisibilityOf("barang_dijual_bulk_promoted_push_button");
        tapElement("barang_dijual_bulk_promoted_push_button");
    }

    public void verifyPromotedPushSnackbar(String type) {
        if (type.equals("satuan")) {
            validateDisplayed("barang_dijual_promoted_satuan_snackbar");
        }
        else {
            validateDisplayed("barang_dijual_promoted_grup_snackbar");
        }
        HelperData.setLastActionPage(new BarangDijualPage(iosDriver));
    }
}
