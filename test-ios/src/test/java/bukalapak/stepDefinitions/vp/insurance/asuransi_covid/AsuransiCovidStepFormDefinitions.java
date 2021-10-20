package bukalapak.stepDefinitions.vp.insurance.asuransi_covid;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import cucumber.api.java8.En;

public class AsuransiCovidStepFormDefinitions extends TestInstrument implements En {
    
    public AsuransiCovidStepFormDefinitions() {

        When("^user buy asuransi covid (1 Bulan|3 Bulan)$", (String premi) -> {
            bukalapak.asuransiCovidFormPengajuanPage().setInsuranceDataCovidInsurance(premi);
            bukalapak.asuransiCovidFormPengajuanPage().chooseCovidInsuranceType(premi);
        });

        And("^user fill (single|family) form asuransi covid$", (String quantity) -> {
            bukalapak.asuransiCovidFormPengajuanPage().validateFormPengajuanPage();
            bukalapak.asuransiCovidFormPengajuanPage().choosTypeInsurance(quantity.equals("family"));
            bukalapak.asuransiCovidFormPengajuanPage().tapCheckBoxCovidInsurance();
            bukalapak.asuransiCovidFormPengajuanPage().tapLanjutkanButtonCovidInsurance();
        });

        And("user should be able to see information that have active policy", () -> {
            bukalapak.asuransiCovidFormPengajuanPage().validateActivePolicy();
        });
    }
}
