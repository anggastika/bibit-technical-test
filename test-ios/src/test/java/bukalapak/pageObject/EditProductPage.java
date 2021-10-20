package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PXData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.time.LocalDate;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by Odheta on 19/3/19.
 */
public class EditProductPage extends BasePage {

    public EditProductPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnProductEditPage() {
        verifyElementExist("edit_product_ubah_info_title");
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void userOnProductEditPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        userOnProductEditPage();
    }

    public void editWithMenuLainnya() {
        tapElement("edit_product_menu_lainnya");
    }

    public void editProdName() {
        String prodNameEdited = PXData.generateRandomProductName("prodName");
        tapElement("edit_product_tab_nama_barang");
        waitForVisibilityOf("edit_product_nama_barang_text_field", 10);
        typeAndEnterValueWithTimeOut("edit_product_nama_barang_text_field", prodNameEdited);
        tapElement("edit_product_simpan_deskripsi_barang");
        PXData.setRandomProductName(prodNameEdited);
        PXData.setProductName(prodNameEdited);
    }

    public void editProdDesc(String prod_desc) {
        tapElement("edit_product_tab_deskripsi_barang");
        tapCenterOfElement("edit_product_deskripsi_barang_text_field");
        typeAndEnterValueWithTimeOut("edit_product_kolom_deskripsi_barang", dotenv.get(prod_desc));
        tapElement("edit_product_simpan_deskripsi_barang");
        tapElement("edit_product_simpan_deskripsi_barang");
    }

    public void editProdDetails() {
        tapElement("edit_product_tab_detail_barang");
        typeAndEnterValueWithTimeOut("edit_product_berat_barang_text_field", dotenv.get("EDITED_BERAT_BARANG"));
        typeAndEnterValueWithTimeOut("edit_product_stok_barang_text_field", dotenv.get("EDITED_STOK_BARANG"));
        typeAndEnterValueWithTimeOut("edit_product_harga_barang_text_field", dotenv.get("EDITED_HARGA_SATUAN_BARANG"));
        tapElement("edit_product_simpan_deskripsi_barang");
    }

    public void verifyEditProdNameSuccess(String productName) {
        delay(5000); //need to load product info
        verifyElementExist(constructLocator("edit_product_edited_name", productName), 10, "Product name is not " + productName);
    }

    public void verifyEditProdSuccess() {
        verifyElementExist("edit_product_verify_berat");
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void editProductName() {
        String prodNameEdited = PXData.generateRandomProductName("prodName");
        if (isElementVisible("edit_product_tab_nama_barang_name", 15)) {
            tapElement("edit_product_tab_nama_barang_name");
        } else if (isElementVisible("edit_product_tab_nama_barang_label", 15)){
            tapElement("edit_product_tab_nama_barang_label");
        } else if (isElementVisible("edit_product_tab_nama_barang_name_xpath", 15)){
            tapElement("edit_product_tab_nama_barang_name_xpath");
        }

        waitForVisibilityOf("edit_product_nama_barang_text_field", 10);
        typeAndEnterValueWithTimeOut("edit_product_nama_barang_text_field", prodNameEdited);
        tapElement("edit_product_simpan_deskripsi_barang");
        PXData.setRandomProductName(prodNameEdited);
        PXData.setProductName(prodNameEdited);
    }

    public void visitProductTidakDijual() {
        swipeUpToElement("edit_product_barang_jualan_saya_menu");
        swipeDownToClickableElement("edit_product_tidak_dijual_tab");
        tapElement("edit_product_tidak_dijual_tab");
    }

    public void visitProductBarangDijual() {
        swipeUpToElement("edit_product_barang_jualan_saya_menu");
        swipeDownToClickableElement("edit_product_barang_dijual_tab");
        tapElement("edit_product_barang_dijual_tab");
    }

    public void verifyProductonListTidakDijual(String productName) {
        waitForVisibilityOf("edit_product_cari_barang_tdk_dijual", 20);
        tapElement("edit_product_cari_barang_tdk_dijual");
        typeAndEnterValue("edit_product_cari_barang_tdk_dijual", productName);
        verifyElementNotExist("edit_product_barang_tdk_ditemukan");
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void verifyProductonListBarangDijual(String productName) {
        waitForVisibilityOf("edit_product_cari_barang_dijual", 20);
        tapElement("edit_product_cari_barang_dijual");
        typeAndEnterValue("edit_product_cari_barang_dijual", productName);
        verifyElementNotExist("edit_product_barang_tdk_ditemukan");
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void verifyProductDeletedonListBarangDijual(String productName) {
        waitForVisibilityOf("edit_product_cari_barang_dijual", 20);
        tapElement("edit_product_cari_barang_dijual");
        typeAndEnterValue("edit_product_cari_barang_dijual", productName);
        verifyElementExist("edit_product_barang_tdk_ditemukan");
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void saveProductNameandPrice() {
        waitForVisibilityOf("edit_product_product_name", 30);
        PXData.setProductName(getTextFromElement("edit_product_product_name"));
        PXData.setProductPrice(getTextFromElement("edit_product_product_price"));
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void setRandomProductNameText() {
        String randomName = PXData.generateRandomProductName("prodName");
        PXData.setRandomProductName(randomName);
    }

    public void updateFreeOngkir() {
        String randomName = PXData.generateRandomProductName("prodName");
        PXData.setRandomProductName(randomName);
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void setSalinBarang() {
        swipeDownToClickableElement("edit_product_salin_barang");
        tapElement("edit_product_salin_barang");
    }

    public void backToBarangDijual() {
        waitFor(15);
        isElementVisible("base_back_button", 30);
        tapElement("base_back_button");
    }

    public void verifyCloneProdNameSuccess(String productName) {
      try {
        delay(5000); //need to load product info
        verifyElementExist(constructLocator("edit_product_edited_name", productName), 10, "Product name is not " + productName);
      } catch (AssertionError e) {
        doPullRefresh(1);
        verifyElementExist(constructLocator("edit_product_edited_name", productName), 10, "Product name is not " + productName);
        }
       HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void clickSetDiscount() {
        // need waitFor to handle timeout when fetching data
        waitFor(20);
        if (isElementVisible("set_diskon_menu_lainnya", 10)) {
            tapElement("set_diskon_menu_lainnya");
        } else {
            deleteDiscount();
            editWithMenuLainnya();
            clickSetDiscount();
        }
    }

    public void setDiscount(String discountNom) {
        // PXData.setProductName(getTextFromElement("edit_product_verify_name"));
        waitForVisibilityOf("set_diskon_text_field", 20);
        tapElement("set_diskon_text_field");
        typeAndEnterValueWithTimeOut("set_diskon_text_field", discountNom);
    }

    public void setTanggalMulaiDiskon() {
        tapElement("base_back_button");
    }

    public void setTanggalBerakhirDiskon() {
        tapElement("tanggal_berakhir_diskon_button");
        tapElement("base_back_button");
        tapElement("set_diskon_button");
        waitForVisibilityOf("barang_dijual_filter_button", 15);
    }

    public void deleteDiscount() {
        waitForVisibilityOf("hapus_diskon_button", 15);
        tapElement("hapus_diskon_button");
        waitFor(15);
    }

    public void verifySetFreeOngkirSuccess() {
        if (isElementVisible("edit_product_cek_ongkir", 30)) {
            tapElement("edit_product_cek_ongkir");
            waitForVisibilityOf("edit_product_gratis_ongkir", 30);
        } 
        else {
            waitForVisibilityOf("edit_product_no_ongkir", 30);
            tapElement("edit_product_no_ongkir");
            verifyElementExist("edit_product_gratis_ongkir_pelapak");
        }

    }

    public void setDatePicker() {
        String startDate = LocalDate.now().toString().substring(8, 10);
        String endDate = LocalDate.now().plusDays(1L).toString().substring(8,10);
        if (startDate.startsWith("0")){
            startDate = startDate.substring(1,2);
        }
        waitForVisibilityOf("edit_product_pilih_tanggal_buka", 20);
        tapElement("edit_product_pilih_tanggal_buka");
        tapElement(constructLocator("edit_product_start_date", startDate));

        if (endDate.startsWith("0")){
            endDate = endDate.substring(1,2);
        }
        tapElement("edit_product_pilih_tanggal_tutup");
        tapElement(constructLocator("edit_product_end_date", endDate));
        tapElement("edit_product_simpan_deskripsi_barang");
    }

    public void cancelTutupLapak() {
        waitForVisibilityOf("edit_product_buka_lapak", 20);
        tapElement("edit_product_buka_lapak");
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void verifyProdDescription() {
        waitForVisibilityOf("edit_product_description_display", 10);
        verifyElementExist("edit_product_description_display");
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void updateCourier() {
        waitForVisibilityOf("jasa_pengiriman_title", 10);
        if (getElementValue("jasa_pengiriman_ninja_reg").equals("0")){
            tapElement("jasa_pengiriman_ninja_reg");
            if (isElementVisible("jasa_pengiriman_header_ubah", 10)){
                tapElement("jasa_pengiriman_lanjutkan");
            }
        }
        if (getElementValue("jasa_pengiriman_sicepat_reg").equals("0")){
            tapElement("jasa_pengiriman_sicepat_reg");
        }
        tapElement("edit_product_simpan_deskripsi_barang");
    }

    public void verifyProductCourier() {
        tapElement("create_product_tab_ongkir");
        verifyElementExist("edit_product_cek_ongkir_header");
        verifyElementExist("edit_product_cek_sicepat");
        verifyElementExist("edit_product_cek_ninjareg");
        HelperData.setLastActionPage(new EditProductPage(iosDriver));
    }

    public void openDeliveryMenu() {
        swipeUpToElement("edit_product_delivery_button");
        tapElement("edit_product_delivery_button");
        verifyElementExist("edit_product_sla_header");
    }

    public void tapUbahButton() {
        if (isElementVisible("edit_product_ubah")) {
            tapElement("edit_product_ubah");
        }
    }

    public void tapOnSaveButton() {
        verifyElementExist("edit_product_simpan_deskripsi_barang");
        tapElement("edit_product_simpan_deskripsi_barang");
        verifyElementNotExist("edit_product_simpan_deskripsi_barang");
    }
}
