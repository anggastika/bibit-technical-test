package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class UlasanInstanBeliPakaiBukaDompetPage extends BasePage {

    public UlasanInstanBeliPakaiBukaDompetPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyBeliPakaiBukaDompetPage() {
        waitForVisibilityOf("ulasan_instan_beli_pakai_bukadompet_text", 20);
    }

    public void clickBayarSekarangButton() {
        waitForVisibilityOf("ulasan_instan_bayar_sekarang_button", 20);
        tapElement("ulasan_instan_bayar_sekarang_button", 20);
    }
}
