package all.pizzaShop.tests;

import all.base.TestBase;
import all.pizzaShop.pages.HeaderPanel;
import all.pizzaShop.pages.LoginPage;
import all.pizzaShop.pages.PizzaPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class HeaderPanelTests extends TestBase {

    private static Stream<Arguments> headerCardsIndexAndTitle() {
        return Stream.of(
                arguments(4, "�����"),
                arguments(5, "� ���"),
                arguments(6, "�������"),
                arguments(7, "��� �������"),
                arguments(9, "�������� ���������")
        );
    }

    @ParameterizedTest
    @MethodSource("headerCardsIndexAndTitle")
    public void headerPanel__openHeaderCards__andDisplayCurrentPage__test(int index, String title) throws UnsupportedEncodingException {
        //arrange
        var headerPanel = new HeaderPanel(driver, wait);
        headerPanel.open();
        //action
        headerPanel.clickHeaderCard(index);
        //assert
        var expectedTitle = title;
        Assertions.assertEquals(expectedTitle, headerPanel.getCurrentTitle(), "������� �� ���������!");
    }

    @Test
    public void headerPanel__openOrderCard__andDisplayCurrentPage__test() {
        //arrange
        var headerPanel = new HeaderPanel(driver, wait);
        var pizzaPage = new PizzaPage(driver, wait);
        var loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login();
        headerPanel.open();
        pizzaPage.open();
        //action
        pizzaPage.addPizzaToTheBasket();
        headerPanel.clickHeaderCard(8);
        //assert
        var expectedTitle = "���������� ������";
        Assertions.assertEquals(expectedTitle, headerPanel.getCurrentTitle(), "������� �� ���������!");
    }

    @Test
    public void headerPanel__openPizzaCard__andDisplayCurrentPage__test() {
        //arrange
        var headerPanel = new HeaderPanel(driver, wait);
        var pizzaPage = new PizzaPage(driver, wait);
        headerPanel.open();
        //action
        headerPanel.openPizzaPage();
        //assert
        var expectedTitle = "�����";
        Assertions.assertEquals(expectedTitle, pizzaPage.getPageTitle(), "������� �� ���������!");
    }

    @Test
    public void headerPanel__openDessertCard__andDisplayCurrentPage__test() {
        //arrange
        var headerPanel = new HeaderPanel(driver, wait);
        var pizzaPage = new PizzaPage(driver, wait);
        headerPanel.open();
        //action
        headerPanel.openDessertPage();
        //assert
        var expectedTitle = "�������";
        Assertions.assertEquals(expectedTitle, pizzaPage.getPageTitle(), "������� �� ���������!");
    }

    @Test
    public void headerPanel__openDrinkCard__andDisplayCurrentPage__test() {
        //arrange
        var headerPanel = new HeaderPanel(driver, wait);
        var pizzaPage = new PizzaPage(driver, wait);
        headerPanel.open();
        //action
        headerPanel.openDrinkPage();
        //assert
        var expectedTitle = "�������";
        Assertions.assertEquals(expectedTitle, pizzaPage.getPageTitle(), "������� �� ���������!");
    }
}
