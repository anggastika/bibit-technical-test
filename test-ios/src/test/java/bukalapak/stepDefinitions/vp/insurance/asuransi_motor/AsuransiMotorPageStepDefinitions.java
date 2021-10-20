package bukalapak.stepDefinitions.vp.insurance.asuransi_motor;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiMotorPageStepDefinitions extends TestInstrument implements En {

    public AsuransiMotorPageStepDefinitions() {

      When("user chooses a Asuransi Motor product", () -> {
          bukalapak.asuransiMotorPage().setPlanPrice();
          bukalapak.asuransiMotorPage().setPlanName();
          bukalapak.asuransiMotorPage().setLimitClaim();
          bukalapak.asuransiMotorPage().tapOnChoosePlanButton();
      });

      Then("the Asuransi Motor product list will have displayed", () -> {
         bukalapak.asuransiMotorPage().validateOnPage();
      });

      When("user does not input data on info pribadi form", () -> {
          bukalapak.asuransiMotorPage().validateOnDetailPage();
          bukalapak.asuransiMotorPage().tapOnBuyButton();
          bukalapak.asuransiMotorPage().validatePopUpModalShown();
          bukalapak.asuransiMotorPage().tapOnContinueButton();

          // Input product information
          bukalapak.asuransiMotorPage().inputChassisMotor();
          bukalapak.asuransiMotorPage().setBrandMotor();
          bukalapak.asuransiMotorPage().inputPlateMotor(true);
          bukalapak.asuransiMotorPage().setModelMotor();
          bukalapak.asuransiMotorPage().tapOnNextButton();
      });

        Then("^error message on \"([^\"]*)\" field the Asuransi Motor info (pribadi|motor) form will have displayed required$", (String field, String product) -> {
            bukalapak.asuransiMotorPage().validateErrorMessageRequiredInput(field);
        });

        When("user taps on pelajari button the Asuransi Motor", () -> {
            bukalapak.asuransiMotorPage().tapOnSeeDetailButton();
        });

        Then("the Asuransi Motor pelajari section will have displayed", () -> {
            bukalapak.asuransiMotorPage().validateDetailModalDisplayed();
            bukalapak.asuransiMotorPage().tapOnSeePackageButton();
        });

        When("user does not input data on info motor form", () -> {
            bukalapak.asuransiMotorPage().tapOnNextButton();
        });

        When("^user inputs (valid|invalid) data on info pribadi form$", (String state) -> {
            bukalapak.asuransiMotorPage().validateOnDetailPage();
            bukalapak.asuransiMotorPage().tapOnBuyButton();
            bukalapak.asuransiMotorPage().validatePopUpModalShown();
            bukalapak.asuransiMotorPage().tapOnContinueButton();

            // Input personal information
            bukalapak.asuransiMotorPage().validateOnRegistrationPage();
            bukalapak.asuransiMotorPage().inputInsuredName();
            bukalapak.asuransiMotorPage().setInsuredBirthday();
            bukalapak.asuransiMotorPage().inputInsuredEmail(state == null);
            bukalapak.asuransiMotorPage().inputInsuredPhoneNumber(state == null);
            bukalapak.asuransiMotorPage().tapOnNextButton();
        });

        When("user buys a Asuransi Motor package with a valid data", () -> {
            bukalapak.asuransiMotorPage().validateOnDetailPage();
            bukalapak.asuransiMotorPage().tapOnBuyButton();
            bukalapak.asuransiMotorPage().validatePopUpModalShown();
            bukalapak.asuransiMotorPage().tapOnContinueButton();

            // Input personal information
            bukalapak.asuransiMotorPage().validateOnRegistrationPage();
            bukalapak.asuransiMotorPage().inputInsuredName();
            bukalapak.asuransiMotorPage().setInsuredBirthday();
            bukalapak.asuransiMotorPage().inputInsuredEmail(true);
            bukalapak.asuransiMotorPage().inputInsuredPhoneNumber(true);

            // Input product information
            bukalapak.asuransiMotorPage().inputChassisMotor();
            bukalapak.asuransiMotorPage().inputPlateMotor(true);
            bukalapak.asuransiMotorPage().setBrandMotor();
            bukalapak.asuransiMotorPage().setModelMotor();
            bukalapak.asuransiMotorPage().tapOnNextButton();

            // Validate data on purchased detail page
            bukalapak.asuransiMotorPage().validateOnDetailPurchasedPage();
            bukalapak.asuransiMotorPage().validatePlanInfo();
            bukalapak.asuransiMotorPage().validatePersonalInfo();
            bukalapak.asuransiMotorPage().validateMotorInfo();
            bukalapak.asuransiMotorPage().tapOnTNCCheckbox();
            bukalapak.asuransiMotorPage().tapOnContinueToPaymentButton();
        });

        Then("error message on info pribadi form will have displayed", () -> {
            bukalapak.asuransiMotorPage().tapOnNextButton();
            bukalapak.asuransiMotorPage().validateInvalidEmail();
            bukalapak.asuransiMotorPage().validateInvalidPhone();
        });
  }
}
