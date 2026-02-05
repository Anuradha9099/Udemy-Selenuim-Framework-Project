package org.anuradhaacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderViewPage extends AbstractComponent {

    WebDriver driver;

    public OrderViewPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productList;

    public Boolean verifyProductIsDisplayed(String productName) {
        waitToWebElementListToBeAppreard(productList);
        Boolean matchProduct = productList.stream()
                .anyMatch(product-> product.getText().equals(productName));
        return matchProduct;
    }
}
