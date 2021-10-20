import Core.AshotHelper;
import Core.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import static Core.AshotHelper.createScreenshotFolders;
import static Core.AshotHelper.getScreenshotsDiff;
import static Core.CustomErrors.displayScreenshotDiscrepancyError;

public class TeraIgnoreElementTest extends BrowserFactory {

    @Test(description = "Terra Ignore Element Test")
    public void terraIgnoreElementTest() throws IOException {
        createScreenshotFolders();

        driver.get("https://engineering.cerner.com/terra-ui/home/terra-ui/index");

        WebElement searchBtn = driver.findElement(By.cssSelector(".Icon-module__tui-Icon___1myD6.Icon-module__icon___3r8Lk"));
        searchBtn.click();

        WebElement searchField = driver.findElement(By.cssSelector(".SearchField-module__input___32v5Z"));
        searchField.sendKeys("Email" + System.currentTimeMillis() + "@gmail.com");

        int difference = getScreenshotsDiff(AshotHelper.takeScreenshot(".SearchField-module__input___32v5Z"));

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, displayScreenshotDiscrepancyError(acceptableDifference, difference));
    }
}