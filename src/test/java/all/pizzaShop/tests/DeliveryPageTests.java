package all.pizzaShop.tests;

import all.base.TestBase;
import all.pizzaShop.pages.DeliveryPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeliveryPageTests extends TestBase {

    @Test
    public void DeliveryPage__openPageSuccess__test() {
        //arrange
        var deliveryPage = new DeliveryPage(driver, wait);
        deliveryPage.open();
        //action
        deliveryPage.switchToIframe();
        //assert
        var expectedText = "Минимальная сумма заказа 800 рублей.";
        Assertions.assertEquals(expectedText, deliveryPage.getTermsDeliveryText(), "Станица доставки не открылась!");
    }
}
