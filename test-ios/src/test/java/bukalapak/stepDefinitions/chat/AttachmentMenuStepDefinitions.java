package bukalapak.stepDefinitions.chat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

import java.util.ArrayList;

public class AttachmentMenuStepDefinitions extends TestInstrument implements En {

    private ArrayList<String> attachmentProductIdentifier = new ArrayList<String>();

    public AttachmentMenuStepDefinitions() {

        When("user select \"([^\"]*)\" products to attach", (String amount) -> {
            bukalapak.attachmentMenuPage().tapCheckboxes(amount);
            attachmentProductIdentifier = bukalapak.attachmentMenuPage().saveProductsIdentifier();
        });

        And("user verify products attached successfully", () -> {
            bukalapak.attachmentMenuPage().verifyGeneratedProducts(attachmentProductIdentifier);
        });
    }
}
