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

    @Test (priority = 2)
    public void verifyTitleNameForCartPage() {
        inventoryPage.clicOnAddToCart();
        topNavPage.clickOnShoppingCart();
        Assert.assertEquals(driver.getTitle(), "Swag Labs",
                "Title page is not correct");
    }

    @Test (priority = 3)
    public void verifyHeaderNameForCartPage() {
        inventoryPage.clicOnAddToCart();
        topNavPage.clickOnShoppingCart();
        Assert.assertEquals(cartPage.getHeaderTitle(), "Swag Labs",
                "Title page is not correct");
    }

}