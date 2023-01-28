package managers;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static DriverManager driverManager = null;

    private WebDriver driver;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = BrowserFactory.getInstance().getDriver();
        }
        return driver;
    }

    public void quitDriver() {
        driver.manage().deleteAllCookies();
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
