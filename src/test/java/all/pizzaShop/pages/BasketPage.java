package all.pizzaShop.pages;

import all.base.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasketPage extends Page {

    @FindBy(name = "cart[25b2822c2f5a3230abfadd476e8b04c9][qty]")
    public WebElement countEditor;
    @FindBy(name = "update_cart")
    public WebElement refreshButton;
    @FindBy(css = ".woocommerce-message")
    public WebElement refreshMessage;
    @FindBy(css = ".cart_item")
    public WebElement goods;
    @FindBy(css = ".cart_item")
    public List<WebElement> goodsList;
    @FindBy(css = ".woocommerce-cart-form__cart-item:nth-child(2) .remove")
    public WebElement deleteButton;
    @FindBy(css = ".blockUI")
    public WebElement loader;
    @FindBy(css = ".checkout-button")
    public WebElement checkOutButton;
    @FindBy(id = "coupon_code")
    public WebElement couponCodeField;
    @FindBy(name = "apply_coupon")
    public WebElement applyCoupon;
    @FindBy(css = ".coupon-givemehalyava")
    public WebElement discountCard;

    public BasketPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void addCount() {
        for (int i = 0; i < 4; i++) {
            new Actions(driver)
                    .click(countEditor)
                    .sendKeys(Keys.ARROW_UP)
                    .perform();
        }
    }

    public void subtractCount() {
        new Actions(driver)
                .click(countEditor)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys("5")
                .perform();
        for (int i = 5; i > 1; i--) {
            new Actions(driver)
                    .click(countEditor)
                    .sendKeys(Keys.ARROW_DOWN)
                    .perform();
        }
    }

    public Boolean refreshButtonIsEnabled() {
        wait.until(driver -> refreshButton.isDisplayed());
        return refreshButton.isEnabled();
    }

    public Boolean refreshMessageIsDisplayed() {
        wait.until(driver -> !loader.isDisplayed());
        return refreshMessage.isDisplayed();
    }

    public String getGoodsSize() {
        return String.valueOf(goodsList.size());
    }

    public Boolean goodsIsDisplayed() {
        return goods.isDisplayed();
    }

    public void deleteCard() {
        deleteButton.click();
        wait.until(driver -> !loader.isDisplayed());
    }

    public void goToPayment() {
        checkOutButton.click();
    }

    public void setDiscountCoupon() {
        couponCodeField.sendKeys("GIVEMEHALYAVA");
        applyCoupon.click();
    }

}