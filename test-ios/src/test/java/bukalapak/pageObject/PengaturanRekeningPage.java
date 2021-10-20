package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class PengaturanRekeningPage extends BasePage {

    public PengaturanRekeningPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPengaturanRekeningPage() {
        waitForVisibilityOf("rekening_pengaturan_rekening_page_title",60);
        HelperData.setLastActionPage(new PengaturanRekeningPage(iosDriver));
    }

    public void typeOnRekeningField(String action) {
        switch (action) {
            case "tambah rekening":
                tapElement("tambah_rekening_pilih_bank_dropdown", 30);
                verifyElementExist("tambah_rekening_BCA_Syariah_dropdown");
                tapElement("tambah_rekening_BCA_Syariah_dropdown", 30);
                typeAndEnterValueWithTimeOut("tambah_rekening_nama_text_field", "Rekening 1");
                typeAndEnterValueWithTimeOut("tambah_rekening_nomor_text_field", "12345678");
                tapElement("tambah_rekening_simpan_button", 30);
                break;
            case "edit rekening":
                tapElement("edit_rekening_dropdown", 30);
                tapElement("edit_rekening_BII_Syariah_dropdown", 30);
                typeValue("edit_rekening_rekening1_text_field", " edit");
                typeValue("edit_rekening_no12345678_text_field", "90");
                hideKeyboard();
                tapElement("edit_rekening_simpan_button", 30);
                break;
            default:
                Assert.fail(action + " is not available");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
