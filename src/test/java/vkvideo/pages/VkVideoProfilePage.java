package vkvideo.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

import static vkvideo.common.constants.VkVideoTestConstants.TIMEOUT;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import java.time.Duration;

public class VkVideoProfilePage {
    private static final String OFFLINE_MODE_TITLE_TEXT = "Offline mode";
    private static final String NO_INTERNET_TEXT = "No internet";
    private final SelenideElement offlineModeTitle =
            $(By.xpath("//*[@resource-id='com.vk.vkvideo:id/placeholder_view_title'" +
                    " and @text='" + OFFLINE_MODE_TITLE_TEXT + "']"));
    private final SelenideElement offlineModeDescription =
            $(By.xpath("//*[@resource-id='com.vk.vkvideo:id/placeholder_view_description'" +
                    " and contains(@text,'" + NO_INTERNET_TEXT + "')]"));

    public void verifyOfflineModeDisplayed(Duration timeout) {
        offlineModeTitle
                .shouldBe(visible, timeout)
                .shouldHave(text(OFFLINE_MODE_TITLE_TEXT), timeout);

        offlineModeDescription
                .shouldBe(visible, timeout)
                .shouldHave(text(NO_INTERNET_TEXT), timeout);
    }
}
