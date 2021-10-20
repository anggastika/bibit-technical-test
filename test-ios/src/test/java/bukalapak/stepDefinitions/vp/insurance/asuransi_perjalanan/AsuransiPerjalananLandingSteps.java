package bukalapak.stepDefinitions.vp.insurance.asuransi_perjalanan;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiPerjalananLandingSteps extends TestInstrument implements En {
	public AsuransiPerjalananLandingSteps() {
		Given("^user taps on benefit asuransi Tiket (Kereta|Pesawat):? landing page$", (String flag) -> {
			bukalapak.asuransiPerjalananLandingPage().tapOnInsuranceButton(flag);
		});

		Then("user validate Asuransi Perjalanan product detail page", () -> {
			bukalapak.asuransiPerjalananLandingPage().validateAllSection();
		});

		Then("the Asuransi Perjalanan detail page will have displayed", () -> {
			bukalapak.asuransiPerjalananLandingPage().validateOnDetailPage();
		});

		When("user tap on cari tiket perjalanan button", () -> {
			bukalapak.asuransiPerjalananLandingPage().tapOnCariTiketPerjalanan();
		});

	}
}
