package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.XPRData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;


public class JasaPengirimanPage extends BasePage {

    public JasaPengirimanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnJasaPengirimanPage() {
        verifyElementExist("jasa_pengiriman_title");
        verifyElementExist("jasa_pengiriman_back_button");
        HelperData.setLastActionPage(new JasaPengirimanPage(iosDriver));
    }

    public void validateJanioInfo() {
        validateDisplayed("jasa_pengiriman_luar_negeri_txt");
        validateDisplayed("jasa_pengiriman_luar_negeri_info");
    }

    public void verifyPopUpWindow() {
        verifyElementExist("jasa_pengiriman_set_koordinat_title");
        verifyElementExist("jasa_pengiriman_set_koordinat_button");
    }

    public void verifyOnDemandEnable(String onDemandKurir) {
        if (("grab").equals(onDemandKurir)) {
            swipeDownToElement("jasa_pengiriman_grab_text");
            boolean isSelected = ("1").equals(getElementValue("jasa_pengiriman_grab_button"));
            assertTrue(isSelected, "Grab is enable");
        } else if (("go_send").equals(onDemandKurir)) {
            swipeDownToElement("jasa_pengiriman_go_send_text");
            boolean isSelected = ("1").equals(getElementValue("jasa_pengiriman_go_send_button"));
            assertTrue(isSelected, "go send is enable");
        }
    }

    public void userEnableOrDisableOnDemandService(String onDemand) {
        if (("go_send").equals(onDemand)) {
            swipeDownToElement("jasa_pengiriman_go_send_text");
            tapElement("jasa_pengiriman_go_send_button");
        } else if (("grab").equals(onDemand)) {
            swipeDownToElement("jasa_pengiriman_grab_text");
            tapElement("jasa_pengiriman_grab_button");
        }
    }

    public void changeTheSettingOnDemand() {
        tapElement("jasa_pengiriman_simpan_button");
        waitFor(5);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    private static int getIndexCourier(List<MobileElement> table, String courier) {
        int index = 1;
        for (MobileElement element : table) {
            String temp = element.getText();
            if (temp.equalsIgnoreCase(courier)) {
                return index;
            }
            if ((!temp.equalsIgnoreCase("Baca Ketentuan")) && !temp.contains("Dalam Negeri") && !temp.contains("Layanan")) {
                index++;
            }
        }
        return index;
    }

    public void tapCheckboxCourier(String courier) {
        List<MobileElement> table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeTextView");
        int index = getIndexCourier(table, courier);
        swipeUpToElement(constructLocator("jasa_pengiriman_checkbox", index));
        tapElement(constructLocator("jasa_pengiriman_checkbox", index));
    }

    public void validateCheckboxCourier(String courier, String status) {

        List<MobileElement> table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeTextView");
        int index = getIndexCourier(table, courier);
        swipeUpToElement(constructLocator("jasa_pengiriman_checkbox", index));
        validateValue().equals(getElementValue(constructLocator("jasa_pengiriman_checkbox", index)), status);
        HelperData.setLastActionPage(new JasaPengirimanPage(iosDriver));
    }

    public void verifyPopUpCOD() {
        verifyElementExist("jasa_pengiriman_popup_cod_title");
        verifyElementExist("jasa_pengiriman_popup_cod_button");
    }

    public void clickPopUpCODButton() {
        tapElement("jasa_pengiriman_popup_cod_button");
    }

    public void verifyPopUpSetLocation() {
        verifyElementExist("jasa_pengiriman_popup_coordinate_title");
        verifyElementExist("jasa_pengiriman_popup_coordinate_body");
        verifyElementExist("jasa_pengiriman_popup_coordinate_confirm_button");
        verifyElementExist("jasa_pengiriman_popup_coordinate_dismiss_button");
    }

    public void clickPopUpSetLocationButton() {
        tapElement("jasa_pengiriman_popup_coordinate_confirm_button");
    }

    public void verifyShowMaps() {
        changeContext().toWebview();
        verifyElementExist("jasa_pengiriman_coordinat_title");
        verifyElementExist("jasa_pengiriman_coordinat_ubah_button");
        verifyElementExist("jasa_pengiriman_coordinat_set_button");
    }

    public void allowPopupMapsPermission() {
        if (isElementExist("jasa_pengiriman_allow_popup_permission", 10)) {
            tapElement("jasa_pengiriman_allow_popup_permission");
        }
        if (isElementExist("home_close_debug_confirmation_ok_button", 50)) {
            tapElement("home_close_debug_confirmation_ok_button");
        }
    }

    public void tapBackToJasaPengirimanPage() {
        webViewTapOnElement("jasa_pengiriman_back_button_maps");
        changeContext().toNative();
        HelperData.setLastActionPage(new JasaPengirimanPage(iosDriver));
    }

    public void clickSetLocationButtonMaps() {
        tapElement("jasa_pengiriman_coordinat_set_button");
    }

    public void validateAvailabilityCourier(String courier, String availability) {
        swipeUpToElement(constructLocator("jasa_pengiriman_courier_txt", courier), 4);
        if (availability.equals("available")) {
            validateDisplayed(constructLocator("jasa_pengiriman_courier_txt", courier));
        } else {
            validateNotDisplayed(constructLocator("jasa_pengiriman_courier_txt", courier));
        }
        HelperData.setLastActionPage(new JasaPengirimanPage(iosDriver));
    }

    public void validateUpdatingCourier(String courier) {
        List<MobileElement> table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeTextView");
        int index = getIndexCourier(table, courier);
        swipeUpToElement(constructLocator("jasa_pengiriman_checkbox", index));
        validateValue().equals(getElementValue(constructLocator("jasa_pengiriman_checkbox", index)), XPRData.getStatusCourier());
        HelperData.setLastActionPage(new JasaPengirimanPage(iosDriver));
    }
}
