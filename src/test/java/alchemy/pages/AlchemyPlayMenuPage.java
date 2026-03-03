package alchemy.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

public class AlchemyPlayMenuPage {
    private final SelenideElement hintsButton =
            $(By.xpath("(//android.widget.TextView[" +
                    "translate(@text,'0123456789','')='' and string-length(@text)>0" +
                    "])[1]/ancestor::*[@clickable='true'][1]"));
    private final SelenideElement hintsCount =
            $(By.xpath("(//android.widget.TextView[translate(@text,'0123456789','')=" +
                    "'' and string-length(@text)>0])[1]"));

    private final SelenideElement hiddenElementsTitle =
            $(By.xpath("//android.widget.TextView[@text='Hidden elements']"));

    public void goToHintsDrawer() {
        hintsButton.shouldBe(visible).click();
    }

    public String getHintsCountText() {
        return hintsCount.shouldBe(visible).getText();
    }

    public void verifyPlayMenuOpened() {
        hiddenElementsTitle.shouldBe(visible);
    }
}