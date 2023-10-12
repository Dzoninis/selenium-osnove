package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SaucedemoTests extends BasicTests {

    @Test(priority = 1)
    public void verifyUrlForCartPage() {

        topNavPage.clickOnShoppingCart();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "cart.html",
                "Current url should be 'https://www.saucedemo.com/cart.html' ");
    }
}