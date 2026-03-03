package vkvideo.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import java.time.Duration;

public class VkVideoPlayerPage {
    private final SelenideElement playerContainer =
            $(By.xpath("//*[@resource-id='com.vk.vkvideo:id/" +
                    "vk_video_minimizable_player_container']"));

    private final SelenideElement videoDisplay =
            $(By.xpath("//*[@resource-id='com.vk.vkvideo:id/video_display']"));

    private final SelenideElement videoTitle =
            $(By.xpath("//*[@resource-id='com.vk.vkvideo:id/" +
                    "video_about_description_root']" +
                    "//*[@resource-id='com.vk.vkvideo:id/title']"));

    public void shouldBePlayerOpened(Duration timeout) {
        playerContainer.shouldBe(visible, timeout);
        videoDisplay.shouldBe(visible, timeout);
        videoTitle.shouldBe(visible, timeout);
    }

    public String getVideoTitle(Duration timeout) {
        return videoTitle
                .shouldBe(visible, timeout)
                .getText()
                .replaceAll("\\s+", " ")
                .trim();
    }
}