package bukalapak.pageObject;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class PembiayaanTunaiPage extends BasePage {
    public PembiayaanTunaiPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void selectApplication(String state) {
        String tmpState = state;
        switch (state) {
            case "Kontrak Dibatalkan":
                tmpState = "Kontrak Dibatalkan";
                break;
            case "Diproses":
                tmpState = "Diproses";
                break;
            case "Menunggu Persetujuan Kontrak":
                tmpState = "Menunggu Persetujuan Kontrak";
                break;
            case "Proses Pengecekan Dokumen":
                tmpState = "Proses Pengecekan Dokumen";
                break;
            case "Uang Sedang Dikirim":
                tmpState = "Uang Sedang Dikirim";
                break;
            case "Pembiayaan Aktif":
                tmpState = "Pembiayaan Aktif";
                break;
            case "Tidak Disetujui":
                tmpState = "Tidak Disetujui";
                break;
            default:
                Assert.fail(tmpState + " isn't the state");
        }
        waitFor(3);
        waitForVisibilityOf(constructLocator("pembiayaantunai_state_text", tmpState, 10));
        tapElement(constructLocator("pembiayaantunai_state_text", tmpState));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
