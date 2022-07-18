package all.pizzaShop.pages;
import all.base.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPanel extends Page {
    private JavascriptExecutor javascriptExecutor;
    private String url = "http://pizzeria.skillbox.cc/";

    @FindBy(css = ".current")
    public WebElement currentTitle;
    @FindBy(id = "menu-item-389")
    public WebElement mainCard;
    @FindBy(css = "#menu-item-390 > a")
    public WebElement pizzaCard;
    @FindBy(css = "#menu-item-391 > a")
    public WebElement dessertCard;
    @FindBy(css = "#menu-item-393 > a")
    public WebElement drinkCard;

    public HeaderPanel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        javascriptExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(url);
    }
    public By getHeaderCardLocator(int index) {
        return By.cssSelector(String.format(".menu-item:nth-of-type(%d) > a", index));
    }

    public Page clickHeaderCard(int index) {
        var card = getHeaderCardLocator(index);
        card.findElement(driver).click();
        return new Page(driver, wait);
    }

    public String getCurrentTitle() {
        return currentTitle.getText();
    }

    public void openPizzaPage() {
        new Actions(driver)
                .moveToElement(mainCard)
                .moveToElement(pizzaCard)
                .click(pizzaCard)
                .perform();
    }

    public void openDessertPage() {
        new Actions(driver)
                .moveToElement(mainCard)
                .moveToElement(dessertCard)
                .click(dessertCard)
                .perform();
    }

    public void openDrinkPage() {
        new Actions(driver)
                .moveToElement(mainCard)
                .moveToElement(drinkCard)
                .click(drinkCard)
                .perform();
    }
}