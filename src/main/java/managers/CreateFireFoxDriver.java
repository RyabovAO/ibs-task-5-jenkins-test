package managers;

import constants.PropConst;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class CreateFireFoxDriver implements IDriverCreate {
    @Override
    public WebDriver create() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        //options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setAcceptInsecureCerts(true);
        options.addArguments(PropConst.BROWSER_OPTION);
        return new FirefoxDriver();
    }
}
