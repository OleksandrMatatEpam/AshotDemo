import Core.AshotHelper;
import Core.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static Core.AshotHelper.createScreenshotFolders;
import static Core.AshotHelper.getScreenshotsDiff;
import static Core.Errors.displayScreenshotDiscrepancyError;

public class TeraIgnoreElementTest extends BrowserFactory {

    @Test(description = "Terra Ignore Element Test")
    public void terraIgnoreElementTest() throws IOException {
        createScreenshotFolders();

        driver.get("https://www.ikea.com/ua/uk/profile/login/");

        WebElement email = driver.findElement(By.cssSelector("#login-form-username"));
        email.click();
        email.sendKeys("Email" + + System.currentTimeMillis() + "@gmgmg.com");

        WebElement pass = driver.findElement(By.cssSelector("#login-form-password"));
        pass.click();
        pass.sendKeys("pass");

        int difference = getScreenshotsDiff(AshotHelper.takeScreenshot("input[type='email']"));

        // Acceptable difference for this particular test
        int acceptableDifference = 20;
        Assert.assertTrue(difference <= acceptableDifference, displayScreenshotDiscrepancyError(acceptableDifference, difference));
    }
}
