package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StartPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(StartPage.class);

    @FindBy(xpath = "//*[@class='kitt-header__logo']")
    private WebElement logo;

    @FindBy(xpath = "//li[contains(@class,'kitt-top-menu__item')]//a[@role='button']")
    private List<WebElement> topMenu;

    @Step("Проверка что открыта стартовая страница Сбербанка")
    public StartPage checkStartPAge() {
        logger.info("Проверка открытия стартовой страницы");
        Assertions.assertTrue(isPageOpen(logo), "Стартовая страница не открыта");
        return this;
    }

    @Step("Выбрать из списка основного меню {menuName}")
    public SubMenuHypothecBlock chooseMenu(String menuName) {
        logger.info("Выбор меню {}", menuName);
        for (WebElement menuItem : topMenu) {
            if (menuItem.getText().contains(menuName)) {
                menuItem.click();
                return pageManager.getPage(SubMenuHypothecBlock.class);
            }
        }
        Assertions.fail("Меню '" + menuName + "' не было найдено");
        return pageManager.getPage(SubMenuHypothecBlock.class);
    }

}
