package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.OCAData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import com.bukalapak.salad.util.LogUtil;

public class KumpulinkWebPage extends BasePage {

    public KumpulinkWebPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnKumpulinkBuyerPage() {
        changeContext().toWebview();
        verifyElementExist("KUMPULINK_PREVIEW_NAME");
        verifyElementExist("KUMPULINK_PREVIEW_IMAGE");
    }

    public void verifyKumpulinkBuyerPage() {
        verifyElementExist("KUMPULINK_PREVIEW_BUKALAPAK_LINK");
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnVoucherCode() {
        if (isElementExist("KUMPULINK_BUYER_COPY_VOUCHER_BUTTON", 2)) {
            tapElement("KUMPULINK_BUYER_COPY_VOUCHER_BUTTON");
        }
    }

    public void successCopyVoucher() {
        verifyElementExist("KUMPULINK_BUYER_COPY_VOUCHER_MESSAGE");
    }

    private void tapOnboarding() {
        if (isElementVisible("KUMPULINK_ONBOARDING_TITLE")) {
            waitForVisibilityOf("KUMPULINK_FISRT_DESCRIPTION", 5);
            waitForVisibilityOf("KUMPULINK_ONBOARDING_NEXT_BUTTON", 5);
            tapElement("KUMPULINK_ONBOARDING_NEXT_BUTTON");
            waitForVisibilityOf("KUMPULINK_SECOND_DESCRIPTION", 5);
            waitForVisibilityOf("KUMPULINK_ONBOARDING_SKIP_BUTTON", 5);
            tapElement("KUMPULINK_ONBOARDING_SKIP_BUTTON");
        }
    }

    private void checkFirstVisit() {
        if (isElementVisible("KUMPULINK_LANDING_PAGE_FIRST_TIME")) {
            verifyElementExist("KUMPULINK_LANDING_PAGE_FIRST_TIME_DESC");
            verifyElementExist("KUMPULINK_LANDING_PAGE_FIRST_TIME_BUTTON");
            tapElement("KUMPULINK_LANDING_PAGE_FIRST_TIME_BUTTON");
        }
    }

    private void tapLinkTryAgain() {
        if (isElementExist("KUMPULINK_ERROR_STATE_CONTENT", 5)) {
            waitForVisibilityOf("KUMPULINK_TRY_AGAIN_BUTTON", 5);
            tapElement("KUMPULINK_TRY_AGAIN_BUTTON");
        }
    }

    public void isOnKumpulinkPage() {
        changeContext().toWebview();
        tapOnboarding();
        checkFirstVisit();
        tapLinkTryAgain();
        checkSectionBarang();
        verifyElementExist("KUMPULINK_LANDING_PAGE_PELAPAK_NAME");
        verifyElementExist("KUMPULINK_LANDING_PAGE_PELAPAK_PICTURE");
        verifyElementExist("KUMPULINK_LANDING_TAB_CONTAINER");
        verifyElementExist("KUMPULINK_SALIN_LINK_BUTTON");
        verifyElementExist("KUMPULINK_PREVIEW_BUTTON");
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void verifyBukalapakAsPrimaryLink() {
        validateExist("KUMPULINK_BUKALAPAK_PRIMARY_LINK", 20);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnAddLink() {
        verifyElementExist("KUMPULINK_TAMBAH_LINK");
        tapElement("KUMPULINK_TAMBAH_LINK");
    }

    public void isOnTambahLinkPage() {
        verifyElementExist("KUMPULINK_TAMBAH_LINK_DESCRIPTION");
        validateValue().contains("Bisa link jualan, link sosmed", getTextFromElement("KUMPULINK_TAMBAH_LINK_DESCRIPTION"));
    }

    public void inputLinkTitle(String linkTitle) {
        typeValue("KUMPULINK_TAMBAH_LINK_TITLE", linkTitle);
        OCAData.setLinkTitle(linkTitle);
    }

    public void inputLinkURL(String linkUrl) {
        typeValue("KUMPULINK_TAMBAH_LINK_URL", linkUrl);
    }

    public void validateNonWhitelistedLink() {
        validateExist("KUMPULINK_ERROR_LINK_MESSAGE", 5);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnCheckButton() {
        tapElement("KUMPULINK_TAMBAH_LINK_CHECKBOX");
    }

    public void tapOnSaveButton() {
        tapElement("KUMPULINK_TAMBAH_LINK_SAVE_BUTTON");
    }

    public void validateLink() {
        validateExist(constructLocator("KUMPULINK_LINK_NAME", OCAData.getLinkTitle()), 10);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnLInk(String link) {
        webViewSwipeToElement(constructLocator("KUMPULINK_NON_PRIMARY_LINK", link));
        tapElement(constructLocator("KUMPULINK_NON_PRIMARY_LINK", link));
    }

    public void validateLinkUpdated() {
        verifyElementExist("KUMPULINK_EDIT_LINK_SUCCESS_SNACKBAR");
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnDeleteButton() {
        tapElement("KUMPULINK_DELETE_LINK_BUTTON");
        verifyElementExist("KUMPULINK_DELETE_LINK_YES_BUTTON");
        tapElement("KUMPULINK_DELETE_LINK_YES_BUTTON");
    }

    public void validateDeleteLinkSuccess() {
        verifyElementExist("KUMPULINK_DELETE_SUCCESS_MESSAGE");
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapPasangKumpulinkButton() {
        tapElement("KUMPULINK_PASANG_BUTTON");
    }

    public void validatePasangKumpulinkSheet() {
        validateExist("KUMPULINK_PASANG_TITLE", 10);
        validateExist("KUMPULINK_PROFILE_COPY_CONTENT", 5);
        validateValue().contains(OCAData.getPelapakUsername(), getText("KUMPULINK_PROFILE_COPY_CONTENT"));
    }

    public void tapOnCopyButton() {
        tapElement("KUMPULINK_SALIN_LINK_BUTTON");
    }

    public void tapOnPreviewButton() {
        tapElement("KUMPULINK_PREVIEW_BUTTON");
    }

    public void isOnPreviewPage() {
        verifyElementExist("KUMPULINK_PREVIEW_NAME");
        verifyElementExist("KUMPULINK_PREVIEW_IMAGE");
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnBukalapaLink() {
        verifyElementExist("KUMPULINK_PREVIEW_BUKALAPAK_LINK");
        tapElement("KUMPULINK_PREVIEW_BUKALAPAK_LINK");
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapToggleKumpulink() {
        verifyElementExist("KUMPULINK_LINK_STATUS");
        OCAData.setLinkStatus(getTextFromElement("KUMPULINK_LINK_STATUS"));
        tapElement("KUMPULINK_TOGGLE_BUTTON");
    }

    public void validateKumpulinkStatusChange() {
        validateExist("KUMPULINK_LINK_STATUS", 5);
        validateValue().notEquals(OCAData.getLinkStatus(), getTextFromElement("KUMPULINK_LINK_STATUS"));
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnHowtoEntryPoint() {
        validateExist("KUMPULINK_HOW_TO_USE_LANDING_PAGE", 5);
        tapElement("KUMPULINK_HOW_TO_USE_LANDING_PAGE");
    }

    public void isOnHowtoPage() {
        validateExist("KUMPULINK_HOW_TO_CREATE", 5);
        validateExist("KUMPULINK_HOW_TO_SHARE", 5);
        tapElement("KUMPULINK_HOW_TO_USE_LANDING_PAGE", Direction.DOWN);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void validateCopyLinkButton() {
        validateExist("KUMPULINK_PREVIEW_COPY_LINK_BUTTON", 5);
    }

    public void tapOnCopyLinkButton() {
        tapElement("KUMPULINK_PREVIEW_COPY_LINK_BUTTON");
    }

    public void validateCopyLinkSnackBar() {
        validateExist("KUMPULINK_PREVIEW_COPY_LINK_SUCCESS_SNACKBAR", 10);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void validateUbahProfile() {
        validateExist("KUMPULINK_PREVIEW_EDIT_PROFILE_LINK", 5);
    }

    public void tapOnUbahProfile() {
        tapElement("KUMPULINK_PREVIEW_EDIT_PROFILE_LINK");
    }

    public void validateEditProfilePage() {
        validateExist("KUMPULINK_TOKO_PROFILE_PICTURE", 20);
        validateExist("KUMPULINK_TOKO_NAME_FIELD", 10);
        validateExist("KUMPULINK_LINK_TOKO_FIELD", 10);
        validateExist("KUMPULINK_DESCIPTION_FIELD", 10);
        validateExist(constructLocator("KUMPULINK_THEME_OPTION", 1), 10);
        validateExist("KUMPULINK_SIMPAN_BUTTON", 10);
    }

    public void validateProductList() {
        validateExist("KUMPULINK_PRODUCT_LIST", 10);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void checkSectionBarang() {
        if (!isElementExist("KUMPULINK_TAMBAH_BARANG", 15)) {
            refreshWebview();
        }
        if (isElementExist("KUMPULINK_SECTION_TITLE")) {
            validateExist("KUMPULINK_EDIT_CATALOG_ICON", 20);
        }
        validateExist("KUMPULINK_TAMBAH_BARANG", 20);
    }

    public void validateEmptyState() {
        validateExist("KUMPULINK_PRODUCT_EMPTY_IMAGE",10);
        validateExist("KUMPULINK_PRODUCT_EMPTY_TITLE",10);
    }

    public void tapButtonTambahBarang() {
        tapElement("KUMPULINK_TAMBAH_BARANG");
    }

    public void inputTokoName(String tokoName) {
        OCAData.setTokoName(tokoName);
        typeValue("KUMPULINK_TOKO_NAME_FIELD", OCAData.getTokoName());
    }

    public void inputDescription(String description) {
        OCAData.setTokoDescription(description);
        typeValue("KUMPULINK_DESCIPTION_FIELD", OCAData.getTokoDescription());
    }

    public void chooseTheme() {
        int randomTheme = randomize().numberBetween(1, 16);

        tapElement(constructLocator("KUMPULINK_THEME_OPTION", randomTheme));
    }

    public void validateSimpanProfile() {
        validateExist("KUMPULINK_SIMPAN_BUTTON", 5);
    }

    public void tapOnSimpanProfile() {
        tapElement("KUMPULINK_SIMPAN_BUTTON");
    }

    public void validateSuccessEditProfileSnackBar() {
        validateExist("KUMPULINK_EDIT_PROFILE_SUCCESS_SNACKBAR", 15);
    }

    public void validateProfileSuccessEdited() {
        validateElementContainsText("KUMPULINK_PREVIEW_NAME", OCAData.getTokoName());
        validateElementContainsText("KUMPULINK_PREVIEW_DESCRIPTION", OCAData.getTokoDescription());
    }

    public void verifyPopupAddCatalog() {
        validateExist("KUMPULINK_TAMBAH_KATALOG_BARANG_TITLE", 10);
        validateExist("KUMPULINK_SECTION_NAME_FIELD", 5);
        validateExist("KUMPULINK_BUAT_SEKARANG_BUTTON", 5);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void inputCatalogTitle() {
        OCAData.setCatalogName(fakerUtil().getFakeName());
        waitForVisibilityOf("KUMPULINK_SECTION_NAME_FIELD", 10);
        typeValue("KUMPULINK_SECTION_NAME_FIELD", OCAData.getCatalogName());
    }

    public void tapSaveCatalogTitle() {
        waitForVisibilityOf("KUMPULINK_BUAT_SEKARANG_BUTTON", 5);
        tapElement("KUMPULINK_BUAT_SEKARANG_BUTTON");
    }

    public void tapProductCheckbox() {
        waitForVisibilityOf("KUMPULINK_PRODUCT_ANIMATED", 10);
        waitForVisibilityOf("KUMPULINK_PRODUCT_DESCRIPTION", 5);
        waitForVisibilityOf("KUMPULINK_PRODUCT_CHECKBOX", 5);
        tapElement("KUMPULINK_PRODUCT_CHECKBOX");
    }

    public void tapSubmitProductButton() {
        waitForVisibilityOf("KUMPULINK_SUBMIT_PRODUCT", 5);
        tapElement("KUMPULINK_SUBMIT_PRODUCT");
    }

    public void verifyManageProductPage() {
        validateExist("KUMPULINK_JUDUL_KATALOG_LABEL", 10);
        validateExist("KUMPULINK_PRODUCT_INFO", 5);
        validateExist("KUMPULINK_ATUR_LINK", 5);
        validateExist("KUMPULINK_SAVE_CATALOG_BUTTON", 5);
        validateExist("KUMPULINK_DELETE_CATALOG_BUTTON", 5);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapSelesaiButton() {
        waitForVisibilityOf("KUMPULINK_SAVE_CATALOG_BUTTON", 5);
        tapElement("KUMPULINK_SAVE_CATALOG_BUTTON");
    }

    public void verifyCatalogSuccesfullyAdded() {
        waitForVisibilityOf("KUMPULINK_SECTION_TITLE", 10);
        validateElementContainsText("KUMPULINK_SECTION_TITLE", OCAData.getCatalogName());
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapEditIconCatalog() {
        waitForVisibilityOf("KUMPULINK_SECTION_TITLE", 10);
        OCAData.setCatalogName("KUMPULINK_SECTION_TITLE");
        waitForVisibilityOf("KUMPULINK_EDIT_CATALOG_ICON", 10);
        tapElement("KUMPULINK_EDIT_CATALOG_ICON");
    }

    public void tapHapusCatalogButton() {
        waitForVisibilityOf("KUMPULINK_DELETE_CATALOG_BUTTON", 10);
        tapElement("KUMPULINK_DELETE_CATALOG_BUTTON");
    }

    public void tapYesDeletedCatalogPopup() {
        waitForVisibilityOf("KUMPULINK_CONFIRM_DELETE_BUTTON", 10);
        tapElement("KUMPULINK_CONFIRM_DELETE_BUTTON");
    }

    public void verifyCatalogDeleteSuccesfully() {
        validateExist("KUMPULINK_SUCCESS_DELETE_CATALOG_SNACKBAR", 10);
        validateExist("KUMPULINK_SECTION_TITLE", 5);
        validateValue().notEquals(getText("KUMPULINK_SECTION_TITLE"), OCAData.getCatalogName());
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void inputEditSectionTitle() {
        OCAData.setCatalogName(fakerUtil().getFakeName());
        waitForVisibilityOf("KUMPULINK_EDIT_SECTION_NAME_FIELD", 10);
        typeValue("KUMPULINK_EDIT_SECTION_NAME_FIELD", OCAData.getCatalogName());
    }

    public void validateCatalogEditSuccess() {
        validateExist("KUMPULINK_SECTION_TITLE", 10);
        validateValue().contains(OCAData.getCatalogName(), getText("KUMPULINK_SECTION_TITLE"));
        validateExist("KUMPULINK_EDIT_CATALOG_SUCCESS_SNACKBAR", 10);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapAturLink() {
        waitForVisibilityOf("KUMPULINK_ATUR_LINK", 10);
        tapElement("KUMPULINK_ATUR_LINK");
    }

    public void validateAturLinkPage() {
        validateExist("KUMPULINK_ATUR_LINK_INFO", 10);
        validateExist("KUMPULINK_PRODUCT_LINK_DETAIL", 5);
        validateExist("KUMPULINK_LINK_UTAMA", 5);
        validateExist("KUMPULINK_ADD_LINK_BUTTON", 5);
        validateExist("KUMPULINK_SIMPAN_LINK_BUTTON", 5);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapTambahLinkBarangButton() {
        tapElement("KUMPULINK_ADD_LINK_BUTTON");
    }

    public void inputLinkNameBarang(String linkName) {
        waitForVisibilityOf("KUMPULINK_NAME_LINK_FIELD", 5);
        typeValue("KUMPULINK_NAME_LINK_FIELD", linkName);
    }

    public void inputLinkUrlBarang(String linkUrl) {
        waitForVisibilityOf("KUMPULINK_URL_LINK_FIELD", 5);
        typeValue("KUMPULINK_URL_LINK_FIELD", linkUrl);
    }

    public void tapSimpanLinkBarang() {
        waitForVisibilityOf("KUMPULINK_SIMPAN_LINK_BUTTON", 10);
        tapElement("KUMPULINK_SIMPAN_LINK_BUTTON");
    }

    public void tapDeleteLinkIcon() {
        waitForVisibilityOf("KUMPULINK_DELETE_ICON_LINK", 10);
        tapElement("KUMPULINK_DELETE_ICON_LINK");
    }

    public void tapConfirmDeleteLink() {
        waitForVisibilityOf("KUMPULINK_CONFIRM_DELETE_LINK", 10);
        tapElement("KUMPULINK_CONFIRM_DELETE_LINK");
    }

    public void validateLinkProductSuccessDeleted() {
        validateExist("KUMPULINK_DELETE_LINK_SUCCESS_SNACKBAR", 10);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnUrutanTab() {
        tapLinkTryAgain();
        waitForVisibilityOf("KUMPULINK_URUTAN_TAB", 10);
        tapElement("KUMPULINK_URUTAN_TAB");
    }

    public void tapOnOrderOption(String option) {
        waitForVisibilityOf("KUMPULINK_URUTAN_POPUP", 20);
        switch (option.toLowerCase()) {
            case "stok terbanyak":
                waitForVisibilityOf("KUMPULINK_ORDER_BY_STOCK", 15);
                tapElement("KUMPULINK_ORDER_BY_STOCK");
                break;
            case "termurah":
                waitForVisibilityOf("KUMPULINK_ORDER_BY_TERMURAH", 15);
                tapElement("KUMPULINK_ORDER_BY_TERMURAH");
                break;
            default:
                LogUtil.error("Wrong option name!");
                break;
        }
    }

    public void validateSortByOption(String option) {
        waitForVisibilityOf("KUMPULINK_PRODUCT_STOCK", 20);
        waitForVisibilityOf("KUMPULINK_PRODUCT_PRICE_LIST", 20);
        int productPriceFirst = parseIntegerFromText(getTextFromElement("KUMPULINK_PRODUCT_PRICE_LIST", 0));
        int productPriceSecond = parseIntegerFromText(getTextFromElement("KUMPULINK_PRODUCT_PRICE_LIST", 1));
        switch (option.toLowerCase()) {
            case "stok terbanyak":
                int productStockFirst = parseIntegerFromText(getTextFromElement("KUMPULINK_PRODUCT_STOCK", 0));
                int productStockSecond = parseIntegerFromText(getTextFromElement("KUMPULINK_PRODUCT_STOCK", 1));
                validateValue().equalsTrue(productStockFirst >= productStockSecond);
                break;
            case "termurah":
                validateValue().equalsTrue(productPriceFirst <= productPriceSecond);
                break;
            case "termahal":
                validateValue().equalsTrue(productPriceFirst >= productPriceSecond);
                break;
            default:
                LogUtil.error("Wrong option name!");
                break;
        }
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnEtalaseTab() {
        tapLinkTryAgain();
        waitForVisibilityOf("KUMPULINK_ETALASE_TAB", 10);
        tapElement("KUMPULINK_ETALASE_TAB");
    }

    public void tapOnEtalaseOption(String option) {
        waitForVisibilityOf("KUMPULINK_ETALASE_POPUP", 10);
        waitForVisibilityOf(constructLocator("KUMPULINK_ETALASE_OPTION", option), 5);
        tapElement(constructLocator("KUMPULINK_ETALASE_OPTION", option));
    }

    public void validateEtalaseByOption(String option) {
        validateExist("KUMPULINK_PRODUCT_LIST_NAME", 10);
        validateElementContainsText("KUMPULINK_PRODUCT_LIST_NAME", option);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void tapOnSettingOrderButton() {
        waitForVisibilityOf("KUMPULINK_ORDER_LINK_BUTTON");
        tapElement("KUMPULINK_ORDER_LINK_BUTTON");
    }

    public void validateSettingOrderPage() {
        validateExist("KUMPULINK_ORDER_DRAG_HANDLE",10);
        validateExist("KUMPULINK_ORDER_RESET_BUTTON",10);
        validateExist("KUMPULINK_ORDER_PREVIEW_BUTTON",10);
        validateExist("KUMPULINK_ORDER_SAVE_BUTTON",10);
        HelperData.setLastActionPage(new KumpulinkWebPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapAturBarangLinkIcon() {
        tapElement("KUMPULINK_MANAGE_LINK_ICON");
    }
}
