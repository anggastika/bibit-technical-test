package bukalapak.pageObject;

import bukalapak.data.CSIData;
import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class DiskusiKomplainPage extends BasePage {

    public DiskusiKomplainPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnDiskusiKomplainPage() {
        if (isElementVisible("diskusi_komplain_batalkan_button", 5)) {
            tapElement("diskusi_komplain_batalkan_button");
        }
        waitForVisibilityOf("diskusi_komplain_title", 30);
        HelperData.setLastActionPage(new DiskusiKomplainPage(iosDriver));
    }

    public void tapDetailKomplain() {
        waitForVisibilityOf("diskusi_komplain_detail_button",5);
        tapElement("diskusi_komplain_detail_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void fillChatField(String chatText) {
        typeAndEnterValue("diskusi_komplain_chat_textfield", chatText);
        CSIData.setDiskusiText(chatText);
    }

    public void uploadImage() {
        waitForVisibilityOf("image_chooser_first_folder", 10);
        tapElement("image_chooser_first_folder");
        tapElement("image_chooser_first_image");
        tapElement("image_chooser_lanjut_button");
    }

    public void uploadAttachment() {
        tapIconOnKomplainPage("attachment");
        uploadImage();
    }

    public void assertChatColumn() {
        //wait for image to be uploaded
        delay(30000);
        waitForVisibilityOf("diskusi_komplain_attachment_picture");
        assertEquals(CSIData.getDiskusiText(), getTextFromElement("diskusi_komplain_chat_text"));
    }

    public void tapIconOnKomplainPage(String param) {
        switch (param.toLowerCase()) {
            case "file":
                tapElement(constructLocator("diskusi_komplain_file_icon"));
                break;
            case "detail":
                waitForVisibilityOf("diskusi_komplain_detail_text", 10);
                tapElement("diskusi_komplain_detail_text");
                break;
            case "attachment":
                tapElement(constructLocator("diskusi_komplain_attachment_icon"));
                break;
            case "send":
                tapElement(constructLocator("diskusi_komplain_send_icon"));
                break;
            default:
                LogUtil.warn("Not recognized icon on diskusi komplain");
                break;
        }
    }

    public void userIsOnAttachmentPage() {
        waitForVisibilityOf("diskusi_komplain_attachment_title");
        waitForVisibilityOf("diskusi_komplain_attachment_date");
        waitForVisibilityOf("diskusi_komplain_attachment_image");
        waitForVisibilityOf("diskusi_komplain_attachment_caption");
    }

    public void pressBackButton() {
        tapElement("diskusi_komplain_back_button");
    }

    public void validateStatusKomplain(String statusKomplain) {
        if (statusKomplain.equals("selesai")) {
            assertEquals("Komplain Selesai", getTextFromElement("diskusi_komplain_status"));
            assertEquals("Kamu berhasil menutup komplain", getTextFromElement("diskusi_komplain_last_chat"));
        } else {
            assertEquals(statusKomplain, getTextFromElement("diskusi_komplain_status"));
        }
    }

    public void lanjutkanKomplain() {
        tapElement("diskusi_komplain_lanjutkan_button");
        tapElement("diskusi_komplain_confirm_button");
    }

    public void tapTerimaSolution(Boolean item) {
        tapElement("diskusi_komplain_confirm_button",5);
        confirmItemReturn(item);
        tapElement("diskusi_retur_confirm_solution_button", 5);
    }

    public void confirmItemReturn(Boolean state) {
        waitForVisibilityOf("diskusi_retur_itemreturn_checkbox", 10);
        if (!state) {
            tapElement("diskusi_retur_itemreturn_checkbox");
        }
    }

    public void tapButtonPilihPengiriman() {
        tapElement("diskusi_komplain_pilih_pengiriman_button", 10);
    }

    public void inputPengirimanRetur() {
        if (isElementVisible("diskusi_retur_kirim_manual_option", 5)) {
            tapElement("diskusi_retur_kirim_manual_option");
            tapElement("diskusi_retur_kirim_manual_button");
        } else {
            waitForVisibilityOf("diskusi_retur_input_jasa_pengiriman_title", 10);
        }
        validateDisplayed("diskusi_retur_jasa_kirim_select");
        tapElement("diskusi_retur_jasa_kirim_select");
        waitForVisibilityOf("diskusi_retur_pickerwheel", 10);
        selectPickerWheel(dotenv.get("RETURN_COURIER"));
        tapElement("diskusi_retur_jasa_kirim_pilih_button");
        typeAndEnterValue("diskusi_retur_resi_field", dotenv.get("RETURN_RESI"));
        tapElement("diskusi_retur_kirim_manual_simpan_button");
    }

    public void validateFinishedReturnComplaint() {
        waitForVisibilityOf("diskusi_komplain_last_message", 30);
        String lastMessage = getText("diskusi_komplain_last_message");
        lastMessage.equals("Komplain transaksi " + TransactionData.getNomorTransaksi() + " telah selesai");
        HelperData.setLastActionPage(new DiskusiKomplainPage(iosDriver));
    }

    public void assertChatAfterRetur() {
        waitForVisibilityOf("diskusi_komplain_attachment_picture", 30);
        assertEquals(CSIData.getDiskusiText(), getTextFromElement("diskusi_komplain_after_retur"));
    }

    public void assertSolutionReturn() {
        waitForVisibilityOf("diskusi_komplain_terima_solution_btn", 5);
        verifyElementExist("diskusi_komplain_terima_solution_btn");
        verifyElementExist("diskusi_komplain_ganti_solution_btn");
    }

    public void tapActionForSolution(String actionButton) {
        if (actionButton.equals("accept")) {
            tapElement("diskusi_komplain_terima_solution_btn");
        } else {
            tapElement("diskusi_komplain_ganti_solution_btn");
        }
    }

    public void assertModalTerimaSolusi() {
        waitForVisibilityOf("diskusi_komplain_modal_terima_solusi_konfirmasi_button",5);
        verifyElementExist("diskusi_komplain_modal_terima_solusi_batal_button");
    }

    public void tapActionForConfirmation(String actionButton) {
        if (actionButton.equals("ya, terima solusi")) {
            tapElement("diskusi_komplain_modal_terima_solusi_konfirmasi_button");
        } else {
            tapElement("diskusi_komplain_modal_terima_solusi_batal_button");
        }
    }

    public void tapInputResiButton() {
        waitForVisibilityOf("diskusi_komplain_masukkan_nomor_resi_solution", 5);
        tapElement("diskusi_komplain_masukkan_nomor_resi_button");
    }

    public void tapTerimaBarang() {
        waitForVisibilityOf("diskusi_komplain_terima_barang_label", 5);
        tapElement("diskusi_komplain_terima_barang_button");
    }

    public void tapKonfirmasiTerimaBarang() {
        waitForVisibilityOf("diskusi_komplain_modal_terima_barang_option2", 5);
        tapElement("diskusi_komplain_modal_terima_barang_option1");
        tapElement("diskusi_komplain_modal_terima_barang_button");
    }

    public void displayAllAttachment() {
        waitForVisibilityOf("diskusi_komplain_attachment_title", 10);
    }

    public void confirmReceivedReturnItem() {
        waitForVisibilityOf("diskusi_komplain_terima_button", 10);
        tapElement("diskusi_komplain_terima_button", 10);
        waitForVisibilityOf("diskusi_retur_ktb_modal_title", 10);
        waitForVisibilityOf("diskusi_retur_ktb_modal_remit_option_false", 5);
        tapElement("diskusi_retur_ktb_modal_remit_option_true");
        tapElement("diskusi_retur_ktb_modal_konfirmasi_button");
    }
}
