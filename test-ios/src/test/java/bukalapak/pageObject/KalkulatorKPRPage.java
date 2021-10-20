package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KalkulatorKPRPage extends BasePage {

    public KalkulatorKPRPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnKalkulatorKPRPage() {
        verifyElementExist("kpr_kalkulator_kpr_title");
        verifyElementExist("kpr_credit_duration_dropdown");
        HelperData.setLastActionPage(new KalkulatorKPRPage(iosDriver));
    }

    public void clickKalkulatorKPRMenu() {
        allowPopup();
        tapElement("home_blhome_tab");
        swipeDownToElement("home_lainnya_widget");
        tapElement("home_lainnya_widget");
        swipeDownToElement("widget_kalkulator_finansial_widget");
        tapElement("widget_kalkulator_finansial_widget");
        waitForElementClickable("finansial_kpr_widget", 10);
        tapElement("finansial_kpr_widget");

        HelperData.setLastActionPage(new KalkulatorKPRPage(iosDriver));
    }

    public void enterHargaRumah(String price) {
        IOSElement hargaRumahElement = getElement("kpr_harga_rumah_field", 10);
        hargaRumahElement.click();
        hargaRumahElement.clear();
        hargaRumahElement.setValue(price);
        tapElement("toolbar_done_button");
    }

    public void pilihJangkaWaktu(String period) {
        tapElement("kpr_credit_duration_dropdown");
        tapElement("kpr_" + period + "_tahun_dropdown");
    }

    public void enterEstimasiSukuBunga(String interestRate) {
        IOSElement estimasiSukuBungaElement = getElement("kpr_suku_bunga_field", 10);
        estimasiSukuBungaElement.click();
        estimasiSukuBungaElement.clear();
        estimasiSukuBungaElement.setValue(interestRate);
        tapElement("toolbar_done_button");
    }

    private String getUangMuka() {
        return getTextFromElement("kpr_uang_muka");
    }

    private String getPlafon() {
        return getTextFromElement("kpr_plafon");
    }

    private String getCicilanPerBulan() {
        return getTextFromElement("kpr_cicilan_perbulan");
    }

    private String getBiayaAkadKredit() {
        return getTextFromElement("kpr_biaya_akad");
    }

    public void clickButtonHitung() {
        tapElement("kpr_hitung_button");
        swipeToDirection(Direction.DOWN);
    }

    public void verifyLoanCalculation() {
        String uangMukaReality, plafonReality, cicilanPerBulanReality;
        String biayaAkadKreditReality;

        uangMukaReality = getUangMuka();
        plafonReality = getPlafon();
        cicilanPerBulanReality = getCicilanPerBulan();
        biayaAkadKreditReality = getBiayaAkadKredit();

        assertTrue(uangMukaReality.equals("Rp20.000.000"), "uang muka tidak sesuai");
        assertTrue(plafonReality.equals("Rp80.000.000"), "plafon tidak sesuai");
        assertTrue(biayaAkadKreditReality.equals("Rp4.000.000 - Rp5.600.000"), "biaya akad tidak sesuai");
        assertTrue(cicilanPerBulanReality.equals("Rp1.779.556"), "cicilan tidak sesuai");
    }

    public void goToHomePage() {
        tapBackButton(3);
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
