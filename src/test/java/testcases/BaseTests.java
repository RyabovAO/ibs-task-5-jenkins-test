package testcases;

import constants.PropConst;
import io.qameta.allure.Step;
import managers.DriverManager;
import managers.InitFramework;
import managers.PageManager;
import managers.TestPropManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.MyAllureListener;

@ExtendWith(MyAllureListener.class)
public class BaseTests {

    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager props = TestPropManager.getInstance();


    @BeforeAll
    public static void beforeAll() {
        InitFramework.initFramework();
    }

    @Step("Запуск браузера и открытие страницы стартовой страницы Сюербанка")
    @BeforeEach
    public void beforeEach() {
        driverManager.getDriver().get(props.getProperty(PropConst.BASE_URL));
    }

    @AfterEach
    public void afterEach() {
        pageManager.clearMap();
    }

    @AfterAll
    public static void afterAll() {
      InitFramework.quitFramework();
    }
}
