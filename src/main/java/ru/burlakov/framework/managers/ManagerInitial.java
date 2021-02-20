package ru.burlakov.framework.managers;

import java.util.concurrent.TimeUnit;

import static ru.burlakov.framework.managers.ManagerDriver.getDriver;
import static ru.burlakov.framework.managers.ManagerDriver.quitDriver;
import static ru.burlakov.framework.utils.Constant.IMPLICITLY_WAIT;
import static ru.burlakov.framework.utils.Constant.PAGE_LOAD_TIMEOUT;

/**
 * @author Alexey_Burlakov
 * Класс для инициализации фреймворка
 */
public class ManagerInitial {

    /**
     * Менеджер properties
     *
     * @see ManagerTest#getManagerTest()
     */
    private static final ManagerTest props = ManagerTest.getManagerTest();

    /**
     * Инициализация framework и запуск браузера со страницей приложения
     *
     * @see ManagerDriver#getDriver()
     * @see ManagerTest#getProperty(String)
     * @see ru.burlakov.framework.utils.Constant
     */
    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    /**
     * Завершения работы framework - гасит драйвер и закрывает сессию с браузером
     *
     * @see ManagerDriver#quitDriver()
     */
    public static void quitFramework() {
        quitDriver();
    }
}

