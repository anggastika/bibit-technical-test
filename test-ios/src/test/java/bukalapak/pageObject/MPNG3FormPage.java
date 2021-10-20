package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MPNG3FormPage extends BasePage {

    public MPNG3FormPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void inputBillCode(String billType) {
        typeAndEnterValueWithTimeOut("mpng3_billcode_input", TestInstrument.dotenv.get("MPNG3_BILL_" + billType));
        HelperData.setLastActionPage(new MPNG3FormPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("alchemy_navbar_back_button");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
