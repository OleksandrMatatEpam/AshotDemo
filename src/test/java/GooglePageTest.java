import Core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import static Core.AshotHelper.*;

public class GooglePageTest extends BrowserFactory {

    @Test
    public void googlePageTest() throws IOException {
        //Test name is used to create folder and a screenshot
        String testName = getCurrentTestName();

        createScreenshotFolders(testName);

        driver.get("https://www.google.com.ua");

        int difference = getScreenshotsDiff(takeScreenshot(), testName, testName);

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, String.format("Screenshots discrepancy found. \n " +
                        "Expected difference: %d pixels. \n " +
                        "Actual difference: %d pixels. \n " +
                        "See the difference: %s \n",
                acceptableDifference, difference, DIFF_DIR + testName + "/" + testName + ".png"));
    }
}