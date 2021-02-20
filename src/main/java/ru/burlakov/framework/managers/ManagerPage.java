package ru.burlakov.framework.managers;

import ru.burlakov.framework.pages.DepositPage;
import ru.burlakov.framework.pages.StartPage;

/**
 * @author Alexey_Burlakov
 * Класс для управления страничками
 */
public class ManagerPage {

    /**
     * Менеджер страничек
     */
    private static ManagerPage managerPage;

    /**
     * Стартовая страница
     */
    private StartPage startPage;

    /**
     * Страница подбора вклада
     */
    private DepositPage depositPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see ManagerPage#getManagerPage()
     */
    private ManagerPage() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static ManagerPage getManagerPage() {
        if (managerPage == null) {
            managerPage = new ManagerPage();
        }
        return managerPage;
    }
    /**
     * Ленивая инициализация {@link StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link DepositPage}
     *
     * @return DepositPage
     */
    public DepositPage getDepositPage() {
        if (depositPage == null) {
            depositPage = new DepositPage();
        }
        return depositPage;
    }

}

