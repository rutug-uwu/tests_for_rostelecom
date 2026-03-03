package vkvideo.tests;

import core.base.BaseMobileTest;

public class BaseVkVideoTest extends BaseMobileTest {

    private static final String VK_VIDEO_PACKAGE = "com.vk.vkvideo";

    @Override
    protected String appPackage() {
        return VK_VIDEO_PACKAGE;
    }
}