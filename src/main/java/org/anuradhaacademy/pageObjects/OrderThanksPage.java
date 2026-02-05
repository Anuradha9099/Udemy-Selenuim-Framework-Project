package org.anuradhaacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderThanksPage extends AbstractComponent {

    WebDriver driver;

    public OrderThanksPage(WebDriver webDriver){
        super(webDriver);
        this.driver=webDriver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(tagName = "h1")
    WebElement thanksHeadingInfo;

    By thanksHeading= By.tagName("h1");

    public String getSucessMesageOfOrderThanks(){
        waitToElementToBeAppreard(thanksHeading);
        return thanksHeadingInfo.getText();
    }
}
