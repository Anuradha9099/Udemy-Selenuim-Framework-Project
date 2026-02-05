package AbstractComponents;

import org.anuradhaacademy.pageObjects.CartDetailsPage;
import org.anuradhaacademy.pageObjects.OrderViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {
    //holding reusable codes here

    WebDriver driver;

    @FindBy(css = "[routerlink*=cart]")
    WebElement addToCartButton;

    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement viewOrderButton;

    public AbstractComponent(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    public void waitToElementToBeAppreard(By FindBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }

    public void waitToWebElementToBeAppreard(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitToWebElementListToBeAppreard(List<WebElement> webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
    }

    public void waitForElementToDisappear(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    //store heading info
    public CartDetailsPage clickAddToCartPage() {
        addToCartButton.click();
        CartDetailsPage cartDetailsPage = new CartDetailsPage(driver);
        return cartDetailsPage;
    }

    public OrderViewPage clickViewOrderButton() {
        waitToWebElementToBeAppreard(viewOrderButton);
        viewOrderButton.click();
        OrderViewPage orderViewPage = new OrderViewPage(driver);
        return orderViewPage;
    }
}
