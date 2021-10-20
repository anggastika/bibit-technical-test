package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class RincianPromosiPage extends BasePage {
    public RincianPromosiPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnRincianPromosiPage() {
        verifyElementDisplayed("rincian_promosi_page_title");
    }

    public void verifyProductDetailDisplay(String nameProduct) {
        verifyElementDisplayed(constructLocator("rincian_promosi_detail_product_title", nameProduct));
        verifyElementDisplayed("rincian_promosi_detail_total_penjualan");
        verifyElementDisplayed("rincian_promosi_detail_biaya_promosi");
        verifyElementDisplayed("rincian_promosi_detail_persentase_keuntungan_text");
        verifyElementDisplayed("rincian_promosi_detail_persent_amount");
    }

    public void verifyProductListDisplay() {
        waitForVisibilityOf("rincian_promosi_list_product_title", 15);
        verifyElementDisplayed("rincian_promosi_list_biaya_promosi");
        verifyElementDisplayed("rincian_promosi_list_total_penjualan");
    }

    public void clickProductOrLabelName(String productLabeName) {
        tapElement(constructLocator("rincian_promosi_general_text", productLabeName));
    }

    public void verifyProductDisplay(String productName) {
        waitForVisibilityOf(constructLocator("rincian_promosi_general_text", productName));
    }

    public void clickFilterButton() {
        tapCenterOfElement("rincian_promosi_list_filter_btn");
    }

    public void clickFilterOption(String filterOption) {
        waitForVisibilityOf(constructLocator("rincian_promosi_general_text", filterOption));
        tapElement(constructLocator("rincian_promosi_general_text", filterOption));
    }

    public void clickTerapkanOrResetFilter(String filterButton) {
        tapElement(constructLocator("rincian_promosi_general_text", filterButton));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
