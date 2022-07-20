package all.pizzaShop.pages;

import all.base.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeliveryPage extends Page {

    private String url = "http://pizzeria.skillbox.cc/delivery/";

    @FindBy(tagName = "iframe")
    public WebElement iframe;
    @FindBy(css = "li:nth-of-type(2)")
    public WebElement termsDelivery;

    public DeliveryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(url);
    }

    public String getTermsDeliveryText() {
        return termsDelivery.getText();
    }

    public void switchToIframe() {
        driver.switchTo().frame(iframe);
    }
}
