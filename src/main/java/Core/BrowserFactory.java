package Core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BrowserFactory {

    protected static WebDriver driver;
    protected static String testName;

    @BeforeTest
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/matat/Documents/Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Test name is used for folder and screenshot paths
    @BeforeTest
    public void setTestName(){
        testName = this.getClass().getSimpleName();
    }

    @AfterTest
    public void afterTestExample () {
        driver.quit();
    }
}
