package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BarangTerlarisPage extends BasePage {

    public BarangTerlarisPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyUserOnBarangTerlarisPage() {
        verifyElementExist("barang_terlaris_text");
        HelperData.setLastActionPage(new BarangTerlarisPage(iosDriver));
    }

    public void tapBarangTerlarisRank(int rank) {
        swipeUpToElement(constructLocator("barang_terlaris_product_rank_text", rank));
        tapElement(constructLocator("barang_terlaris_product_rank_text", rank));
        HelperData.setLastActionPage(new BarangTerlarisPage(iosDriver));
    }

    public void verifyBarangTerlarisCategory(String categoryName) {
        waitForVisibilityOf("barang_terlaris_category_text");
        validateValue().equalsTrue(getTextFromElement("barang_terlaris_category_text").matches(categoryName));
        HelperData.setLastActionPage(new BarangTerlarisPage(iosDriver));
    }

    public void verifyProducts(int total) {
        int rank = 1;
        // handle when data barang terlaris from BE is less than expected
        int totalProduct = total > 3 ? 3 : total;
        while (rank <= totalProduct) {
            verifyProduct(rank);
            rank++;
        }
    }

    private void verifyProduct(int rank) {
        swipeUpToElement(constructLocator("barang_terlaris_product_rank_text", rank));
        validateValue().equals(Integer.toString(rank), getTextFromElement(constructLocator("barang_terlaris_product_rank_text", rank)));
        validateValue().equalsFalse(getTextFromElement(constructLocator("barang_terlaris_product_title_text", rank)).isEmpty());
        validateRpFormat(constructLocator("barang_terlaris_product_price_text", rank));
        validateValue().equalsFalse(getTextFromElement(constructLocator("barang_terlaris_product_seller_text", rank)).isEmpty());
        validateValue().equalsTrue(getTextFromElement(constructLocator("barang_terlaris_product_sold_text", rank)).matches("\\d+ Transaksi"));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
