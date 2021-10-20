package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class ManajemenPelangganPage extends BasePage {

    public ManajemenPelangganPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnManajemenPelangganDashboard() {
        waitForVisibilityOf("manajemen_pelanggan_title_text", 15);
        HelperData.setLastActionPage(new ManajemenPelangganPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateSebarPromosiEnabled() {
        validateDisplayed("manajemen_pelanggan_kuota_pembeli_baru_text");
        validateEnabled("manajemen_pelanggan_sebar_promosi_pembeli_baru_button");
        validateDisplayed("manajemen_pelanggan_kuota_pelanggan_text");
        validateEnabled("manajemen_pelanggan_sebar_promosi_pelanggan_button");
        HelperData.setLastActionPage(new ManajemenPelangganPage(iosDriver));
    }

    public void tapSebarPromosiButtonOnCategory(String category) {
        waitForVisibilityOf("manajemen_pelanggan_sebar_promosi_pembeli_baru_button");
        switch (category.toLowerCase()) {
            case "pembeli baru":
                tapElement("manajemen_pelanggan_sebar_promosi_pembeli_baru_button");
                break;
            case "pelanggan":
                tapElement("manajemen_pelanggan_sebar_promosi_pelanggan_button");
                break;
            case "masukin ke keranjang":
                swipeUpToElement("manajemen_pelanggan_sebar_promosi_favorit_barang_button");
                tapElement("manajemen_pelanggan_sebar_promosi_masukin_keranjang_button");
                break;
            case "lihat-lihat barangmu":
                swipeUpToElement("manajemen_pelanggan_sebar_promosi_favorit_barang_button");
                tapElement("manajemen_pelanggan_sebar_promosi_lihat_barang_button");
                break;
            case "yang favoritin barangmu":
                swipeUpToElement("manajemen_pelanggan_sebar_promosi_favorit_barang_button");
                tapElement("manajemen_pelanggan_sebar_promosi_favorit_barang_button");
                break;
            default:
                Assert.fail("Please recheck the CRM category selection!");
                break;
        }
    }
}
