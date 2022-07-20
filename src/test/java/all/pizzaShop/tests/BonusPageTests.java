package all.pizzaShop.tests;

import all.base.TestBase;
import all.pizzaShop.pages.BonusPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BonusPageTests extends TestBase {

    @Test
    public void bonusPage__addBonusCardSuccess__test() {
        //arrange
        var bonusPage = new BonusPage(driver, wait);
        bonusPage.open();
        //action
        bonusPage.addBonusCard();
        var alert = driver.switchTo().alert();
        //assert
        var expectedText = "������ ����������, ���������, ����������, ���������� �����!";
        Assertions.assertEquals(expectedText, alert.getText(), "������ �� �������� ����� �� ���� ����������!");
    }
}
