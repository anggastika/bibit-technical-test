package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.XPRData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KirimBarangBukaSendPage extends BasePage {

    public KirimBarangBukaSendPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnKirimBarangPage() {
        verifyElementExist("kirim_barang_title");
        HelperData.setLastActionPage(new KirimBarangBukaSendPage(iosDriver));
    }

    public int getOnkosKirim() {
        String temp = getElementValue("kirim_barang_ongkos_kirim_text").replaceAll("\\(.*\\)", "");
        return Integer.parseInt(temp.replaceAll("[^0-9]", ""));
    }

    public void verifyOngkirIsChanged() {
        int ongkirBeforeChangeAddress = XPRData.getOngkirBukaSend();
        assertNotSame(ongkirBeforeChangeAddress, getOnkosKirim(), "ongkir berubah");
    }

    public void tapKirimbarang(String type) {
        int y, height;
        IOSElement element;
        if (type.equals("gunakan volumetrik")) {
            element = getElement("kirim_barang_gunakan_volumetrik_text", 5);
            y = element.getCoordinates().onPage().getY();
            height = element.getSize().getHeight();
            tapElement(30, y + (height / 2));
        } else {
            element = getElement("kirim_barang_gunakan_berat_text", 5);
            y = element.getCoordinates().onPage().getY();
            height = element.getSize().getHeight();
            tapElement(30, y + (height / 2));
        }
    }

    public void deleteTinggiBarang() {
        String tinggiBarang = getElementValue("kirim_barang_tinggi_field");
        for (int i = 0; i < tinggiBarang.length(); i++) {
            tapElement("kirim_barang_tinggi_field");
            tapElement("kirim_barang_delete_numpad_button");
        }
    }

    public void inputTinggiBarang(String tinggi) {
        String number = "name_";
        String temp;
        for (int i = 0; i < tinggi.length(); i++) {
            tapElement("kirim_barang_tinggi_field");
            number = number + tinggi.charAt(i);
            tapElement(number);
            temp = number.replaceAll("[0-9]", "");
            number = temp;
        }
    }

    public void verifyOngkirKirim(String kurir, String ongkir) {
        String courierName = getElementValue("kirim_barang_ongkos_kirim_text");
        String biayaOngkir = courierName.replaceAll("\\(.*\\)", "").replaceAll("[^0-9]", "");
        assertTrue(courierName.contains(kurir), "Kurir Sesuai");
        assertTrue(ongkir.equals(biayaOngkir), "Ongkir Sesuai");
    }

    public void verifyErrorMessageInMauKirimKemanaPage(String warningMessage) {
        String warning = getElementValue("kirim_barang_warning_message_text");
        assertEquals(warningMessage, warning);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
