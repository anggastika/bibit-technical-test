package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * @author Andi Maryono
 */

public class CatalogProductListingPage extends BasePage {
    private static String invalidProductTypeMessage = "Invalid catalog product type";

    public CatalogProductListingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePage();
        if (isElementVisible("back_button_produt_listing_revamp")) {
            tapElement("back_button_produt_listing_revamp");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void userOnCatalogProductListing() {
        if (isElementVisible("catalog_product_listing_product_price_text", 30)) {
            verifyElementExist("catalog_product_listing_product_price_text");
        } else {
            verifyElementExist("catalog_product_listing_discount_text");
        }
        verifyElementExist("catalog_product_listing_seller_location_text");
        verifyElementExist("catalog_product_listing_seller_name_text");
        verifyElementExist("catalog_product_listing_seller_feedback_text");
        verifyElementExist("catalog_product_listing_buy_button");
        verifyElementExist("catalog_product_listing_qf_cell");
        HelperData.setLastActionPage(new CatalogProductListingPage(iosDriver));
    }

    public void verifyProductInfoExist(String productType) {
        verifyElementExist("catalog_product_listing_qf_cell");
        switch (productType) {
            case "promoted":
                verifyElementExist("catalog_product_listing_reco_product_cell");
                verifyElementExist("catalog_product_listing_reco_text");
                break;
            case "normal":
                verifyElementExist("catalog_product_listing_normal_product_cell");
                verifyElementExist("catalog_product_listing_product_count_text");
                break;
            default:
                Assert.fail(invalidProductTypeMessage);
                break;
        }
    }

    public void clickOnProduct(String productType) {
        switch (productType) {
            case "promoted":
                tapElement("catalog_product_listing_reco_product_cell");
                break;
            case "normal":
                tapElement("catalog_product_listing_normal_product_cell");
                break;
            default:
                Assert.fail(invalidProductTypeMessage);
                break;
        }
    }

    public void clickOnProductListingBuyBtn(String productType) {
        switch (productType) {
            case "promoted":
                tapElement("catalog_product_listing_reco_buy_button", 20);
                break;
            case "normal":
                tapElement("catalog_product_listing_buy_button", 20);
                break;
            default:
                Assert.fail(invalidProductTypeMessage);
                break;
        }
    }

    public void verifyNavbarTitle(String catalogTitle) {
        String elm_catalogtitle = constructLocator("catalog_product_nav_cat_ttl", catalogTitle);
        swipeToDirection(Direction.DOWN);
        assertTrue(isElementVisible(elm_catalogtitle), "catalog title is not displayed");
    }

    public void verifyProductListingByFilter() {
        verifyElementExist("catalog_product_listing_qf_product_count");
        verifyElementExist("catalog_product_listing_filter_checked_icon");
        verifyElementExist("catalog_product_listing_used_label");
    }

    public void verifyCatalogRecoNotExist() {
        verifyElementNotExist("catalog_product_listing_reco_text");
        verifyElementNotExist("catalog_product_listing_reco_product_cell");
    }

    public double getProductPriceBySort(Integer index) {
        String priceValue = getTextFromElement("catalog_product_listing_product_price_text", index)
                .replace("Rp", "")
                .replace(",", ".");
        if (priceValue.contains("rb")) {
            priceValue = priceValue.replace("rb", "");
            return Double.parseDouble(priceValue) * 1000;
        } else if (priceValue.contains("jt")) {
            priceValue = priceValue.replace("jt", "");
            return Double.parseDouble(priceValue) * 1000000;
        } else {
            return 0;
        }
    }

    public void verifyProductListingBySort() {
        verifyElementExist("catalog_product_listing_filter_checked_icon");
        verifyElementExist("catalog_product_listing_qf_product_count");

        Double firstProductPrice = getProductPriceBySort(0);
        Double secondProductPrice = getProductPriceBySort(1);
        LogUtil.info("First product price " + firstProductPrice + " and second product price " + secondProductPrice);
        assertTrue(firstProductPrice <= secondProductPrice, "First product price is bigger than second product price");
    }

    public void clickOnBtn(String button) {
        List<WebElement> productListingBtn = getElements("catalog_product_listing_button", 30);
        switch (button) {
            case "filter":
                productListingBtn.get(0).click();
                break;
            case "sort":
                productListingBtn.get(1).click();
                break;
            default:
                Assert.fail("Invalid catalog product listing button");
                break;
        }
    }

    public void clickOnQuickFilter() {
        List<WebElement> quickFilterBtn = getElements("catalog_product_listing_qf", 6);
        if (quickFilterBtn != null) {
            quickFilterBtn.get(0).click();
        } else {
            Assert.fail("Quick filter catalog product listing didn't found");
        }
    }
}
