package all.pizzaShop.pages;
import all.base.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {

    private String url = "http://pizzeria.skillbox.cc";

    @FindBy(css = "html")
    public WebElement html;
    @FindBy(className = "slick-next")
    public WebElement nextButton;
    @FindBy(className = "slick-prev")
    public WebElement backButton;
    @FindBy(css = ".slick-slide:nth-of-type(9)")
    public WebElement forwardCard;
    @FindBy(css = ".slick-slide:nth-of-type(5)")
    public WebElement backCard;
    @FindBy(css = "#accesspress_store_product-7 .span3:nth-child(1) .attachment-shop_catalog")
    public WebElement drinkCard;
    @FindBy(css = "#accesspress_store_product-7 .span3:nth-child(1) .add_to_cart_button")
    public WebElement orderButton;
    @FindBy(id = "ak-top")
    public WebElement upButton;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(url);
    }

    public void waitUpButton() {wait.until(driver -> upButton.isDisplayed());}

    public void scrollCarouselForward(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getCardIndex(index)));
        new Actions(driver)
                .moveToElement(nextButton)
                .click(nextButton)
                .perform();
    }

    public void scrollCarouselBack(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getCardIndex(index)));
        new Actions(driver)
                .moveToElement(backButton)
                .click(backButton)
                .perform();
    }

    public By getCardIndex(int index) {return By.cssSelector(String.format(".slick-slide:nth-of-type(%d)", index));}

    public By getFooterLink(String value) {return By.linkText(String.format("(%d)", value));}

    public Boolean forwardCardIsVisible() {
        if (forwardCard.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public Boolean backCardIsVisible() {
        if (backCard.isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void scrollPageDown() {
        for (int i = 0; i < 20; i++) {
            html.sendKeys(Keys.ARROW_DOWN);
        }
    }

    public void moveCursorToDrinkCard() {
        new Actions(driver)
                .moveToElement(drinkCard)
                .perform();
    }
}
