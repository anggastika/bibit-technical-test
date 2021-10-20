package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by ferawati h.p on 02/09/20.
 */
public class TiketKeretaBuyerContactFormStepDefinitions extends TestInstrument implements En {
    public TiketKeretaBuyerContactFormStepDefinitions(){
        When("user fill in the booking form with the contact format order is invalid", () -> {
            bukalapak.travelTrainDetailOrderPage().tapOnChangeBuyerContact();
            bukalapak.tiketKeretaBuyerContactPage().inputFullname(false);
            bukalapak.tiketKeretaBuyerContactPage().inputEmail(false);
            bukalapak.tiketKeretaBuyerContactPage().inputPhone(false);
            bukalapak.tiketKeretaBuyerContactPage().tapSimpanButton();
        });

        Then("the validation text will have equalled the contact format must match", () -> {
            bukalapak.tiketKeretaBuyerContactPage().validateFullNameErrorMessage("invalid");
            bukalapak.tiketKeretaBuyerContactPage().validateEmailErrorMessage("invalid");
            bukalapak.tiketKeretaBuyerContactPage().validatePhoneNumberErrorMessage("invalid");
        });

        When("user fill in the booking form with the contact order blank", () -> {
            bukalapak.travelTrainDetailOrderPage().tapOnChangeBuyerContact();
            bukalapak.tiketKeretaBuyerContactPage().clearFullname();
            bukalapak.tiketKeretaBuyerContactPage().clearEmail();
            bukalapak.tiketKeretaBuyerContactPage().clearPhone();
            bukalapak.tiketKeretaBuyerContactPage().tapSimpanButton();
        });

        Then("the validation text will have equalled the contact order can not be empty", () -> {
            bukalapak.tiketKeretaBuyerContactPage().validateFullNameErrorMessage("empty");
            bukalapak.tiketKeretaBuyerContactPage().validateEmailErrorMessage("empty");
            bukalapak.tiketKeretaBuyerContactPage().validatePhoneNumberErrorMessage("empty");
        });
    }
}
