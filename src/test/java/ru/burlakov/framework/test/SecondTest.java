package ru.burlakov.framework.test;

import org.junit.Test;
import ru.burlakov.framework.base.BaseTests;

public class SecondTest extends BaseTests {
    @Test
    public void startTest2() {
        app.getStartPage()
                .selectBaseMenu("Вклады")//Карты, Кредиты, Вклады, Инвестиции
                .selectCurrency("Доллары")//Рубли
                .chooseDepositData("Капитализация", true)
                .checkDepositPeriod("12 месяцев")
                .chooseDepositData("Сумма вклада", "500000")
                .chooseDepositData("Ежемесячное пополнение", "70000")
                .checkResults("Начислено - 1003,59","Пополнение - 330000", "К снятию - 831003,59");

    }
}
