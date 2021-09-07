package Core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BrowserFactory {

    protected static WebDriver driver;

    @BeforeTest
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/matat/Documents/Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public String getCurrentTestName(){
        return this.getClass().getSimpleName();
    }

    @AfterTest
    public void afterTestExample () {
        driver.quit();
    }
}
