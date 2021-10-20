package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class PraRegisPage extends BasePage {

    public PraRegisPage(IOSDriver<IOSElement> iosDriver) {super(iosDriver); }

    public void userOnPraRegisPage() {
        verifyElementExist("pra_regis_title");
        verifyElementExist("pra_regis_description");
        verifyElementExist("phone_number_field");
        HelperData.setLastActionPage(new PraRegisPage(iosDriver));
    }

    public void enterPhoneNumber(String phoneNumber) {
        switch (phoneNumber){
            case "valid":
                typeAndEnterValueWithTimeOut("phone_number_field", dotenv.get("PRAREG_PHONE_NUMBER"));
                break;
            case "registered":
                typeAndEnterValueWithTimeOut("phone_number_field", dotenv.get("REGISTERED_PHONE_NUMBER"));
                break;
            case "invalid":
                typeAndEnterValueWithTimeOut("phone_number_field", "abcd1234");
                break;
            default:
                LogUtil.info("phone number not found!");
                break;
        }
        HelperData.setLastActionPage(new PraRegisPage(iosDriver));
    }

    public void validateEmptyPhoneNumber(){
        verifyElementExist("warning_empty_text");
        assertEquals("No. handphone wajib diisi ya", getElementLabel("warning_empty_text"));
        HelperData.setLastActionPage(new PraRegisPage(iosDriver));
    }

   public void validateWrongPhoneNumber(){
        verifyElementExist("warning_registered_text");
        assertEquals("Pastiin kamu masukin no. handphone yang tadi diisi pulsa", getElementLabel("warning_registered_text"));
        HelperData.setLastActionPage(new PraRegisPage(iosDriver));
   }

    public void validateInvalidPhoneNumber(){
        verifyElementExist("warning_invalid_text");
        HelperData.setLastActionPage(new PraRegisPage(iosDriver));
    }

   public void goToHomePage() { backToHomePage(); }
}
