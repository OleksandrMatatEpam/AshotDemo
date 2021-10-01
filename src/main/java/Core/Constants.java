package Core;

import static Core.BrowserFactory.testName;

public class Constants {

    public static class FoldersPaths {
        public static final String RESOURCES_LAYOUT_DIR = "/Users/matat/IdeaProjects/AshotDemo/src/main/resources/screenshots/";
        public static final String ACTUAL_DIR = RESOURCES_LAYOUT_DIR + "actual/";
        public static final String EXPECTED_DIR = RESOURCES_LAYOUT_DIR + "expected/";
        public static final String DIFF_DIR = RESOURCES_LAYOUT_DIR + "diff/";

        public static final String EXPECTED_SCREENSHOT_PATH = EXPECTED_DIR + testName + "/" + testName + ".png";
        public static final String ACTUAL_SCREENSHOT_PATH = ACTUAL_DIR + testName + "/" + testName + ".png";
        public static final String DIFF_SCREENSHOT_PATH = DIFF_DIR + testName + "/" + testName + ".png";
    }
}
