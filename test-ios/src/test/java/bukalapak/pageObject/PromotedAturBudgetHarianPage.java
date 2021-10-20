package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class PromotedAturBudgetHarianPage extends BasePage {

    public PromotedAturBudgetHarianPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnAturBudgetHarianPage() {
        verifyElementExist("promoted_atur_budget_harian_text");
        verifyElementExist("promoted_isi_budget_harian_text");
        verifyElementExist("promoted_simpan_pengaturan_budget_button");
        HelperData.setLastActionPage(new PromotedAturBudgetHarianPage(iosDriver));
    }

    public void setDailyBudgetToggle(String state) {
        if (state.equalsIgnoreCase("on")) {
            if (!PROMData.isPromotedDailyBudgetStatusActive()) {
                tapElement("promoted_harian_toggle_button");
            }
        } else {
            if (PROMData.isPromotedDailyBudgetStatusActive()) {
                tapElement("promoted_harian_toggle_button");
            }
        }
    }

    public void inputBidValue(String bid) {
        validateDisplayed("promoted_bid_text_enabled_field");
        typeAndEnterValueWithTimeOut("promoted_bid_text_field", bid);
    }

    public void setLoanCheckbox(String state) {
        waitForVisibilityOf("promoted_keyword_loan_checkbox", 15);
        boolean isChecked = Boolean.parseBoolean(getElementValue("promoted_keyword_loan_checkbox"));
        if (state.equalsIgnoreCase("disable")) {
            if (isChecked) {
                tapElement("promoted_keyword_loan_checkbox");
            }
        } else {
            if (!isChecked) {
                tapElement("promoted_keyword_loan_checkbox");
            }
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
