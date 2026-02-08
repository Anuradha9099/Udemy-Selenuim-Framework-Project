package org.anuradhaacademy;

import TestComponents.BaseTest;
import TestComponents.RetryMechanisumNG;
import com.sun.net.httpserver.Authenticator;
import org.anuradhaacademy.pageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandaloneTest extends BaseTest {

    @Test(dataProvider = "getData", retryAnalyzer = RetryMechanisumNG.class)
    public void endToEndTest(HashMap<String, String> input) throws IOException {

        ProductCatalogPage productCatalogPage = landingPage.loginApplication(input.get("email"), input.get("password"));
        Assert.assertEquals(landingPage.getLoginMessage(), "Login Successfully");

        List<WebElement> productList = productCatalogPage.getProductList();
        productCatalogPage.productAddToCart(input.get("product"));
        Assert.assertEquals(productCatalogPage.getMessageProductAdded(), "Product Added To Cart");
        CartDetailsPage cartDetailsPage = productCatalogPage.clickAddToCartPage();

        Assert.assertTrue(cartDetailsPage.getCartProductDetails(input.get("product")));
        ShippingDetialsPage shippingDetialsPage = cartDetailsPage.clickCheckoutButton();
        shippingDetialsPage.setCountryPlaceHolder();
        shippingDetialsPage.placeOrder();

        OrderThanksPage orderThanksPage = shippingDetialsPage.placeOrder();
        Assert.assertEquals(orderThanksPage.getSucessMesageOfOrderThanks(), "THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = {"endToEndTest"}, dataProvider = "getData")
    public void checkOrderHistoryTest(HashMap<String, String> input) {
        ProductCatalogPage productCatalogPage = landingPage.loginApplication(input.get("email"), input.get("password"));
        OrderViewPage orderViewPage = productCatalogPage.clickViewOrderButton();
        Assert.assertTrue(orderViewPage.verifyProductIsDisplayed(input.get("product")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        //using hashmaps
//        HashMap<String, String> hashMap = new HashMap<String, String>();
//        hashMap.put("email", "nima1@gmail.com");

//        hashMap.put("password", "Nima@5056");
//        hashMap.put("product", "ADIDAS ORIGINAL");
//
//        HashMap<String, String> hashMap1 = new HashMap<String, String>();
//        hashMap1.put("email", "nima2@gmail.com");
//        hashMap1.put("password", "Nima@5057");
//        hashMap1.put("product", "ZARA COAT 3");

//why use hashmap? because we can handle seperate arguments then next day no of arguments can be chnaged.

        //using json file
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//TestData//Data.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
