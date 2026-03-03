package core.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Map;

import core.config.Config;
import core.driver.AndroidDriverProvider;
import io.appium.java_client.android.AndroidDriver;

public class BaseMobileTest {

    @BeforeAll
    static void setup() {
        Configuration.browser = AndroidDriverProvider.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 6000;
    }

    protected String appPackage() {
        return Config.getProperty("app.package");
    }

    @BeforeEach
    void startDriver() {
        Selenide.open();
        enableAllNetworks(driver());
        restartApp();
    }

    @AfterEach
    void stopDriver() {
        closeApp();
        Selenide.closeWebDriver();
    }

    protected AndroidDriver driver() {
        return (AndroidDriver) WebDriverRunner.getWebDriver();
    }

    protected void restartApp() {
        String pkg = requirePackage();
        driver().terminateApp(pkg);
        driver().activateApp(pkg);
    }

    protected void closeApp() {
        String pkg = requirePackage();
        driver().terminateApp(pkg);
    }

    private String requirePackage() {
        return requirePackage(appPackage());
    }

    private String requirePackage(String pkg) {
        if (pkg == null || pkg.isBlank()) {
            throw new IllegalStateException("appPackage() вернул пустое значение");
        }
        return pkg;
    }

    private static void mobileShell(AndroidDriver driver, String command, List<String> args) {
        driver.executeScript("mobile: shell", Map.of(
                "command", command,
                "args", args
        ));
    }

    private static void disableWifi(AndroidDriver driver) {
        try {
            mobileShell(driver, "svc", List.of("wifi", "disable"));
        } catch (Exception e) {
            System.out.println("❌ Ошибка отключения Wi-Fi: " + e.getMessage());
        }
    }

    private static void enableWifi(AndroidDriver driver) {
        try {
            mobileShell(driver, "svc", List.of("wifi", "enable"));
        } catch (Exception e) {
            System.out.println("❌ Ошибка включения Wi-Fi: " + e.getMessage());
        }
    }

    private static void disableMobileData(AndroidDriver driver) {
        try {
            mobileShell(driver, "svc", List.of("data", "disable"));
        } catch (Exception e) {
            System.out.println("❌ Ошибка отключения мобильных данных: " + e.getMessage());
        }
    }

    private static void enableMobileData(AndroidDriver driver) {
        try {
            mobileShell(driver, "svc", List.of("data", "enable"));
        } catch (Exception e) {
            System.out.println("❌ Ошибка включения мобильных данных: " + e.getMessage());
        }
    }

    private static void disableAllNetworks(AndroidDriver driver) {
        disableWifi(driver);
        disableMobileData(driver);
    }

    private static void enableAllNetworks(AndroidDriver driver) {
        enableMobileData(driver);
        enableWifi(driver);
    }

    protected void withAllNetworksDisabled(Runnable action) {
        AndroidDriver driver = driver();

        disableAllNetworks(driver);

        try {
            action.run();
        } finally {
            enableAllNetworks(driver);
        }
    }
}