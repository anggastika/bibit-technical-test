package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import java.util.Arrays;
import java.util.List;

public class BukaReksaProductPage extends BasePage {

    private List<String> productTypeList = Arrays.asList(
            "Pasar Uang",
            "Pendapatan Tetap",
            "Campuran",
            "Saham"
    );

    private List<String> productRiskList = Arrays.asList(
            "Rendah",
            "Menengah",
            "Tinggi"
    );

    private List<String> investmentManagerList = Arrays.asList(
            "Ashmore Asset Management",
            "BNP Paribas Investment Partners",
            "BNP Paribas Investment Partners, PT",
            "Bahana TCW Investment Management",
            "Batavia Prosperindo Aset Manajemen",
            "CIMB Principal Asset Management, PT",
            "CIMB-Principal Asset Management",
            "Ciptadana Asset Management",
            "Kresna Asset Management",
            "Mandiri Manajemen Investasi",
            "Mandiri Manajemen Investasi, PT",
            "Manulife Aset Manajemen Indonesia, PT",
            "Schroder Investment Management Indonesia, PT"
    );

    public BukaReksaProductPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void selectQuickFilter(String filterName) {
        switch (filterName.toLowerCase()) {
            case "semua produk":
                tapElement("REXA_QUICK_FILTERS_SEMUA_PRODUK", 5);

                /*only run if the first tap by xpath location not running properly due to
                 element covered by other hidden element*/
                if (!isElementExist("REXA_QUICK_FILTERS_RISK_SECTION_LOW")) {
                    tapElement(94, 184);
                }
                break;
            case "produk pilihan":
                tapElement("REXA_QUICK_FILTERS_PRODUK_PILIHAN", 5);
                break;
            case "favoritku":
                tapElement("REXA_QUICK_FILTERS_PRODUK_FAVORIT", 5);
                break;
            case "syariah":
                tapElement("REXA_QUICK_FILTERS_PRODUK_SYARIAH", 5);
                break;
            default:
                Assert.fail(filterName + " isn't the product name");
        }
    }

    public void selectProductFilter(String option) {
        switch (option.toLowerCase()) {
            case "type":
                selectFilterProductType();
                break;
            case "risk":
                selectFilterProductRisk();
                break;
            case "investment manager":
                selectFilterInvestmentManager();
                break;
            default:
                Assert.fail(option.toUpperCase() + " isn't a option name");
        }
    }

    public void selectProductList() {
        int randomProductList = randomize().number(getTotalProductList());
        // Set value
        InvestmentData.setProductNameBukaReksa(getSelectedProductListText("REXA_PRODUCT_LIST_NAME", randomProductList));
        InvestmentData.setProductTypeBukaReksa(getSelectedProductListText("REXA_PRODUCT_LIST_TYPE", randomProductList));
        InvestmentData.setProductNABBukaReksa(getSelectedProductListText("REXA_PRODUCT_LIST_NAV_VAL", randomProductList));
        tapSelectedProductList(randomProductList);
    }

    public void selectProfitHistoryProduct(String option) {
        switch (option.toLowerCase()) {
            case "7 hari":
                tapElement("PROD_DETAIL_CHART_7_DAY");
                break;
            case "1 bulan":
                tapElement("PROD_DETAIL_CHART_1_MONTH");
                break;
            case "3 bulan":
                tapElement("PROD_DETAIL_CHART_3_MONTH");
                break;
            default:
                Assert.fail(option + " isn't option");
        }
    }

    public void selectProductSortingList(String option) {
        matchingSortOptions(option);
        tapElement(constructLocator("REXA_PRODUCT_SORTING_LIST_OPTION", InvestmentData.getSortOption()));
    }

    public void tapOnProductFilterButton() {
        tapElement("PROD_TAB_PRODUCT_FILTER_BTN");
    }

    public void tapOnApplyFilterBtn() {
        validateDisplayed("FILTER_PAGE_TERAPKAN_BTN");
        tapElement("FILTER_PAGE_TERAPKAN_BTN");
    }

    public void tapOnResetFilterBtn() {
        tapElement("FILTER_PAGE_RESET_BTN");
    }

    public void tapOnProductGrowthDropdown() {
        tapElement("PROD_GROWTH_DROPDOWN");
    }

    public void tapOnProductInfoDropdown() {
        tapElement("PROD_INFORMATION_DROPDOWN");
    }

    public void tapOnProductSortingBtn() {
        tapElement("PROD_TAB_PRODUCT_SORTING_BTN");
    }

    public void tapOnProductSortingCloseBtn() {
        tapElement("REXA_PRODUCT_SORTING_CLOSE_BTN");
    }

    public void tapOnBuyProductBtn() {
        verifyElementExist("PROD_DETAIL_BUY_BUTTON");
        tapElement("PROD_DETAIL_BUY_BUTTON");
    }

    public void isProductPageDisplayed() {
        isProductTabSelected();
        isProductHeaderTitleDisplayed();
        isSearchFieldDisplayed();
        isFilterButtonDisplayed();
        HelperData.setLastActionPage(new BukaReksaProductPage(iosDriver));
    }

    public void isProductRiskDisplayed() {
        verifyLowRiskDisplayed();
        verifyMiddleRiskDisplayed();
        verifyHighRiskDisplayed();
    }

    public void isFilterPageDisplayed() {
        validateDisplayed("FILTER_PAGE_NAVBAR_TITLE");
        validateDisplayed("FILTER_PAGE_RESET_FILTER_BTN");
        validateDisplayed("FILTER_PAGE_TERAPKAN_BTN");
        isProductTypeSectionDisplayed();
        isProductRiskSectionDisplayed();
        isInvestmentManagerSectionDisplayed();
    }

    public void isProductListingDisplayed() {
        String quickFilter = InvestmentData.getQuickFilter();
        if (quickFilter.equals("favoritku")) {
            doPullRefresh(1);
        }
        validateExist("REXA_PRODUCT_LIST_CARD", 3);
    }

    public void isProductSortingOptionDisplayed() {
        checkProductSortingModal();
        checkProductSortingHeader();
        checkProductSortingList();
    }

    public void isProductGrowthSectionDisplayed() {
        validateExist("PROD_DETAIL_PRODUCT_GROWTH", 5, "Perkembangan produk dropdown not displayed");
        validateExist("PROD_DETAIL_CHART", 5,"Perkembangan produk chart not displayed");
        HelperData.setLastActionPage(new BukaReksaProductPage(iosDriver));
    }

    public void validatePromotedProductDisplayed() {
        validateNotDisplayed("REXA_LIHAT_SEMUA_BTN","lihat semua button displayed");
    }

    public void verifyProductGrowthDropdownDismiss() {
        validateNotExist("PROD_DETAIL_CHART", 5, "Product Growth dropdown expanded");
    }

    public void verifyProductInfoDropdownDismiss() {
        validateNotExist("PROD_INFO_DATE_OF_DATA_TXT", 2,
                "Product Information dropdown expanded");
    }

    public void swipeUpToProductInfoSection() {
        try {
            validateDisplayed("PROD_INFO_PROSPECTUS");
        } catch (AssertionError e) {
            swipeUpToElement("PROD_INFO_PROSPECTUS");
        }
    }

    public void verifyProductInfoComponent() {
        swipeUpToProductInfoSection();
        validateExist("PROD_INFO_DATE_OF_DATA_TXT", 2);
        validateExist("PROD_INFO_TOTAL_FUND_MANAGER", 2);
        validateExist("PROD_INFO_INVESTMENT_MANAGER", 2);
        validateExist("PROD_INFO_CUSTODIAN_BANK", 2);
        validateExist("PROD_INFO_LAUNCH_DATE", 2);
        validateExist("PROD_INFO_SUBSCRIPTION_FEE", 2);
        validateExist("PROD_INFO_REDEMPTION_FEE", 2);
        validateExist("PROD_INFO_MINIMUM_SUBSCRIPTION", 2);
        validateExist("PROD_INFO_ASSET_ALLOCATION", 2);
        validateExist("PROD_INFO_PROSPECTUS", 2);
        validateExist("PROD_INFO_FACT_SHEET", 2);
    }

    public void verifyQuickFilter(String quickFilterType) {
        if (quickFilterType.equals("Produk Pilihan")) {
            assertTrue(isElementVisible("REXA_PRODUCT_PILIHAN_MENARIK_TEXT"),
                    "Teks tidak tampil");
        } else if (quickFilterType.equals("Semua Produk")) {
            isProductRiskDisplayed();
        } else if(quickFilterType.equals("Favoritku")){
            String investmentProduct = InvestmentData.getInvestmentProduct();
            selectQuickFilter("favoritku");
            swipeToDirection(Direction.UP);
            if(investmentProduct.equals("Reksa Dana Syariah Mandiri Bukareksa Pasar Uang")) {
                assertTrue(isElementVisible("bukareksa_rdpu_syariah_mandiri_text"),
                        "Produk investasi ada di Favoritku");
            }
        }
    }

    public void verifyRiskOptionAsDefaultSortSelection() {
        validateEnabled("REXA_PRODUCT_SORTING_LIST_RISIKO");
    }

    public void verifyProductListingSortByRisk() {
        doPullRefresh(1);
        List<IOSElement> elements = getElements("REXA_PRODUCT_SORTING_LIST_RISK");
        int totalRiskTypes = elements.size();
        // verify total level of risk
        validateValue().equals(3, totalRiskTypes, "Total Level of Risk doesn't match");
        // verify level of component risk
        for (int i = 0; i < totalRiskTypes; i++) {
            switch (i) {
                case 0:
                    validateValue().equalsTrue(elements.get(0).getText().equals("Risiko Rendah"),
                            "Low risk level is not displayed");
                    break;
                case 1:
                    validateValue().equalsTrue(elements.get(1).getText().equals("Risiko Menengah"),
                            "Medium risk level is not displayed");
                    break;
                case 2:
                    validateValue().equalsTrue(elements.get(2).getText().equals("Risiko Tinggi"),
                            "High risk level is not displayed");
                    break;
                default:
                    Assert.fail("Index out bounds");
            }
        }
    }

    public void verifySelectedSortSelection() {
        validateEnabled(constructLocator("REXA_PRODUCT_SORTING_LIST_OPTION_CHECK", InvestmentData.getSortOption()));
    }

    public void verifyProductListingSortByNAB(String method) {
        verifyProductListDisplayed();
        for (int i = 0; i < getTotalProductListByReturnNAV(); i++) {
            if (method.toLowerCase().equals("highest")) {
                validateValue().equalsTrue(getProductReturnNAB(i) > getProductReturnNAB(i + 1),
                        "Product list displaying wrong sorting by greatest NAB value");
            } else if (method.toLowerCase().equals("lowest")) {
                validateValue().equalsTrue(getProductReturnNAB(i) < getProductReturnNAB(i + 1),
                        "Product list displaying wrong sorting by greatest NAB value");
            }
            if (i == (getTotalProductListByReturnNAV() - 2)) break;
        }
    }

    public void verifyProductListDisplayed() {
        verifyElementExist("REXA_PRODUCT_LIST_CARD");
        verifyElementExist("REXA_PRODUCT_LIST_NAME");
        verifyElementExist("REXA_PRODUCT_LIST_RETURN_VAL");
    }

    public void verifyProductListingSortByReturn(String method) {
        verifyProductListDisplayed();
        for (int i = 0; i < getTotalProductListByReturnPercentage(); i++) {
            if (method.toLowerCase().equals("highest")) {
                validateValue().equalsTrue(getProductReturnPercentage(i) > getProductReturnPercentage(i + 1),
                        "Product list displaying wrong sorting by greatest return value");
            } else if (method.toLowerCase().equals("lowest")) {
                validateValue().equalsTrue(getProductReturnPercentage(i) < getProductReturnPercentage(i + 1),
                        "Product list displaying wrong sorting by lowest return value");
            }
            if (i == (getTotalProductListByReturnPercentage() - 2)) break;
        }
    }

    public void searchProduct(String productName) {
        InvestmentData.setProductNameBukaReksa(productName);
        typeAndEnterValue("PROD_TAB_SEARCH_INPUT_FIELD", productName);
    }

    public void editFavorite(String method) {
        switch (method.toLowerCase()) {
            case "add favorite":
                addFavorite();
                break;
            case "remove favorite":
                removeFavorite();
                break;
            default:
                Assert.fail(method + " isn't a method name");
        }
    }

    public void checkProductFilterButtonSelected() {
        validateDisplayed("PROD_TAB_PRODUCT_FILTER_ICON");
    }

    public void checkFilteredProductByProductName() {
        checkFilteringProductResult("Name");
    }

    public void checkFilteredProductByCategory() {
        validateDisplayed("REXA_FILTER_DESCRIPTION_CATEGORY", "Description of filter category not found!");
        checkFilteringProductResult("Type");
    }

    public void checkFavoriteProductExist() {
        if (isElementVisible("REXA_FILTER_FAVORITE_EMPTY_TEXT", 3)){
            InvestmentData.setFavoriteProductExist(false);
        } else if (isElementVisible("REXA_NOT_FOUND_FAVORITE_TEXT", 3)) {
            InvestmentData.setFavoriteProductExist(false);
        } else {
            InvestmentData.setFavoriteProductExist(true);
        }
    }

    public void checkProductFavouriteRemoved() {
        doPullRefresh(1);
        if (isElementExist("REXA_FILTER_FAVORITE_EMPTY_TEXT")) {
            validateExist("REXA_FILTER_FAVORITE_EMPTY_TEXT", 3, "Favorite empty text not displayed");
            validateExist("REXA_FILTER_FAVORITE_EMPTY_DESC", 3, "Favorite empty desc text not displayed");
        }
        else if (getTotalProductList() > 0) {
            for (int i = 0; i < getTotalProductList(); i++) {
                String productName = getSelectedProductListText("REXA_PRODUCT_LIST_NAME", i);
                validateValue().equalsFalse(productName.contains(InvestmentData.getProductNameBukaReksa()),
                        "Product name still displaying on favourite listing");
            }
        }
    }

    public void checkProductDetailDisplayed() {
        isProductDetailAccordionSectionDisplayed();
        isHeaderProductSectionDisplayed();
        HelperData.setLastActionPage(new BukaReksaProductPage(iosDriver));
    }

    public void checkProductRiskValue() {
        validateValue().equalsTrue(getText(constructLocator("PROD_DETAIL_PRODUCT_RISK", InvestmentData.getProductTypeBukaReksa()))
                .contains(InvestmentData.getProductRiskBukaReksa()));
    }

    public void checkProductInvestmentManagerValue() {
        validateValue().equals(InvestmentData.getInvestmentManagerBukaReksa(), getInvestmentManagerText(),
                "Product Investment Manager text doesn't match");
    }

    public void checkProductSortingButtonSelected() {
        validateExist("PROD_TAB_PRODUCT_SORTING_ICON");
    }

    public void checkProductDetailInstantDisplayed() {
        isProductDetailAccordionSectionDisplayed();
        isHeaderProductInstantSectionDisplayed();
        HelperData.setLastActionPage(new BukaReksaProductPage(iosDriver));
    }

    private void isProductTypeSectionDisplayed() {
        validateValue().equalsTrue(getElementPresent(constructLocator(
                "FILTER_PAGE_OPTIONS", "Jenis Reksa Dana")).isDisplayed());
    }

    private void isProductRiskSectionDisplayed() {
        validateValue().equalsTrue(getElementPresent(constructLocator(
                "FILTER_PAGE_OPTIONS", "Risiko")).isDisplayed());
    }

    private void isInvestmentManagerSectionDisplayed() {
        validateValue().equalsTrue(getElementPresent(constructLocator(
                "FILTER_PAGE_OPTIONS", "Manajer Investasi")).isDisplayed());
    }

    private void isProductDetailAccordionSectionDisplayed() {
        validateExist("PROD_DETAIL_ACCORDION_SECTION", 5, "Accordion not displayed");
    }

    private void isHeaderProductSectionDisplayed() {
        validateValue().equals(InvestmentData.getProductNameBukaReksa(), getProductNameHeaderTxt(), "Product name doesn't match");
        validateValue().equalsTrue(getProductTypeHeaderTxt().contains(InvestmentData.getProductTypeBukaReksa()), "Product type doesn't match");
        validateValue().equals(InvestmentData.getProductNABBukaReksa(), getProductNABHeaderTxt(), "Product NAB doesn't match");
        InvestmentData.setProductDetailState(true);
    }

    private void isHeaderProductInstantSectionDisplayed() {
        validateValue().equals(InvestmentData.getProductNameBukaReksa(), getProductNameHeaderTxt(), "Product name doesn't match");
    }

    private void isProductTabSelected() {
        validateSelected("REXA_NAV_PRODUK", "Product listing tab not selected");
    }

    private void isProductHeaderTitleDisplayed() {
        validateExist("REXA_PRODUK_HEADER_TITLE", 10, "Produk title not displayed!");
    }

    private void isSearchFieldDisplayed() {
        verifyElementExist("PROD_TAB_SEARCH_TEXT_FIELD");
    }

    private void isFilterButtonDisplayed() {
        verifyElementExist("PROD_TAB_PRODUCT_FILTER_BTN");
    }

    private void tapSelectedProductList(int index) {
        tapElements("REXA_PRODUCT_LIST_CARD", index, Direction.UP);
    }

    private void verifyLowRiskDisplayed() {
        validateExist("REXA_QUICK_FILTERS_RISK_SECTION_LOW", 3, "section risiko rendah not display");
    }

    private void verifyMiddleRiskDisplayed() {
        validateExist("REXA_QUICK_FILTERS_RISK_SECTION_MIDDLE", 3, "section risiko menengah not display");
    }

    private void verifyHighRiskDisplayed() {
        validateExist("REXA_QUICK_FILTERS_RISK_SECTION_HIGH", 3, "section risiko tinggi not display");
    }

    private void selectFilterProductType() {
        int productTypes = productTypeList.size();
        int randomInt = randomize().number(productTypes);
        String productTypeVal = productTypeList.get(randomInt);
        InvestmentData.setProductTypeBukaReksa(productTypeVal);
        tapElement(constructLocator("FILTER_PAGE_CHECKBOX", productTypeVal));
    }

    private void selectFilterProductRisk() {
        int productRisk = productRiskList.size();
        int randomInt = randomize().number(productRisk);
        String productRiskVal = productRiskList.get(randomInt);
        InvestmentData.setProductRiskBukaReksa(productRiskVal);
        tapElement(constructLocator("FILTER_PAGE_CHECKBOX", productRiskVal));
    }

    private void selectFilterInvestmentManager() {
        int randomInt;
        String investmentManagerVal;
        boolean checkBoxState = false;
        int investmentManager = investmentManagerList.size();
        while (!checkBoxState) {
            randomInt = randomize().number(investmentManager);
            investmentManagerVal = investmentManagerList.get(randomInt);
            if (isElementExist(constructLocator("FILTER_PAGE_CHECKBOX", investmentManagerVal), 3)) {
                InvestmentData.setInvestmentManagerBukaReksa(investmentManagerVal);
                tapElement(constructLocator("FILTER_PAGE_CHECKBOX", investmentManagerVal));
                checkBoxState = true;
            } else {
                nativeSwipeUp();
            }
        }
    }

    /**
     * To check filtering result
     * By search field and filter
     *
     * @param assertion is parameter by name and type of product
     */

    private void checkFilteringProductResult(String assertion) {
        try {
            if (!isElementVisible("REXA_PRODUCT_LIST_EMPTY", 3)) {
                for (int i = 0; i < getTotalProductList(); i++) {
                    if (assertion.toLowerCase().equals("name")) {
                        String productName = getSelectedProductListText("REXA_PRODUCT_LIST_NAME", i);
                        validateValue().equalsTrue(productName.contains(InvestmentData.getProductNameBukaReksa()),
                                "Product Name doesn't match");
                    } else if (assertion.toLowerCase().equals("type")) {
                        String productType = getSelectedProductListText("REXA_PRODUCT_LIST_TYPE", i);
                        validateValue().equalsTrue(productType.contains(InvestmentData.getProductTypeBukaReksa()),
                                "Product Type doesn't match");
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail("Product listing empty!");
        }
    }

    private void checkProductSortingModal() {
        validateDisplayed("REXA_PRODUCT_SORTING_MODAL", "Product sorting modal not displayed");
    }

    private void checkProductSortingHeader() {
        validateDisplayed("REXA_PRODUCT_SORTING_HEADER", "Product sorting header not displayed");
    }

    private void checkProductSortingList() {
        validateDisplayed("REXA_PRODUCT_SORTING_LIST", "Product sorting list not displayed");
    }

    private int getTotalProductList() {
        verifyElementExist("REXA_PRODUCT_LIST_CARD", 5, "Product card not exist");
        int totalList = getElements("REXA_PRODUCT_LIST_CARD").size();
        int totalResult = 0;
        int maxList = 3;
        try {
            if (totalList > maxList) {
                totalResult = totalList - (totalList - maxList);
            } else if (totalList != 0 && totalList < maxList || totalList == maxList) {
                totalResult = totalList;
            }
        } catch (Exception e) {
            Assert.fail("No product list displayed!");
        }
        return totalResult;
    }

    private String getSelectedProductListText(String locator, int index) {
        return getElementsPresent(locator).get(index).getText();
    }

    private String getProductNameHeaderTxt() {
        validateExist("PROD_DETAIL_HEADER_PRODUCT_NAME", 5);
        return getText("PROD_DETAIL_HEADER_PRODUCT_NAME");
    }

    private String getProductTypeHeaderTxt() {
        validateExist("PROD_DETAIL_HEADER_PRODUCT_TYPE", 3);
        return getText("PROD_DETAIL_HEADER_PRODUCT_TYPE");
    }

    private String getProductNABHeaderTxt() {
        boolean fromInvestasikuPage = InvestmentData.getFromInvestasikuPage();
        validateExist("PROD_DETAIL_HEADER_PRODUCT_NAB", 3);
        if (fromInvestasikuPage) {
            double nab = getDoubleFromRp(getText("PROD_DETAIL_HEADER_PRODUCT_NAB"));
            return String.valueOf(nab);
        } else {
            return getText("PROD_DETAIL_HEADER_PRODUCT_NAB");
        }
    }

    private String getInvestmentManagerText() {
        return getText("PROD_INFO_INVESTMENT_MANAGER_VAL");
    }

    private int getTotalProductListByReturnNAV () {
        return getElementsPresent("REXA_PRODUCT_LIST_NAV_VAL").size();
    }

    private double getProductReturnNAB(int index) {
        return getDoubleFromRp((getText("REXA_PRODUCT_LIST_NAV_VAL", index)));
    }

    private int getTotalProductListByReturnPercentage () {
        return getElementsPresent("REXA_PRODUCT_LIST_RETURN_VAL").size();
    }

    private int getProductReturnPercentage(int index) {
        return parseIntegerFromText(getText("REXA_PRODUCT_LIST_RETURN_VAL", index)
                .replace("%", ""));
    }

    private boolean getStatusFavorite() {
        boolean status = false;
        if (isElementExist("PROD_DETAIL_FAVORITE_TXT", 2)) {
            status = true;
        }
        return status;
    }

    private void matchingSortOptions(String option) {
        String letterFormat;
        if (option.toLowerCase().contains("nab")) {
            letterFormat = option.substring(0, 3).toUpperCase()
                    + option.substring(3).toLowerCase();
        } else {
            letterFormat = option.substring(0, 1).toUpperCase()
                    + option.substring(1).toLowerCase();
        }
        InvestmentData.setSortOption(letterFormat);
    }

    private void addFavorite() {
        tapElement("PROD_DETAIL_FAVORITE_BTN");
        if (!getStatusFavorite()) {
            tapElement("PROD_DETAIL_FAVORITE_BTN");
            validateExist("PROD_DETAIL_FAVORITE_TXT", 3, "Product favorite removed!");
        }
    }

    private void removeFavorite() {
        tapElement("PROD_DETAIL_FAVORITE_BTN");
        if (getStatusFavorite()) {
            tapElement("PROD_DETAIL_FAVORITE_BTN");
            validateExist("PROD_DETAIL_UNFAVORITE_TXT", 3, "Product unfavorite removed!");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
