package ru.burlakov.framework.test;


import org.junit.Test;
import ru.burlakov.framework.base.BaseTests;

public class FirstTest extends BaseTests {

    @Test
    public void startTest() {
        app.getStartPage()
               .selectBaseMenu("Вклады")//Карты, Кредиты, Вклады, Инвестиции
               .selectCurrency("Рубли")//Доллары
               .chooseDepositData("Капитализация", true)
               .checkDepositPeriod("6 месяцев")
               .chooseDepositData("Сумма вклада", "300000")
               .chooseDepositData("Ежемесячное пополнение", "50000")
               .checkResults("Начислено - 9132.17","Пополнение - 250000", "К снятию - 559132.17");

    }

}
