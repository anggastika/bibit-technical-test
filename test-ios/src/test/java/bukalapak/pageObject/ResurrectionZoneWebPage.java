package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ResurrectionZoneWebPage extends BasePage {

    public ResurrectionZoneWebPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnResurrectionZone() {
        changeContext().toWebview();
        verifyElementExist("buka_buku_text", 20, "Resurrection zone banner not displayed");
    }

    public void validateOnBukuKenangan() {
        changeContext().toWebview();
        verifyElementExist("banner_special_rz", 20, "Banner special not shown");
    }

    public void clickCTAProductSpecial() {
        verifyElementExist("banner_special_rz");
        webViewSwipeToElement("cta_product_special_rz");
        webViewTapOnElement("cta_product_special_rz");
    }

    public void validateOnProductSpecial() {
        changeContext().toNative();
        validateExist("favorit_section", 20);
    }

    public void clickCTACart() {
        webViewSwipeToElement("cta_product_rz");
        verifyElementExist("product_cart_rz");
        webViewTapOnElement("cta_product_rz");
    }

    public void validateOnCartSection() {
        changeContext().toNative();
        validateExist("cart_section", 20);
    }

    public void clickCTARecent() {
        webViewSwipeToElement("cta_product_rz");
        verifyElementExist("product_recent_rz", 10, "product recent not shown");
        webViewTapOnElement("cta_recent_rz");
    }

    public void validateOnRiwayatPage() {
        changeContext().toNative();
        validateExist("recent_section", 20);
    }

    public void validateVoucherResurrectionZone() {
        validateExist("voucher_resurrection", 20);
    }

    public void typeSearchResurrectionZone() {
        changeContext().toWebview();
        String searchKeywords = "madu";
        IOSElement searchResurrection = iosDriver.findElement(getLocator("resurrection_search_textfield"));
        searchResurrection.sendKeys(searchKeywords + "\n");
    }

    public void validateSearchResultPage() {
        changeContext().toNative();
        waitFor(10);
        if (isElementVisible("search_modal")){
            tapElement("search_modal");
        }
        validateExist("relevansi_tab");
        goToHomePage();
    }

    public void tapOnResurrectionVoucherCode() {
        changeContext().toWebview();
        webViewTapOnElement("resurrection_copy_code_btn");
    }

    public void verifySuccessCopyCode() {
        validateExist("resurrection_success_copy_code");
        changeContext().toNative();
        goToHomePage();
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
