package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TerlarisDanKurangLarisPage extends BasePage {

    public TerlarisDanKurangLarisPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnTerlarisDanKurangLarisPage() {
        waitForVisibilityOf("terlaris_dan_kurang_laris_title_page", 5);
        validateDisplayed("terlaris_dan_kurang_laris_header_nama_text");
        validateDisplayed("terlaris_dan_kurang_laris_header_terjual_text");
        validateDisplayed("terlaris_dan_kurang_laris_header_stok_text");
        HelperData.setLastActionPage(new TerlarisDanKurangLarisPage(iosDriver));
    }

    public void verifyBarangTerjualStokValueFullList(int posisiInventaris) {
        waitForVisibilityOf("terlaris_dan_kurang_terlaris_column_amount_nama", 20);
        validateValue().equals(posisiInventaris, getElements("terlaris_dan_kurang_terlaris_column_amount_nama").size());
        validateValue().equals(posisiInventaris, getElements("terlaris_dan_kurang_terlaris_column_amount_terjual").size());
        validateValue().equals(posisiInventaris, getElements("terlaris_dan_kurang_terlaris_column_amount_stok").size());
        HelperData.setLastActionPage(new TerlarisDanKurangLarisPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
