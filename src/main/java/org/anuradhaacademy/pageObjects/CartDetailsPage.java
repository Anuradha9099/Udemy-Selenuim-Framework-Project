package org.anuradhaacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartDetailsPage extends AbstractComponent {
    /*
    this page is used to get the cart product details and click the checkout button.
    we use stream to get the product details and check if the product is in the cart.

    why use stream? because it is a library that provides a way to process collections of data.
    we use stream to get the product details and check if the product is in the cart.
    why use anyMatch? because it is a method that returns true if any of the elements in the stream match the predicate.
    */


    WebDriver driver;

    public CartDetailsPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='cartSection']/h3")
    private List<WebElement> cartProductDetails;

    @FindBy(css=".totalRow button:last-of-type")
    private WebElement checkOutButton;

    public Boolean getCartProductDetails(String productName) {
        waitToWebElementListToBeAppreard(cartProductDetails);
        Boolean matchProductFlag = cartProductDetails.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equals(productName));
        return matchProductFlag;
    }

    public ShippingDetialsPage clickCheckoutButton(){
        waitToWebElementToBeAppreard(checkOutButton);
        checkOutButton.click();
        ShippingDetialsPage shippingDetialsPage = new ShippingDetialsPage(driver);
        return shippingDetialsPage;
    }
}
