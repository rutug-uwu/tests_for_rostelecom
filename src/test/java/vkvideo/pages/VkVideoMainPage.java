package vkvideo.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import java.time.Duration;

public class VkVideoMainPage {
    private final SelenideElement firstVideoCard =
            $(By.xpath("(//*[@resource-id='com.vk.vkvideo:id/recycler']/" +
                    "/*[@resource-id='com.vk.vkvideo:id/content'])[1]"));
    private final SelenideElement firstVideoTitleInFeed =
            $(By.xpath("(//*[@resource-id='com.vk.vkvideo:id/recycler']/" +
                    "/*[@resource-id='com.vk.vkvideo:id/title'])[1]"));

    public void openFirstVideo(Duration timeout) {
        firstVideoCard.shouldBe(visible, timeout).click();
    }

    public String getFirstVideoTitle(Duration timeout) {
        return firstVideoTitleInFeed
                .shouldBe(visible, timeout)
                .getText()
                .replaceAll("\\s+", " ")
                .trim();
    }
}