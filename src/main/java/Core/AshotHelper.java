package Core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static Core.BrowserFactory.getWebDriver;
import static Core.BrowserFactory.testName;
import static Core.Constants.FoldersPaths.*;

public class AshotHelper {

    private static Logger logger = Logger.getLogger(String.valueOf(AshotHelper.class));

    private static File expectedFile;
    private static File actualFile;
    private static File diffFile;

    public static int getScreenshotsDiff(Screenshot actualScreenshot) throws IOException {
        createScreenshotFiles();
        ImageIO.write(actualScreenshot.getImage(), "png", actualFile);
        if (!expectedFile.exists()) {
            logger.info("Expected screenshot does not exist, creating a new screenshot: " + testName);
            ImageIO.write(actualScreenshot.getImage(), "png", expectedFile);
        }
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(EXPECTED_SCREENSHOT_PATH)));
        expectedScreenshot.setIgnoredAreas(actualScreenshot.getIgnoredAreas());
        expectedScreenshot.setCoordsToCompare(actualScreenshot.getCoordsToCompare());
        int colorDistortion = 5;
        ImageDiff diff = new ImageDiffer().withColorDistortion(colorDistortion).makeDiff(expectedScreenshot, actualScreenshot);
        BufferedImage diffImage = diff.getMarkedImage();
        ImageIO.write(diffImage, "png", diffFile);

        return diff.getDiffSize();
    }

    private static void createScreenshotFiles(){
        expectedFile = new File(EXPECTED_SCREENSHOT_PATH);
        actualFile = new File(ACTUAL_SCREENSHOT_PATH);
        diffFile =  new File(DIFF_SCREENSHOT_PATH);
    }

    public static void createScreenshotFolders(){
        createFolder(EXPECTED_DIR + testName + "/");
        createFolder(ACTUAL_DIR + testName + "/");
        createFolder(DIFF_DIR + testName + "/");
    }

    public static void createFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static Screenshot takeScreenshot(){
        return new AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(getWebDriver());
    }

    public static Screenshot takeScreenshot(WebElement specificElement){
        return new AShot()
                .shootingStrategy(ShootingStrategies.simple())
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(getWebDriver(), specificElement);
    }


    public static Screenshot takeScreenshot(String elementToIgnore){
        return new AShot()
                .shootingStrategy(ShootingStrategies.simple())
                .coordsProvider(new WebDriverCoordsProvider())
                .addIgnoredElement(By.cssSelector(elementToIgnore))
                .takeScreenshot(getWebDriver());
    }

    public static Screenshot takeScreenshot(int scrollTimeout){
        return new AShot().shootingStrategy(ShootingStrategies.viewportPasting(scrollTimeout)).takeScreenshot(getWebDriver());
    }
}
