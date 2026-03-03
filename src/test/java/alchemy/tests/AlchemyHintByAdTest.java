package alchemy.tests;

import static alchemy.common.constants.AlchemyTestConstants.AD_BUTTON_TIMEOUT;
import static alchemy.common.constants.AlchemyTestConstants.AD_WATCH_TIMEOUT_SECONDS;
import static alchemy.common.constants.AlchemyTestConstants.HINTS_PER_AD;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import alchemy.common.helpers.AlchemyAdHelper;
import alchemy.pages.AlchemyMainMenuPage;
import alchemy.pages.AlchemyPlayMenuPage;
import alchemy.pages.components.AlchemyHintsDrawer;
import alchemy.steps.AlchemyMobileSteps;
import core.annotations.MobileTestTimeout;

@MobileTestTimeout
public class AlchemyHintByAdTest extends BaseAlchemyTest {
    private final AlchemyMainMenuPage mainMenu = new AlchemyMainMenuPage();
    private final AlchemyPlayMenuPage playMenu = new AlchemyPlayMenuPage();
    private final AlchemyHintsDrawer hintsMenu = new AlchemyHintsDrawer();
    private final AlchemyMobileSteps steps = new AlchemyMobileSteps(mainMenu, playMenu, hintsMenu);

    @Test
    @DisplayName("Позитивный: Получение подсказки за просмотр рекламы")
    void getHintsByWatchingAdTest() {
        steps.openHintsDrawerFromMain();
        int hintsBeforeAd = steps.getHintsCountInHintsDrawer();

        hintsMenu.tapWatchButton(AD_BUTTON_TIMEOUT);
        AlchemyAdHelper.waitForAd(AD_WATCH_TIMEOUT_SECONDS);
        restartApp();

        steps.openPlayMenuFromMain();
        int hintsAfterAd = steps.getHintsCountInMainMenu();
        Assertions.assertEquals(hintsBeforeAd + HINTS_PER_AD, hintsAfterAd);
    }
}