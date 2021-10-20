package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PXData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.security.SecureRandom;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by Odheta on 19/2/19.
 */
public class CreateProductPage extends BasePage {

    public CreateProductPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnCreateProductPage() {
        waitForElementClickable("create_product_header_jual_barang", 10);
        assertTrue(isElementVisible("create_product_header_jual_barang"));
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }

    private void bypassWithOK() {
        boolean autoOK = getAutoAcceptAlert();
        LogUtil.info("Auto OK : " + autoOK);
        if (isElementVisible("create_product_ok", 5) && !autoOK) {
            tapElement("create_product_ok");
        }
    }

    public void uploadProductImages() {
        if (isElementExist("create_product_allow_access",10)) {
            tapElement("create_product_allow_access");
            tapElement("create_product_get_folder");
        } else {
            tapElement("create_product_get_folder");
        }
        tapElement("create_product_pilih_gambar_satu");
        tapElement("create_product_lanjut_added_button");
        waitFor(20); //Element waiting for image perfectly uploaded
    }

    public void setProdName() {
        String prodName = PXData.generateRandomProductName("prodName");
        waitForVisibilityOf("create_product_nama_barang_text_field", 10);
        typeAndEnterValueWithTimeOut("create_product_nama_barang_text_field", prodName);
        PXData.setRandomProductName(prodName);
    }

    public void typeProdPrice() {
        waitForVisibilityOf("create_product_harga_barang_text_field", 30);
        typeAndEnterValueWithTimeOut("create_product_harga_barang_text_field", dotenv.get("HARGA_BARANG"));
    }

    public void typeProdStock() {
        typeAndEnterValueWithTimeOut("create_product_stok_barang_text_field", dotenv.get("STOK_BARANG"));
    }

    public void typeProdWeight() {
        typeAndEnterValueWithTimeOut("create_product_berat_barang_text_field", dotenv.get("BERAT_BARANG"));
    }

    public void typeProdDesc() {
        typeAndEnterValueWithTimeOut("create_product_kolom_deskripsi_barang", dotenv.get("DESKRIPSI_BARANG"));
        waitForElementClickable("create_product_simpan_deskripsi", 10);
        tapElement("create_product_simpan_deskripsi");
    }

    public void typeProdBrand() {
        typeAndEnterValueWithTimeOut("create_product_merk_field", dotenv.get("MERK_BARANG"));
        waitForElementClickable("create_product_simpan_deskripsi", 10);
        tapElement("create_product_simpan_deskripsi");
    }

    public void setProdVideo() {
        typeAndEnterValueWithTimeOut("create_product_video_text_field", dotenv.get("VIDEO_BARANG"));
        waitForElementClickable("create_product_simpan_deskripsi", 20);
        tapElement("create_product_simpan_deskripsi");
    }

    public void setProdAssurance() {
        swipeUpToElement("create_product_tap_pengiriman");
        tapElement("create_product_tap_pengiriman");
        waitForElementClickable("create_product_wajib_asuransi", 20);
        tapElement("create_product_wajib_asuransi");
        waitForElementClickable("create_product_ubah_nama_barang_simpan_button", 20);
        tapElement("create_product_ubah_nama_barang_simpan_button");
    }

    public void setGrosirPrice() {
        typeAndEnterValueWithTimeOut("create_product_jumlah_text_field", dotenv.get("JUMLAH_GROSIR_1"));
        typeAndEnterValueWithTimeOut("create_product_hargasatuan_text_field", dotenv.get("HARGA_SATUAN_1"));
        tapElement("create_product_tambah_harga");
        typeAndEnterValueWithTimeOut("create_product_jumlah_text_field", dotenv.get("JUMLAH_GROSIR_2"));
        typeAndEnterValueWithTimeOut("create_product_hargasatuan_text_field", dotenv.get("HARGA_SATUAN_2"));
    }

    public void setProdLabel() {
        swipeDownToElement("create_product_tambah_label_lain");
        tapElement("create_product_tambah_label_lain");
        typeAndEnterValueWithTimeOut("create_product_nama_label_text_field", dotenv.get("NAMA_LABEL"));
        typeAndEnterValueWithTimeOut("create_product_deskripsi_label_text_field", dotenv.get("DESKRIPSI_LABEL"));
        tapElement("create_product_tambah_label_button");
    }

    public void verifyCreateProdSuccess(String productName) {
        waitForVisibilityOf("create_product_preview", 20);
        tapElement("create_product_preview");
        waitForVisibilityOf("create_product_title", 20);
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }

    public void verifyProductonList(String productName) {
        waitForVisibilityOf("create_product_cari_barang", 20);
        tapElement("create_product_cari_barang");
        typeAndEnterValueWithTimeOut("create_product_cari_barang", productName);
        verifyElementNotExist("create_product_barang_tdk_ditemukan");
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }

    public void setProdCategory() {
        tapElement("create_product_tab_pilih_kategori");
        waitForVisibilityOf("create_product_category_suggestion", 20);
        tapElement("create_product_category_suggestion");
    }

    public void setProdCategorywithVariant() {
        tapElement("create_product_tab_pilih_kategori");
        tapElement("create_product_search_kategori");
        typeAndEnterValue("create_product_search_kategori", dotenv.get("KATEGORI_VARIAN"));
        tapElement("create_product_kategori_kaos_fashion_pria");
    }

    public void setSpecsVariantKaosPria() {
        typeAndEnterValueWithTimeOut("create_product_merk_field", dotenv.get("MEREK_KAOS"));
        tapElement("create_product_pilih_merk_kaos");
        tapElement("create_product_pilih_billabong");
        tapElement("create_product_pilih_tipe_kaos");
        tapElement("create_product_pilih_raglan");
        tapElement("create_product_pilih_kaos_size");
        tapElement("create_product_pilih_size_M");
        tapElement("create_product_pilih_size_L");
        tapElement("create_product_simpan_deskripsi");
        tapElement("create_product_simpan_deskripsi_barang");
    }

    public void setVariantKaosPria() {
        verifyElementExist("create_product_pilih_ukuran");
        tapElement("create_product_pilih_ukuran");
        tapElement("create_product_pilih_size_M");
        tapElement("create_product_pilih_selesai");
        verifyElementExist("create_product_pilih_warna");
        tapElement("create_product_pilih_warna");
        tapElement("create_product_pilih_warna_hitam");
        tapElement("create_product_pilih_warna_kuning");
        tapElement("create_product_pilih_selesai");
        verifyElementExist("create_product_pilih_bahan");
        tapElement("create_product_pilih_bahan");
        tapElement("create_product_pilih_bahan_katun");
        tapElement("create_product_pilih_selesai");
    }

    public void uploadEachProductVariant(String locator) {
        tapElement(locator);
        waitForVisibilityOf("create_product_variant_add_image", 10);
        tapElement("create_product_variant_add_image");
        bypassWithOK();
        tapElement("create_product_pilih_camera_roll");
        tapElement("create_product_pilih_gambar_satu");
        tapElement("create_product_lanjut_added_button");
        waitForElementClickable("create_product_simpan_variant", 30);
        tapElement("create_product_simpan_variant");
    }

    public void uploadNewProduct() {
        tapElement("create_product_jual_barang_button");
        waitForVisibilityOf("create_product_saya_mengerti_button", 20);
        tapElement("create_product_saya_mengerti_button");
    }

    public void uploadCloneProduct() {
        tapElement("clone_product_jual_barang_button");
        waitForVisibilityOf("barang_dijual_header_berhasil_unggah", 20);
        tapElement("clone_product_pop_up_berhasil_unggah");
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }

    public void verifyCreateProdVariantCorrect() {
        swipeDownToElement("create_product_verify_kategori");
        verifyElementExist("create_product_verify_kategori");
        verifyElementExist("create_product_verify_kondisi");
        verifyElementExist("create_product_verify_berat");
        verifyElementExist("create_product_verify_tipe");
        verifyElementExist("create_product_verify_ukuran");
        verifyElementExist("create_product_verify_merek");
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }

    public void verifyCreateProdWholesaleCorrect() {
        swipeDownToElement("create_product_label_grosir");
        verifyElementExist("create_product_label_grosir");
        verifyElementExist("create_product_grosir_jumlah_1");
        verifyElementExist("create_product_grosir_harga_1");
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void editProdName(String randomProdName) {
        typeAndEnterValueWithTimeOut("create_product_nama_barang_text_field", randomProdName);
        PXData.setRandomProductName(randomProdName);
    }

    public int getRandomNum(int range) {
        return new SecureRandom().nextInt(range);
    }

    public void isonPDPpage() {
        waitForElementClickable("create_product_benefit_ok", 20);
        tapElement("create_product_benefit_ok");
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }

    public void goToPreview() {
        waitForVisibilityOf("barang_dijual_header_berhasil_unggah", 10);
        tapElement("barang_dijual_preview_barang");
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }
    public void goToProductInfo() {
        swipeUpToElement("create_product_selengkapnya");
        tapElement("create_product_selengkapnya");
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
    }

    // Region change product's name
    private void checkEditNamaBarangPageDisplayed() {
        waitForVisibilityOf("create_product_ubah_nama_barang_simpan_button", 5);
        verifyElementDisplayed("create_product_ubah_nama_barang_field");
    }

    public void inputProductName(String productName) {
        HelperData.setLastActionPage(new CreateProductPage(iosDriver));
        tapElement("create_product_nama_barang_text");
        checkEditNamaBarangPageDisplayed();
        typeAndEnterValue("create_product_ubah_nama_barang_field", productName);
        tapElement("create_product_ubah_nama_barang_simpan_button");
    }

    public void goToCreateProductPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        userOnCreateProductPage();
    }

    public void validateFreeze(String freezeInfo, String selfUnfreezeBtn) {
        if (freezeInfo == null ){
            validateExist("create_product_freeze_label", 5);
            if (selfUnfreezeBtn == null){
                validateExist("create_product_self_unfreeze_btn", 5);
            } else {
                validateNotExist("create_product_self_unfreeze_btn", 3);
            }
        } else {
            validateNotExist("create_product_freeze_label", 3);
            validateNotExist("create_product_self_unfreeze_btn", 3);
        }
    }
}
