package org.anuradhaacademy;

import TestComponents.BaseTest;
import org.anuradhaacademy.pageObjects.ProductCatalogPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void loginWithValidCredentails() {
        ProductCatalogPage productCatalogPage = landingPage.loginApplication("nima1@gmail.com", "Nima@5056");
        Assert.assertEquals(landingPage.getLoginMessage(), "Login Successfully");
    }

    @Test(priority = 2)
    public void loginWithInvalidCredentails(){
        ProductCatalogPage productCatalogPage = landingPage.loginApplication("nima77@gmail.com", "Nima@5057");
        Assert.assertEquals(landingPage.getErrorMessageWithInvalidCredentails(), "Incorrect email or password.");
    }

    @Test(priority = 3)
    public void loginwithEmptyCredentails(){
        ProductCatalogPage productCatalogPage = landingPage.loginApplication("", "");
        Assert.assertEquals(landingPage.getErrorWithBlankCredentails(), "*Email is required");
    }
}
