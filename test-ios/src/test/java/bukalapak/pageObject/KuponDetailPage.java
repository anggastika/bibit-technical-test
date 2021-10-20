package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 31/05/20, Sun
 **/
public class KuponDetailPage extends VpBasePage {

    public KuponDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementExist("KUPON_DETAIL_IMAGE_VIEW");
        verifyElementExist("KUPON_DETAIL_EXPIRY_TEXT");
        verifyElementExist("KUPON_DETAIL_REMAINING_STOCK_TEXT");
        verifyElementExist("KUPON_DETAIL_SOLD_TEXT");
        swipeUpToElement("KUPON_DETAIL_SKU_PLUS_BUTTON", 3);
        verifyElementExist("KUPON_DETAIL_DESCRIPTION_TEXT");
        verifyElementExist("KUPON_DETAIL_TNC_TEXT");
        verifyElementExist("KUPON_DETAIL_SKU_SECTION");
        verifyElementExist("KUPON_DETAIL_SKU_PLUS_BUTTON");
        verifyElementExist("KUPON_DETAIL_BUY_BUTTON");
    }

    public void swipeDownToTitleSection() {
        swipeDownToElement("KUPON_DETAIL_IMAGE_VIEW");
    }

    public void addCouponSKU(int count) {
        swipeUpToElement("KUPON_DETAIL_SKU_SECTION");
        waitForVisibilityOf("KUPON_DETAIL_SKU_PLUS_BUTTON", 5);
        tapMultipleOnElement("KUPON_DETAIL_SKU_PLUS_BUTTON", count);
    }

    public void validateCoupon() {
        assertNotSame(getElementLabel("KUPON_VALUE_SKU"), "0");
    }

    public void tapOnBuyButton() {
        tapElement("KUPON_DETAIL_BUY_BUTTON");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
