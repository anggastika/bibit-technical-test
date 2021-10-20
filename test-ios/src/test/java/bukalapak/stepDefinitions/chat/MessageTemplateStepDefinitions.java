package bukalapak.stepDefinitions.chat;

import bukalapak.TestInstrument;
import bukalapak.data.CHATData;
import cucumber.api.java8.En;

public class MessageTemplateStepDefinitions extends TestInstrument implements En {

    public MessageTemplateStepDefinitions() {

        Then("user should see default message template", () -> {
            bukalapak.messageTemplatePage().verifyDefaultMessageTemplate();
        });

        Then("user create new message template", () -> {
            bukalapak.messageTemplatePage().clickCreateNewTemplateButton();
            bukalapak.messageTemplatePage().fillTemplateForm(CHATData.template);
            bukalapak.messageTemplatePage().clickSaveButton();
        });

        Then("user verify message template saved", () -> {
            bukalapak.messageTemplatePage().verifyMessageTemplate(CHATData.template);
        });

        Then("user edit existing message template", () -> {
            bukalapak.messageTemplatePage().clickExistingMessageTemplate();
            bukalapak.messageTemplatePage().fillTemplateForm(CHATData.template);
            bukalapak.messageTemplatePage().clickSaveButton();
        });

        Then("user delete existing message template", () -> {
            bukalapak.messageTemplatePage().clickExistingMessageTemplate();
            bukalapak.messageTemplatePage().clickDeleteButton();
        });

        Then("user verify message template deleted", () -> {
            bukalapak.messageTemplatePage().verifyMessageTemplateNotExist(CHATData.template);
        });
    }
}
