package org.anuradhaacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartDetailsPage extends AbstractComponent {

    WebDriver driver;

    public CartDetailsPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='cartSection']/h3")
    List<WebElement> cartProductDetails;

    @FindBy(css=".totalRow button:last-of-type")
    WebElement checkOutButton;

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
