package all.pizzaShop;
import all.base.TestBase;
import all.pizzaShop.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PizzaTests extends TestBase {

    private String errorMessage = "Элемент не отображается!";

    @Test
    public void scrollCarouselForward__andDisplayCard__Test() {
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
    public void scrollCarouselBack__andDisplayCard__Test() {
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
    public void moveToCard__click__andDisplayTitle__Test() {
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
    public void moveToFooter__andDisplayUpButton__Test() {
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

    private static Stream<Arguments> socialLinks() {
        return Stream.of(
                arguments("Facebook"),
                arguments("ВКонтакте"),
                arguments("Instagram")
        );
    }

    @ParameterizedTest
    @MethodSource("socialLinks")
    public void moveToFooter__andClickSocialLink__Test(String link) {
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        //action
        page.scrollPageDown();
        page.waitUpButton();
    }
}
