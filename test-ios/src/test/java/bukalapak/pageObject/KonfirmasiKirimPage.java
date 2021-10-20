package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class KonfirmasiKirimPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(KonfirmasiKirimPage.class);

    public KonfirmasiKirimPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnKonfirmasiKirimPage() {
        verifyElementExist("konfirmasi_kirim_title");
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    private void validateCourierServiceForManualReceipt(String courier) {
        validateDisplayed("Konfirmasi_kirim_pilih_kurir_text");
        validateDisplayed("Konfirmasi_kirim_kurir_manual_dropdown");
        tapElement("Konfirmasi_kirim_kurir_manual_dropdown");
        swipeUpToElement(constructLocator("konfirmasi_kirim_kurir_text", courier));
        validateDisplayed(constructLocator("konfirmasi_kirim_kurir_text", courier));
        tapElement("Konfirmasi_kirim_manual_close_popup");
    }

    private void validateCourierServiceForAutomatidReceipt(String courier) {
        validateDisplayed("konfirmasi_kirim_jasa_pengiriman_text");
        validateDisplayed(constructLocator("konfirmasi_kirim_kurir_text", courier));
    }

    public void validateCourierService(String courier, String isManual) {
        switch (courier) {
            case "Wahana Tarif Normal":
            case "TIKI Reg":
            case "Pos Kilat Khusus":
            case "Kurir Lapak":
                validateCourierServiceForManualReceipt(courier);
                break;
            case "J&T REG":
                if (isManual != null) {
                    tapElement("konfirmasi_kirim_resi_manual_tab");
                    validateCourierServiceForManualReceipt(courier);
                } else {
                    validateCourierServiceForAutomatidReceipt(courier);
                }
                break;
            case "NINJA REG":
            case "SiCepat REG":
            case "Indah Logistik REG":
            case "JNE REG":
            case "Lion Parcel REGPACK":
                validateCourierServiceForAutomatidReceipt(courier);
                break;
            default:
                LOGGER.info("courier service is not available, please change to another courier service");
                break;
        }

        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void validateOptionRecipt(String courier) {
        switch (courier) {
            case "J&T REG":
            case "Pos Kilat Khusus":
                validateDisplayed("konfirmasi_kirim_resi_otomatis_tab");
                validateDisplayed("konfirmasi_kirim_resi_manual_tab");
                break;
            case "Lion Parcel REGPACK":
            case "SiCepat REG":
            case "Indah Logistik REG":
            case "Wahana Tarif Normal":
            case "TIKI Reg":
            case "JNE REG":
                validateNotDisplayed("konfirmasi_kirim_resi_otomatis_tab");
                validateNotDisplayed("konfirmasi_kirim_resi_manual_tab");
                break;
            default:
                LOGGER.info("courier service is not available, please change to another courier service");
                break;
        }
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void validateShippingType(String courier) {
        String[] shippingType = {"Kurir Jemput Barang", "Datangi Kantor Kurir"};
        validateDisplayed("konfirmasi_kirim_datangi_kantor_kurir_radio_button");
        validateDisplayed("konfirmasi_kirim_kurir_jemput_barang_radio_button");
        validateDisplayed(constructLocator("konfirmasi_kirim_opsi_pengiriman_text", shippingType[0]));
        validateDisplayed(constructLocator("konfirmasi_kirim_opsi_pengiriman_text", shippingType[1]));
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void validateAlamatPenjemputan(String alamat) {
        validateDisplayed("konfirmasi_kirim_alamat_penjemputan_text");
        validateValue().equals(alamat, getTextFromElement("konfirmasi_kirim_detil_alamat_text", 0));
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void tapShippingType(String shippingType) {
        swipeDownToElement("konfirmasi_kirim_opsi_pengiriman_text");
        waitForVisibilityOf("konfirmasi_kirim_opsi_pengiriman_text");
        if (("Kurir Jemput Barang").equals(shippingType)) {
            if (getElementValue("konfirmasi_kirim_kurir_jemput_barang_radio_button").equals("false")) {
                tapElement("konfirmasi_kirim_kurir_jemput_barang_radio_button");
            }
        } else {
            if (getElementValue("konfirmasi_kirim_datangi_kantor_kurir_radio_button").equals("false")) {
                tapElement("konfirmasi_kirim_datangi_kantor_kurir_radio_button");
            }
        }
    }

    public void validateHowToShipping(DataTable langkahLangkah) {
        swipeUpToElement("konfirmasi_kirim_lihat_selengkapnya_button");
        List<List<String>> rows = langkahLangkah.asLists(String.class);
        for (List<String> columns : rows) {
            for (String label : columns) {
                validateDisplayed(constructLocator("konfirmasi_kirim_langkah_pengiriman_text", label));
                validateValue().equals(getElementValue(constructLocator("konfirmasi_kirim_langkah_pengiriman_text", label)), label);
            }
        }
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void validateTermAndCondition(String type, DataTable tnc) {
        List<List<String>> rows = tnc.asLists(String.class);
        for (List<String> columns : rows) {
            for (String label : columns) {
                validateDisplayed(constructLocator("konfirmasi_kirim_langkah_pengiriman_text", label));
                validateValue().equals(getElementValue(constructLocator("konfirmasi_kirim_langkah_pengiriman_text", label)), label);
            }
        }
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void validateNoteKonfirmationPage(String type, String note) {
        if ("automatic".equals(type)) {
            swipeUpToElement("konfirmasi_kirim_lihat_selengkapnya_button");
            validateDisplayed("konfirmasi_kirim_lihat_selengkapnya_button");
        }
        validateDisplayed(constructLocator("konfirmasi_kirim_note_text", note));
        validateValue().equals(note, getElementValue(constructLocator("konfirmasi_kirim_note_text", note)));
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void validateGenerateBooking(String label) {
        validateValue().equals(getElementLabel("konfirmasi_kirim_kode_booking_button"), label);
        validateDisplayed("konfirmasi_kirim_kode_booking_button");
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void validateManualResiField(String kodeBooking) {
        validateDisplayed("Konfirmasi_kirim_masukkan_nomor_resi_text");
        validateDisplayed("Konfirmasi_kirim_input_manual_resi_field");
        typeAndEnterValue("Konfirmasi_kirim_input_manual_resi_field", kodeBooking);
    }

    public void validateAlamatPengambilan(String address) {
        validateDisplayed("konfirmasi_kirim_alamat_pengambilan_text");
        validateValue().equals(address, getTextFromElement("Konfirmasi_kirim_detil_alamat_pengambilan_text"));
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void inputKodeUnikAmbilSendiri(String kodeUnik) {
        waitForVisibilityOf("Konfirmasi_kirim_kode_unik_text");
        tapElement("Konfirmasi_kirim_input_manual_resi_field");
        typeAndEnterValueWithTimeOut("Konfirmasi_kirim_input_manual_resi_field", kodeUnik);
    }

    public void clickProsesUniqueCodeAmbilSendiri() {
        waitForElementClickable("konfirmasi_kirim_kode_booking_button", 30);
        tapElement("konfirmasi_kirim_kode_booking_button");
    }

    public void validateBanner(String type, DataTable itemBanner) {
        int index = 0;
        if (getElementValue("konfirmasi_kirim_tampilkan_button").equalsIgnoreCase("Tampilkan")) {
            tapElement("konfirmasi_kirim_tampilkan_button");
        }
        List<List<String>> rows = itemBanner.asLists(String.class);
        for (List<String> columns : rows) {
            for (String label : columns) {
                validateValue().equals(label, getElementValue("konfirmasi_kirim_item_banner_education_text", index));
                index++;
            }
        }
        if ("Datangi Kantor Kurir".equalsIgnoreCase(type)) {
            validateNotDisplayed("konfirmasi_kirim_item_kurir_jemput_barang_text");
        }
        HelperData.setLastActionPage(new KonfirmasiKirimPage(iosDriver));
    }

    public void validateTitleEducationBanner(String status, String titleBanner) {
        if (status == null) {
            validateValue().equals(getElementValue("konfirmasi_kirim_title_banner_education_text"), titleBanner);
        } else {
            validateValue().notEquals(getElementValue("konfirmasi_kirim_title_banner_education_text"), titleBanner);
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
