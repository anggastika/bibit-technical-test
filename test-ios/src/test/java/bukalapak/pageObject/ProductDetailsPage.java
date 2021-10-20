package bukalapak.pageObject;

import bukalapak.data.*;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void checkPriceOnProductDetail(String price) {
        switch (price) {
            case "price1":
                PriceData.setPrice1(getTextFromElement("price_on_product_detail").replaceAll("[^0-9]", ""));
                break;
            case "price2":
                PriceData.setPrice2(getTextFromElement("price_on_product_detail").replaceAll("[^0-9]", ""));
                break;
            default:
                break;
        }
    }

    public void comparePriceOnProductDetail() {
        int maximalPrice = Integer.parseInt(PriceData.getmaxPrice());
        int minimalPrice = Integer.parseInt(PriceData.getminPrice());
        int price = Integer.parseInt(PriceData.getPrice1());
        assertTrue((price <= maximalPrice) && (price >= minimalPrice), "Filter Rentang Harga Berhasil");
    }

    public void backToHome() {
        while (isElementVisible("base_back_button")) {
            tapElement("base_back_button");
        }
    }

    public void checkDiskonOnProductDetail() {
        assertTrue(isElementVisible("icon_diskon"));
    }

    public void validateDiscount() {
        //need wait to load icon discount on product detail
        waitFor(5);
        verifyElementExist("icon_diskon");
    }

    public void checkCicilanOnProductDetail() {
        tapElement("icon_cicilan");
        assertTrue(isElementVisible("page_cicilan"), "Filter Cicilan Berhasil");
        tapElement("close_page_cicilan");
    }

    public void checkGrosirOnProductDetail() {
        tapElement("icon_grosir");
        assertTrue(isElementVisible("page_grosir"), "Filter Cicilan Berhasil");
        tapElement("close_page_grosir");
    }

    public void validateGrosirIcon() {
        //need wait to load icon grosir on product detail
        waitFor(5);
        verifyElementExist("product_detail_icon_grosir");
    }

    public void clickBackToProductListing() {
        tapElement("base_back_button");
    }

    public void checkTheCheaperPrice() {
        int price1 = Integer.parseInt(PriceData.getPrice1());
        int price2 = Integer.parseInt(PriceData.getPrice2());
        assertTrue(price1 <= price2, "Filter Urutkan Termurah Berhasil");
    }

    public void checkTheMoreExpensivePrice() {
        int price1 = Integer.parseInt(PriceData.getPrice1());
        int price2 = Integer.parseInt(PriceData.getPrice2());
        assertTrue(price1 >= price2, "Filter Urutkan Termahal Berhasil");
    }

    public void clickUlasanBarang() {
        if (isElementVisible("product_detail_rating_area")) {
            tapElement("product_detail_ulasan_count");
        } else {
            tapElement("product_detail_old_ulasan_count");
        }
    }

    public void checkUlasanPage() {
        assertTrue(isElementVisible("ulasan_product_page"), "Gagal Redirect ke Ulasan Produk Page");
    }

    public void clickTitikTigaIcon(String elementName) {
        tapElement(elementName);
    }

    public void clickBagikan() {
        verifyElementExist("pdp_three_dots_bagikan_option");
        tapElement("pdp_three_dots_bagikan_option");
    }

    public void checkBagikanPopUp() {
        assertTrue(isElementVisible("pdp_three_dots_bagikan_pop_up"), "Pop Up Titik Tiga Muncul");
        tapElement("pdp_three_dots_bagikan_pop_up");
    }

    public void clickCekKurirAndOngkir(String elementName) {
        swipeToLocator(elementName);
        tapElement(elementName);
    }

    public void clickUbahCekOngkir() {
        assertTrue(isElementVisible("cek_ongkir_page"), "Halaman Cek Ongkos Kirim Berhasil di Akses");
        tapElement("ubah_cek_ongkir_button");
    }

    public void selectLocationOngkir(String provinsi, String kota, String kabupaten) {
        tapElement(provinsi);
        tapElement(kota);
        tapElement(kabupaten);
        waitForVisibilityOf("price_on_cek_ongkir", 5);
        int price = Integer.parseInt(ProductDetailData.setOngkirPrice1(getTextFromElement("price_on_cek_ongkir").replaceAll("[^0-9]", "")));
        LogUtil.info(String.valueOf(price));
    }


    public void checkRecommendationPanelAppear() {
        swipeUpToElement("reco_number_of_sold_product", 7);
        if (isElementVisible("product_detail_rek_buat_kamu")) {
            verifyElementExist("product_detail_rek_buat_kamu");
        }
    }

    public void userOnProductDetailPage() {
        assertTrue(isElementVisible("product_detail_beli_sekarang_btn", 40) || isElementVisible("product_detail_edit_button", 5));
        if (isElementVisible("icon_cart_button")) {
            verifyElementExist("icon_cart_button");
        } else {
            verifyElementExist("icon_cart_page");
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
        if (isElementVisible("product_detail_normal_price", 30)){
            ProductDetailData.setProductPrice(getText("product_detail_normal_price"));
        } else {
            ProductDetailData.setProductPrice(getText("product_detail_range_price"));
        }
        ProductDetailData.setProductName(getElementValue("product_detail_name_revamp"));
    }

    public void tapBuyNowButton() {
        if (isElementVisible("product_detail_normal_price")) {
            LogUtil.info("A = " + getElementValue("product_detail_normal_price"));
            MtxData.setProductPricePdp(getElementValue("product_detail_normal_price"));
            LogUtil.info("B = " + MtxData.getProductPricePdp());
        }
        else if (isElementVisible("product_detail_discount_price")) {
            MtxData.setProductPricePdp(getElementValue("product_detail_discount_price"));
        }
        tapElement("button_beli_sekarang");
    }

    public void userOnProductDetailPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        userOnProductDetailPage();
    }

    public void validationBatasWaktuPengirimanInstan(int batasWaktuPengiriman) {
        swipeDownToElement("waktu_proses_pesanan_custom_on_product_Detail", 1);
        assertTrue(getElementValue("waktu_proses_pesasanan_instan_text_field").contains((String.valueOf(batasWaktuPengiriman))));
    }

    public void validationBatasWaktuPengirimanCustom(int batasWaktuPengiriman) {
        swipeDownToElement("waktu_proses_pesanan_custom_on_product_Detail", 1);
        verifyElementNotExist("waktu_proses_pesanan_not_present_text_field");
    }

    public void validationBatasWaktuPengirimanPreorder() {
        swipeDownToElement("waktu_proses_pesanan_custom_on_product_Detail", 1);
        verifyElementNotExist("waktu_proses_pesanan_not_present_text_field");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void setLocationDelivery() {
        String provinsi1 = "PROVINSI1";
        String kota1 = "KOTA1";
        String kab1 = "KABUPATEN1";
        String provinsi2 = "PROVINSI2";
        String kota2 = "KOTA2";
        String kab2 = "KABUPATEN2";
        clickUbahCekOngkir();
        selectLocationOngkir(provinsi1, kota1, kab1);
        selectLocationOngkir(provinsi2, kota2, kab2);
    }

    public void checkDeliveryFee() {
        int price1 = Integer.parseInt(ProductDetailData.getOngkirPrice1());
        int price2 = Integer.parseInt(ProductDetailData.getOngkirPrice2());
        assertTrue(price1 > price2, "Pengecekan Biaya Ongkos Kirim Berhasil");
    }

    public void changesAmountProduct() {
        Integer.parseInt(ProductDetailData.setAmountValue1(getElementValue("amount_product_ongkir")));
        tapElement("plus_button");
        Integer.parseInt(ProductDetailData.setAmountValue2(getElementValue("amount_product_ongkir")));
    }

    public void closePopUpCheckOngkir() {
        tapElement("close_pop_up_check_ongkir");
    }

    public void checkAmountProduct() {
        int amountProduct1 = Integer.parseInt(ProductDetailData.getAmountValue1());
        int amountProduct2 = Integer.parseInt(ProductDetailData.getAmountValue2());
        assertTrue(amountProduct2 > amountProduct1, "Jumlah Barang Berhasil Diubah");
        closePopUpCheckOngkir();
    }

    public void checkInfoBarangPage() {
        assertTrue(isElementVisible("pop_up_info_barang"), "Info Barang Page Berhasil di Buka");
    }

    public void clickKategoriOnInfoBarang() {
        ProductDetailData.setKategoriName(getElementValue("kategori_on_info_barang"));
        tapElement("kategori_on_info_barang");
    }

    public void clickPelapakOnProductDetail(String elementName) {
        swipeDownToElement(elementName);
        ProductDetailData.setPelapakName(getElementValue(elementName));
        tapElement(elementName);
    }

    public void scrollToUlasanBarang() {
        swipeUpToElement("lihat_semua_ulasan");
        if (!isElementVisible("lihat_semua_ulasan")) {
            LogUtil.info("Belum Ada Ulasan");
        } else {
            LogUtil.info("Ulasan Ditemukan");
        }
    }

    public void clickLihatSemuaUlasan() {
        tapElement("lihat_semua_ulasan");
    }

    public void scrollToDiskusiBarang() {
        swipeDownToElement("diskusi_barang");
    }

    public void clickLihatSemuaDiskusiBarang(String elementName) {
        if (isElementVisible(elementName)) {
            tapElement(elementName);
        } else {
            tapElement("tanya_pelapak");
        }
    }

    public void clickButtonFromProductDetail(String elementName) {
        switch (elementName) {
            case "titik_tiga_icon":
                clickTitikTigaIcon(elementName);
                break;
            case "ulasan":
                clickUlasanBarang();
                break;
            case "cek_ongkir_button":
                clickCekKurirAndOngkir(elementName);
                break;
            case "pelapak_name":
                clickPelapakOnProductDetail(elementName);
                break;
            case "lihat_semua_ulasan":
                clickLihatSemuaUlasan();
                break;
            case "lihat_semua_diskusi_barang":
                clickLihatSemuaDiskusiBarang(elementName);
                break;
            case "selengkapnya_semua_info_barang":
                scrollToSelengkapnyaInfoBarang(elementName);
                break;
            default:
                tapElement(elementName);
                break;
        }
    }

    public void checkDiskusiPage() {
        if (isElementVisible("tanya_barang")) {
            LogUtil.info("Berhasil Klik Lihat Semua Diskusi");
        } else if (isElementVisible("pertanyaan_saya")) {
            LogUtil.info("Berhasil Klik Tanya Pelapak");
        } else {
            LogUtil.error("Gagal Klik Pada Diskusi Produk");
        }
    }

    public void scrollToSelengkapnyaInfoBarang(String elementName) {
        while (!isElementVisible(elementName)) {
            swipeDownToElement(elementName);
            tapElement(elementName);
        }
    }

    public void clickLaporkan() {
        verifyElementExist("laporkan_icon");
        tapElement("laporkan_icon");
    }

    public void checkLaporkanPage() {
        verifyElementExist("laporkan_icon");
        tapElement("close_page_laporkan");
    }

    public void checkProductName() {
        if (isElementVisible("product_detail_name_revamp")) {
            ProductDetailData.setProductName(getElementValue("product_detail_name_revamp"));
        } else {
            ProductDetailData.setProductName(getElementValue("product_detail_name"));
        }
    }

    public void clickCatalogInfo() {
        String catalogInfoLocator = "value_" + WOWData.getCatalogTitle();
        tapElement(catalogInfoLocator);
    }

    public void verifyLapakTerbaikBadgeTitle() {
        verifyElementExist("lapak_terbaik_title_image");
    }

    public void verifySuperSellerBadgeTitle() {
        if (isElementVisible("super_seller_title_image", 15)) {
            verifyElementExist("super_seller_title_image");
            LogUtil.info("User on PDP non revamp");
        } else {
            waitForVisibilityOf("super_seller_title_image_revamp", 20);
            LogUtil.info("User on PDP revamp");
        }
    }

    public void verifyLapakTerbaikOnPelapak() {
        swipeDownToElement("pelapak_page");
        waitForVisibilityOf("lapak_terbaik_pelapak_image", 3);
    }

    public void verifySuperSellerOnPelapak() {
        swipeUpToElement("pelapak_page");
        if (isElementVisible("lapak_terbaik_pelapak_image", 15)) {
            verifyElementExist("super_seller_pelapak_image");
            LogUtil.info("User on PDP non revamp");
        } else {
            waitForVisibilityOf("super_seller_pelapak_image_revamp", 20);
            LogUtil.info("User on PDP revamp");
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void isDANAVoucherExist(Integer totalVoucher) {
        swipeUpToElement("create_product_info_barang");
        if (totalVoucher == 0) {
            verifyElementNotExist("dana_voucher_pdp");
        } else {
            verifyElementExist("dana_voucher_pdp");
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void closePopUpBenefitBeliBarang() {
        doPullRefresh(1);
        if (isElementVisible("product_detail_pop_up_keuntungan_beli_barang", 10)) {
            tapElement("product_detail_pop_up_keuntungan_beli_barang_Ok_btn");
        }
    }

    public void clickBeliSekarangButton() {
        tapElement("product_detail_beli_sekarang_btn", 15);
    }

    public void verifyVoucherLapakInfo(String info) {
        verifyElementExist(constructLocator("product_details_voucher_lapak_title", info));
    }

    public void verifyVoucherLapakEtalase(String etalaseName) {
        if (etalaseName.equalsIgnoreCase("all")) {
            verifyElementExist("product_details_voucher_lapak_all_etalase");
        } else {
            verifyElementExist(constructLocator("product_details_voucher_lapak_specified_etalase", etalaseName));
        }
    }

    public void verifyVoucherMinTrx(String minTrx) {
        assertEquals(minTrx, getElementValue("product_detail_info_min"));
    }

    public void verifyProductNamePopular() {
        String productName = CategoryData.getProductNamePopular();
        LogUtil.info("PRODUCT NAME" + productName);
        assertTrue(isElementVisible(constructLocator("product_detail_popular_prod", productName)), "Product Name Not Similar");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void verifyProductNameRecommendation() {
        String productName = CategoryData.getProductNameRecommendation();
        assertTrue(isElementVisible(constructLocator("product_detail_recom_prod", productName)), "Product Name Not Similar");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapFavoriteIcon() {
        waitForVisibilityOf("favorite_icon_pdp", 15);
        waitForElementClickable("favorite_icon_pdp", 15);
        tapElement("favorite_icon_pdp");
    }

    public void tapCartButton() {
        waitForVisibilityOf("product_detail_cart_button", 10);
        waitForElementClickable("product_detail_cart_button", 10);
        tapElement("product_detail_cart_button");
    }

    public void tapAddtoCartButton() {
        swipeUp();
        waitForVisibilityOf("product_detail_add_to_cart_button", 10);
        waitForElementClickable("product_detail_add_to_cart_button", 10);
        tapElement("product_detail_add_to_cart_button");
    }

    private void validateValidPrice(String element) {
        waitForVisibilityOf(element, 10);
        int price = getIntegerFromTextElement(element);
        assertTrue(price > 0, "Product price is not valid");
    }

    public void validatePopUpAddtocart() {
        if (isElementVisible("berhasil_masuk_keranjang", 20)) {
            validateValidPrice("product_price_pop_up_cart");
            validateValidPrice("total_price_pop_up_cart");
        }
        else {
            validateValidPrice("new_pop_cart_dialog_product_price");
            validateValidPrice("new_pop_cart_dialog_total_price");
        }
    }

    public void assertProductRatingSection() {
        if (isElementVisible("product_detail_rating_area", 10)) {
            validateDisplayed("product_detail_rating_icon");
            validateDisplayed("product_detail_rating");
            validateDisplayed("product_detail_ulasan_count");
        } else {
            validateDisplayed("favorite_star_rating");
            validateDisplayed("product_detail_old_ulasan_count");
        }
    }

    public void productDetailTag(String action, String tag) {
        String pdpTag = null;
        switch (tag) {
            case "Preorder":
                pdpTag = "product_detail_preorder_tag";
                break;
            case "Preorder 14 Hari":
                pdpTag = "product_detail_preorder_14hari_tag";
                break;
            case "Cicilan":
                pdpTag = "product_detail_cicilan_tag";
                break;
            case "Gratis Ongkir":
                pdpTag = "product_detail_gratis_ongkir_tag";
                break;
            case "Grosir":
                pdpTag = "product_detail_grosir_tag";
                break;
            case "Bisa dikirim ke luar negeri":
                pdpTag = "product_detail_global_shipment_tag";
                break;
            default:
                Assert.fail("PDP tag not visible");
                break;
        }
        if (action.equals("see")) {
            swipeDownToElement(pdpTag);
            assertTrue(isElementVisible(pdpTag, 5), "Element " + pdpTag + " not visible");
        } else {
            tapElement(pdpTag);
        }

        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void assertProductDetailTag(String tag) {
        switch (tag) {
            case "preorder":
                validateDisplayed("modal_" + tag + "_detail");
                break;
            case "preorder 14 hari":
                validateDisplayed("modal_preorder14hari_detail");
                break;
            default:
                Assert.fail("PDP tag not visible");
                break;
        }
    }

    public void closeTagModal() {
        tapElement("modal_tag_close");
    }

    public void assertPDPTagModalClosed() {
        validateNotDisplayed("modal_tag_close");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void exploreProductSection(String section) {
        nativeSwipeUpToElement(constructLocator("product_section_item", section));
        validateDisplayed(constructLocator("product_section_item", section));
    }

    public void tapSelengkapnyaProductInfoSection() {
        swipeUpToElement("product_info_section_selengkapnya_link");
        tapElement("product_info_section_selengkapnya_link");
    }

    public void assertRelatedProductGridPage() {
        assertTrue(isElementVisible(constructLocator("related_product_grid_title",
                ProductDetailData.getProductName()), 15));
        validateDisplayed("related_product_beli_button");
    }

    public void tapProductExploreSection(String section) {
        if (!isElementVisible(constructLocator("product_section_item", section)))
            swipeUpToElement(constructLocator("product_section_item", section));
        tapElement(constructLocator("product_section_item", section));
    }

    public void assertVideoPDP() {
        validateDisplayed("video_on_image_slider", "video not visible");
    }

    public void assertPlayVideoButton() {
        validateDisplayed("play_button_video", "play button not visible");
        assertTrue(isElementClickable("play_button_video"), "Cannot click play button");
    }

    public void tapVoucherSection() {
        swipeUpToElement("pdp_product_info_modal");
        if (isElementExist("voucher_section_revamp")) {
            tapElement("voucher_section_revamp", 5);
        } else {
            tapElement("voucher_section", 5);
        }
    }

    public void tapVoucherItem(String voucherType) {
        if (voucherType.equals("random")) {
            tapElement("voucher_list");
        } else {
            tapElement(constructLocator("voucher_type", voucherType));
        }
    }

    public void validateVoucherSection() {
        swipeUpToElement("pdp_product_info_modal");
        if (!isElementExist("voucher_section_revamp", 5)) {
            verifyElementExist("voucher_section");
        } else {
            verifyElementExist("voucher_section_revamp");
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void validateNormalPrice() {
        assertTrue(isElementVisible("product_detail_normal_price", 20));
    }

    public void validateDiscountPrice() {
        //handle variant price
        if (!isElementExist("product_detail_variant_discount_price", 20)) {
            verifyElementExist("product_detail_discount_price");
        } else {
            verifyElementExist("product_detail_variant_discount_price");
        }
    }

    public void validateDiscountLabel() {
        assertTrue(isElementVisible("product_detail_discount_label", 20));
    }

    public void validateRatingLabel(Integer expectedRating) {
        float actualRating = Float.parseFloat(getTextFromElement("product_detail_rating").replaceAll(
                "/5", ""));
        assertTrue(actualRating >= expectedRating);
    }

    public void validateProductInfoModal() {
        verifyElementExist("pdp_product_info_modal", 5, "pdp_product_info_modal doesn't exist");
    }

    public void validateDescriptionLabel() {
        verifyElementExist("pdp_product_info_description_section");
    }

    public void tapDescLink() {
        verifyElementExist("pdp_product_info_description_field");
        tapElement("pdp_product_info_description_text");
    }

    public void validateProductInformationLabel() {
        verifyElementExist("pdp_product_info_section");
    }

    public void tapDetailProductInfo() {
        swipeUpToElement("pdp_product_info_selengkapnya");
        verifyElementExist("pdp_product_info_selengkapnya");
        tapElement("pdp_product_info_selengkapnya");
    }

    public void validatePromoOverlay() {
        assertTrue(isElementVisible("product_detail_overlay_text", 20));
        assertTrue(isElementVisible("product_detail_overlay_first_background", 20));
        assertTrue(isElementVisible("product_detail_overlay_second_background", 20));
    }

    public void tapImageinPDP() {
        assertTrue(isElementVisible("product_image_in_pdp", 20));
        tapElement("product_image_in_pdp", 20);
    }

    public void validatePotraitImageModal() {
        assertTrue(isElementVisible("product_preview_image", 20));
        assertTrue(isElementVisible("product_preview_share_btn", 20));
        assertTrue(isElementVisible("product_preview_close_btn", 20));
    }

    public void tapCloseButtonInPotraitImage() {
        assertTrue(isElementVisible("product_preview_close_btn", 20));
        tapElement("product_preview_close_btn", 20);
    }

    public void validateNewWholesaleSection() {
        assertTrue(isElementVisible("wholesale_section", 20));
        assertTrue(isElementVisible("wholesale_section_price_range", 20));
        assertTrue(isElementVisible("wholesale_section_info", 20));
        assertTrue(isElementVisible("wholesale_section_dropdown", 20));
    }

    public void tapDropdownWholesale() {
        tapElement("wholesale_section_dropdown", 20);
    }

    public void validateInfoDetailWholesaleSection() {
        assertTrue(isElementVisible("wholesale_section_detail_label", 20));
        assertTrue(isElementVisible("wholesale_section_detail_price_list", 20));
    }

    public void validateInfoDetailWholesaleSectionNotExist() {
        verifyElementNotExist("wholesale_section_detail_label");
        verifyElementNotExist("wholesale_section_detail_price_list");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void validateInfoDetailWholesaleSectionStayExpanded() {
        assertTrue(isElementVisible("wholesale_section_detail_label", 20));
        assertTrue(isElementVisible("wholesale_section_detail_price_list", 20));
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void scrollDownInPDP() {
        swipeUpToElement("product_pilihan_lain_untukmu_label");
        assertTrue(isElementVisible("product_pilihan_lain_untukmu_label", 20));
    }

    public void scrollUpInPDP() {
        swipeDownToElement("wholesale_section");
    }

    public void validateRecommendationNotShowing() {
        swipeUpToElement("title_jualan_lain");
        if (isElementVisible("title_jualan_lain", 20)) {
            validateNotDisplayed("title_recommendation_section");
        }
    }

    public void clickBack() {
        validateDisplayed("button_back", "button back is not displayed");
        tapElement("button_back");
    }

    public void validateBadgeIconBesideProductName(String badges) {
        switch (badges) {
            case "superseller":
                assertTrue(isElementVisible("product_superseller_icon_beside_name", 20));
                break;
            case "bukamall":
                assertTrue(isElementVisible("product_bukamall_icon_beside_name", 20));
                break;
            default:
                Assert.fail("Invalid Parameter!");
        }
    }

    public void validateBadgeIconInPelapakSection(String badges) {
        switch (badges) {
            case "superseller":
                swipeUpToElement("product_superseller_icon_pelapak_section");
                assertTrue(isElementVisible("product_superseller_icon_pelapak_section", 20));
                break;
            case "bukamall":
                swipeUpToElement("product_bukamall_icon_pelapak_section");
                assertTrue(isElementVisible("product_bukamall_icon_pelapak_section", 20));
                break;
            default:
                Assert.fail("Invalid Parameter!");
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapThreeDotsButton() {
        verifyElementExist("pdp_three_dots_button");
        tapElement("pdp_three_dots_button");
    }

    public void validateBatalOption() {
        verifyElementExist("pdp_three_dots_batal_option");
        tapElement("pdp_three_dots_batal_option");
    }

    public void validateOutOfStockProduct() {
        verifyElementExist("pdp_empty_state_field");
        verifyElementExist("pdp_out_of_stock_info");
        verifyElementExist("pdp_out_of_stock_detail");
    }

    public void tapOnBenefitOnboarding() {
        if (isElementExist("text_benefit_onboarding", 10)) {
            tapElement("text_benefit_onboarding");
        }
    }

    public void verifyInstantCourier() {
        // wait for list to show
        // using waitFor because currently there is no unique identifier to wait for
        waitFor(3);
        swipeUpToElement("courier_instant", 5);
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void closePopUpAddToCart() {
        // handle close button on pop up revamp
        if (isElementVisible("close_atc_revamp_button")) {
            tapElement("close_atc_revamp_button");
        } else {
            validateDisplayed("close_atc_button", "button close is not displayed");
            tapElement("close_atc_button");
        }
    }

    public void tapToCartPage() {
        validateDisplayed("icon_cart_page", "icon cart is not displayed");
        tapElement("icon_cart_page");
    }

    public void setAddress(String address) {
        String tmpAddress = address;
        if(address == null) {
            tmpAddress = SearchData.getAddress();
        }

        typeValue("cari_daerah_input", tmpAddress);
        waitForVisibilityOf("cari_daerah_indonesia", 3);
        tapElement(
            constructLocator("cari_daerah_result_index", 1)
        );

        // wait modal to finish loading content
        // using waitFor because currently there is no unique identifier to wait for
        waitFor(2);
        tapElement("cari_daerah_close");
        tapElement("cek_ongkir_button");
    }

    public void validatePriceATCPopup() {
        validateValue().equals(ProductDetailData.getProductPrice(),getProductPriceATCPopup());
    }

    private String getProductPriceATCPopup() {
        return getText("product_price_atc");
    }

    public void verifyCourier(String courier) {
        swipeUpToElement(constructLocator("courier_name_pdp", courier));
        validateDisplayed(constructLocator("courier_name_pdp", courier));
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void verifyVariantType(String variant) {
        if (!isElementVisible(constructLocator("product_detail_variant_tag", variant), 10)) {
            validateDisplayed(constructLocator("product_detail_variant_subtitle", variant));
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapOnVariantSection() {
        swipeUpToElement("product_detail_variant_section");
        tapElement("product_detail_variant_section");
    }

    public void tapVariantType(String variant) {
        waitForVisibilityOf(
            constructLocator("product_detail_variant_dropdown", variant), 3
        );
        tapElement(
            constructLocator("product_detail_variant_dropdown", variant)
        );
    }

    // deprecated soon due to ab test win for revamp
    public void verifyVariantValue(String value) {
        validateDisplayed(
            constructLocator("product_variant_options", value)
        );
        tapElement(constructLocator("product_variant_options", value));
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void verifyVariant(String variant, String value) {
        validateDisplayed(
            constructLocator("product_detail_variant_value", variant, value)
        );
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void verifyPreorderButton() {
        int days = STRIPEData.getSLAType() == null ? 14 : STRIPEData.getSLADuration();

        assertTrue(isElementVisible("product_detail_beli_sekarang_btn", 20));

        if (days > 2 && days < 7) {
            validateValue().equals("Beli Sekarang", getText("product_detail_beli_sekarang_btn"));
        } else if (days > 6 && days < 31) {
            validateValue().equals(String.format("Preorder %d Hari", days), getText("product_detail_beli_sekarang_btn"));
        }
    }

    public void tapPreorderButton() {
        verifyElementExist("product_detail_pre-order_btn");
        tapElement("product_detail_pre-order_btn");
    }

    public void verifySpecialCampaignBanner(){
        verifyElementExist("special_campaign_banner");
        verifyElementExist("special_campaign_countdown_title");
        verifyElementExist("special_campaign_countdown");
        verifyElementExist("special_campaign_price");
    }

    public void validateSLA(String slaType, int sla) {
        switch(slaType) {
            case "custom":
                assertEquals(String.format("Waktu proses maks. %d hari", sla), getTextFromElement("product_detail_sla_text"));
                break;
            case "instant":
                assertEquals("Waktu proses 1 hari", getTextFromElement("product_detail_sla_instant_text"));
                break;
            case "preorder":
                assertEquals(String.format("Preorder %d Hari", sla), getTextFromElement("product_detail_sla_text"));
                break;
            case "reguler":
                verifyElementNotExist("product_detail_sla_text");
                verifyElementNotExist("product_detail_sla_instant_text");
                break;
            default:
                break;
        }
    }

    public void verifyTrayATC() {
        verifyElementExist("TRAY_ATC_POPUP_TITLE");
    }

    public void tapCloseButtonTrayATC() {
        tapElement("TRAY_ATC_POPUP_CLOSE_BTN");
    }

    public void verifySectionHematOngkir(String action) {
        if(action.equals("see")){
            swipeUpToElement("TRAY_ATC_POPUP_HEMAT_ONGKIR_TITLE");
            verifyElementExist("TRAY_ATC_POPUP_HEMAT_ONGKIR_TITLE", 20, "section hemat ongkir not exist");
        } else {
            verifyElementNotExist("TRAY_ATC_POPUP_HEMAT_ONGKIR_TITLE");
        }
    }

    public void tapProductHematOngkir() {
        swipeUpToElement("TRAY_ATC_POPUP_HEMAT_ONGKIR_PRODUCT");
        tapElement("TRAY_ATC_POPUP_HEMAT_ONGKIR_PRODUCT");
    }

    public void verifySectionDibeliBersamaan(String flag) {
        if (flag.equals("see")){
            swipeUpToElement("TRAY_ATC_POPUP_BELI_BERSAMAAN_TITLE");
            verifyElementExist("TRAY_ATC_POPUP_BELI_BERSAMAAN_TITLE", 20, "section dibeli bersamaan not exist");
        }else if (flag.equals("not see")){
            verifyElementNotExist("TRAY_ATC_POPUP_BELI_BERSAMAAN_TITLE");
        }else {
            Assert.fail("Flag " + flag + " is undefined");
        }
    }

    public void tapProductDibeliBersamaan() {
        swipeUpToElement("TRAY_ATC_POPUP_BELI_BERSAMAAN_PRODUCT");
        tapElement("TRAY_ATC_POPUP_BELI_BERSAMAAN_PRODUCT");
    }

    public void tapLihatSemuaHematOngkir() {
        swipeUpToElement("TRAY_ATC_POPUP_HEMAT_ONGKIR_TITLE");
        waitForElementClickable("TRAY_ATC_POPUP_HEMAT_ONGKIR_LIHAT_SEMUA", 10);
        tapElement("TRAY_ATC_POPUP_HEMAT_ONGKIR_LIHAT_SEMUA");
    }

    public void tapButtonBeliOnVariant() {
        tapElement("product_detail_button_beli_variant", 5);
    }

    public void tapButtonKeranjangOnVariant() {
        setSelectedVariant();
        waitForElementClickable("keranjang_button_variant_modal", 20);
        tapCenterOfElement("keranjang_button_variant_modal", 10);
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    private void setSelectedVariant() {
        int varSize = getMultipleElement().withLocator("variant_product", 3).size();
        String selectedVariant = null;
        StringBuffer buf = new StringBuffer();
        if (varSize > 1) {
            String tmpVar;
            for (int i = 0; i < varSize; i++) {
                tmpVar = getElements("variant_product").get(i).getAttribute("label");
                if (i != 0 ) {
                    buf.append(", ");
                }
                buf.append(tmpVar);
                selectedVariant = buf.toString();
            }
        } else if (varSize == 1){
            selectedVariant = getElementLabel("variant_product");
        }
        ProductDetailData.setVariant(selectedVariant);
    }

    public void validateRewardNotShown() {
        validateNotDisplayed("product_detail_reward_pop_up");
        goToHomePage();
    }

    public void validateRewardShown() {
        validateDisplayed("product_detail_reward_pop_up");
        validateDisplayed("product_detail_reward_lihat_button");
        validateDisplayed("product_detail_reward_lanjut_belanja_button");
    }

    public void validateOwnProductDetail() {
        waitForVisibilityOf("product_detail_own_edit_button", 5);
        validateDisplayed("product_detail_own_edit_button");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapOnRewardPopUp(String action) {
        switch (action.toLowerCase()) {
            case "lihat bonus":
                tapElement("product_detail_reward_lihat_button");
                break;
            case "lanjut belanja":
                tapElement("product_detail_reward_lanjut_belanja_button");
                break;
            case "close":
                tapElement("product_detail_reward_lanjut_close_button");
                break;
            default:
                Assert.fail("Invalid action: " + action);
        }
    }

    public void validateSpecification(String specValue) {
        validateDisplayed(constructLocator("product_detail_spec_value", specValue));
    }

    public void validateFotoDariPembeliOnImageSection() {
        assertTrue(isElementVisible("foto_dari_pembeli_button", 20));
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void swipeImageOnPdp() {
        assertTrue(isElementVisible("product_image_in_pdp", 20));
        //cek if video play button exist
        if (isElementVisible("play_button_video")) {
            verifyElementExist("play_button_video");
        }
        swipeLeftAtSpecifiedLocator("product_image_in_pdp");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapFotoDariPembeli() {
        tapElement("foto_dari_pembeli_button");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void validateOnFotoUlasanPage() {
        assertTrue(isElementVisible("foto_dari_ulasan_nav_bar", 20));
        assertTrue(isElementVisible("foto_dari_ulasan_back_btn", 20));
        assertTrue(isElementVisible("foto_dari_ulasan_photo", 20));
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void swipeUpAndDownOnFotoDariUlasanPage() {
        nativeSwipeUp();
        nativeSwipeDown();
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void validateStickyNavbar() {
        validateOnFotoUlasanPage();
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapBackBtnOnUlasanPage() {
        tapElement("foto_dari_ulasan_back_btn");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void verifyPssSection() {
        swipeUpToElement("ulasan_barang");
        verifyElementExist("title_jualan_lain", 20, "section pss not exist");
    }

    public void swipePssProduct(){
        swipeLeftAtSpecifiedLocator("pss_image");
        swipeRightAtSpecifiedLocator("pss_image");
    }

    public void verifyTextLinkPss(){
        verifyElementExist("pss_text_link", 20, "text link pss not exist");
    }

    public void tapTextLinkPss(){
        tapElement("pss_text_link");
    }

    public void redirectToPelapakPage(){
        if (isElementVisible("pelapak_button_mengerti", 3)){
            tapElement("pelapak_button_mengerti");
        }
        verifyElementExist("pelapak_name_seller_page");
    }

    public void tapBackButtonFromSellerPage(){
        tapElement("pelapak_back_button");
    }

    public void tapPssProduct(){
        tapElement("pss_product_desc");
    }

    public void validatePssProductCard(){
        verifyElementExist("pss_image");
        verifyElementExist("pss_product_desc");
        verifyElementExist("pss_price");
        verifyElementExist("pss_rating_star");
        verifyElementExist("pss_rating_value");
        verifyElementExist("pss_bmss");
        verifyElementExist("pss_number_of_sold_product");
    }

    public void validateRecommendationProductCard(){
        swipeUpToElement("reco_number_of_sold_product");
        verifyElementExist("reco_image");
        verifyElementExist("reco_product_desc");
        verifyElementExist("reco_price");
        verifyElementExist("reco_rating_star");
        verifyElementExist("reco_rating_value");
        verifyElementExist("reco_bmss");
        verifyElementExist("reco_number_of_sold_product");
    }

    public void tapRecommendationProduct(){
        tapElement("reco_product_desc");
    }

    public void verifyElementRecoComplementary(){
        verifyElementExist("TRY_ATC_POPUP_LIHAT_SEMUA");
        verifyElementExist("TRY_ATC_POPUP_NAME_PRODUCT");
        verifyElementExist("TRY_ATC_POPUP_PRICE_PRODUCT");
        verifyElementExist("TRY_ATC_POPUP_SOLD_PRODUCT");
        verifyElementExist("TRY_ATC_PUPUP_USER_BADGE");
    }

    public void tapSelengkapnyaPss() {
        swipeUpToElement("pss_selengkapnya_link");
        tapElement("pss_selengkapnya_link");
    }

    public void verifyPssSectionNotShowing() {
        swipeUpToElement("ulasan_barang");
        verifyElementNotExist("title_jualan_lain");
    }

    public void validateVariantModal(String flag) {
        validateValue().equalsTrue(
                isElementVisible("variant_dropdown_title", 3) ||
                        isElementVisible("variant_selection_tab_title", 2)
        );

        if (flag != null) {
            validateExist("variant_selection_tab_title", 2, "Selection variant Modal is not displayed");
            validateExist("variant_selection_tab_section", "Selection variant Options is not displayed");
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void selectVariant(String variant, String option) {
        if (isElementExist("variant_selection_tab_title", 2)) {
            selectSelectionTabVariant(variant, option);
        } else {
            selectDropdownVariant(variant, option);
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    private void selectDropdownVariant(String variant, String option) {
        if (variant == null && option == null) {
            int variantSize = getMultipleElement().withLocator("variant_menu_dropdown").size();
            for (int i = 0; i < variantSize; i++) {
                tapElements("variant_menu_dropdown", i);
            }
        } else {
            tapElement(constructLocator("product_detail_variant_dropdown", variant));
            tapElement(constructLocator("variant_option", option));
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    private void selectSelectionTabVariant(String variant, String option) {
        if (variant == null && option == null) {
            int variantSize = getMultipleElement().withLocator("variant_selection_tab_option").size();
            for (int i = 1; i <= variantSize; i++) {
                tapElement(constructLocator("variant_selection_tab_option", i));
                String opt = getElement().withLocator(
                        constructLocator("variant_selection_tab_option", i)).getAttribute("name");
                validateExist(constructLocator("variant_selected", opt));
            }
         } else {
            tapElement(constructLocator("variant_selection_tab_specific_option", variant, option));
            validateExist(constructLocator("variant_selected", option));
        }
    }

    public void validateNewVoucherSection() {
        swipeUpToElement("product_details_new_voucher_icon");
        verifyElementExist("product_details_new_voucher_icon");
    }

    public void swipeOnVoucherSection() {
        swipeLeftAtSpecifiedLocator("product_details_new_voucher_icon");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void clickChatButton() {
        waitForElementClickable("product_detail_chat_btn", 10);
        tapElement("product_detail_chat_btn");
        HelperData.setLastActionPage(new ChatRoomPage(iosDriver));
    }

    public void tapMerchandiseBanner() {
        swipeUpToElement("bukamall_reco_title", 15);
        tapElement("merchandise_banner");
        HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
    }

    public void verifyBannerNotDisplayed() {
        swipeUpToElement("bukamall_reco_title", 15);
        validateNotExist("merchandise_banner", 3);
    }

    public void validateRevampVariantSection(){
        swipeUpToElement("variant_section_revamp");
        verifyElementExist("variant_section_revamp");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapRevampVariantOption(){
        swipeUpToElement("variant_marun_option");
        waitForElementClickable("variant_marun_option", 10);
        RAGEData.setSelectedVariant(getText("variant_marun_option"));
        tapElement("variant_marun_option");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void verifyRevampVariantModalExist(){
        verifyElementExist("variant_selection_tab_title");
        verifyElementExist("variant_selection_tab_general_info");
        verifyElementExist("variant_selection_tab_section");
        verifyElementExist("variant_selection_tab_quantity");
    }

    public void verifyCTAButtonOnVariantModal(){
        verifyElementExist("variant_selection_tab_chat_button");
        verifyElementExist("variant_selection_tab_cart_button");
        verifyElementExist("variant_selection_tab_buy_button");
    }

    public void validateSpecificVariantSelected() {
        verifyElementExist("variant_selection_tab_option_marun_selected");
        validateValue().equals(RAGEData.getSelectedVariant(), getText("variant_selected_info"));
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void swipeOnVariantOptionSection() {
        swipeLeftAtSpecifiedLocator("variant_other_option");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void verifyPreorderButtonOnVariantModal(){
        verifyElementExist("variant_selection_tab_preorder_button");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapPaymentAds() {
        swipeUpToElement("payment_ads_section",1);
        tapElement("payment_ad_section");
    }

    public void checkPaymentAdsPage() {
        verifyElementExist("payment_ad_content");
    }

    public void tapCekSelengkapnya() {
        waitForElementClickable("button_cek_selengkapnya", 10);
        tapElement("button_cek_selengkapnya");
    }

    public void verifyVoucherId(){
        verifyElementExist("product_details_new_voucher_code");
    }

    public void goToMarketplacePage(){
        if (isElementExist("pdp_product_image") || isElementExist("pdp_product_name_text")) {
            validateExist("pdp_product_price_text", 15);
        } else {
            validateExist("pdp_empty_state_title", 15);
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }
}
