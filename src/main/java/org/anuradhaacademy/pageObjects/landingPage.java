package org.anuradhaacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends AbstractComponent {
    /*
    this page is used to login to the application.
    we use page factory to initialize the elements.
    */

    WebDriver driver;

    public landingPage(WebDriver webDriver) {
        //Initalization
        super(webDriver); // sending child-parent variables using super keyword
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    //page factory
    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(id = "login")
    private WebElement submitButton;

    @FindBy(xpath = "//div[text()=' Login Successfully ']")
    private WebElement successMessage;

    @FindBy(css = "[class*=flyInOut]")
    private WebElement errorMessage;

    @FindBy(xpath = "(//div[@class='ng-star-inserted'])[1]")
    private WebElement errorUsernameLable;

    private By successToast = By.cssSelector("#toast-container");

    public ProductCatalogPage loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitButton.click();
        ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
        return productCatalogPage;
    }

    public void navigateToPageURL(String url) {
        driver.get(url);
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
