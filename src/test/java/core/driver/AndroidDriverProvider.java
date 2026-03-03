package core.driver;

import com.codeborne.selenide.WebDriverProvider;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import core.config.Config;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AndroidDriverProvider implements WebDriverProvider {
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();

        String appiumUrl = Config.getProperty("appium.url");
        String udid = Config.getProperty("udid");

        if (appiumUrl == null || appiumUrl.isBlank()) {
            throw new IllegalStateException("appium.url is empty");
        }
        if (udid == null || udid.isBlank()) {
            throw new IllegalStateException("udid is empty");
        }

        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Android Emulator");
        options.setUdid(udid);

        options.setAutoGrantPermissions(true);
        options.setNoReset(true);

        options.setCapability("appium:autoLaunch", false);
        options.setCapability("appium:enforceXPath1", true);

        try {
            return new AndroidDriver(new URL(appiumUrl), options);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create AndroidDriver. Appium URL: " + appiumUrl, e);
        }
    }
}