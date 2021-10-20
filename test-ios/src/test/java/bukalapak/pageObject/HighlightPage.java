package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HighlightPage extends BasePage {

    public HighlightPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    private void scrollToKunjungiLapak() {
        swipeDownToElement("highlight_seller_visit_store");
        waitForVisibilityOf("highlight_seller_visit_store", 10);
    }

    public void isOnHighlightPage() {
        verifyElementExist("highlight_selected_product_text");
        verifyElementExist("highlight_selected_product");
        verifyElementExist("highlight_seller_header");
        scrollToKunjungiLapak();
        verifyElementExist("highlight_seller_products");
        verifyElementExist("highlight_seller_subscribe");
        verifyElementExist("highlight_seller_visit_store");
        HelperData.setLastActionPage(new HighlightPage(iosDriver));
    }

    public void verifyMerchantOrBrandPage() {
        if (isElementVisible("bukamall_brand_page_ori_text")) {
            verifyElementExist("bukamall_brand_page_ori_text");
            HelperData.setLastActionPage(new BukamallBrandPage(iosDriver));
        } else {
            verifyElementExist("seller_page_waktu_proses_text");
            HelperData.setLastActionPage(new MerchantPage(iosDriver));
        }
    }

    public void verifySelectedProducts() {
        List<String> selectedProducts = new ArrayList<>();

        // swipe 5 times
        for (int i = 0; i < 5; i++) {
            List<WebElement> selectedProductsElement = getElements("highlight_selected_product_name", 5);
            for (int j = 0; j < selectedProductsElement.size(); j++) {
                selectedProducts.add(selectedProductsElement.get(j).getText());
            }
            swipeLeft(0.7, 0.1, 0.4);
        }

        selectedProducts = selectedProducts.stream().distinct().collect(Collectors.toList());

        assertTrue(selectedProducts.size() <= 10, "Produk pilihan muncul lebih dari 10");
        selectedProducts.clear();
        HelperData.setLastActionPage(new HighlightPage(iosDriver));
    }

    public void verifyHighlightEndOfList() {
        swipeDownToElement("highlight_end_of_list", 10);
        verifyElementExist("highlight_end_of_list");
        HelperData.setLastActionPage(new HighlightPage(iosDriver));
    }

    public void tapLihatSemuaOnSpecificSeller() {
        scrollToKunjungiLapak();
        swipeLeft(0.5, 0.1, 0.6);
        tapElement("highlight_see_all");
    }

    public void tapOnSpecificSellerProduct() {
        scrollToKunjungiLapak();
        tapElement("highlight_specific_seller_product");
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
