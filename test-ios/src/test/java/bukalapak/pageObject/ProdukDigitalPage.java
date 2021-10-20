package bukalapak.pageObject;

import bukalapak.data.ProdukDigitalData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ProdukDigitalPage extends BasePage {

    public ProdukDigitalPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        waitForVisibilityOf("produk_digital_name", 50);
        validateDisplayed("produk_digital_name");
    }

    public void tapOnBeliSekarangButton() {
        waitForVisibilityOf("produk_digital_beli_sekarang_button", 50);
        ProdukDigitalData.setProdukDigitalName(getElementValue("produk_digital_name"));
        ProdukDigitalData.setProdukDigitalPrice(getElementValue("produk_digital_price"));
        tapElement("produk_digital_beli_sekarang_button");
    }

    public void validateProductName() {
        swipeUpToElement("produk_digital_total_harga_label");
        waitForVisibilityOf("produk_digital_name_label");
        validateValue().equals(ProdukDigitalData.getProdukDigitalName(), getTextFromElement("produk_digital_name_label"));
    }

    public void validateProductPrice() {
        swipeUpToElement("produk_digital_total_harga_label");
        waitForVisibilityOf("produk_digital_total_harga_label");
        validateValue().contains(ProdukDigitalData.getProdukDigitalPrice(), getTextFromElement("produk_digital_total_harga_label"));
    }
}
