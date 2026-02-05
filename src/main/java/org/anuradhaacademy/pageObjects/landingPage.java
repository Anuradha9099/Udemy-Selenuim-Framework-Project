package org.anuradhaacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends AbstractComponent {
    WebDriver driver;

    public landingPage(WebDriver webDriver) {
        //Initalization
        super(webDriver); // sending child-parent variables using super keyword
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    //page factory
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement submitButton;

    @FindBy(xpath = "//div[text()=' Login Successfully ']")
    WebElement successMessage;

    @FindBy(css = "[class*=flyInOut]")
    WebElement errorMessage;

    @FindBy(xpath = "(//div[@class='ng-star-inserted'])[1]")
    WebElement errorUsernameLable;

    By successToast = By.cssSelector("#toast-container");

    public ProductCatalogPage loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitButton.click();
        ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
        return productCatalogPage;
    }

    public void navigateToPageURL() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getLoginMessage() {
        waitToElementToBeAppreard(successToast);
        return successMessage.getText();
    }

    public String getErrorMessageWithInvalidCredentails(){
        waitToWebElementToBeAppreard(errorMessage);
        return errorMessage.getText();
    }

    public String getErrorWithBlankCredentails(){
        waitToWebElementToBeAppreard(errorUsernameLable);
        return errorUsernameLable.getText();
    }
}
