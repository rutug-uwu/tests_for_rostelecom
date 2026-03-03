package alchemy.steps;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alchemy.pages.AlchemyMainMenuPage;
import alchemy.pages.AlchemyPlayMenuPage;
import alchemy.pages.components.AlchemyHintsDrawer;

public class AlchemyMobileSteps {
    private static final Pattern DIGITS = Pattern.compile("\\d+");

    private final AlchemyMainMenuPage mainMenu;
    private final AlchemyPlayMenuPage playMenu;
    private final AlchemyHintsDrawer hintsDrawer;

    public AlchemyMobileSteps(AlchemyMainMenuPage mainMenu, AlchemyPlayMenuPage playMenu, AlchemyHintsDrawer hintsDrawer) {
        this.mainMenu = mainMenu;
        this.playMenu = playMenu;
        this.hintsDrawer = hintsDrawer;
    }

    public void openPlayMenuFromMain() {
        mainMenu.goToPlayMenu();
        playMenu.verifyPlayMenuOpened();
    }

    public void openHintsDrawerFromMain() {
        openPlayMenuFromMain();
        playMenu.goToHintsDrawer();
        hintsDrawer.verifyHintsDrawerOpened();
    }

    public int getHintsCountInHintsDrawer() {
        return parseInt(hintsDrawer.getHintsCountText());
    }

    public int getHintsCountInMainMenu() {
        return parseInt(playMenu.getHintsCountText());
    }

    public int parseInt(String text) {
        Matcher m = DIGITS.matcher(text == null ? "" : text);
        if (!m.find()) {
            throw new AssertionError("Не удалось извлечь число из текста: '" + text + "'");
        }
        return Integer.parseInt(m.group());
    }
}