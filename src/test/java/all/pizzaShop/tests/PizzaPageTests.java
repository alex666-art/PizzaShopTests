package all.pizzaShop.tests;

import all.base.TestBase;
import all.pizzaShop.pages.PizzaPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PizzaPageTests extends TestBase {

    private static Stream<Arguments> selectValues() {
        return Stream.of(
                arguments("menu_order", "Обычная сортировка"),
                arguments("popularity", "По популярности"),
                arguments("date", "Последние"),
                arguments("price", "По возрастанию цены"),
                arguments("price-desc", "По убыванию цены")
        );
    }
    //проверки сортировок в разделе Пицца
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
    //проверка списка сортировок
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

    //проверка сортировки По цене
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

    //добавление пиццы в корзину
    @Test
    public void pizzaPage__addPizzaAndDisplayCardOnBasket__test() {
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        //action
        page.addPizzaToTheBasket();
        //assert
        var expectedText = "Пицца \"4 в 1\"";
        Assertions.assertAll(
                () -> Assertions.assertTrue(page.goodsIsDisplayed(), "Товар не добавился в корзину!"),
                () -> Assertions.assertEquals(expectedText, page.getPriceText(), "Не отображается добавленный товар в корзине!")
        );

    }
}