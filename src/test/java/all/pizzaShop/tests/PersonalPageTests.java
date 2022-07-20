package all.pizzaShop.tests;

import all.base.TestBase;
import all.pizzaShop.pages.HeaderPanel;
import all.pizzaShop.pages.LoginPage;
import all.pizzaShop.pages.PersonalPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonalPageTests extends TestBase {

    @Test
    public void personalPage__loadFile__test() {
        //arrange
        var loginPage = new LoginPage(driver, wait);
        var headerPanel = new HeaderPanel(driver, wait);
        var personalPage = new PersonalPage(driver, wait);
        loginPage.open();
        loginPage.login();
        headerPanel.clickHeaderCard(7);
        //action
        personalPage.personalDataClick();
        personalPage.loadFile();
        //assert
        Assertions.assertTrue(personalPage.imageIsVisible(), "Изображение не загрузилось!");
    }
}
