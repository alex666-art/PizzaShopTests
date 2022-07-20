package all.pizzaShop.pages;
import all.base.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BonusPage extends Page {

    private String url = "http://pizzeria.skillbox.cc/bonus/";

    @FindBy(id = "bonus_username")
    public WebElement userName;
    @FindBy(id = "bonus_phone")
    public WebElement userPhone;
    @FindBy(css = ".woocommerce-form-register__submit")
    public WebElement submitButton;
    @FindBy(css = "h3")
    public WebElement bonusTitle;

    public BonusPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(url);
    }

    public void addBonusCard() {
        userName.sendKeys(" Î∏Ô‡");
        userPhone.sendKeys("+79994157633");
        submitButton.click();
    }
}
