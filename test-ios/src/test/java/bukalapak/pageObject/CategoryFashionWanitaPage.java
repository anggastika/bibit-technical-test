package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Prasetyo Nugroho on January 2019.
 */
public class CategoryFashionWanitaPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(CategoryFashionWanitaPage.class);

    public CategoryFashionWanitaPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public List<WebElement> getFeaturedItemTitleElements() {
        return getElements("featured_item_section_title_text", 6);
    }

    public List<WebElement> getFeaturedItemProductImageElements() {
        return getElements("featured_item_product_image", 6);
    }

    public List<WebElement> getFeaturedItemLihatSemuaTextElements() {
        return getElements("featured_item_lihat_semua_text", 6);
    }

    public IOSElement getSubcategorySpecialTitle(String subcategory) {
        switch (subcategory) {
            case "Koleksi Tas Stylish":
                return getElement("subcategory_special_tas_wanita_text", 6);
            case "Hijab Elegan":
                return getElement("subcategory_special_jilbab_text", 6);
            default:
                // default is Pernak Pernik OOTD
                return getElement("subcategory_special_accessories_text", 6);
        }
    }

    public List<String> getFeaturedItemTitle() {
        List<WebElement> featuredItemTitleElements = getFeaturedItemTitleElements();
        List<String> featuredItemTitles = new ArrayList<>();

        for (int i = 0; i < featuredItemTitleElements.size(); i++) {
            featuredItemTitles.add(featuredItemTitleElements.get(i).getText());
        }

        return featuredItemTitles.stream().distinct().collect(Collectors.toList());
    }

    public void swipeLeftOnFeaturedItemSection() {
        swipeLeft(0.97, 0.08, 0.4);
    }

    public void checkFeaturedItemSection() {
        List<String> featuredItemTitles = getFeaturedItemTitle();

        assertEquals(3, featuredItemTitles.size());
        for (String title : featuredItemTitles) {
            assertTrue(title.equals("Gratis Ongkir") || title.equals("Sedang Diskon") || title.equals("Terbaru"));
        }
    }

    public void clickProductFeaturedItem(String featured_item) {
        List<WebElement> featuredItemProductImageElements;
        int indexFeaturedItemTitle = 0;

        swipeLeftOnFeaturedItemSection();

        List<String> featuredItemTitles = getFeaturedItemTitle();

        while (featuredItemTitles.size() == 2) {
            featuredItemTitles.clear();
            doPullRefresh(1);
            featuredItemTitles = getFeaturedItemTitle();
        }

        for (int i = 0; i < featuredItemTitles.size(); i++) {
            if (featuredItemTitles.get(i).equals(featured_item)) {
                indexFeaturedItemTitle = i;
            }
        }

        switch (indexFeaturedItemTitle) {
            case 1:
                // section 2
                featuredItemProductImageElements = getFeaturedItemProductImageElements();
                // 0 - 15
                featuredItemProductImageElements.get(7).click();
                break;
            case 2:
                // section 3
                swipeLeftOnFeaturedItemSection();
                featuredItemProductImageElements = getFeaturedItemProductImageElements();
                // 0 - 11
                featuredItemProductImageElements.get(11).click();
                break;
            default:
                // section 1
                // default is 0
                swipeRight(0.08, 0.97, 0.4);
                featuredItemProductImageElements = getFeaturedItemProductImageElements();
                // 0 - 11
                featuredItemProductImageElements.get(1).click();
                break;
        }
    }

    public void clickLihatSemuaFeaturedItem(String featured_item) {
        List<WebElement> featuredItemLihatSemuaTextElements;
        int indexFeaturedItemTitle = 0;

        swipeLeftOnFeaturedItemSection();

        List<String> featuredItemTitles = getFeaturedItemTitle();

        for (int i = 0; i < featuredItemTitles.size(); i++) {
            if (featuredItemTitles.get(i).equals(featured_item)) {
                indexFeaturedItemTitle = i;
            }
        }

        switch (indexFeaturedItemTitle) {
            case 1:
                // section 2
                featuredItemLihatSemuaTextElements = getFeaturedItemLihatSemuaTextElements();
                featuredItemLihatSemuaTextElements.get(1).click();
                break;
            case 2:
                // section 3
                swipeLeftOnFeaturedItemSection();
                featuredItemLihatSemuaTextElements = getFeaturedItemLihatSemuaTextElements();
                featuredItemLihatSemuaTextElements.get(1).click();
                break;
            default:
                // section 1
                // default is 0
                swipeRight(0.08, 0.97, 0.4);
                featuredItemLihatSemuaTextElements = getFeaturedItemLihatSemuaTextElements();
                featuredItemLihatSemuaTextElements.get(0).click();
                break;
        }
    }

    public void checkFilteringOnSearchPage(String featured_item) {
        HelperData.setLastActionPage(new SearchPage(iosDriver));

        switch (featured_item) {
            case "Sedang Diskon":
                verifyElementExist("listing_category_fashion_wanita_text");
                tapElement("listing_filter_text");
                verifyElementExist("filter_diskon_text");
                break;
            case "Gratis Ongkir":
                verifyElementExist("listing_category_fashion_wanita_text");
                tapElement("listing_filter_text");
                swipeDownToElement("filter_gratis_ongkir_text");
                verifyElementExist("filter_gratis_ongkir_text");
                verifyElementExist("filter_gratis_ongkir_seluruh_indonesia_text");
                verifyElementExist("filter_badge_count_gratis_ongkir_icon");
                break;
            default:
                // default is Terbaru
                verifyElementExist("listing_category_fashion_wanita_text");
                verifyElementExist("filter_terbaru_text");
                break;
        }
    }

    public void swipeLeftOnSubcategoryList() {
        swipeLeft(0.7, 0.3, 0.5);
    }

    public void scrollDownToSubcategorySpecial(String subcategory_special) {
        switch (subcategory_special) {
            case "Koleksi Tas Stylish":
                swipeDownToElement("subcategory_special_lihat_semua_tas_wanita_text");
                break;
            case "Hijab Elegan":
                swipeDownToElement("subcategory_special_lihat_semua_jilbab_text");
                break;
            default:
                // default is Pernak Pernik OOTD
                swipeDownToElement("subcategory_special_lihat_semua_accessories_text");
                break;
        }
    }

    public void checkSubcategorySpecialTitle(String subcategory_special) {
        switch (subcategory_special) {
            case "Koleksi Tas Stylish":
                assertTrue(isElementVisible("subcategory_special_tas_wanita_text"));
                break;
            case "Hijab Elegan":
                assertTrue(isElementVisible("subcategory_special_jilbab_text"));
                break;
            default:
                // default is Pernak Pernik OOTD
                assertTrue(isElementVisible("subcategory_special_accessories_text"));
                break;
        }
    }

    public void swipeLeftOnSubcategorySpecial(String subcategory_special) {
        double xStart = 0.7;
        double xEnd = 0.05;
        IOSElement titleElement;

        switch (subcategory_special) {
            case "Koleksi Tas Stylish":
                titleElement = getSubcategorySpecialTitle("Koleksi Tas Stylish");
                swipeLeft(xStart, xEnd, (double) (titleElement.getLocation().y + 200) / 667);
                break;
            case "Hijab Elegan":
                titleElement = getSubcategorySpecialTitle("Hijab Elegan");
                swipeLeft(xStart, xEnd, (double) (titleElement.getLocation().y + 200) / 667);
                break;
            default:
                // default is Pernak Pernik OOTD
                titleElement = getSubcategorySpecialTitle("Pernak Pernik OOTD");
                LOGGER.info(">>>>>>>>>>>>> " + (double) (titleElement.getLocation().y + 200) / 667);
                swipeLeft(xStart, xEnd, (double) (titleElement.getLocation().y + 200) / 667);
                break;
        }
    }

    public void clickLihatSemuaSubcategorySpecial(String subcategory_special) {
        switch (subcategory_special) {
            case "Koleksi Tas Stylish":
                tapElement("subcategory_special_lihat_semua_tas_wanita_text");
                break;
            case "Hijab Elegan":
                tapElement("subcategory_special_lihat_semua_jilbab_text");
                break;
            default:
                // default is Pernak Pernik OOTD
                tapElement("subcategory_special_lihat_semua_accessories_text");
                break;
        }
    }

    public void checkFilteringSubcategoryOnSearchPage(String featured_item) {
        HelperData.setLastActionPage(new SearchPage(iosDriver));

        switch (featured_item) {
            case "Koleksi Tas Stylish":
                verifyElementExist("subcategory_fashion_wanita_tas_wanita_text");
                break;
            case "Hijab Elegan":
                verifyElementExist("subcategory_fashion_wanita_jilbab_text");
                break;
            default:
                // default is Pernak Pernik OOTD
                verifyElementExist("subcategory_fashion_wanita_accessories_text");
                break;
        }
    }

    public void swipeLeftOnPromoSection() {
        IOSElement promoSectionTitle = getElement("category_penawaran_spesial_text", 5);
        swipeLeft(0.7, 0.05, (double) (promoSectionTitle.getLocation().y + 50) / 667);
    }

    public void swipeLeftOnBrandSection() {
        IOSElement brandSectionTitle = getElement("category_brand_favorit_text", 5);
        swipeLeft(0.7, 0.05, (double) (brandSectionTitle.getLocation().y + 50) / 667);
    }

    public void clickProductNameSubcategorySpecial(String subcategory_special) {
        if (subcategory_special.equals("Hijab Elegan")) {
            List<WebElement> elements = getElements("subcategory_special_product_name_text", 6);
            elements.get(3).click();
        } else {
            tapElement("subcategory_special_product_name_text");
        }
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
