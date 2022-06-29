package all.pizzaShop.pages;
import all.base.pages.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaPage extends Page {

    private String url = "http://pizzeria.skillbox.cc/product-category/menu/pizza/";

    @FindBy(name = "orderby")
    public WebElement sortedSelect;
    @FindBy(css = ".product")
    public WebElement productCards;
    @FindBy(css = ".product")
    public List<WebElement> productCardList;
    @FindBy(css = ".button:nth-child(3)")
    public WebElement submitButton;
    @FindBy(css = ".ui-slider-handle:nth-of-type(1)")
    public WebElement leftSliderButton;
    @FindBy(css = ".collection_title")
    public WebElement cardTitle;
    @FindBy(css = ".product:nth-child(1) .button")
    public WebElement buyButton;
    @FindBy(css = ".added_to_cart")
    public WebElement goToBasketButton;
    @FindBy(linkText = "[ 435,00₽ ]")
    public WebElement price;

    public PizzaPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open() {driver.navigate().to(url);}

    public void setSorted(String value) {
        var sorted = new Select(sortedSelect);
        sorted.selectByValue(value);
    }

    public String getSorted() {
        var sorted = new Select(sortedSelect);
        return sorted.getFirstSelectedOption().getText();
    }

    public List<String> getSortedList() {
        var author = new Select(sortedSelect);
        return author.getOptions().stream().map(element -> element.getText()).collect(Collectors.toList());
    }

    public String getCardSize() {return String.valueOf(productCardList.size());}

    public void moveSlider() {
        for (int i = 0; i < 8; i++) {
            leftSliderButton.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public void waitTitle() {wait.until(driver -> cardTitle.isDisplayed());}

    public String getTitleText() {return cardTitle.getText();}

    public void addPizzaToTheBasket() {buyButton.click();}

    public String getPriceText() {return price.getText();}
}