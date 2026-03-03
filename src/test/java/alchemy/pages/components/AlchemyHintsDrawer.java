package alchemy.pages.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import java.time.Duration;

public class AlchemyHintsDrawer {
    private final SelenideElement titleYourHints =
            $(By.xpath("//*[contains(@text,'Your hints')]"));
    private final SelenideElement hintsCount =
            $(By.xpath("//*[contains(@text,'Your hints')]/following::*" +
                    "[translate(@text,'0123456789','')='' and string-length(@text)>0][1]"));
    private final SelenideElement watchButton =
            $(By.xpath("//*[@text='Watch']"));

    public void tapWatchButton(Duration timeout) {
        watchButton.shouldBe(visible, timeout).click();
    }

    public String getHintsCountText() {
        return hintsCount.shouldBe(visible).getText();
    }

    public void verifyHintsDrawerOpened() {
        titleYourHints.shouldBe(visible);
    }
}