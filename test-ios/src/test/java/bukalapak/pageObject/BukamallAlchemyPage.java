package bukalapak.pageObject;

import bukalapak.data.CategoryData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.util.ArrayList;

public class BukamallAlchemyPage extends BasePage {

    public BukamallAlchemyPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void swipeOnCategoryTabAlchemy() {
        swipeLeft(0.3, 0.7, 0.2);
        verifyElementExist("bukamall_fashion_tab");
    }

    public void userOnBukamallPageAlchemy() {
        checkFirstAppLoad();
        if (isElementVisible("bukamall_alchemy_original_text")) {
            verifyElementExist("bukamall_alchemy_pengembalian_text");
        } else {
            scrollToTopBukamallAlchemy();
            verifyElementExist("bukamall_alchemy_original_text");
            verifyElementExist("bukamall_alchemy_pengembalian_text");
        }
        HelperData.setLastActionPage(new BukamallAlchemyPage(iosDriver));
    }

    public void scrollToTopBukamallAlchemy() {
        swipeUpToElement("bukamall_alchemy_spesial_untukmu_text");
    }

    public void checkFirstAppLoad() {
        if (isElementVisible("onboarding_ok_button")) {
            tapElement("onboarding_ok_button");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void isBukaMallPageExist(){
        assertTrue(isElementVisible("info_lapak"), "Tidak Berhasil Redirect ke halaman BukaMall");
        HelperData.setLastActionPage(new BukamallAlchemyPage(iosDriver));
    }

    public void validateRecoTitle() {
        swipeUpToElement("bukamall_reco_title", 15);
        verifyElementExist("bukamall_reco_title", 5, "Recommendation is not displayed");
        HelperData.setLastActionPage(new BukamallAlchemyPage(iosDriver));
    }

    public void validateInfoProductReco(String productInfo) {
        swipeUpToElement("bukamall_reco_product_bmss_icon");
        switch (productInfo) {
            case "product name":
                if(!isElementVisible("bukamall_reco_product_name", 5)) {
                    swipeUpToElement("bukamall_reco_product_name");
                }
                verifyElementExist("bukamall_reco_product_name");
                break;
            case "product price":
                if (!isElementVisible("bukamall_reco_product_price", 5)) {
                    swipeUpToElement("bukamall_reco_product_price");
                }
                verifyElementExist("bukamall_reco_product_price");
                break;
            case "discount label":
                if (!isElementVisible("bukamall_reco_product_discount", 5)) {
                    swipeUpToElement("bukamall_reco_product_discount");
                }
                verifyElementExist("bukamall_reco_product_discount");
                break;
            case "star rating":
                if (!isElementVisible("bukamall_reco_product_star_rating", 5)) {
                    swipeUpToElement("bukamall_reco_product_star_rating");
                }
                verifyElementExist("bukamall_reco_product_star_rating");
                break;
            case "product rating":
                if (!isElementVisible("bukamall_reco_product_rating", 5)) {
                    swipeUpToElement("bukamall_reco_product_rating");
                }
                verifyElementExist("bukamall_reco_product_rating");
                break;
            case "number of product sold":
                if (!isElementVisible("bukamall_reco_product_number_product_sold", 5)) {
                    swipeUpToElement("bukamall_reco_product_number_product_sold");
                }
                verifyElementExist("bukamall_reco_product_number_product_sold");
                break;
            case "bmss icon":
                if (!isElementVisible("bukamall_reco_product_bmss_icon", 5)) {
                    swipeUpToElement("bukamall_reco_product_bmss_icon");
                }
                verifyElementExist("bukamall_reco_product_bmss_icon");
                break;
            default:
                Assert.fail("Product info " + productInfo + " is not available");
        }
        HelperData.setLastActionPage(new BukamallAlchemyPage(iosDriver));
    }

    public void tapRecommendationProduct() {
        tapElement("bukamall_reco_product_name");
    }

    public void validateInfiniteProductReco() {
        int swapCount = 0;
        while (swapCount < 10) {
            ArrayList<String> listProduct = CategoryData.getRecoProductList();
            int recoProduct = getMultipleElement().withLocator("bukamall_reco_product_name").size();

            for (int i = 0; i < recoProduct; i++) {
                String productName = getText("bukamall_reco_product_name", i);
                if (listProduct.size() < 4) {
                    CategoryData.setRecoProductList(productName);
                } else {
                    setProductList(listProduct, productName);
                }
            }
            swipeUp();
            swapCount++;
        }

        int recoProduct = CategoryData.getRecoProductList().size();
        validateValue().equalsTrue(recoProduct <= 24);
        HelperData.setLastActionPage(new BukamallAlchemyPage(iosDriver));
    }

    private void setProductList(ArrayList<String> list, String product) {
        for (String productOnList : list) {
            if (productOnList.equals(product)) {
                CategoryData.getRecoProductList().remove(productOnList);
                break;
            }
        }
        CategoryData.setRecoProductList(product);
    }
}
