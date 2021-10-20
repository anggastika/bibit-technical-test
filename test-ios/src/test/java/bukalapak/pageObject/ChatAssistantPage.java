package bukalapak.pageObject;

import bukalapak.data.CHATData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

public class ChatAssistantPage extends BasePage {

    public ChatAssistantPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void isOnChatAssistantPage() {
        verifyElementExist("chat_assistant_title_text");
    }

    public void toggleChatAssitant(String type) {
        verifyElementExist(constructLocator("chat_assistant_toggle", getAssistantType(type)));
        tapElement(constructLocator("chat_assistant_toggle", getAssistantType(type)));
    }

    public void editAssistantText(String type) {
        tapUbahButton(type);
        verifyElementExist(constructLocator("chat_assistant_edit_field", getAssistantType(type)));
        typeAndEnterValueWithTimeOut(constructLocator("chat_assistant_edit_field", getAssistantType(type)), CHATData.editedTemplate);
        tapSimpanButton(type);
    }

    public void tapUbahButton(String type) {
        verifyElementExist(constructLocator("chat_assistant_edit_ubah_button", getAssistantType(type)));
        tapElement(constructLocator("chat_assistant_edit_ubah_button", getAssistantType(type)));
    }

    public void tapSimpanButton(String type) {
        verifyElementExist(constructLocator("chat_assistant_edit_simpan_button", getAssistantType(type)));
        tapElement(constructLocator("chat_assistant_edit_simpan_button", getAssistantType(type)));
    }
    public void assertAssistantText(String type) {
        verifyElementExist(constructLocator("chat_assistant_edit_content", getAssistantType(type), CHATData.editedTemplate));
    }

    private String getAssistantType(String type) {
        String text;
        switch (type) {
            case "tutup lapak":
                text = "Balasan saat lapak tutup";
                break;
            case "selamat datang":
                text = "Salam selamat datang";
                break;
            case "offline":
            default:
                text = "Balasan saat offline";
                break;
        }
        return text;
    }
}
