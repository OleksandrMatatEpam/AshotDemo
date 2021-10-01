package Core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BrowserFactory {

    protected static WebDriver driver;
    protected static String testName;

    @BeforeTest
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/matat/Documents/Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    // Test name is used for folder and screenshot paths
    @BeforeTest
    public void setTestName(){
        testName = this.getClass().getSimpleName();
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    @AfterTest
    public void afterTestExample () {
        driver.quit();
    }
}
