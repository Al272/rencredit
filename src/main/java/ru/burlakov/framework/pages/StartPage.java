package ru.burlakov.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


/**
 * @author Alexey_Burlakov
 * Стартовая страница приложения https://rencredit.ru/
 */
public class StartPage extends BasePage {

    @FindBy(className = "service__title-text")
    private List <WebElement> mainMenu;

    /**
     * Функция наведения мыши на любую вкладку основного меню
     *
     * @param nameBaseMenu - наименование меню
     * @return StartPage - т.е. остаемся на этой странице
     */
    //@Step("Переход в главное меню {nameBaseMenu}")
    public DepositPage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : mainMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameBaseMenu)) {
                action.moveToElement(menuItem).click().build().perform();
                return app.getDepositPage().checkOpenDepositPage(nameBaseMenu);
            }
        }
        Assert.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return null;
    }


}
