package org.anuradhaacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShippingDetialsPage extends AbstractComponent {

    WebDriver driver;

    public ShippingDetialsPage(WebDriver webDriver){
        super(webDriver);
        this.driver=webDriver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement countryPlaceHolder;

    @FindBy(xpath = "//button[contains(@class,'ta-item')]")
    private List<WebElement> countryList;

    @FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
    private WebElement placeOrderButton;

    private By waitDropDownElementsToBeVisible= By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']");


    public void setCountryPlaceHolder(){
        Actions actions = new Actions(driver);
        actions.sendKeys(countryPlaceHolder, "Sr").build().perform();
        waitToElementToBeAppreard(waitDropDownElementsToBeVisible);

        for (int i = 0; i <= countryList.size(); ++i) {
            if (countryList.get(i).getText().equals("Sri Lanka")) {
                countryList.get(i).click();
                break;
            }
        }
    }

    public OrderThanksPage placeOrder(){
        Actions actions = new Actions(driver);
        actions.moveToElement(placeOrderButton).click().perform();
        OrderThanksPage orderThanksPage = new OrderThanksPage(driver);
        return orderThanksPage;
    }
}
