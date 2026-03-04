package alchemy.common.constants;

import java.time.Duration;

public class AlchemyTestConstants {
    private AlchemyTestConstants() {
    }

    public static final int HINTS_PER_AD = 2;
    public static final Duration AD_WATCH_TIMEOUT_SECONDS = Duration.ofSeconds(60);
    public static final Duration AD_BUTTON_TIMEOUT = Duration.ofSeconds(60);
}
