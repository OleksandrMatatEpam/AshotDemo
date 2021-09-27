import Core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static Core.AshotHelper.*;
import static Core.Errors.displayScreenshotDiscrepancyError;

public class TerraPageTest extends BrowserFactory {

    @Test(description = "Terra About Page Test")
    public void terraAboutPageTest() throws IOException {
        createScreenshotFolders();

        driver.get("https://engineering.cerner.com/terra-ui/about/terra-ui/what-is-terra");

        int difference = getScreenshotsDiff(takeScreenshot());

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, displayScreenshotDiscrepancyError(acceptableDifference, difference));
    }
}