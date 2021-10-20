package bukalapak.pageObject.pnl;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class RecurringTransactionFormPage extends BasePage {
    public RecurringTransactionFormPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver); }

    public void userOnRecurringTransactionFormPage() {
        waitForVisibilityOf("RECURRING_TRANSACTION_FORM_VP_INPUT_FIELD",10);
    }

    public void typeOnVANumber(String productType) {
        switch (productType) {
            case "Bpjs dji":
                typeAndEnterValue("RECURRING_TRANSACTION_FORM_VP_INPUT_FIELD", dotenv.get("BPJS_PNL_DJI"));
                break;
            case "Bpjs sepulsa":
                typeAndEnterValue("RECURRING_TRANSACTION_FORM_VP_INPUT_FIELD", dotenv.get("BPJS_PNL_SEPULSA"));
                break;
            case "Listrik":
                typeAndEnterValue("RECURRING_TRANSACTION_FORM_VP_INPUT_FIELD", dotenv.get("LISTRIK_PNL_POSTPAID"));
                break;
            case "Air PDAM":
                tapElement("RECURRING_TRANSACTION_FORM_VP_DROPDOWN_PILIH_AREA_FIELD",35);
                tapElement("RECURRING_TRANSACTION_PDAM_AREA_KAB_BULELENG",30);
                typeAndEnterValue("RECURRING_TRANSACTION_FORM_VP_INPUT_FIELD", dotenv.get("AIR_PDAM_PNL_POSTPAID"));
                break;
            case "Telkom/IndiHome":
                typeAndEnterValue("RECURRING_TRANSACTION_FORM_VP_INPUT_FIELD", dotenv.get("TELKOM_INDIHOME_PNL"));
                break;
            default:
                LogUtil.error("Product doesn't exist");
                break;
        }
    }

    public void tapPilihArea() {
        waitForVisibilityOf("RECURRING_TRANSACTION_FORM_VP_DROPDOWN_PILIH_AREA_FIELD",10);
        tapElement("RECURRING_TRANSACTION_FORM_VP_DROPDOWN_PILIH_AREA_FIELD");
    }

    public void tapOnDate(String date) {
        tapElement("RECURRING_TRANSACTION_FORM_DATE_DROPDOWN");
        TransactionData.setRecurringDate(date);
    }

    public void tapOnTerapkanButton() {
        validateDisplayed("RECURRING_TRANSACTION_FORM_TERAPKAN_BUTTON");
        tapElement("RECURRING_TRANSACTION_FORM_TERAPKAN_BUTTON");
    }

    public void tapOnTnCCheckbox() {
        waitForVisibilityOf("RECURRING_TRANSACTION_FORM_CHECKBOX", 3);
        tapElement("RECURRING_TRANSACTION_FORM_CHECKBOX");
    }

    public void tapOnDaftarButton() {
        waitForVisibilityOf("RECURRING_TRANSACTION_FORM_DAFTAR_BUTTON",3);
        tapElement("RECURRING_TRANSACTION_FORM_DAFTAR_BUTTON");
    }

    public void tapOnMetodePembayaran() {
        waitForVisibilityOf("RECURRING_TRANSACTION_METODE_PEMBAYARAN_BUTTON",3);
        tapElement("RECURRING_TRANSACTION_METODE_PEMBAYARAN_BUTTON");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
