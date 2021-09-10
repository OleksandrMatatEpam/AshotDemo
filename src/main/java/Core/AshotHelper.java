package Core;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static Core.BrowserFactory.driver;
import static Core.BrowserFactory.testName;
import static Core.Constants.FoldersPaths.*;

public class AshotHelper {

    private static Logger logger = Logger.getLogger(String.valueOf(AshotHelper.class));

    public static int getScreenshotsDiff(Screenshot actualScreenshot) throws IOException {
        String expectedScreenshotPath = EXPECTED_DIR + testName + "/" + testName + ".png";
        String actualScreenshotPath = ACTUAL_DIR + testName + "/" + testName + ".png";
        String diffScreenshotPath = DIFF_DIR + testName + "/" + testName + ".png";

        File expectedFile = new File(expectedScreenshotPath);
        File actualFile = new File(actualScreenshotPath);
        ImageIO.write(actualScreenshot.getImage(), "png", actualFile);
        if (!expectedFile.exists()) {
            logger.info("Expected screenshot does not exist, creating a new screenshot: " + testName);
            ImageIO.write(actualScreenshot.getImage(), "png", expectedFile);
        }
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(expectedScreenshotPath)));
        expectedScreenshot.setIgnoredAreas(actualScreenshot.getIgnoredAreas());
        int colorDistortion = 5;
        ImageDiff diff = new ImageDiffer().withColorDistortion(colorDistortion).makeDiff(expectedScreenshot, actualScreenshot);
        File diffFile = new File(diffScreenshotPath);
        ImageIO.write(diff.getMarkedImage(), "png", diffFile);
        return diff.getDiffSize();
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
        return new AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(driver);
    }

    public static Screenshot takeScrollScreenshot(){
        return new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1500)).takeScreenshot(driver);
    }
}
