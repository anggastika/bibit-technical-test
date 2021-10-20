package bukalapak.pageObject;

import bukalapak.data.ChampionData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * Created by Jaswir Siregar
 * 23 Des 2019
 */

public class ProductsCampaignPage extends BasePage {

    public ProductsCampaignPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnCampaignProductPage() {
        if (isElementExist("campaign_product_images", 20)) {
            verifyElementExist("campaign_product_images");
            verifyElementExist("campaign_header_banner");
        } else {
            changeContext().toWebview();
            verifyElementExist("campaign_webview_search_bar", 20, "Kolom pencarian halaman promo tidak ditampilkan!");
            verifyElementExist("campaign_webview_product_images", 30, "Gambar produk halaman promo tidak ditampilkan!");
        }

        HelperData.setLastActionPage(new ProductsCampaignPage(iosDriver));
    }

    public void goToCampaignPageWithDeeplink(String deeplink) {
        openDeepLink(deeplink);
        isOnCampaignProductPage();
    }

    public void sortProducts(String sortMode) {
        if (iosDriver.getContext().contains("NATIVE_APP")) {
            waitForVisibilityOf("campaign_sort_button", 30);
            tapElement("campaign_sort_button");
            waitForVisibilityOf("campaign_sort_title", 10);
            tapElement(constructLocator("campaign_sort_option_text", sortMode));
        } else {
            tapElement("campaign_webview_sort_by_price_tab");
            if (sortMode.equals("Termahal")) {
                tapElement("campaign_webview_sort_by_price_tab");
            }
        }
        HelperData.setLastActionPage(new ProductsCampaignPage(iosDriver));
    }

    public void validateSortResult(String sortMode) {
        if (iosDriver.getContext().contains("NATIVE_APP")) {
            waitForVisibilityOf(constructLocator("campaign_selected_sort_mode", sortMode));
            verifyElementExist(constructLocator("campaign_selected_sort_mode", sortMode));
        } else {
            assertEquals(getText("campaign_webview_sort_by_price_text"), sortMode);
        }

        switch(sortMode) {
            case "Termurah":
                validateSortPrice("Termurah",5);
                break;
            case "Termahal":
                validateSortPrice("Termahal",5);
                break;
            case "Diskon Terbesar":
                //to be developed
                break;
            default:
                break;
        }
    }

    public void validateSortPrice(String priceSortMode, int maxScroll) {
        if (iosDriver.getContext().contains("WEBVIEW")) {
            List<WebElement> priceElements = getElements("campaign_webview_product_prices", 1);
            int size = priceElements.size();
            int middleIndex = (int) Math.ceil((double) size / 2);
            int prevPrice = -1;

            for (int i = 0; i < size; i++) {
                int index = i / 2;
                index += (i % 2 == 0) ? 0 : middleIndex;

                int price = ChampionData.priceStringToInteger(priceElements.get(index).getText());

                if (prevPrice >= 0) {
                    assertTrue((priceSortMode.equals("Termurah")) ? (prevPrice <= price) : (prevPrice >= price));
                }

                prevPrice = price;
            }

            return;
        }

        int tmpMaxScroll = maxScroll;
        while (tmpMaxScroll > 0) {
            waitForVisibilityOf("campaign_product_price");
            List<IOSElement> finalPrices =  getFinalPrice(getElements("campaign_product_container"));
            int prevPrice, nextPrice;
            for (int i = 1; i < finalPrices.size(); i++) {

                prevPrice = ChampionData.priceStringToInteger(finalPrices.get(i - 1).getText());
                nextPrice = ChampionData.priceStringToInteger(finalPrices.get(i).getText());

                if(priceSortMode.equals("Termurah")) {
                    assertTrue(prevPrice <= nextPrice,
                            "prevPrice " + prevPrice + " is not cheaper than nextPrice " + nextPrice);
                }
                else {
                    assertTrue(prevPrice >= nextPrice,
                            "prevPrice " + prevPrice + " is not more expensive than nextPrice " + nextPrice);
                }

            }
            swipeToDirection(Direction.UP);
            tmpMaxScroll--;
        }
    }

    private List<IOSElement> getFinalPrice(List<IOSElement> productCards) {
        List<IOSElement> filtered = new ArrayList<>();
        IOSElement finalPrice;
        for(IOSElement card : productCards) {
            finalPrice = (IOSElement) card.findElement(getLocator("campaign_product_discounted_price"));
            if(finalPrice.getText().equals(""))
                finalPrice = (IOSElement) card.findElement(getLocator("campaign_product_price"));
            filtered.add(finalPrice);
        }
        return filtered;
    }

    public void searchProducts() {
        String keyword;

        if (iosDriver.getContext().contains("NATIVE_APP")) {
            waitForVisibilityOf("campaign_product_names", 20);
            List<IOSElement> displayedProductName = getElements("campaign_product_names");
            keyword = displayedProductName.get(displayedProductName.size() - 1).getText().split(" ")[0];
            typeAndEnterValue("campaign_search_bar", keyword);
        } else {
            List<WebElement> names = getElements("campaign_webview_product_names", 1);
            keyword = names.get(names.size() - 1).getText().split(" ")[0];

            tapElement("campaign_webview_search_bar");
            verifyElementExist("campaign_webview_omnisearch_category_image");
            webViewTypeElementValue("campaign_webview_search_bar_field", keyword);
        }

        ChampionData.setSearchKeyword(keyword);
        HelperData.setLastActionPage(new ProductsCampaignPage(iosDriver));
    }

    public void validateSearchResult(String keyword, int scrollNumber) {
        if (iosDriver.getContext().contains("WEBVIEW")) {
            verifyElementExist("campaign_webview_product_prices", 10, "Hasil pencarian tidak ditampilkan!");

            List<WebElement> names;
            try {
                names = getElements("campaign_webview_product_names", 1);
            } catch (StaleElementReferenceException ex) {
                // element not displayed even though it is existed
                waitFor(5);
                names = getElements("campaign_webview_product_names", 1);
            }

            for (WebElement name : names) {
                assertTextContains(keyword, name.getText());
            }

            return;
        }

        int tmpScrollNumber = scrollNumber;
        waitForVisibilityOf("campaign_product_names", 30);
        validateValue().equals(keyword, getElementValue("campaign_search_bar"));
        while(tmpScrollNumber > 0) {
            List<IOSElement> displayedProductName = getElements("campaign_product_names");
            String productName;
            for(IOSElement name : displayedProductName) {
                productName = name.getText();
                assertTrue(productName.toLowerCase().replaceAll(" ", "").contains(keyword.toLowerCase()),
                        "Product name \"" + productName + "\" does not containse search keyword : " + keyword);
            }
            swipeToDirection(Direction.UP);
            tmpScrollNumber--;
        }
    }

    @Override
    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
