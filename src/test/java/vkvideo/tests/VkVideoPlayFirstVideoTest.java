package vkvideo.tests;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static vkvideo.common.constants.VkVideoTestConstants.TIMEOUT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import annotations.MobileTestTimeout;
import vkvideo.pages.VkVideoMainPage;
import vkvideo.pages.VkVideoPlayerPage;
import vkvideo.pages.VkVideoProfilePage;
import vkvideo.pages.components.VkVideoAuthPopup;

@MobileTestTimeout
public class VkVideoPlayFirstVideoTest extends BaseVkVideoTest {
    private final VkVideoAuthPopup authPopup = new VkVideoAuthPopup();
    private final VkVideoMainPage main = new VkVideoMainPage();
    private final VkVideoPlayerPage videoPlayer = new VkVideoPlayerPage();
    private final VkVideoProfilePage profile = new VkVideoProfilePage();

    @Test
    @DisplayName("Позитивный: Видео должно воспроизводиться при наличии интернета")
    void shouldPlayVideoSuccessfullyTest() {
        authPopup.tapSkipButton(TIMEOUT);

        String expectedTitle = main.getFirstVideoTitle(TIMEOUT);
        main.openFirstVideo(TIMEOUT);

        videoPlayer.shouldBePlayerOpened(TIMEOUT);

        String actualTitle = videoPlayer.getVideoTitle(TIMEOUT);
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Негативный: Видео не воспроизводится без интернета")
    void videoShouldNotPlayWithoutInternetTest() {
        withAllNetworksDisabled(() -> {
            restartApp();
            profile.verifyOfflineModeDisplayed(TIMEOUT);
        });
    }
}