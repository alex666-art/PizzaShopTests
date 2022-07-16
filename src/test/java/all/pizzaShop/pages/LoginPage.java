package all.pizzaShop.pages;
import all.base.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {

    private String url = "http://pizzeria.skillbox.cc/";

    @FindBy(css = ".account")
    public WebElement accountIn;
    @FindBy(id = "username")
    public WebElement username;
    @FindBy(id = "password")
    public WebElement password;
    @FindBy(name = "login")
    public WebElement loginButton;

    public LoginPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(url);
        accountIn.click();
    }

    public void login() {
        username.sendKeys("Alexey676");
        password.sendKeys("test1234@test.com");
        loginButton.click();
    }
}
