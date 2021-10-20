package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class LapakTerlarisPage extends BasePage{

    public LapakTerlarisPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnLapakTerlarisPage() {
        waitForVisibilityOf("lapak_terlaris_text");
        validateValue().equalsTrue(getTextFromElement("lapak_terlaris_lapaksaya_info_text").contains("Lapak saya ada pada peringkat di atas 100."));
        validateValue().equalsTrue(getTextFromElement("lapak_terlaris_lapaksaya_trx_text").matches("\\d+ Transaksi"));
        HelperData.setLastActionPage(new LapakTerlarisPage(iosDriver));
    }

    public void verifyListLapakTerlaris(int totalLapak) {
        int rank = 1;
        while (rank <= totalLapak) {
            verifyLapakRank(rank);
            rank++;
        }
        HelperData.setLastActionPage(new LapakTerlarisPage(iosDriver));
    }

    private void verifyLapakRank(int rank) {
        swipeUpToElement(constructLocator("lapak_terlaris_seller_rank_text", rank));
        validateValue().equals(rank + "", getTextFromElement(constructLocator("lapak_terlaris_seller_rank_text", rank)));
        validateValue().equalsFalse(getTextFromElement(constructLocator("lapak_terlaris_seller_name_text", rank)).isEmpty());
        validateValue().equalsTrue(getTextFromElement(constructLocator("lapak_terlaris_seller_sold_text", rank)).matches("\\d+ Transaksi"));
        HelperData.setLastActionPage(new LapakTerlarisPage(iosDriver));
    }

    public void tapLapakTerlarisRank(int rank) {
        swipeUpToElement(constructLocator("lapak_terlaris_seller_rank_text", rank));
        tapElement(constructLocator("lapak_terlaris_seller_rank_text", rank));
        HelperData.setLastActionPage(new LapakTerlarisPage(iosDriver));
    }

    public void verifyLapakTerlarisCategory(String categoryName) {
        waitForVisibilityOf("lapak_terlaris_category_text");
        validateValue().equalsTrue(getTextFromElement("lapak_terlaris_category_text").matches(categoryName));
        HelperData.setLastActionPage(new LapakTerlarisPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
