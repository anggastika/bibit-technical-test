package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ApplyCCPageStepDefinitions extends TestInstrument implements En {

    public ApplyCCPageStepDefinitions() {

        When("user choose type of credit card", () -> {
            bukalapak.applyCCPage().chooseCard();
        });

        When("^user fill data on the Ajukan Kartu Kredit form with (valid|invalid) data$", (String dataType) -> {
            if(dataType.equals("valid")){
                bukalapak.applyCCPage().tapLanjutkanButton();
                bukalapak.applyCCPage().inputFillForm(
                        dotenv.get("APPLY_CC_NO_KTP"),dotenv.get("APPLY_CC_TEMPAT_LAHIR"),dotenv.get("APPLY_CC_MONTHLY_INCOME"));
            }else {
                bukalapak.applyCCPage().tapLanjutkanButton();
                bukalapak.applyCCPage().verifiInvalidFillForm(
                        dotenv.get("APPLY_CC_NO_KTP_WRONG"));
            }
        });

        When("the account verification pop up message will have displayed", () -> {
            bukalapak.applyCCPage().chooseCard();
            bukalapak.applyCCPage().validateUnverifiedAccount();
        });

        When("user navigates to Ajukan Kartu Kredit transaction history page", () -> {
            bukalapak.applyCCPage().tapOnIconTransactionHistory();
        });

        When("the user information will verify their data before submitted", () -> {
            bukalapak.applyCCPage().verifiSubmitForm();
        });

        Then("^the Ajukan Kartu Kredit transaction histories will (not)? have displayed$", (String type) -> {
            bukalapak.applyCCPage().validateTransactionHistoryPage();
            bukalapak.applyCCPage().validateItemLoaded(type);
        });

        Then("the error message will have displayed on Ajukan Kartu Kredit landing page", () -> {
            bukalapak.applyCCPage().showAlertMessage();
        });

    }

}
