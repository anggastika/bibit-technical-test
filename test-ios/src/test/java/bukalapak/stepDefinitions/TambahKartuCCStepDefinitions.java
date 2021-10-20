package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class TambahKartuCCStepDefinitions extends TestInstrument implements En {

    public TambahKartuCCStepDefinitions() {

        Then("user is in \"Tambah Kartu CC\" page", () -> {
            bukalapak.tambahKartuCCPage().userOnTambahKartuCCPage();
        });

        When("^user input credit card as (.*)$", (String numberCC) -> {
            bukalapak.tambahKartuCCPage().inputCCNumber(numberCC);
        });

        When("^user select month as (.*)$", (String month) -> {
            bukalapak.tambahKartuCCPage().selectMonth(month);
        });

        When("^user select year as (.*)$", (String year) -> {
            bukalapak.tambahKartuCCPage().selectYear(year);
        });

        When("^user input cvv as (.*)$", (String cvv) -> {
            bukalapak.tambahKartuCCPage().inputCVV(cvv);
        });
    }
}
