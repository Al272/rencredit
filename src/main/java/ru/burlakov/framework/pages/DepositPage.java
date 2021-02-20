package ru.burlakov.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static ru.burlakov.framework.utils.Parser.stringFormat;

public class DepositPage extends BasePage{

@FindBy(xpath = "//h1")
private WebElement namePage;

@FindBy(className = "calculator__currency-field-text")
private List<WebElement> currencyButton;

@FindBy(xpath = "//input")
List<WebElement> depositData;

private String XPathParent = "./parent::*";

@FindBy(tagName = "select")
private WebElement periodIcon;

@FindBy(xpath = "//div[@class=\"calculator__result-block\"]//*[contains(.,'')]")
private List<WebElement> checkData;

private String checkDataEndXPath = ".//span";



    /**
     * Проверка открытия страницы
     *
     * @return DepositPage - т.е. остаемся на этой странице
     */
    public DepositPage checkOpenDepositPage(String nameMenu) {
                Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому", nameMenu, namePage.getText());
        return this;
    }

    /**
     * Метод выбирает валюту вклада
     //* @param currency - валюта вклада
     * @return DepositPage - т.е. остаемся на этой странице
     */
    public DepositPage selectCurrency(String currency) {
        for (WebElement currencyItem : currencyButton) {
            if (currencyItem.getText().contains(currency)) {
                action.moveToElement(currencyItem).click().build().perform();
                return this;
            }
        }
        Assert.fail("Валюта '" + currency + "' не была найдена на странице вкладов!");
        return null;
    }

    /**
     *
     * @param name - наименование поля ввода данных
     * @param value - значение для поля ввода данных
     * @return DepositPage - т.е. остаемся на этой странице
     */
    public DepositPage chooseDepositData(String name, String value) {
            switch (name){
                case "Сумма вклада" : depositData.stream().filter(x->x.getAttribute("name")
                                            .equalsIgnoreCase("amount"))
                                            .limit(1)
                                            .forEach(x->fillInputField(x,value));
                       break;
                case "Ежемесячное пополнение" : depositData.stream().filter(x->x.getAttribute("name")
                                                      .equalsIgnoreCase("replenish"))
                                                      .limit(1)
                                                      .forEach(x->fillInputField(x,value));
                        break;
                default:  Assert.fail("Значение поля '" + name + "' не было найдено на странице вкладов!");
            }
       return this;

    }

    /**
     *
     * @param name - наименование чек-бокса
     * @param condition - логическое значение для активации чек-бокса
     * @return DepositPage - т.е. остаемся на этой странице
     */
    public DepositPage chooseDepositData(String name, boolean condition) {
        switch (name){
            case "Капитализация" :
               depositData.stream().filter(x->x.getAttribute("name")
                    .equalsIgnoreCase("capitalization"))
                    .limit(1)
                    .forEach(x-> {
                        WebElement checkBox = x.findElement(By.xpath(XPathParent));
                        if(!condition==checkBox.getAttribute("class").contains("checked"))
                            action.moveToElement(checkBox).click().build().perform();
                                 });

                break;
            case "В отделении банка" :
                depositData.stream().filter(x->x.getAttribute("name")
                        .equalsIgnoreCase("deposit_b_n"))
                        .limit(1)
                        .forEach(x-> {
                            WebElement checkBox = x.findElement(By.xpath(XPathParent));
                            if(!condition==checkBox.getAttribute("class").contains("checked"))
                                action.moveToElement(checkBox).click().build().perform();
                        });

                break;
            case "В интернет-банке" :
                depositData.stream().filter(x->x.getAttribute("name")
                        .equalsIgnoreCase("deposit_b_y"))
                        .limit(1)
                        .forEach(x-> {
                            WebElement checkBox = x.findElement(By.xpath(XPathParent));
                            if(!condition==checkBox.getAttribute("class").contains("checked"))
                                action.moveToElement(checkBox).click().build().perform();
                        });

                break;
            case "Частичное снятие" :
                depositData.stream().filter(x->x.getAttribute("name")
                        .equalsIgnoreCase("partial_out"))
                        .limit(1)
                        .forEach(x-> {
                            WebElement checkBox = x.findElement(By.xpath(XPathParent));
                            if(!condition==checkBox.getAttribute("class").contains("checked"))
                                action.moveToElement(checkBox).click().build().perform();
                        });

                break;
            default:  Assert.fail("Значение чек-бокса '" + name + "' не было найдено на странице вкладов!");
        }
        return this;

    }

    /**
     * метод для выбора срока вклада
     * @param value - значение срока вклада
     * @return DepositPage - т.е. остаемся на этой странице
     */
    public DepositPage checkDepositPeriod(String value){
        new Select(periodIcon).selectByVisibleText(value);
        return this;
    }

    public DepositPage checkResults(String ... data){
        for(String arrData : data) {
            String[] parse = arrData.split("-");
            if (parse.length != 2)
                Assert.fail("Данные в метод checkResults переданы не корректно!");
            String value = parse[0].trim();
            String digit = stringFormat(parse[1].trim());

            if(checkData.stream().filter(x -> x.getText().contains(value)).count()!=0){
                checkData.stream().filter(x->x.getText().contains(value))
                        .limit(1)
                        .forEach(x-> {
                            if(x.findElements(By.xpath(checkDataEndXPath)).stream().filter(y->y.getText().contains(digit)).count()==0)
                                Assert.fail("'"+value+"' значение '"+digit+"' - рассчитано не верно!");
                            else
                                x.findElements(By.xpath(checkDataEndXPath)).stream()
                                        .filter(y->y.getText().contains(digit))
                                        .limit(1)
                                .forEach(y-> Assert.assertEquals("Значение '"+digit+"' рассчитано не верно!",y.getText(),digit));
                        });
            }else
                Assert.fail("Значение '"+value+"' не найдено!");

        }
        return this;
    }
}
