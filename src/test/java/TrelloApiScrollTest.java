import Core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import static Core.AshotHelper.*;
import static Core.CustomErrors.displayScreenshotDiscrepancyError;

public class TrelloApiScrollTest extends BrowserFactory {

    @Test(description = "Trello API Screenshot With Scroll Test")
    public void trelloApiScrollTest() throws IOException {
        createScreenshotFolders();

        driver.get("https://developer.atlassian.com/cloud/trello/rest/api-group-actions/");

        int difference = getScreenshotsDiff(takeScreenshot(1500));

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, displayScreenshotDiscrepancyError(acceptableDifference, difference));
    }
}