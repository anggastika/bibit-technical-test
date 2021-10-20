package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TambahKataKunciStepDefinition extends TestInstrument implements En {

    public TambahKataKunciStepDefinition() {

        Given("user is in \"Tambah Kata Kunci\" page", () -> {
            bukalapak.tambahKataKunciPage().userOnTambahKataKunciPage();
        });

        When("^user search keyword as (.*)$", (String keyword) -> {
            bukalapak.tambahKataKunciPage().searchKeyword(keyword);
        });


        When("^user select keyword as (.*)$", (String keyword) -> {
            bukalapak.tambahKataKunciPage().selectKeyword(keyword);
        });

        When("^user see \"([^\"]*)\" text as (.*) keywords are selected$", (String infoText, String totalKeywords) -> {
            bukalapak.tambahKataKunciPage().verifyInfoSelectedKeywords(infoText, totalKeywords);
        });
    }
}
