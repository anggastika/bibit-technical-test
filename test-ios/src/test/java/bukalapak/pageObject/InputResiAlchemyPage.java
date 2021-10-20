package bukalapak.pageObject;

import bukalapak.data.STRIPEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class InputResiAlchemyPage extends BasePage {
    
    public InputResiAlchemyPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void typeOnMasukkanNomorResiEditText(String resiNumber) {
        if (isElementVisible("penjualan_resi_manual_alchemy_text", 10)) {
            typeAndEnterValueWithTimeOut("penjualan_resi_manual_alchemy_text", resiNumber);
        } else if (isElementVisible("penjualan_edit_nomor_resi_xpath", 10)){
            typeAndEnterValueWithTimeOut("penjualan_edit_nomor_resi_xpath", resiNumber);
        }
        STRIPEData.setResiNumber(resiNumber);
    }

    public void tapOnInputCourierName(String namaKurir) {
        typeAndEnterValueWithTimeOut("penjualan_textfield_courier_manual_alchemy", namaKurir + "");
   }

    public void tapOnMasukkanNomorResi() {
        tapElement("input_resi_btn");
    }

    public void tapOnDropdownCourier() {
        tapElement("penjualan_dropdown_edit_kurir_alchemy_id");
        swipeDownToElement("penjualan_textview_courier_lainnya_alchemy", 15);
        tapElement("penjualan_textview_courier_lainnya_alchemy");
    }

    public void tapOnSimpanNomorResi() {
        waitForElementClickable("penjualan_simpan_edit_resi_text", 10);
        tapElement("penjualan_simpan_edit_resi_text");
    }
}
