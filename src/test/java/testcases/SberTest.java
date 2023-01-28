package testcases;

import org.junit.jupiter.api.Test;
import pages.StartPage;

public class SberTest extends BaseTests{

    @Test
    public void test() {
        pageManager.getPage(StartPage.class)
                .checkStartPAge()
                .chooseMenu("Ипотека")
                .checkHypothecBlock()
                .chooseSubMenu("Ипотека на вторичное жильё")
                .checkHypothecPage()
                .switchFrame()
                .fillFields("Стоимость недвижимости", "5180000")
                .fillFields("Первоначальный взнос", "3058000")
                .fillFields("Срок кредита", "30")
                .clickInsuranceCheckBox()
                .checkInsuranceCheckBox()
                .checkCreditInfo("Ежемесячный платеж", "21 664")
                .checkCreditInfo("Сумма кредита", " 2 122 000")
                .checkCreditInfo("Необходимый доход", " 36 829 ₽")
                .checkCreditInfo("Процентная ставка", "11% ");
    }

}
