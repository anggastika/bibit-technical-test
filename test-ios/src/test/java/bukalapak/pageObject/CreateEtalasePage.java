package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by Odheta on 3/10/19.
 */
public class CreateEtalasePage extends BasePage {

    public CreateEtalasePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void verifyPage() {
        waitForVisibilityOf("header_page_label", 20);
        HelperData.setLastActionPage(new CreateEtalasePage(iosDriver));
    }

    public void startSetEtalase(){
        swipeDownToElement("pengaturan_lapak_etalase");
        tapElement("pengaturan_lapak_etalase");
        waitForElementClickable("button_tambah_etalase", 5);
        tapElement("button_tambah_etalase");
    }

    public void createNewEtalase(){
        waitForVisibilityOf("textfield_nama_label");
        typeAndEnterValueWithTimeOut("textfield_nama_label", dotenv.get("NAMA_ETALASE"));
        typeAndEnterValueWithTimeOut("textfield_deskripsi_label", dotenv.get("DESKRIPSI_ETALASE"));
        tapElement("button_tambah_label");
        waitForElementClickable("header_page_label", 5);
    }

    public void verifyNewEtalase() {
        String newEtalase = "name_" + dotenv.get("NAMA_ETALASE");
        verifyElementExist(newEtalase);
    }

    public void updateExistEtalase(){
        String existEetalase = "name_" + dotenv.get("NAMA_ETALASE");
        tapElement(existEetalase);
        typeAndEnterValueWithTimeOut("textfield_nama_label", dotenv.get("NEW_NAMA_ETALASE"));
        typeAndEnterValueWithTimeOut("textfield_deskripsi_label", dotenv.get("NEW_DESKRIPSI_ETALASE"));
        tapElement("button_simpan_etalase");
    }

    public void verifyUpdatedEtalase() {
        String newEtalaseName = "value_" + dotenv.get("NEW_NAMA_ETALASE");
        String newEtalaseDesc = "value_" + dotenv.get("NEW_DESKRIPSI_ETALASE");
        tapElement(newEtalaseName);
        verifyElementExist(newEtalaseName);
        verifyElementExist(newEtalaseDesc);
        tapElement("button_simpan_etalase");
    }

    public void deleteEtalase() {
        tapElement("button_delete_label");
        waitForElementClickable("button_popup_hapus", 10);
        tapElement("button_popup_hapus");
    }

    public void verifyListEtalaseEmpty() {
        verifyElementExist("label_barang_kosong");
        HelperData.setLastActionPage(new CreateEtalasePage(iosDriver));
    }

    public void offeringSuperSellerPopUp() {
        waitForVisibilityOf("super_seller_popup", 20);
        HelperData.setLastActionPage(new CreateEtalasePage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
