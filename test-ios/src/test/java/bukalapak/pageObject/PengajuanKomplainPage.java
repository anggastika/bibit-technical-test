package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PengajuanKomplainPage extends BasePage {
    
    public PengajuanKomplainPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPengajuanKomplainPage() {
        waitForVisibilityOf("pengajuan_komplain_title", 10);
        waitForVisibilityOf("pengajuan_komplain_foto_komplain_text");
        waitForVisibilityOf("pengajuan_komplain_alasan_komplain_text");
        waitForVisibilityOf("pengajuan_komplain_solusi_komplain_text");
        HelperData.setLastActionPage(new PengajuanKomplainPage(iosDriver));
    }

    public void uploadImage() {
        waitForVisibilityOf("image_chooser_first_folder", 10);
        tapElement("image_chooser_first_folder");
        waitForVisibilityOf("image_chooser_first_image", 5);
        tapElement("image_chooser_first_image");
        waitForElementClickable("image_chooser_lanjut_button", 10);
        tapElement("image_chooser_lanjut_button");
    }

    public void chooseReason(String reason) {
        tapElement("pengajuan_komplain_alasan_dropwdown");
        waitForVisibilityOf("pengajuan_komplain_alasan_barang_rusak_cell");
        switch (reason) {
            case "barang rusak":
                tapElement("pengajuan_komplain_alasan_barang_rusak_cell");
                break;
            case "jumlah kurang":
                tapElement("pengajuan_komplain_alasan_jumlah_kurang_cell");
                break;
            case "tidak lengkap":
                tapElement("pengajuan_komplain_alasan_tidak_lengkap_cell");
                break;
            case "tidak sesuai":
                tapElement("pengajuan_komplain_alasan_tidak_sesuai_cell");
                break;
            default:
                break;
        }
    }

    public void verifyDefaultAddress() {
        waitForVisibilityOf("pengajuan_komplain_barang_akan_dikembalikan_text", 5);
        waitForVisibilityOf("pengajuan_komplain_bagaimana_proses_text", 5);
        waitForVisibilityOf("pengajuan_komplain_nama_user_text", 5);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
