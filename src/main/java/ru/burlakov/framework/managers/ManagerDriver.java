package ru.burlakov.framework.managers;

import org.apache.commons.exec.OS;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static ru.burlakov.framework.utils.Constant.*;

/**
 * @author Alexey_Burlakov
 * Класс для управления веб драйвером
 */
public class ManagerDriver {

    /**
     * Переменная для хранения объекта веб-драйвера
     *
     * @see WebDriver
     */
    private static WebDriver driver;


    /**
     * Переменна для хранения объекта ManagerDriver
     */
    private static ManagerDriver INSTANCE = null;


    /**
     * Менеджер properties
     *
     * @see ManagerTest#getManagerTest()
     */
    private final ManagerTest props = ManagerTest.getManagerTest();


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see ManagerDriver#getDriver()
     * @see ManagerDriver#initDriver()
     */
    private ManagerDriver() {
        initDriver();
    }


    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */
    public static WebDriver getDriver() {
        if (INSTANCE == null) {
            INSTANCE = new ManagerDriver();
        }
        return driver;
    }


    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @see WebDriver#quit()
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            //driver = null;
        }
    }


    /**
     * Метод инициализирующий веб драйвер
     */
    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        } else if (OS.isFamilyMac()) {
            initDriverMacOsFamily();
        } else if (OS.isFamilyUnix()) {
            initDriverUnixOsFamily();
        }
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Windows
     */
    private void initDriverWindowsOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_WINDOWS, PATH_CHROME_DRIVER_WINDOWS);
    }


    /**
     * Метод инициализирующий веб драйвер под ОС семейства Mac
     */
    private void initDriverMacOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_MAC, PATH_CHROME_DRIVER_MAC);
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Unix
     */
    private void initDriverUnixOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_UNIX, PATH_CHROME_DRIVER_UNIX);
    }


    /**
     * Метод инициализирующий веб драйвер под любую ОС
     *
     * @param gecko - переменная firefox из файла application.properties в классе {@link ru.burlakov.framework.utils.Constant}
     * @param chrome - переменная chrome из файла application.properties в классе {@link ru.burlakov.framework.utils.Constant}
     */
    private void initDriverAnyOsFamily(String gecko, String chrome) {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(gecko));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
                driver = new ChromeDriver();
                break;
            default:
                Assert.fail("Типа браузера '" + props.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }


}
