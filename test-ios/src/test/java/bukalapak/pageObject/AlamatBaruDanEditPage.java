package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by faida royani on 28/12/18.
 */

public class AlamatBaruDanEditPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(AlamatBaruDanEditPage.class);

    public AlamatBaruDanEditPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnAlamatBaruPage() {
        allowPopup();
        if (isElementVisible("alamat_baru_title")) {
            verifyElementExist("alamat_baru_title");
        } else {
            verifyElementExist("alamat_baru_edit_alamat_title");
        }
    }

    private void selectElementTable(List<MobileElement> table, String input) {
        for (MobileElement element : table) {
            String temp = element.getText();
            if (temp.equals(input)) {
                element.click();
                break;
            }
        }
    }

    public void selectLabelAlamat(String label) {
        List<MobileElement> table;
        waitForVisibilityOf("alamat_baru_dropdoown_label_address_button");
        tapElement("alamat_baru_dropdoown_label_address_button");
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeStaticText");
        selectElementTable(table, label);
    }

    public void fillLebelAlamat(String labelAlamat) {
        typeAndEnterValueWithTimeOut("alamat_baru_pilih_label_alamat_text", labelAlamat);
    }

    public void fillnoTlp(String labelAlamat) {
        typeAndEnterValueWithTimeOut("alamat_baru_telpon_penerima_fieldtext", labelAlamat);
    }

    public void chooseProvinsi(String provinsi) {
        List<MobileElement> table;
        tapElement("alamat_baru_pilih_provinsi_dropdown");
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeStaticText");
        selectElementTable(table, provinsi);
    }

    public void chooseKota(String kota) {
        List<MobileElement> table;
        if (!(isElementVisible("alamat_baru_kota_text", 1))) {
            swipeDownToElement("alamat_baru_kota_text");
        }
        tapElement("alamat_baru_pilih_kota_dropdown");
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeStaticText");
        selectElementTable(table, kota);
    }

    public void chooseKecamatan(String kecamatan) {
        List<MobileElement> table;
        if (!(isElementVisible("alamat_baru_kecamatan_text", 1))) {
            swipeDownToElement("alamat_baru_kecamatan_text");
        }
        tapElement("alamat_baru_pilih_kecamatan_dropdown");
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeStaticText");
        selectElementTable(table, kecamatan);
    }

    public void chooseKodePos(String kodepos) {
        List<MobileElement> table;
        if (!(isElementVisible("alamat_baru_kodepos_text", 1))) {
            swipeDownToElement("alamat_baru_kodepos_text");
        }
        tapElement("alamat_baru_pilih_kode_pos_dropdown");
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeStaticText");
        selectElementTable(table, kodepos);
    }

    public void setLokasiKoordinat(String kecamatan) {
        tapElement("alamat_baru_map_button");
        waitForVisibilityOf("alamat_baru_set_lokasi_button");
        tapElement("alamat_baru_set_lokasi_button");
    }

    public void fillAlamatLengkap(String alamatLengkap) {
        if (!(isElementVisible("alamat_baru_pilih_alamat_lengkap_text", 1))) {
            swipeUpToElement("alamat_baru_pilih_alamat_lengkap_text");
        }
        typeAndEnterValueWithTimeOut("alamat_baru_pilih_alamat_lengkap_text", alamatLengkap);
    }

    public void inputRandomZipCode(String zipcode) {
        swipeDownToElement("alamat_baru_pilih_kode_pos_dropdown");
        tapElement("alamat_baru_pilih_kode_pos_dropdown");
        waitForVisibilityOf("edit_alamat_random_zipcode_option");
        tapElement("edit_alamat_random_zipcode_option");
        typeAndEnterValueWithTimeOut("alamat_baru_pilih_kode_pos_dropdown", zipcode);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickTambahButtonAlamat() {
        waitForVisibilityOf("alamat_baru_edit_alamat_page");
        waitForVisibilityOf("alamat_tambah_button");
        tapElement("alamat_tambah_button");
    }

    private static void selectItemContain(List<MobileElement> table, String alamat) {
        for (MobileElement element : table) {
            String temp = element.getText();
            if (temp.contains(alamat)) {
                LOGGER.info("found the item");
                element.click();
                break;
            }
        }
    }

    public void searchAlamatInMap(String alamat) {
        waitFor(30); //wait map load properly
        if (isElementExist("home_allow_location_button", 5)) {
            tapElement("home_allow_location_button");
        }
        try {
            waitForVisibilityOf("home_close_debug_confirmation_ok_button", 10);
            tapElement("home_close_debug_confirmation_ok_button");
            LOGGER.info("tap button OK");
        } catch (Exception e) {
            LOGGER.info("OK button not available");
        }
        changeContext().toWebview();
        verifyElementDisplayed("alamat_baru_edit_koordinat_alamat_web_view_text");
        webViewTapOnElement("alamat_baru_edit_search_input_web_view_field");
        typeValue("alamat_baru_edit_search_input_web_view_field", alamat);
        waitFor(10);
        if (isElementExist("alamat_baru_edit_search_result_web_view_text")) {
            LOGGER.info("result search");
            webViewTapOnElement("alamat_baru_edit_search_result_web_view_text");
        }
        waitFor(10); // waiting map move to location
        webViewTapOnElement("alamat_baru_edit_set_lokasi_web_view_button");
        //sometime, tap on element set lokasi is not working
        if (isElementExist("alamat_baru_edit_koordinat_alamat_web_view_text")) {
            webViewTapOnElement("alamat_baru_edit_set_lokasi_web_view_button");
        }
        changeContext().toNative();
    }

    public void searchRegion(String region) {
        nativeSwipeDown();
        nativeSwipeDown();
        typeAndEnterValue("alamat_baru_edit_cari_wilayah_text", region);
        waitForVisibilityOf("alamat_baru_edit_list_international_text", 20);
        tapResultRegion(region);
        if (isElementExist("alamat_baru_edit_list_international_text")) {
            tapResultRegion(region);
            LOGGER.info("tap again");
        }
    }

    private void tapResultRegion(String region) {
        List<MobileElement> table;
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeButton");
        if (!table.isEmpty()) {
            selectItemContain(table, region);
        }
    }

    public void tapSimpanButton() {
        waitForElementClickable("alamat_baru_simpan_button", 20);
        tapElement("alamat_baru_simpan_button");
    }

    public void fillEditFullAddress(String fullAddress) {
        if (!(isElementVisible("alamat_baru_alamat_lengkap_text", 1))) {
            swipeDownToElement("alamat_baru_alamat_lengkap_text");
        }
        typeAndEnterValueWithTimeOut("alamat_baru_pilih_edit_alamat_lengkap_text", fullAddress);
    }

    public void validateWarningMessage(String warningMessage) {
        validateValue().equals(getElementValue("alamat_baru_edit_banner_text"), warningMessage);
        HelperData.setLastActionPage(new AlamatBaruDanEditPage(iosDriver));
    }

    public void validateGeocoderInformation(String region) {
        waitForVisibilityOf("alamat_baru_edit_result_search_text", 20);
        LOGGER.info("info :" + getElementValue("alamat_baru_edit_result_search_text"));
        validateValue().equals(getElementValue("alamat_baru_edit_result_search_text"), region);
        HelperData.setLastActionPage(new AlamatBaruDanEditPage(iosDriver));
    }
}
