import Core.AshotHelper;
import Core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static Core.AshotHelper.*;
import static Core.Errors.displayScreenshotDiscrepancyError;

public class TeraIgnoreElementTest extends BrowserFactory {

    @Test(description = "Terra Ignore Element Test")
    public void terraIgnoreElementTest() throws IOException {
        createScreenshotFolders();

        driver.get("https://engineering.cerner.com/terra-ui/about/terra-ui/what-is-terra");

        int difference = getScreenshotsDiff(AshotHelper.takeScreenshot("terra-dev-site-nav-menu"));

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, displayScreenshotDiscrepancyError(acceptableDifference, difference));
    }
}
