package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PajakDaerahCustomerInfoPage extends BasePage {

    public PajakDaerahCustomerInfoPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void choosePaymentVA(String va) {
        swipeDownToElement("pajak_daerah_va_method");
        tapElement("pajak_daerah_va_method");
        swipeDownToElement("pajak_daerah_" + va + "_payment");
        tapElement("pajak_daerah_" + va + "_payment");
        swipeDownToElement("pajak_daerah_pay_button");
        tapElement("pajak_daerah_pay_button");

        if (isElementVisible("pajak_daerah_continue_va")) {
            tapElement("pajak_daerah_continue_va");
        }

        if (isElementVisible("pajak_daerah_pay_button")) {
            LogUtil.info("Selesaikan dulu 5 transaksi sebelumnya");
        }
    }

    public void choosePaymentCredits() {
        swipeDownToElement("pajak_daerah_credits_method");
        tapElement("pajak_daerah_credits_method");
        swipeDownToElement("pajak_daerah_pay_button");
        tapElement("pajak_daerah_pay_button");
    }

    public void validateNotEnoughCredits() {
        verifyElementExist("pajak_daerah_pay_button");
        HelperData.setLastActionPage(new PajakDaerahCustomerInfoPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("back_icon");
        tapElement("back_from_confirm_data_page");
        tapElement("pajak_daerah_back_icon");
        tapElement("back_icon");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
