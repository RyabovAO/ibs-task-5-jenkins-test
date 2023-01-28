package managers;

import constants.PropConst;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreateChromeDriver implements IDriverCreate{
    @Override
    public WebDriver create() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(false);
        options.addArguments(PropConst.BROWSER_OPTION);
        return new ChromeDriver();
    }
}
