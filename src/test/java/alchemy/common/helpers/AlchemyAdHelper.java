package alchemy.common.helpers;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Selenide;

import org.openqa.selenium.By;

import java.time.Duration;

public class AlchemyAdHelper {
    public static boolean waitForAd(int seconds) {
        try {
            Selenide.$(By.xpath("//*[@text='reward granted' or @text='Reward granted' or @text='REWARD GRANTED']"))
                    .shouldBe(visible, Duration.ofSeconds(seconds));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }
}
