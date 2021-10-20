package bukalapak.pageObject.bee.bukaSend.Native;

import bukalapak.data.BeeData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaSendPage extends BasePage {

    public BukaSendPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void bukaSendOnBoarding() {
        dragElement("login_notif_berhasil", "alchemy_back_button");
    }

    public void sendPackage() {
        tapElements("bukasend_kirim_barang_button", 0);
    }

    public void inputAlamatPenerima(String alamatPenerima, String detailAlamat) {
        BeeData.setAlamatPenerima(alamatPenerima);
        BeeData.setDetailAlamat(detailAlamat);
        tapElement("bukasend_cari_alamat_penerima_button");
        typeAndEnterValueWithTimeOut("bukasend_cari_alamat_penerima", BeeData.getAlamatPenerima());
        waitFor(5);
        tapElement("bukasend_alamat_search_result1");
        try {
            allowPopup();
        } catch (Exception e) {
            LogUtil.info("there is no allow pop up!");
        }
        tapElement("back_button");
        tapElement("bukasend_alamat_search_result1");
        typeAndEnterValueWithTimeOut("bukasend_detail_alamat", BeeData.getDetailAlamat());
        typeAndEnterValueWithTimeOut("bukasend_alamat_penerima", BeeData.getAlamatPenerima());
    }

    public void fromAlamatToPackagePage() {
        tapElement("bukasend_gunakan_alamat_button");
    }

    public void chooseAlamatPengirim(String alamatPengirirm) {
        BeeData.setAlamatPengirim(alamatPengirirm);
        tapElement("bukasend_alamat_pengirim_button", 5);
        tapElement(constructLocator("bukasend_pilih_kurir_option", BeeData.getAlamatPengirim()));
    }

    public void inputWeight(String weight) {
        BeeData.setWeight(weight);
        typeAndEnterValueWithTimeOut("bukasend_berat_paket", BeeData.getWeight());
    }

    public void inputPackageInfo(String packageinfo) {
        BeeData.setPackageInfo(packageinfo);
        typeAndEnterValueWithTimeOut("bukasend_isi_paket", BeeData.getPackageInfo());
    }

    public void chooseCourier(String courier) {
        BeeData.setCourier(courier);
        tapElement("bukasend_dijemput_kurir");
        swipeDownToElement("bukasend_kurir_otomatis_pickup");
        tapElement("bukasend_kurir_otomatis_pickup");
        //propertyUtil.setDynamicElementValue(BeeData.getCourier());
        swipeDownToElement(constructLocator("bukasend_pilih_kurir_option", BeeData.getCourier()));
        tapElement(constructLocator("bukasend_pilih_kurir_option", BeeData.getCourier()));
        String getPrice = getTextFromElement("bukasend_pilih_kurir_option_harga");
        String price = getPrice.substring(getPrice.indexOf("Rp") + 2);
        BeeData.setPrice(price);
        tapElement("bukasend_gunakan_kurir_button");
    }

    public void fillReceiverAddress() {
        tapElement("bukasend_cari_alamat_penerima_button");
        verifyElementExist("bukasend_postal_code_button");
    }

    public void fillReceiverDetail(String name, String phone) {
        BeeData.setName(name);
        BeeData.setPhone(phone);
        typeAndEnterValueWithTimeOut("bukasend_nama_penerima", BeeData.getName());
        typeAndEnterValueWithTimeOut("bukasend_no_telepon_penerima", BeeData.getPhone());
    }

    public void validateShippingList(String price, String totalPrice) {
        BeeData.setPrice(price);
        BeeData.setTotalPrice(totalPrice);
        swipeUpToElement("bukasend_lihat_daftar_pengiriman_button");
        tapElement("bukasend_lihat_daftar_pengiriman_button");
        int data = BeeData.getElementCount(3);
        swipeDownToElement(getText(constructLocator("bukasend_elm_1", data)));
        assertTextContains(getText(constructLocator("bukasend_elm_3", data)), BeeData.getName());
        assertTextContains(BeeData.getAlamatPenerima(), getText(constructLocator("bukasend_elm_4", data)));
        assertTextContains(BeeData.getPhone(), getText(constructLocator("bukasend_elm_5", data)));
        assertTextContains(BeeData.getWeight(), getText(constructLocator("bukasend_elm_6", data)));
        assertTextContains(BeeData.getPackageInfo(), getText(constructLocator("bukasend_elm_7", data)));
        assertTextContains("Dijemput Kurir", getText(constructLocator("bukasend_elm_8", data)));
        assertTextContains(BeeData.getCourier(), getText(constructLocator("bukasend_elm_9", data)));
        assertTextContains(BeeData.getTotalPrice(), getText("bukasend_total_pembayaran"));
    }

    public void validateCheckoutPage() {
        dragElement("bukasend_notif_berhasil", "bukasend_total_pembayaran");
        tapElement("bukasend_lanjutkan_pembayaran_button");
        swipeUpToElement("bukasend_checkout_price");
        assertTextContains(BeeData.getTotalPrice(), getText("bukasend_checkout_price"));
    }

    public void fromCheckoutBukaSendToHome() {
        tapElement("alchemy_back_button");
        tapElement("popup_alert_ya_button");
        tapElement("alchemy_back_button");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void addMorePackage() {
        dragElement("bukasend_notif_berhasil", "bukasend_total_pembayaran");
        tapElement("bukasend_tambahpaket_button");
    }

    public void tapCekTarifDiSini() {
        swipeUpToElement("bukasend_cek_tarif_di_sini");
        tapElement("bukasend_cek_tarif_di_sini");
    }

    public void inputLokasi(String lokasi) {
        typeAndEnterValueWithTimeOut("bukasend_cari_daerah", lokasi);
        tapElement("bukasend_tap_daerah");
    }

    public void seeResultTarif() {
        tapElement("bukasend_cek_tarif_button");
        verifyElementExist("bukasend_lanjut_button");
        verifyElementExist("bukasend_cek_tarif_text");
        verifyElementExist("bukasend_hari_kerja");
        verifyElementExist("bukasend_rupiah");
    }

    public void setWeightPackageTarif(String setWeightTarif) {
        BeeData.setWeight(setWeightTarif);
        typeAndEnterValueWithTimeOut("bukasend_berat_paket_tarif", setWeightTarif);
    }

    public void userExit() {
        if (isElementExist("exit_cek_tarif", 5)){
            tapElement("exit_cek_tarif");
        }
        while (isElementExist("exit_bukasend", 5)){
            tapElement("exit_bukasend");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void seeDescriptionCSV() {
        verifyElementExist("bukasend_upload_csv_text");
        verifyElementExist("bukasend_upload_csv_button");
    }

    public void validateAndClickButton() {
        waitForVisibilityOf("bukasend_upload_csv_landings", 30);
        verifyElementExist("bukasend_upload_csv_landings");
        tapElement("bukasend_upload_csv_landings");
    }

    public void seeDestinationLocation() {
        verifyElementExist("bukasend_tujuan_pengiriman_text");
    }

    public void tapAjukanKerjaSama() {
        swipeUpToElement("bukasend_partnership_text");
        tapElement("bukasend_partnership_text");
    }

    public void seeFailedMessage() {
        verifyElementDisplayed("bukasend_partnership_message_failed");
        verifyElementDisplayed("bukasend_partnership_name_failed");
        verifyElementDisplayed("bukasend_partnership_corporate_failed");
        verifyElementDisplayed("bukasend_partnership_phone_number_failed");
        swipeUpToElement("bukasend_partnership_notes_failed");
        verifyElementDisplayed("bukasend_partnership_notes_failed");
    }
  
    public void tapAndInputNamePartner(String namePartner) {
        waitForVisibilityOf("bukasend_partnership_name_field",30);
        verifyElementExist("bukasend_partnership_name_field");
        tapElement("bukasend_partnership_name_field");
        typeAndEnterValueWithTimeOut("bukasend_partnership_name_field",namePartner);
    }

    public void tapAndInputNameCorporate(String nameCorporate) {
        waitForVisibilityOf("bukasend_partnership_corporate_field",30);
        verifyElementExist("bukasend_partnership_corporate_field");
        tapElement("bukasend_partnership_corporate_field");
        typeAndEnterValueWithTimeOut("bukasend_partnership_corporate_field",nameCorporate);
    }

    public void tapAndInputPhonePartner(String phonePartner) {
        waitForVisibilityOf("bukasend_partnership_phone_number_field",30);
        verifyElementExist("bukasend_partnership_phone_number_field");
        tapElement("bukasend_partnership_phone_number_field");
        typeAndEnterValueWithTimeOut("bukasend_partnership_phone_number_field",phonePartner);
    }

    public void tapAndInputKeterangan(String inputKeterangan) {
        waitForVisibilityOf("bukasend_partnership_keterangan_field",30);
        verifyElementExist("bukasend_partnership_keterangan_field");
        tapElement("bukasend_partnership_keterangan_field");
        typeAndEnterValueWithTimeOut("bukasend_partnership_keterangan_field",inputKeterangan);
    }

    public void seeSuccessMessage() {
        waitForVisibilityOf("bukasend_partnership_terkirim_message", 30);
        verifyElementDisplayed("bukasend_partnership_terkirim_message");
        verifyElementDisplayed("bukasend_partnership_terkirim");
        verifyElementDisplayed("bukasend_partnership_oke");
        tapElement("bukasend_partnership_oke");
    }

    public void tapLanjutKirimButton() {
        tapElement("bukasend_lanjut_button");
        verifyElementDisplayed("bukasend_kirim_paket_text");
        verifyElementDisplayed("bukasend_alamat_penerima_text");
        verifyElementDisplayed("bukasend_detail_paket_text");
    }

    public void tapAndBackToLandingPage() {
        verifyElementExist("bukasend_upload_csv_button");
        tapElement("bukasend_upload_csv_button");
        verifyElementExist("bukasend_landing_page_title");
        verifyElementExist("bukasend_landing_page_sub_title");
        verifyElementExist("bukasend_upload_csv_landings");
        HelperData.setLastActionPage(new BukaSendPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void setSendingType(String type) {
        if (type.equals("Kode Pos")) {
            tapElement("bukasend_postal_code");
        } else {
            tapElement("bukasend_area_code");
        }
    }

    public void inputPostalCode(String postalCode) {
        typeAndEnterValueWithTimeOut("bukasend_postal_code_input", postalCode);
    }

    public void inputAlamatPenerimaManual(String alamatPenerima) {
        BeeData.setAlamatPenerima(alamatPenerima);
        swipeUpToElement("bukasend_continue_button");
        typeAndEnterValueWithTimeOut("bukasend_address_manual_input", alamatPenerima);
        tapElement("bukasend_continue_button");
    }
}

