package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PotongHargaData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PotongHargaPage extends BasePage {

    private String historyState;

    public PotongHargaPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnPotongHargaPage() {
        changeContext().toWebview();
        tapCloseOnboardingPopup();
        validateExist("POTONG_HARGA_BACKGROUND_WEB", 15);
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void tapOnIconOboardingPotongHarga() {
        tapElement("POTONG_HARGA_ONBOARDING_ICON");
    }

    private void tapCloseOnboardingPopup() {
        if (isElementExist("POTONG_HARGA_ONBOARDING_POPUP", 15)) {
            tapElement("POTONG_HARGA_ONBOARDING_POPUP_CLOSE");
        }
    }

    public void validateOnboardingPopupPotongHarga() {
        validateExist("POTONG_HARGA_ONBOARDING_POPUP");
        validateExist("POTONG_HARGA_CARA_POTONG_HARGA_TITLE");
        tapCloseOnboardingPopup();
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void validateFilterCategory() {
        validateExist("POTONG_HARGA_FILTER_CATEGORY", 20);
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void tapOnHistoryTab() {
        validateExist("POTONG_HARGA_HISTORY_TAB", 20);
        tapElement("POTONG_HARGA_HISTORY_TAB");
    }

    public void validateEmptyStateHistoryPage() {
        validateExist("POTONG_HARGA_EMPTY_STATE", 20);
        validateElementContainsText("POTONG_HARGA_EMPTY_STATE", "Belum pernah potong harga?");
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void tapOnSpecificFilterCategory(String category) {
        String tmpCategory = category;
        if (category.toLowerCase().equals("all")) {
            tmpCategory = "Semua";
            validateExist(constructLocator("POTONG_HARGA_SPECIFIC_FILTER_CATEGORY", tmpCategory), 15);
            tapElement(constructLocator("POTONG_HARGA_SPECIFIC_FILTER_CATEGORY", tmpCategory));
        } else {
            validateExist(constructLocator("POTONG_HARGA_SPECIFIC_FILTER_CATEGORY", tmpCategory), 15);
            tapElement(constructLocator("POTONG_HARGA_SPECIFIC_FILTER_CATEGORY", tmpCategory));
        }
    }

    public void validateTotalProductCardList() {
        int product_card = getElementsPresent("POTONG_HARGA_ALL_CARD_PRODUCT").size();
        int expectedProductCard = PotongHargaData.getTotalProductEvent();

        while (product_card < expectedProductCard) {
            webViewSwipeToElement(constructLocator("POTONG_HARGA_CARD_PRODUCT", product_card));
            product_card = getElementsPresent("POTONG_HARGA_ALL_CARD_PRODUCT").size();
        }

        validateExist(constructLocator("POTONG_HARGA_CARD_PRODUCT", product_card));
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void validateProductCardHistoryPage() {
        if (PotongHargaData.getStateHistoryProduct() != null) {
            switch (PotongHargaData.getStateHistoryProduct()) {
                case "succeed":
                    historyState = "Transaksi berhasil";
                    break;
                case "expired":
                    historyState = "Kedaluwarsa";
                    break;
                default:
                    LogUtil.error("State di product card history tidak ada");
                    break;
            }
            validateExist("POTONG_HARGA_HISTORY_NAME_PRODUCT", 20);
            validateElementWithText("POTONG_HARGA_HISTORY_NAME_PRODUCT", PotongHargaData.getProductNamePotongHarga());
            validateElementWithText("POTONG_HARGA_HISTORY_STATE", historyState);
            validateElementWithText("POTONG_HARGA_HISTORY_PRICE", getRpFromPrice(PotongHargaData.getCurrentPriceHistoryProduct()));
        } else {
            validateExist("POTONG_HARGA_HISTORY_NAME_PRODUCT", 20);
        }
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void validateProductDetailPotongHarga() {
        changeContext().toWebview();
        validateExist("POTONG_HARGA_PRODUCT_NAME_WEB", 30);
        if (PotongHargaData.getProductNamePotongHarga() != null){
            validateElementWithText("POTONG_HARGA_PRODUCT_NAME_WEB", PotongHargaData.getProductNamePotongHarga());
        }
        validateExist("POTONG_HARGA_PRODUCT_INFORMATION_SECTION", 15);
        validateExist("POTONG_HARGA_STATUS_POTONG_SECTION", 15);
        validateExist("POTONG_HARGA_BUTTON_ON_PDP", 15);
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void validateProductByCategory(String flag) {
        if (flag == null) {
            validateExist(constructLocator("POTONG_HARGA_CARD_PRODUCT_BY_CATEGORY", PotongHargaData.getProductNamePotongHarga()), 20);
        } else {
            validateNotExist(constructLocator("POTONG_HARGA_CARD_PRODUCT_BY_CATEGORY", PotongHargaData.getProductNamePotongHarga()), 10, "Product Card Still Exists");
        }
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void tapTncIcon() {
        validateExist("POTONG_HARGA_TNC_ICON", 20);
        tapElement("POTONG_HARGA_TNC_ICON");
    }

    public void validateTncPage() {
        validateExist("POTONG_HARGA_TNC_HEADER", 20);
        validateExist("POTONG_HARGA_TNC_TEXTVIEW", 10);
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void tapBackFromTnc() {
        validateExist("POTONG_HARGA_TNC_BACK_NAV", 10);
        tapElement("POTONG_HARGA_TNC_BACK_NAV");
    }

    public void validateProductAlreadySlash() {
        validateExist("POTONG_HARGA_TEMAN_BANTU_POTONG", 20);
        validateExist("POTONG_HARGA_MINTA_TOLONG_TEXT", 20);
        HelperData.setLastActionPage(new PotongHargaPage(iosDriver));
    }

    public void goToHomePage() {
        changeContext().toNative();
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
