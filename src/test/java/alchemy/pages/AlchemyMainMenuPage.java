package alchemy.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

public class AlchemyMainMenuPage {
    private final SelenideElement playButton =
            $(By.xpath("//android.view.View[@clickable='true' and ." +
                    "//android.widget.TextView[@text='Play']]"));

    public void goToPlayMenu() {
        playButton.shouldBe(visible).click();
    }
}
