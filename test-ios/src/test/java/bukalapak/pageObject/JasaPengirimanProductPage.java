package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.XPRData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class JasaPengirimanProductPage extends BasePage {

    public JasaPengirimanProductPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnJasaPengirimanProductPage() {
        verifyElementExist("jasa_pengiriman_product_title");
        verifyElementExist("jasa_pengiriman_product_sync_lapak");
        verifyElementExist("jasa_pengiriman_product_notes");
        HelperData.setLastActionPage(new JasaPengirimanProductPage(iosDriver));
    }

    public void validateAvailabilityCourierProduct(String courier, String availability) {
        swipeUpToElement(constructLocator("jasa_pengiriman_product_courier_txt", courier), 4);
        if (availability.equals("available")) {
            validateDisplayed(constructLocator("jasa_pengiriman_product_courier_txt", courier));
        } else {
            validateNotDisplayed(constructLocator("jasa_pengiriman_product_courier_txt", courier));
        }
        HelperData.setLastActionPage(new JasaPengirimanProductPage(iosDriver));
    }

    public void tapCheckboxCourier(String courier) {
        swipeUpToElement(constructLocator("jasa_pengiriman_product_courier_txt", courier));
        XPRData.setStatusCourier(getElementValue(constructLocator("jasa_pengiriman_product_courier_checkboxAV", courier)));
        tapElement(constructLocator("jasa_pengiriman_product_courier_checkboxAV", courier));

    }

    public void tapSimpanButton() {
        tapElement("jasa_pengiriman_product_simpan_button");
    }

    public void validateStatusCourier(String courier) {
        swipeUpToElement(constructLocator("jasa_pengiriman_product_courier_txt", courier));
        validateValue().notEquals(getElementValue(constructLocator("jasa_pengiriman_product_courier_checkboxAV", courier)), XPRData.getStatusCourier());
        HelperData.setLastActionPage(new JasaPengirimanProductPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
