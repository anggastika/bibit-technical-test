package bukalapak.pageObject;

import bukalapak.data.BukamartData;
import bukalapak.data.CartData;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class BukamartLandingPage extends BasePage {
    public BukamartLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateBukamartPage() {
        skipOnboarding();
        verifyElementDisplayed("BUKAMART_BUKAMART_NAME");
        BukamartData.setBukamartName(getElementValue("BUKAMART_BUKAMART_NAME").replaceAll("Bukamart by ", ""));
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void validateNoSavedAddress() {
        validateExist("BUKAMART_ADD_ADDR_FIELD", 5);
    }

    private void skipOnboarding() {
        if (isElementExist("BUKAMART_NANTI_SAJA_BTN_ONBOARDING", 5)) {
            tapNantiSajaOnboarding();
        } else if (isElementExist("BUKAMART_OK_BTN_ONBOARDING", 5)) {
            tapOkOnboarding();
        }
    }

    public void validateOnboarding() {
        validateExist("BUKAMART_ONBOARDING_TEXT", 5);
    }

    public void tapOkOnboarding() {
        tapElement("BUKAMART_OK_BTN_ONBOARDING");
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void tapNantiSajaOnboarding() {
        tapElement("BUKAMART_NANTI_SAJA_BTN_ONBOARDING");
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void tapAddAddrField() {
        tapElement("BUKAMART_ADD_ADDR_FIELD");
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void validateNewAddressPage() {
        validateExist("BUKAMART_NEW_ADDR_TITLE", 5);
    }

    public void swipeSection(String section) {
        String element = constructLocator("BUKAMART_CATEGORY_SECTION", section);
        swipeDownToElement(element);
        swipeLeftAtSpecifiedLocator(element);
        waitFor(2);
    }

    public void tapATCProductOnSection(String section) {
        CartData.setProductName(constructLocator("BUKAMART_PRODUCT_NAME_CATEGORY_SECTION", section));
        tapElement(constructLocator("BUKAMART_ATC_CATEGORY_SECTION", section), 15);
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void validateStickyCart(String flag) {
        if (flag == null) {
            waitForElementClickable("BUKAMART_STICK_CART", 20);
            validateEnabled("BUKAMART_STICK_CART", "Sticky atc bukamart is not displayed");
        } else {
            validateNotExist("BUKAMART_STICK_CART", 20);
        }
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void validateTriggeredCoverageArea(String flag) {
        if (flag == null) {
            validateExist("BUKAMART_QUICK_LOCATION_SECTION", 5);
        } else {
            validateNotExist("BUKAMART_QUICK_LOCATION_SECTION", 5);
        }
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void tapAddressField() {
        tapElement("BUKAMART_SELECTED_ADDR");
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void validateSelectAddrPage() {
        validateExist("BUKAMART_ADDR_PAGE_TITLE", 5);
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void selectExistingAddr(String addr) {
        tapElement(constructLocator("BUKAMART_EXISTING_ADDR", addr));
    }

    public void validatePopupConfirmationChangeAddr() {
        validateExist("BUKAMART_POPUP_CHANGE_ADDR_TITLE", 3);
    }

    public void tapGunakanBtn() {
        tapElement("BUKAMART_POPUP_CHANGE_ADDR_GUNAKAN_BTN");
    }

    public void tapBatalBtn() {
        tapElement("BUKAMART_POPUP_CHANGE_ADDR_BATAL_BTN");
    }

    public void changeAddress(String address) {
        tapAddressField();
        validateSelectAddrPage();
        selectExistingAddr(address);
        validatePopupConfirmationChangeAddr();
        tapGunakanBtn();
    }

    public void tapBukaMartProfile() {
        tapElement("BUKAMART_BUKAMART_NAME");
    }

    public void tapStickyCart() {
        tapElement("BUKAMART_STICK_CART");
    }

    public void validateProductOnTrayATC() {
        validateValue().equals(CartData.getProductName(), getText("TRAY_ATC_PRODUCT_NAME"));
    }

    public void validateBukamartProfile() {
        validateValue().equals(BukamartData.getBukamartName(), getText("BUKAMART_PROFILE_NAME"));
    }

    public void searchProduct(String product) {
        typeValue("BUKAMART_SEARCH_FIELD", product + "\n");
    }

    public void verifyEmptyState() {
        verifyElementExist("BUKAMART_EMPTY_STATE", 10, "Empty state not shown");
        validateValue().equals("Barang yang kamu cari tidak ditemukan", getText("BUKAMART_EMPTY_STATE"));
        HelperData.setLastActionPage(new BukamartLandingPage(iosDriver));
    }

    public void tapQuickLocation(String location) {
        tapElement(constructLocator("BUKAMART_QUICK_LOCATION", location));
    }

    public void validateProductListExist() {
        verifyElementExist("BUKAMART_PRODUCT_LIST", 2, "Product not found!");
    }

    public void validateSectionOnBukaMart(String sectionType) {
        switch (sectionType) {
            case "Kategori":
                verifyElementExist("BUKAMART_KATEGORI_TAB", 2, "Section not found!");
                break;
            case "Urutkan":
                verifyElementExist("BUKAMART_URUTKAN_TAB", 2, "Section not found!");
                break;
            default:
                Assert.fail("Section Type Not found!");
                break;
        }
    }

    public void validateUrutkanNameActive(String urutkanName) {
        verifyElementExist(constructLocator("BUKAMART_URUTKAN_TAB_NAME", urutkanName), 3, "Urutkan Name not Active!");
    }

    public void tapOnTabSectionBukaMart(String sectionName) {
        switch (sectionName) {
            case "Urutkan":
                tapElement("BUKAMART_URUTKAN_TAB");
                break;
            case "Kategori":
                tapElement("BUKAMART_KATEGORI_TAB");
                break;
            default:
                Assert.fail("Section Name Not found!");
                break;
        }
    }

    public void tapOnUrutkanNameBukaMart(String urutkanName) {
        tapElement(constructLocator("BUKAMART_URUTKAN_NAME", urutkanName));
    }

    public void clearKeywordOnSearchField() {
        tapCenterOfElement("BUKAMART_SEARCH_FIELD");
        getElement("BUKAMART_SEARCH_FIELD").clear();
    }

    public void verifiyCategoryTab() {
        int max = getMultipleElement().withLocator("BUKAMART_QUICK_LOCATION_TAB", 3).size();

        for (int i = 1; i < max; i++) {
            doPullRefresh(2);
            if (!isElementExist("BUKAMART_CATEGORY_LIST")) {
                tapElement(constructLocator("BUKAMART_QUICK_LOCATION_TAB_SECTION", i + 1));
                skipOnboardingChangesArea();
            } else {
                LogUtil.info("Category Tab Exist!");
                break;
            }
        }
    }

    public void tabOnCategoryTab() {
        waitForVisibilityOf("BUKAMART_CATEGORY_LIST_TAB", 3);
        tapElement("BUKAMART_CATEGORY_LIST_TAB");
    }

    public void skipOnboardingChangesArea() {
        if (isElementExist("BUKAMART_ONBOARDING_CHANGES_AREA")) {
            tapElement("BUKAMART_ONBOARDING_CHANGES_AREA_OK");
        }
    }
}
