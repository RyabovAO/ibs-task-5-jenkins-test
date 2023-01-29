package managers;

import constants.PropConst;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class BrowserFactory {

    private final String CHROME = "chrome";
    private final String FIREFOX = "firefox";
    private final String REMOTE = "remote";
    private static BrowserFactory browserFactory = null;

    private BrowserFactory() {
    }

    public static BrowserFactory getInstance() {
        if (browserFactory == null) {
            browserFactory = new BrowserFactory();
        }
        return browserFactory;
    }

    protected WebDriver getDriver() {
        WebDriver driver = null;

        switch (TestPropManager.getInstance().getProperty(PropConst.TYPE_BROWSER)) {
            case CHROME:
                driver = new CreateChromeDriver().create();
                break;
            case FIREFOX:
                driver = new CreateFireFoxDriver().create();
                break;
            case REMOTE:
                String browser = System.getProperty("browser", "chrome");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                //capabilities.setCapability("browserName", browser);
                capabilities.setCapability("browserVersion", "109.0");
//                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                        "enableVNC", true,
//                        "enableVideo", true));
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                capabilities.setBrowserName(browser);
                switch (browser) {
                    case "chrome":
                    case "firefox":
                        capabilities.setVersion("109.0");
                        break;
                }
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://149.154.71.152:8080/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Assertions.fail("Неверное имя браузера");
        }
        return driver;
    }

}

