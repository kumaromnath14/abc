package com.test.framework;

import com.test.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage("https://example.com/login");
        loginPage.login("invaliduser", "invalidpass");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
    }
}
