package all.pizzaShop.tests;

import all.base.TestBase;
import all.pizzaShop.pages.BasketPage;
import all.pizzaShop.pages.LoginPage;
import all.pizzaShop.pages.OrderPage;
import all.pizzaShop.pages.PizzaPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasketPagesTests extends TestBase {

    @Test
    public void basketPage__buyPizzaAndAddCount__test() {
        //arrange
        var pizzaPage = new PizzaPage(driver, wait);
        var basketPage = new BasketPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addPizzaToTheBasket();
        pizzaPage.goToBasketPage();
        //action
        basketPage.addCount();
        //assert
        Assertions.assertTrue(basketPage.refreshButtonIsEnabled(), "Колличество товаров не увеличилось!");
    }

    @Test
    public void basketPage__buyPizzaAndSubtractCount__test() {
        //arrange
        var pizzaPage = new PizzaPage(driver, wait);
        var basketPage = new BasketPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addPizzaToTheBasket();
        pizzaPage.goToBasketPage();
        //action
        basketPage.subtractCount();
        //assert
        Assertions.assertTrue(basketPage.refreshButtonIsEnabled(), "Колличество товаров не уменьшилось!");
    }

    @Test
    public void basketPage__buyPizzaAddCountAndRefresh__test() {
        //arrange
        var pizzaPage = new PizzaPage(driver, wait);
        var basketPage = new BasketPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addTwoPizzaToTheBasket();
        pizzaPage.goToBasketPage();
        //action
        basketPage.deleteCard();
        //assert
        var expectedSize = "1";
        Assertions.assertAll(
                () -> Assertions.assertTrue(basketPage.refreshMessageIsDisplayed(), "Корзина не обновилась!"),
                () -> Assertions.assertTrue(basketPage.goodsIsDisplayed(), "Не отображаются товары в корзине!"),
                () -> Assertions.assertEquals(expectedSize, basketPage.getGoodsSize(), "Колличество товаров в корзине нее изменилось после удаления товара!")

        );
    }

    @Test
    public void basketPage__login__buyPizza__goToPayment__test() {
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
        //assert
        var expectedTitle = "ОФОРМЛЕНИЕ ЗАКАЗА";
        Assertions.assertEquals(expectedTitle, orderPage.getTitleText(), "Страница оплаты не открылась!");
    }

    @Test
    public void basketPage__basketPage__AddPizzaAndSetDiscountCoupon__test() {
        //arrange
        var pizzaPage = new PizzaPage(driver, wait);
        var basketPage = new BasketPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addPizzaToTheBasket();
        pizzaPage.goToBasketPage();
        //action
        basketPage.setDiscountCoupon();
        //assert
        Assertions.assertTrue(basketPage.discountCard.isDisplayed(), "Скидка не применилась!");
    }
}
