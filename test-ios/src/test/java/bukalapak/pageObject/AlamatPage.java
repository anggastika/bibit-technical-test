package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.util.List;

public class AlamatPage extends BasePage {

    private static int totalAlamat = 0;

    public AlamatPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private void setTotalAlamat(int value) {
        totalAlamat = value;
    }

    public void userOnAlamatPage() {
        waitForVisibilityOf("alamat_title", 10);
        verifyElementExist("alamat_title");
        HelperData.setLastActionPage(new AlamatPage(iosDriver));
    }

    public void deleteAlamat(String alamat) {
        swipeUpToElement(constructLocator("alamat_label_text", alamat));
        if (isElementVisible("alamat_label_text")) {
            swipeDownToElement(constructLocator("alamat_label_text", alamat));
        }
        swipeUpToElement(constructLocator("alamat_hapus_per_address_button", alamat));
        tapElement("alamat_hapus_per_address_button");
    }

    public void deleteAlamatIndexKe(String index) {
        List<MobileElement> table;
        verifyElementExist("alamat_title");
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeCell");
        setTotalAlamat(table.size());
        int id = Integer.parseInt(index);
        IOSElement el = (IOSElement) getElements("alamat_elm_hapus", 10).get(id - 1);
        el.click();
        tapElement("alamat_hapus_popup_button");
        HelperData.setLastActionPage(new AlamatPage(iosDriver));
    }

    /**
     * This method is used to change shipping address
     *
     * @param detailAddress, new address will be used
     *                       labelAdress, title of address
     */
    public void changeShippingAddress(String detailAddress, String labelAddress) {
        swipeUpToElement(constructLocator("alamat_label_text", labelAddress));
        if (isElementVisible("alamat_label_text")) {
            swipeDownToElement(constructLocator("alamat_label_text", labelAddress));
        }
        tapElement(constructLocator("alamat_label_text", labelAddress));

        if (isElementVisible("checkout_marketplace_close_popup_ondemand_button")) {
            tapElement("checkout_marketplace_close_popup_ondemand_button");
        }
    }

    public void changeShippingAddressIndex(String index) {
        int id = Integer.parseInt(index);
        List<MobileElement> table;
        List<MobileElement> listButton;
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeCell");
        listButton = getElementsByClassname(table.get(id - 1), "XCUIElementTypeButton");
        listButton.get(0).click();
        HelperData.setLastActionPage(new AlamatPage(iosDriver));
    }

    public void editAlamat(String alamat, String labelAdrees) {
        swipeUpToElement(constructLocator("alamat_label_text", labelAdrees));
        if (isElementVisible("alamat_label_text")) {
            swipeDownToElement(constructLocator("alamat_label_text", labelAdrees));
        }
        swipeUpToElement(constructLocator("alamat_edit_per_address_button", labelAdrees));
        tapElement(constructLocator("alamat_edit_per_address_button", labelAdrees));
    }

    public void validateNewAddressisdeleted() {
        waitLoadingBar();
        List<MobileElement> table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeCell");
        if (table.size() != (totalAlamat - 1)) Assert.fail("new address is not deleted successfully");
    }

    public void waitLoadingBar() {
        int maxWait = 5;
        if (isElementVisible("alamat_harap_tunggu_text")) {

            for (int i = 0; i > maxWait; i++) {
                waitForVisibilityOf("alamat_edit_button");
                if (isElementVisible("alamat_edit_button")) {
                    break;
                }
            }
        }
    }

    private String getDetailAddress(String detailAdress) {
        return getTextFromElement(constructLocator("alamat_detil_adddress_text", detailAdress));
    }

    public void validateModifyAddress(String detilAddress) {
        userOnAlamatPage();
        nativeSwipeDown();
        assertTrue(getDetailAddress(detilAddress).contains(detilAddress), "editing address is saved successfully");
    }

    public void selectAddress(String label) {
        String selectedAddress = "labelcontains_" + label;
        swipeDownToElement(selectedAddress);
        tapElement(selectedAddress);
    }

    public void validateMaskingPhoneNumber() {
        swipeDownToElement("alamat_nomer_telpon_text");
        validateValue().equalsTrue(getElementLabel("alamat_nomer_telpon_text").contains("****"));
        HelperData.setLastActionPage(new AlamatPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateErrorMaxAddress() {
        validateDisplayed("alamat_error_full_address_message");
        HelperData.setLastActionPage(new AlamatPage(iosDriver));
    }

    public void tapSetUtamaOnSelectedAddress(String title) {
        if (!isElementVisible(constructLocator("alamat_selected_address_set_utama_button", title))) {
            swipeUpToElement(constructLocator("alamat_selected_address_set_utama_button", title));
        }
        tapElement(constructLocator("alamat_selected_address_set_utama_button", title));
    }

    public void validateAlamatUtamaUpdated(String actualTitleApiAddress) {
        if (!isElementVisible("alamat_utama_title")) {
            swipeDownToElement(("alamat_utama_title"));
        }
        validateValue().equalsTrue(actualTitleApiAddress.equals(getTextFromElement("alamat_utama_title")));
        HelperData.setLastActionPage(new AlamatPage(iosDriver));
    }
}
