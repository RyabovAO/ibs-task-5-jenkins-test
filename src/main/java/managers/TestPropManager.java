package managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestPropManager {

    private final String FILE_PATH = "src/main/resources/";
    private final String FILE_NAME = "config";
    private final String FILE_PERMISSION = ".properties";
    private final String PROP_FILE = "propFile";
    private static TestPropManager testPropManager = null;

    private final Properties properties = new Properties();

    private TestPropManager() {
        loadApplicationProperties();
    }

    public static TestPropManager getInstance() {
        if (testPropManager == null) {
            testPropManager = new TestPropManager();
        }
        return testPropManager;
    }

    private void loadApplicationProperties() {
        try {
            properties.load(new FileInputStream(FILE_PATH +
                    System.getProperty(PROP_FILE, FILE_NAME) + FILE_PERMISSION));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomProperties() {
        properties.forEach((key, value) -> System.getProperties()
                .forEach((customUserKey, customUserValue) -> {
                    if (key.toString().equals(customUserKey.toString()) &&
                            !value.toString().equals(customUserValue.toString())) {
                        properties.setProperty(key.toString(), customUserValue.toString());
                    }
                }));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
