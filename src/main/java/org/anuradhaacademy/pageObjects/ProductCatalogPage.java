package org.anuradhaacademy.pageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogPage(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    private List<WebElement> productList;

    @FindBy(css = ".ng-animating")
    private WebElement spinner;

    @FindBy(xpath = "//div[text()=' Product Added To Cart ']")
    private WebElement toastMessageProductMessage;

    private By productBy = By.cssSelector(".mb-3");
    private By addToCart = By.cssSelector(".card-body .btn.w-10.rounded");
    private By toastMessageProduct = By.cssSelector("#toast-container");

    public List<WebElement> getProductList() {
        waitToElementToBeAppreard(productBy);
        return productList;
    }

    public WebElement getProductByName(String productName) {
        WebElement productStreamList = getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().contains(productName))
                .findFirst()
                .orElse(null);
        return productStreamList;
    }

    public void productAddToCart(String productName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if(productName.equals("ZARA COAT 3")){
            js.executeScript("window.scrollBy(0,700)");
        }
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        js.executeScript("window.scrollTo(0,0)");
        waitToElementToBeAppreard(toastMessageProduct);
        waitForElementToDisappear(spinner);
    }

    public String getMessageProductAdded() {
        waitToWebElementToBeAppreard(toastMessageProductMessage);
        return toastMessageProductMessage.getText();
    }
}
