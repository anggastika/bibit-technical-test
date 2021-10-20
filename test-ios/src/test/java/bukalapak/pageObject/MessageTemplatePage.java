package bukalapak.pageObject;

import bukalapak.data.CHATData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by dickyedgardo on 19/11/18.
 */
public class MessageTemplatePage extends BasePage {

    public MessageTemplatePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void verifyDefaultMessageTemplate() {
        verifyElementExist("default_template_1");
        verifyElementExist("default_template_2");
        HelperData.setLastActionPage(new MessageTemplatePage(iosDriver));
    }

    public void goToChatListPage() {
        tapBackButton();
        tapBackButton();
        HelperData.setLastActionPage(new ChatListPage(iosDriver));
    }

    public void goToHomePage() {
        tapBackButton();
        tapBackButton();
        tapBackButton();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickCreateNewTemplateButton() {
        validateDisplayed("message_template_add_template_button");
        tapElement("message_template_add_template_button");
    }

    public void fillTemplateForm(String template) {
        String currentTemplate = CHATData.getDynamicChatData();
        if (currentTemplate.equals("")) {
            validateDisplayed("message_template_template_form");
            typeAndEnterValue("message_template_template_form", template);
        } else {
            validateDisplayed(constructLocator("message_template_template_form_dynamic", currentTemplate));
            typeAndEnterValue(constructLocator("message_template_template_form_dynamic", currentTemplate), template);
        }
    }

    public void clickSaveButton() {
        validateDisplayed("message_template_save_button");
        tapElement("message_template_save_button");
    }

    public void verifyMessageTemplate(String template) {
        validateDisplayed(constructLocator("message_template_item", template));
    }

    public void editMessageTemplate(String template) {
        validateDisplayed(constructLocator("message_template_item", template));
        tapElement("message_template_item");
    }

    public void clickFirstMessageTemplate() {
        validateDisplayed("message_template_first_item");
        String template = getElementAttributeValue("message_template_first_item", "name");
        CHATData.setDynamicChatData(template);
        tapElement("message_template_first_item");
    }

    public void clickExistingMessageTemplate() {
        validateDisplayed(constructLocator("message_template_item", "newmessagetemplate"));
        tapElement(constructLocator("message_template_item", "newmessagetemplate"));
        CHATData.setDynamicChatData("newmessagetemplate");
    }

    public void clickDeleteButton() {
        validateDisplayed("message_template_delete_button");
        tapElement("message_template_delete_button");
    }

    public void verifyMessageTemplateNotExist(String template) {
        validateNotDisplayed(constructLocator("message_template_item", template));
    }
}
