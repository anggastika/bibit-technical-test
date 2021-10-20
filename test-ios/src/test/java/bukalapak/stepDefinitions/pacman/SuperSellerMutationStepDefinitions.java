package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSellerMutationStepDefinitions extends TestInstrument implements En {
    public SuperSellerMutationStepDefinitions() {
        And("user is in \"Super Seller Mutation\" page", () -> {
            bukalapak.superSellerMutationPage().userOnSuperSellerMutation();
        });

        And("user is shown pop up New Mutasi Super Seller", () -> {
            bukalapak.superSellerMutationPage().verifyNewSuperSellerMutationPopUp();
        });

        When("user choose trx id \"([^\"]*)\"", (String trxId) -> {
            bukalapak.superSellerMutationPage().tapTransactionId(trxId);
        });

        Then("user is shown Info Biaya Super Seller", () -> {
            bukalapak.superSellerMutationPage().verifyInfoBiayaSuperSeller();
        });
    }
}
