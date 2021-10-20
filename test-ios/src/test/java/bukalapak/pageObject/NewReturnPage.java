package bukalapak.pageObject;

import bukalapak.data.CSIData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class NewReturnPage extends BasePage {

    public NewReturnPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);}

    public void userOnPengajuanKomplainPage() {
        waitForVisibilityOf("pengajuan_komplain_title");
        waitForVisibilityOf("masalah_komplain_title");
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void userChooseReasonBarangRusak() {
        waitForVisibilityOf("alasan_komplain_barang_rusak", 30);
        tapElement("alasan_komplain_barang_rusak");
        tapElement("lanjut_pengajuan_komplain");
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void uploadImageReturn() {
        waitForVisibilityOf("isi_detail_masalah_retur_page");
        waitForVisibilityOf("foto_bukti_barang_bermasalah");
        waitForVisibilityOf("upload_image_return");
        waitForVisibilityOf("deskripsi_masalah_lbl");
        tapElement("upload_image_return");
        waitForVisibilityOf("choose_image_return", 10);
        tapElement("choose_image_return");
        tapElement("choose_image_return_to_upload");
        tapElement("lanjut_retur");
        waitFor(10); // wait for animation to finish
        waitForVisibilityOf("image_return");
        typeAndEnterValue("deskripsi_masalah_return", "new return");
        tapElement("lanjut_retur");
    }

    public void userOnPilihSolusiPage() {
        waitForVisibilityOf("detail_solusi_komplain_page", 10);
        waitForVisibilityOf("solusi_komplain_label", 10);
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void chooseRefundSolution() {
        tapElement("dropdown_solusi");
        tapElement("pengembalian_uang_radio");
        tapElement("terapkan_btn");
        waitForVisibilityOf("maksimum_pengembalian_lbl");
        tapElement("kembalikan_semua_radiobutton");
        tapElement("lanjut_retur");
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void chooseReplaceSolution() {
        tapElement("dropdown_solusi");
        waitForVisibilityOf("penggantian_barang_radio");
        tapElement("penggantian_barang_radio");
        tapElement("terapkan_btn");
        waitForVisibilityOf("lanjut_retur");
        tapElement("lanjut_retur");
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void chooseReplaceWithoutNext() {
        tapElement("dropdown_solusi");
        waitForVisibilityOf("penggantian_barang_radio");
        tapElement("penggantian_barang_radio");
        tapElement("terapkan_btn");
    }

    public void chooseAdditionSolution() {
        tapElement("dropdown_solusi");
        waitForVisibilityOf("penambahan_barang_radio");
        tapElement("penambahan_barang_radio");
        tapElement("terapkan_btn");
        waitForVisibilityOf("lanjut_retur");
        tapElement("lanjut_retur");
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void userOnKonfirmasiPengajuanKomplainPage() {
        waitForVisibilityOf("konfirmasi_pengajuan_komplain_lbl");
        waitForVisibilityOf("checkbox_setuju_retur");
        tapElement("checkbox_setuju_retur");
        tapElement("ajukan_komplain_btn");
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void userOnNewReturnPage() {
        waitForVisibilityOf("diskusi_komplain_lbl");
        tapElement("detail_return_link", 5);
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void userONewReturnDetailPage(){
        waitForVisibilityOf("detail_komplain_lbl");
        waitForVisibilityOf("status_komplain_lbl");
        waitForVisibilityOf("detail_status_komplain_lbl");
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void cancelSubmitRetur() {
        waitForVisibilityOf("back_pengajuan_komplain");
        tapElement("back_pengajuan_komplain");
        waitForVisibilityOf("modals_batalkan_komplain");
        tapElement("batalkan_button");
    }

    public void changeToReplaceSolution() {
        waitForVisibilityOf("solusi_komplain_label");
        tapElement("dropdown_solusi");
        waitForVisibilityOf("penambahan_barang_radio");
        tapElement("penambahan_barang_radio");
        tapElement("terapkan_btn");
        waitForVisibilityOf("simpan_solusi_button");
        tapElement("simpan_solusi_button");
    }

    public void validateReplacementSolution() {
        waitForVisibilityOf("konfirmasi_pengajuan_komplain_lbl");
        assertEquals("Penggantian Barang", getTextFromElement("label_solusi_komplain"));
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void changeReturnSolution(String solution) {
        waitForVisibilityOf("solusi_komplain_label");
        tapElement("dropdown_solusi");
        waitForVisibilityOf("pilih_solusi_label",5);
        if (solution.equals("refund")) {
            tapElement("pengembalian_uang_radio");
        } else if (solution.equals("penambahan barang")) {
            tapElement("penambahan_barang_radio");
        } else {
            tapElement("penggantian_barang_radio");
        }
        tapElement("terapkan_btn");
        waitForVisibilityOf("checkbox_setuju_retur",5);
        tapElement("checkbox_setuju_retur");
        tapElement("simpan_solusi_button");
    }

    public void tapComplaintSolution() {
        waitForVisibilityOf("konfirmasi_pengajuan_komplain_lbl");
        tapElement("solusi_komplain_section");
    }

    public void tapdetailprolem() {
        waitForVisibilityOf("konfirmasi_pengajuan_komplain_lbl");
        tapElement("detai_problem_section");
    }

    public void validateDesc(String description) {
        waitForVisibilityOf("konfirmasi_pengajuan_komplain_lbl");
        assertEquals(description, getTextFromElement("label_description_complaint"));
    }

    public void uploadOtherImageReturn() {
        waitForVisibilityOf("isi_detail_masalah_retur_page");
        waitForVisibilityOf("add_image");
        tapElement("add_image");
        waitForVisibilityOf("choose_image_return");
        tapElement("choose_image_return");
        tapElement("choose_other_image");
        tapElement("lanjut_retur");
        waitFor(10); // wait for animation to finish
        waitForVisibilityOf("image_return");
    }

    public void validateErrorMsg(String message) {
        waitForVisibilityOf("error_msg_solution");
        assertEquals(message, getTextFromElement("label_error_solution"));
    }

    public void refundWithFillNominal() {
        tapElement("dropdown_solusi");
        tapElement("pengembalian_uang_radio");
        tapElement("terapkan_btn");
        waitForVisibilityOf("radio_nominal_lain");
        tapElement("radio_nominal_lain");
        String maxnominal = getTextFromElement("field_nominal");
        typeAndEnterValue("field_nominal", maxnominal+"9");
        tapElement("lanjut_retur");
        HelperData.setLastActionPage(new NewReturnPage(iosDriver));
    }

    public void showErrorMaxNominal() {
        waitForVisibilityOf("error_nominal");
    }

    public void uploadTwoImage() {
        waitForVisibilityOf("isi_detail_masalah_retur_page");
        waitForVisibilityOf("foto_bukti_barang_bermasalah");
        waitForVisibilityOf("upload_image_return");
        waitForVisibilityOf("deskripsi_masalah_lbl");
        tapElement("upload_image_return");
        waitForVisibilityOf("choose_image_return", 10);
        tapElement("choose_image_return");
        tapElement("choose_image_return_to_upload");
        tapElement("choose_other_image");
        tapElement("lanjut_retur");
        waitFor(10); // wait for animation to finish
        waitForVisibilityOf("image_return");
        typeAndEnterValue("deskripsi_masalah_return", "new return");
        tapElement("lanjut_retur");
    }

    public void validateCountImage(String infoImage) {
        waitForVisibilityOf("konfirmasi_pengajuan_komplain_lbl");
        assertEquals(infoImage, getTextFromElement("label_count_image"));
    }

    public void tapKomplainButton() {
        swipeUpToElement("detail_pembelian_komplain_button");
        tapElement("detail_pembelian_komplain_button");
    }

    public void validatePhotosErrorMsg(String error) {
        waitForVisibilityOf("error_msg_photos");
        assertEquals(error, getTextFromElement("error_msg_photos"));
    }

    public void chooseAddress() {
        CSIData.setAddressChanged(getTextFromElement("second_address"));
        tapElement("second_address_radio");
    }

    public void validateAddres() {
        assertTextContains(CSIData.getAddressChanged(), getTextFromElement("label_address"));
    }

    public void uploadAnImageReturn() {
        waitForVisibilityOf("add_image");
        tapElement("add_image");
        if (isElementVisible("OK_button", 10)) {
            tapElement("OK_button");
        }
        if (isElementVisible("create_product_allow_access", 10)) {
            tapElement("create_product_allow_access");
        }
        tapElement("create_product_get_folder");
        tapElement("create_product_pilih_gambar_satu");
        tapElement("create_product_lanjut_added_button");
        waitForVisibilityOf("image_return", 20);
    }

    public void removeAnImageReturn() {
        tapElement("image_return");
        swipeUpToElement("hapus_button");
        tapElement("hapus_button");
        waitForVisibilityOf("ya_hapus_button", 20);
        tapElement("ya_hapus_button");
    }

    public void editImageReturn() {
        tapElement("image_return");
        tapElement("ubah_gambar_button");
        if (isElementVisible("OK_button", 10)) {
            tapElement("OK_button");
        }
        if (isElementVisible("create_product_allow_access", 10)) {
            tapElement("create_product_allow_access");
        }
        waitForVisibilityOf("choose_image_return", 10);
        tapElement("choose_image_return");
        tapElement("choose_image_return_to_edit");
        tapElement("lanjut_retur");
        waitForVisibilityOf("image_return", 20);
    }

    public void tapConfirmChangeSolution(Boolean item) {
        waitForVisibilityOf("label_ganti_solusi",5);
        waitForVisibilityOf("label_kembalikan_item");
        confirmItemReturn(item);
        tapElement("btn_confirm_ganti_solusi", 5);
    }

    public void confirmItemReturn(Boolean state) {
        waitForVisibilityOf("checkbox_kembalikan_item", 10);
        if (!state) {
            tapElement("checkbox_kembalikan_item");
        }
    }
}
