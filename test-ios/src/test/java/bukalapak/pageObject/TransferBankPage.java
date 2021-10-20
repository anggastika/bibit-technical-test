package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.MtxData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 2/1/19.
 */
public class TransferBankPage extends BasePage {

    public TransferBankPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnTransferBankPage() {
        verifyElementExist("transfer_bank_page_title");
        TransactionData.setPaymentMethod("transfer");
        MtxData.setPaymentMethod("transfer");
        HelperData.setLastActionPage(new TransferBankPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
