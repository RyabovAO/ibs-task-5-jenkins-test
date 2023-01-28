package pages;

import constants.PropConst;
import managers.DriverManager;
import managers.PageManager;
import managers.TestPropManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected Actions action = new Actions(driverManager.getDriver());
    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();
    private final TestPropManager props = TestPropManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(),
            Duration.ofSeconds(Long.parseLong(props.getProperty(PropConst.EXPECTED_COND_TIMEOUT))),
            Duration.ofSeconds(Long.parseLong(props.getProperty(PropConst.EXPECTED_COND_SLEEP))));

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void inputField(WebElement element, String value) {
        scrollToElementJs(element);
        waitUtilElementToBeVisible(element);
        element.clear();
        element.sendKeys(value);
    }

    protected void toFrame(WebElement element) {
        driverManager.getDriver().switchTo().frame(element);
    }

    protected boolean isPageOpen(WebElement webElement) {
        return webElement.isDisplayed();
    }

    protected String getElementTextAsNumber(WebElement webElement) {
        return webElement.getText().replaceAll("\\D", "");
    }

    protected void moveToElement(WebElement webElement) {
        action.moveToElement(webElement).perform();
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clickToElementJs(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    protected void waitPageLoad(int maxWaitMillis, int pollDelimiter) {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            String prevState = driverManager.getDriver().getPageSource();
            try {
                Thread.sleep(pollDelimiter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (prevState.equals(driverManager.getDriver().getPageSource())) {
                return;
            }
        }
    }

}
