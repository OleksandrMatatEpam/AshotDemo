import Core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import static Core.AshotHelper.*;
import static Core.Errors.displayScreenshotDiscrepancyError;

public class BingTest extends BrowserFactory {

    @Test(description = "Bing Main Page Test")
    public void bingTest() throws IOException {
        createScreenshotFolders();

        driver.get("https://www.bing.com/");

        int difference = getScreenshotsDiff(takeScreenshot());

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, displayScreenshotDiscrepancyError(acceptableDifference, difference));
    }
}
