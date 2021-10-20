package bukalapak.stepDefinitions.stripe;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class InputResiStepDefinitios extends TestInstrument implements En {

    public InputResiStepDefinitios() {
        
        When("user input Nomor Resi \"([^\"]*)\" column with the valid format of receipt number", (String resiNumb) -> {
            bukalapak.inputResiPage().typeOnMasukkanNomorResiEditText(resiNumb + bukalapak.inputResiPage().getRandomNum(1000));
         });

        And("user click masukkan input resi", () -> {
            bukalapak.inputResiPage().clickOpsionalInputResi();
        });

        And("user choose Lainnya to input manual courier", () -> {
            bukalapak.inputResiAlchemyPage().tapOnDropdownCourier();
        });

        And("user input courier name", () -> {
            bukalapak.inputResiAlchemyPage().tapOnInputCourierName("JNE REG");
        });

        And("user input Nomor Resi \"([^\"]*)\" column with the valid format of receipt number alchemy", (String resiNumb) -> {
            bukalapak.inputResiAlchemyPage().typeOnMasukkanNomorResiEditText(resiNumb + bukalapak.inputResiPage().getRandomNum(1000));
          });

        And("user save resi number after edited", () -> {
            bukalapak.inputResiAlchemyPage().tapOnSimpanNomorResi();
        });

        When("user taps on Masukkan Nomor Resi button alchemy", () -> {
            bukalapak.inputResiAlchemyPage().tapOnMasukkanNomorResi();
        });
    }
}
