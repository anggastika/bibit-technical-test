package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.InfluencerData;
import bukalapak.data.InspirationData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Prasetyo Nugroho 2018
 */
public class InfluencerPage extends BasePage {

    public InfluencerPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnInfluencerPage() {
        assertTrue(isElementVisible("inspiration_post_container"));
        HelperData.setLastActionPage(new InfluencerPage(iosDriver));
    }

    public void checkInfluencerDescription() {
        String text = "value_" + InspirationData.getShortDescription();
        assertTrue(isElementVisible(text, 10), "element not visible");
    }

    public void checkTotalPosts(Integer totalPost) {
        assertEquals(String.valueOf(totalPost), getElementValue("influencer_total_post_text"));
    }

    public void checkPostContent(DataTable arg0) {
        List<Map<String, String>> post = arg0.asMaps();
        //List<WebElement> elementsInfluencerName = getElements(getLocator("inspiration_name_text"), 6);
        String postval;
        for (int i = 0; i < post.size(); i++) {
            postval = "value_" + post.get(i).get("TITLE");
            assertTrue(isElementVisible(postval), "element not visible");
            assertEquals(post.get(i).get("INFLUENCER"), getTextFromElement("inspiration_name_text", 0));
        }
    }

    public void setTotalLike() {
        InfluencerData.setInfluencerTotalLike(Integer.parseInt(getElementValue("influencer_total_like_text")));
    }

    public static int getTotalLike() {
        return InfluencerData.getInfluencerTotalLike();
    }

    public void checkEmptyPostMessage(String influencerName) {
        String val = "value_Belum ada posts dari " + influencerName;
        assertTrue(isElementVisible(val, 5), "element not visible");
    }

    public void checkTotalRelatedInfluencer() {
        List<String> relatedInfluencers = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            List<WebElement> elements = getElements("related_influencer_name_label", 6);

            for (int j = 0; j < elements.size(); j++) {
                relatedInfluencers.add(elements.get(j).getText());
            }

            swipeLeft(0.5, 0.1, 0.9);
        }

        relatedInfluencers = relatedInfluencers.stream().distinct().collect(Collectors.toList());
        assertTrue(relatedInfluencers.size() >= 3 && relatedInfluencers.size() <= 10);
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}