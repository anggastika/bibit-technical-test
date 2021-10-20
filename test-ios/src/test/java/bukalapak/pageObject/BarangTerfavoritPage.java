package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BarangTerfavoritPage extends BasePage {
    public BarangTerfavoritPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnBarangTerfavoritePage() {
        waitForVisibilityOf("barang_terfavorite_jumlah_difavoritkan", 5);
        verifyElementExist("barang_terfavorite_title");
    }

    public void clickBarangTerfavorite(int posisiProduct) {
        tapElement(constructLocator("barang_terfavorite_username", Integer.toString(posisiProduct)));
    }

    public void verifyNameBarangFavorite(String namaBarang) {
        waitForVisibilityOf("barang_terfavorite_username_list1", 20);
        assertTrue(getElement("barang_terfavorite_username_list1").getText().contains(namaBarang));
    }

    public void verifyPopUpFilter() {
        waitForVisibilityOf("barang_terfavorite_filter_title", 20);
        assertTrue(isElementVisible("barang_terfavorite_kategori"));
        assertTrue(isElementVisible("barang_terfavorite_label"));
    }

    public void clickMenuFilter(String menuFilter) {
        tapElement(constructLocator("barang_terfavorite_general_txt", menuFilter));
    }

    public void clickCheckBoxFilter(String urutanCheckbox) {
        tapElement(constructLocator("barang_terfavorite_filter_checkbox", urutanCheckbox));
    }
}
