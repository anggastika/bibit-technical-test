package bukalapak.pageObject.games;

import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KumpulinkPage extends BasePage {


    public KumpulinkPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver); }

    public void isOnKumpulinkPage () {
        changeContext().toWebview();
        verifyElementExist( "KUMPULINK_PROFILE_NAME");
        verifyElementExist("KUMPULINK_PROFILE_PICTURE");
        verifyElementExist("KUMPULINK_STORE_LINK_BUTTON");
    }

    public void validateSeeAllProductButton () {
        webViewSwipeToElement("KUMPULINK_SEE_ALL_PRODUCT_BUTTON");
        verifyElementExist("KUMPULINK_SEE_ALL_PRODUCT_BUTTON");
    }

    public void validateNoHighlightProduct() {
        verifyElementNotExist("KUMPULINK_HIGHLIGT_SECTION");
    }

    public void validateHighlightSection() {
        verifyElementExist("KUMPULINK_HIGHLIGT_SECTION");
        verifyElementExist("KUMPULINK_PRODUCT_PICTURE");
        verifyElementExist("KUMPULINK_PRODUCT_NAME");
        verifyElementExist("KUMPULINK_PRODUCT_PRICE");
    }

    public void tapOnSemuaBarangButton() {
        validateSeeAllProductButton();
        tapElement("KUMPULINK_SEE_ALL_PRODUCT_BUTTON");
    }

    public void isOnKumpulinkCataloguePage() {
        changeContext().toWebview();
        verifyElementExist("KUMPULINK_PRODUCT_CARD_CATALOGUE");
        verifyElementExist("KUMPULINK_FILTER_BTN_CATALOGUE");
        verifyElementExist("KUMPULINK_SORT_BTN_CATALOGUE");
        verifyElementExist("KUMPULINK_PRODUCT_PICTURE");
        verifyElementExist("KUMPULINK_PRODUCT_NAME");
        verifyElementExist("KUMPULINK_PRODUCT_PRICE");
    }

}
