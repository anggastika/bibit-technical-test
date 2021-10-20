package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushTambahPromosiPage extends BasePage {

    public PromotedPushTambahPromosiPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnTambahPromosiPage() {
        verifyElementDisplayed("promoted_tambah_promosi_screen_title");
        HelperData.setLastActionPage(new PromotedPushTambahPromosiPage(iosDriver));
    }

    public void tickCheckboxFirstProduct() {
        tapElement("promoted_tambah_promosi_product_checkbox");
    }

    public void tickCheckboxPilihSemua() {
        tapElement("promoted_tambah_promosi_pilih_semua_checkbox");
    }

    public void clickBuatGrupButton() {
        waitForVisibilityOf("promoted_tambah_promosi_buat_grup_button");
        waitForVisibilityOf("promoted_tambah_promosi_barang_terpilih_text");
        tapElement("promoted_tambah_promosi_buat_grup_button");
    }

    public void clickLanjutButton() {
        waitForVisibilityOf("promoted_tambah_promosi_1_barang_terpilih_text", 15);
        tapElement("promoted_tambah_promosi_lanjut_button");
    }

    public void tickSomeProducts(int totalProducts) {
        for (int i = 0; i < totalProducts; i++) {
            tapElement("promoted_tambah_promosi_product_checkbox");
            if (!isElementVisible("promoted_tambah_promosi_product_checkbox")) {
                swipeDown(0.8, 0.55);
            }
        }
        assertEquals(getText("promoted_tambah_promosi_barang_terpilih_text"), totalProducts + " barang terpilih");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
