package bukalapak.pageObject;

import bukalapak.data.PROMData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SetBudgetPromotedPushPage extends BasePage {

    public SetBudgetPromotedPushPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void inputNominalBudget(String nominalBudget) {
        typeAndEnterValueWithTimeOut("setbudgetpromottedpush_nominal_budget_textfield", String.valueOf(nominalBudget));

        //sending Nominal Budget Non Bonus
        PROMData.setNominalBudgetNonBonus(Integer.parseInt(nominalBudget));
        tapElement("setbudgetpromottedpush_harian_radio");
        tapElement("setbudgetpromottedpush_sekali_isi_radio");

        //handling if Basic Premium
        if (isElementVisible("setbudgetpromottedpush_bonus_budget_premium_basic", 5)) {
            PROMData.setNominalBudgetBonus(Integer.parseInt(nominalBudget) + 10);
        }
        //handling if Professional Premium
        else if (isElementVisible("setbudgetpromottedpush_bonus_budget_premium_pro", 5)) {
            PROMData.setNominalBudgetBonus(Integer.parseInt(nominalBudget) + 20);
        }
        //handling if Non Premium
        else PROMData.setNominalBudgetBonus(Integer.parseInt(nominalBudget));
    }
}
