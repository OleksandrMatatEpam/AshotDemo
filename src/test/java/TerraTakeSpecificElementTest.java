import Core.AshotHelper;
import Core.BrowserFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static Core.AshotHelper.*;
import static Core.CustomErrors.displayScreenshotDiscrepancyError;

public class TerraTakeSpecificElementTest extends BrowserFactory {

    @Test(description = "Terra Specific Element Test")
    public void terraTakeSpecificElementTest() throws IOException {
        createScreenshotFolders();

        driver.get("https://engineering.cerner.com/terra-ui/about/terra-ui/what-is-terra");

        int difference = getScreenshotsDiff(AshotHelper.takeScreenshot(driver.findElement(By.cssSelector("#terra-dev-site-nav-menu"))));

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, displayScreenshotDiscrepancyError(acceptableDifference, difference));
    }
}
