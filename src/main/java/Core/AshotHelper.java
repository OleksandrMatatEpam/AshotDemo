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

public class AshotHelper {

    private static Logger logger = Logger.getLogger(String.valueOf(AshotHelper.class));

    public static final String RESOURCES_LAYOUT_DIR = "/Users/matat/IdeaProjects/AshotDemo/src/main/resources/screenshots/";
    public static final String ACTUAL_DIR = RESOURCES_LAYOUT_DIR + "actual/";
    public static final String EXPECTED_DIR = RESOURCES_LAYOUT_DIR + "expected/";
    public static final String DIFF_DIR = RESOURCES_LAYOUT_DIR + "diff/";

    public static int getScreenshotsDiff(Screenshot actualScreenshot, String folderName, String actualScreenshotName) throws IOException {
        String expectedScreenshotPath = EXPECTED_DIR + folderName + "/" + actualScreenshotName + ".png";
        String actualScreenshotPath = ACTUAL_DIR + folderName + "/" + actualScreenshotName + ".png";
        String diffScreenshotPath = DIFF_DIR + folderName + "/" + actualScreenshotName + ".png";

        File expectedFile = new File(expectedScreenshotPath);
        File actualFile = new File(actualScreenshotPath);
        ImageIO.write(actualScreenshot.getImage(), "png", actualFile);
        if (!expectedFile.exists()) {
            logger.info("Expected screenshot does not exist, creating a new screenshot: " + actualScreenshotName);
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

    public static void createScreenshotFolders(String folderName){
        createFolder(EXPECTED_DIR + folderName + "/");
        createFolder(ACTUAL_DIR + folderName + "/");
        createFolder(DIFF_DIR + folderName + "/");
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
}
