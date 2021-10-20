import Core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import static Core.AshotHelper.*;
import static Core.CustomErrors.displayScreenshotDiscrepancyError;

public class GooglePageTest extends BrowserFactory {

    @Test(description = "Google Main Page Test")
    public void googlePageTest() throws IOException {
        createScreenshotFolders();

        driver.get("https://www.google.com.ua");

        int difference = getScreenshotsDiff(takeScreenshot());

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, displayScreenshotDiscrepancyError(acceptableDifference, difference));
    }
}