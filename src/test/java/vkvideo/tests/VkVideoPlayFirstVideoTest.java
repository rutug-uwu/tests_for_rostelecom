package vkvideo.tests;

import static vkvideo.common.constants.VkVideoTestConstants.APP_TIMEOUT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import core.annotations.MobileTestTimeout;
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
        authPopup.tapSkipButton(APP_TIMEOUT);

        String expectedTitle = main.getFirstVideoTitle(APP_TIMEOUT);
        main.openFirstVideo(APP_TIMEOUT);

        videoPlayer.shouldBePlayerOpened(APP_TIMEOUT);

        String actualTitle = videoPlayer.getVideoTitle(APP_TIMEOUT);
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Негативный: Видео не воспроизводится без интернета")
    void videoShouldNotPlayWithoutInternetTest() {
        withAllNetworksDisabled(() -> {
            restartApp();
            profile.verifyOfflineModeDisplayed(APP_TIMEOUT);
        });
    }
}