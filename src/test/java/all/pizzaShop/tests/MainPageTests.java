package all.pizzaShop.tests;

import all.base.TestBase;
import all.pizzaShop.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MainPageTests extends TestBase {

    private String errorMessage = "������� �� ������������!";

    //����� �������� ������ � �������� ����������� ��������
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

    //����� �������� ����� � �������� ����������� ��������
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

    //����� ������� � �������� �������, ���� � �������� �������� �������� � ���������
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

    //����� ������� � ������� ��������, � �������� ����������� ������ "^"
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

    //����� ������� � ������� ��������, � �������� �������� �� ������� ��������
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
                () -> Assertions.assertEquals(2, getAllWindows().size(), "������ ������� �� ���������!"),
                () -> Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "��������� �� ������ ��������!")
        );
    }
}