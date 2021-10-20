package bukalapak.pageObject;

import bukalapak.data.STRIPEData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DetailPenjualanPage extends BasePage {

    public DetailPenjualanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void setNoteBuyer() {
        STRIPEData.setCatatanPembeli(getElementValue("detail_penjualan_catatan_textfield"));
    }

    public void setReceiverName() {
        String nama = getElementValue("detail_penjualan_nama_penerima_text");
        STRIPEData.setNamaPenerima(nama + " ");
    }
}
