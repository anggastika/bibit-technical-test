package bukalapak.stepDefinitions.dana;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by Denis Hendriansah on 20/09/2019.
 */
public class DANAVoucherkuStepDefinitions extends TestInstrument implements En {
    public DANAVoucherkuStepDefinitions() {
        Then("user tap the first DANA voucher", () -> {
            bukalapak.danaVoucherkuPage().tapFirstDANAVoucher();
        });

        When("user compare voucher detail to user \"([^\"]*)\" DANA pocket", (String danaUser) -> {
            bukalapak.apiCall().setUserAuthv4(danaUser);
            bukalapak.danaVoucherkuPage().validateDetailVoucherkuDANA();
        });
    }
}
