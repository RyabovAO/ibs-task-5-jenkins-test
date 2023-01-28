package managers;

import constants.PropConst;
import java.time.Duration;

public class InitFramework {
    private static final TestPropManager props = TestPropManager.getInstance();
    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(props.getProperty(PropConst.IMPLICITLY_WAIT))));
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                Long.parseLong(props.getProperty(PropConst.PAGE_LOAD_TIMEOUT))));
        driverManager.getDriver().manage().window().maximize();
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
