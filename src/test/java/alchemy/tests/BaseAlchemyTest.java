package alchemy.tests;

import tests.BaseMobileTest;

public class BaseAlchemyTest extends BaseMobileTest {
    private static final String ALCHEMY_PACKAGE = "com.ilyin.alchemy";

    @Override
    protected String appPackage() {
        return ALCHEMY_PACKAGE;
    }
}