package all.pizzaShop;

import all.base.TestBase;
import all.pizzaShop.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PizzaTests extends TestBase {

    private String errorMessage = "Элемент не отображается!";
    private String orderIsNotAddMessage = "Заказ не был оформлен!";

    @Test
    public void mainPage__scrollCarouselForward__andDisplayCard__test() {
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        //action
        page.scrollCarouselForward(8);
        //assert
        var expectedResult = page.forwardCardIsVisible();
        Assertions.assertTrue(expectedResult, errorMessage);
    }

    @Test
    public void mainPage__scrollCarouselBack__andDisplayCard__test() {
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        //action
        page.scrollCarouselBack(8);
        //assert
        var expectedResult = page.backCardIsVisible();
        Assertions.assertTrue(expectedResult, errorMessage);
    }

    @Test
    public void mainPage__moveToCard__click__andDisplayTitle__test() {
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        //action
        page.scrollPageDown();
        page.moveCursorToDrinkCard();
        //assert
        var expectedResult = page.orderButton;
        Assertions.assertTrue(expectedResult.isDisplayed(), errorMessage);
    }

    @Test
    public void mainPage__moveToFooter__andDisplayUpButton__test() {
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        //action
        page.scrollPageDown();
        page.waitUpButton();
        //assert
        var expectedResult = page.upButton;
        Assertions.assertTrue(expectedResult.isDisplayed(), errorMessage);
    }

    private static Stream<Arguments> indexAndUrl__links() {
        return Stream.of(
                arguments(3, "https://www.facebook.com/skillboxru"),
                arguments(4, "https://vk.com/skillbox"),
                arguments(5, "https://www.instagram.com/skillbox.ru/")
        );
    }

    @ParameterizedTest
    @MethodSource("indexAndUrl__links")
    public void mainPage__moveToFooter__andClickSocialLinks__test(int index, String url) {
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        //action
        page.scrollPageDown();
        page.waitLastLink();
        page.clickFooterLink(index);
        //assert
        var expectedUrl = url;
        switchToFirstWindow();
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, getAllWindows().size(), "Вторая вкладка не открылась!"),
                () -> Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "Открылась не верная страница!")
        );
    }

    private static Stream<Arguments> selectValues() {
        return Stream.of(
                arguments("menu_order", "Обычная сортировка"),
                arguments("popularity", "По популярности"),
                arguments("date", "Последние"),
                arguments("price", "По возрастанию цены"),
                arguments("price-desc", "По убыванию цены")
        );
    }

    @ParameterizedTest
    @MethodSource("selectValues")
    public void pizzaPage__getSelectValue__test(String value, String expectedResult) {
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        var initialValue = page.getSorted();
        //action
        page.setSorted(value);
        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResult, page.getSorted(), "Выбрано неверное значение!"),
                () -> Assertions.assertTrue(page.productCards.isDisplayed(), "Карточки не отображаются!")
        );
    }

    @Test
    public void pizzaPage__getSelectValues__test() {
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        //action
        var expectedSorts = Arrays.asList("Обычная сортировка", "По популярности", "Последние", "По возрастанию цены", "По убыванию цены");
        //assert
        Assertions.assertEquals(expectedSorts, page.getSortedList(), "В списке сортировок присутствуют не все значения!");
    }

    @Test
    public void pizzaPage__getCardsSize__test() {
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        //action
        page.moveSlider();
        page.submitButton.click();
        page.waitTitle();
        //assert
        var expectedSize = "1";
        var expectedTitle = "Пицца «Рай»";
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedSize, page.getCardSize(), "Колличество карточек не совпадает с ожидаемым результатом!"),
                () -> Assertions.assertEquals(expectedTitle, page.getTitleText(), "Фильтрация по цене не работает!")
        );
    }

    @Test
    public void pizzaPage__addPizzaAndDisplayCardOnBasket__test() {
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        //action
        page.addPizzaToTheBasket();
        //assert
        var expectedResult = "[ 435,00₽ ]";
        Assertions.assertEquals(expectedResult, page.getPriceText(), "Товар не добавился в корзину!");
    }

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
    public void login__buyPizza__goToPayment__test() {
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
    public void basketPage__AddPizzaAndSetDiscountCoupon__test() {
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

    @Test
    public void buyPizza__goToPaymentAndSetOrderData__test() {
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
    public void buyPizza__goToPaymentAndSetOrderData__payGoods__test() {
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
                () -> Assertions.assertEquals(expectedTitle, orderPage.getAddressDeliveryText(), "Заказ не юыл оплачен!")
        );
    }
}