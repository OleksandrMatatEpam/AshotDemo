package Core;

import static Core.BrowserFactory.testName;
import static Core.Constants.FoldersPaths.DIFF_DIR;

public class Errors {

    public static String displayScreenshotDiscrepancyError(int expectedDifference, int actualDifference){

        return String.format("Screenshots discrepancy found. \n " +
                "Expected difference: %d pixels. \n " +
                "Actual difference: %d pixels. \n " +
                "See the difference: %s \n",
                expectedDifference, actualDifference, DIFF_DIR + testName + "/" + testName + ".png");
    }
}
