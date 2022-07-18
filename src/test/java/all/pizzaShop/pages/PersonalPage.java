package all.pizzaShop.pages;

import all.base.pages.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalPage extends Page {

    private JavascriptExecutor javascriptExecutor;
    @FindBy(linkText = "Данные аккаунта")
    public WebElement personalData;
    @FindBy(id = "uploadFile")
    public WebElement loadFile;
    @FindBy(id = "uploadPreview")
    public WebElement uploadImage;

    public PersonalPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        javascriptExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void personalDataClick() {
        personalData.click();
    }

    public void loadFile() {
        var filePath = System.getProperty("user.dir") + "\\file\\Gubka_Bob.jpg";
        loadFile.sendKeys(filePath);
    }

    public Boolean imageIsVisible() {
        return uploadImage.isDisplayed();
    }
}
