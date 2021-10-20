package bukalapak.stepDefinitions.vp.prepaid;

import bukalapak.TestInstrument;
import bukalapak.data.vp.prepaid.ListrikPrabayarData;
import cucumber.api.java8.En;

public class ListrikPrabayarStepDefinitions extends TestInstrument implements En {

    public ListrikPrabayarStepDefinitions() {

        Then("the transaction customer id will have pre-filled on the Listrik Prabayar landing page", () -> {
            bukalapak.iOSBasePage().openDeepLink("/listrik-pln/token-listrik");
            bukalapak.listrikPrabayarLandingPage().validateOnPage();
            bukalapak.listrikPrabayarLandingPage().validateFieldIdPelanggan(ListrikPrabayarData.getCustomerId());
        });

        Then("the Listrik Prabayar landing page will have displayed", () -> {
            bukalapak.listrikPrabayarLandingPage().validateOnPage();
        });
    }
}
