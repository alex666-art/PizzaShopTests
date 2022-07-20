package all.pizzaShop.tests;

import all.base.TestBase;
import all.pizzaShop.pages.BasketPage;
import all.pizzaShop.pages.LoginPage;
import all.pizzaShop.pages.OrderPage;
import all.pizzaShop.pages.PizzaPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderPageTests extends TestBase {

    @Test
    public void orderPage__buyPizza__goToPaymentAndSetOrderData__test() {
        //arrange
        var loginPage = new LoginPage(driver, wait);
        var pizzaPage = new PizzaPage(driver, wait);
        var basketPage = new BasketPage(driver, wait);
        var orderPage = new OrderPage(driver, wait);
        loginPage.open();
        loginPage.login();
        pizzaPage.open();
        //action
        pizzaPage.addPizzaToTheBasket();
        pizzaPage.goToBasketPage();
        basketPage.goToPayment();
        orderPage.setDate("20.07.2022");
        //assert
        var expectedDate = "2022-07-20";
        Assertions.assertEquals(expectedDate, orderPage.getDate(), "Не удалось установить дату доставки товара!");

    }

    @Test
    public void orderPage__buyPizza__goToPaymentAndSetOrderData__payGoods__test() {
        //arrange
        var loginPage = new LoginPage(driver, wait);
        var pizzaPage = new PizzaPage(driver, wait);
        var basketPage = new BasketPage(driver, wait);
        var orderPage = new OrderPage(driver, wait);
        loginPage.open();
        loginPage.login();
        pizzaPage.open();
        //action
        pizzaPage.addPizzaToTheBasket();
        pizzaPage.goToBasketPage();
        basketPage.goToPayment();
        orderPage.setDataFieldOrder();
        orderPage.submitOrder();
        //assert
        var expectedTitle = "Адрес для отправки чека";
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedTitle, orderPage.getAddressDeliveryText(), "Заказ не был оплачен!")
        );
    }
}
