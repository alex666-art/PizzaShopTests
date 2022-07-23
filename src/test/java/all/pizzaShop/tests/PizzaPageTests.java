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
                arguments("menu_order", "������� ����������"),
                arguments("popularity", "�� ������������"),
                arguments("date", "���������"),
                arguments("price", "�� ����������� ����"),
                arguments("price-desc", "�� �������� ����")
        );
    }
    //�������� ���������� � ������� �����
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
                () -> Assertions.assertEquals(expectedResult, page.getSorted(), "������� �������� ��������!"),
                () -> Assertions.assertTrue(page.productCards.isDisplayed(), "�������� �� ������������!")
        );
    }
    //�������� ������ ����������
    @Test
    public void pizzaPage__getSelectValues__test() {
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        //action
        var expectedSorts = Arrays.asList("������� ����������", "�� ������������", "���������", "�� ����������� ����", "�� �������� ����");
        //assert
        Assertions.assertEquals(expectedSorts, page.getSortedList(), "� ������ ���������� ������������ �� ��� ��������!");
    }

    //�������� ���������� �� ����
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
        var expectedTitle = "����� ����";
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedSize, page.getCardSize(), "����������� �������� �� ��������� � ��������� �����������!"),
                () -> Assertions.assertEquals(expectedTitle, page.getTitleText(), "���������� �� ���� �� ��������!")
        );
    }

    //���������� ����� � �������
    @Test
    public void pizzaPage__addPizzaAndDisplayCardOnBasket__test() {
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        //action
        page.addPizzaToTheBasket();
        //assert
        var expectedText = "����� \"4 � 1\"";
        Assertions.assertAll(
                () -> Assertions.assertTrue(page.goodsIsDisplayed(), "����� �� ��������� � �������!"),
                () -> Assertions.assertEquals(expectedText, page.getPriceText(), "�� ������������ ����������� ����� � �������!")
        );

    }
}