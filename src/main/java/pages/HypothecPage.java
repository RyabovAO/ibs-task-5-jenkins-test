package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HypothecPage extends BasePage {

    @FindBy(xpath = "//*[contains(@sandbox,'allow-forms allow-scripts allow-same')]")
    private WebElement formRegistration;

    @FindBy(xpath = "//label[contains(@class,'inpt-root__label-6-3-4-beta-1-for-calculator')]")
    private List<WebElement> listFieldName;

    @FindBy(xpath = "//input[contains(@class,'inpt-root__input-6-3-4-beta-1-for-calculator')]")
    private List<WebElement> listInputField;

    @FindBy(xpath = "//span[contains(text(),'Страхование')]/..//input[contains(@class,'switch-input')]")
    private WebElement insuranceCheckBox;

    @FindBy(xpath = "//span[contains(text(),'Страхование')]/..//label[contains(@class,'switch-root-4-0-1')]")
    private WebElement checkInsuranceCheckBox;

    @FindBy(xpath = "//div[@id='calculator-root']//span")
    private List<WebElement> listResultMenuTitle;

    @Step("Проверка что открыта страница 'Ипотека'")
    public HypothecPage checkHypothecPage() {
        waitUtilElementToBeVisible(formRegistration);
        Assertions.assertTrue(isPageOpen(formRegistration), "Страница 'Ипотека' не открыта");
        return this;
    }

    @Step("Переход в блок ввода параметров ипотеки")
    public HypothecPage switchFrame() {
        scrollToElementJs(formRegistration);
        toFrame(formRegistration);
        return this;
    }

    @Step("Заполнить поле {fieldName} значением {value}")
    public HypothecPage fillFields(String fieldName, String value) {
        for (WebElement fieldItem : listFieldName) {
            if (fieldItem.getText().contains(fieldName)) {
                listInputField.get(listFieldName.indexOf(fieldItem)).click();
                listInputField.get(listFieldName.indexOf(fieldItem)).sendKeys(Keys.CONTROL + "a");
                listInputField.get(listFieldName.indexOf(fieldItem)).sendKeys(Keys.BACK_SPACE);
                sendKeysByOneCharacter(listInputField.get(listFieldName.indexOf(fieldItem)), value);
                waitPageLoad(500, 250);
                return this;
            }
        }
        Assertions.fail("Поле '" + fieldName + "' не было найдено");
        return this;
    }

    private void sendKeysByOneCharacter(WebElement element, String value) {
        String[] stringArr = value.split("");
        for (String charItem : stringArr) {
            action.moveToElement(element).pause(50).sendKeys(charItem).pause(50).perform();
        }
    }

    @Step("Выключить чекбокс страхование жизни")
    public HypothecPage clickInsuranceCheckBox() {
        clickToElementJs(insuranceCheckBox);
        return this;
    }

    @Step("Прверка что чекбокс выключен")
    public HypothecPage checkInsuranceCheckBox() {
        waitUtilElementToBeVisible(checkInsuranceCheckBox);
        Assertions.assertTrue(checkInsuranceCheckBox.isDisplayed(),
                "Чекбокс 'Страхование жизни и здоровья' выключен");
        return this;
    }

    @Step("Проверить что значение поля {resTitle} соответствует значению {value}")
    public HypothecPage checkCreditInfo(String resTitle, String value) {
        Assertions.assertEquals(value, getCreditResult(resTitle), "Параметры кредита не совпадают");
        return this;
    }

    private String getCreditResult(String resTitle) {
        String creditValue = "";
        waitPageLoad(500, 250);
        for (WebElement element : listResultMenuTitle) {
            if (element.getText().contains(resTitle)) {
                waitPageLoad(500, 250);
                creditValue = getElementTextAsNumber(element.findElement(By.xpath("//span")));
                return creditValue;
            }
            Assertions.fail("Результат кредита с названием '" + resTitle + "' не найдено");
        }
        return creditValue;
    }

}
