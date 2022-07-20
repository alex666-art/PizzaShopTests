package all.pizzaShop.pages;

import all.base.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends Page {

    @FindBy(css = ".post-title")
    public WebElement pageTitle;
    @FindBy(id = "billing_first_name")
    public WebElement firstName;
    @FindBy(id = "billing_last_name")
    public WebElement lastName;
    @FindBy(id = "order_date")
    public WebElement date;
    @FindBy(id = "billing_address_1")
    public WebElement address;
    @FindBy(id = "billing_city")
    public WebElement city;
    @FindBy(id = "billing_state")
    public WebElement state;
    @FindBy(id = "billing_postcode")
    public WebElement postCode;
    @FindBy(id = "billing_phone")
    public WebElement phone;
    @FindBy(id = "payment_method_cod")
    public WebElement paymentMethodCheckBox;
    @FindBy(id = "terms")
    public WebElement checkBoxTerms;
    @FindBy(id = "place_order")
    public WebElement submitOrderButton;
    @FindBy(css = ".woocommerce-column__title")
    public WebElement addressDelivery;

    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String getTitleText() {
        return pageTitle.getText();
    }

    public void setDate(String value) {
        date.sendKeys(value);
    }

    public String getDate() {
        return date.getAttribute("value");
    }

    public void setDataFieldOrder() {
        firstName.sendKeys("Егор");
        lastName.sendKeys("Летов");
        date.sendKeys("20.07.2022");
        address.sendKeys("ул. Пирожковая 11");
        city.sendKeys("Калыма");
        state.sendKeys("Сибирь");
        postCode.sendKeys("110006");
        phone.sendKeys("+79214563455");
        paymentMethodCheckBox.click();
        checkBoxTerms.click();
    }

    public void submitOrder() {
        submitOrderButton.click();
    }

    public String getAddressDeliveryText() {
        wait.until(driver1 -> addressDelivery.isDisplayed());
        return addressDelivery.getText();
    }
}