package vkvideo.pages.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import java.time.Duration;

public class VkVideoAuthPopup {
    private final SelenideElement skipButton =
            $(By.xpath("//*[@resource-id='com.vk.vkvideo:id/fast_login_tertiary_btn']"));

    public void tapSkipButton(Duration timeout) {
        skipButton.shouldBe(visible, timeout).click();
    }
}