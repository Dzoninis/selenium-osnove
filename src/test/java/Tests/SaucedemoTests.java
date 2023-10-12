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

    @Test(priority = 2)
    public void verifyTitleNameForCartPage() {
        inventoryPage.clicOnAddToCart();
        topNavPage.clickOnShoppingCart();
        Assert.assertEquals(driver.getTitle(), "Swag Labs",
                "Title page is not correct");
    }

    @Test(priority = 3)
    public void verifyHeaderNameForCartPage() {
        topNavPage.clickOnShoppingCart();
        Assert.assertEquals(cartPage.getHeaderTitle(), "Swag Labs",
                "Title page is not correct");
    }

    @Test(priority = 4)
    public void verifyIfTheHamburgerMenuButtonIsPresented() {
        topNavPage.clickOnShoppingCart();
        Assert.assertTrue(topNavPage.isElementPresented(), "Hamburger menu is NOT presented");
    }

    @Test(priority = 5)
    public void verifyIfTheCartIconIsPresented() {
        topNavPage.clickOnShoppingCart();
        Assert.assertTrue(topNavPage.isCartIconPresented(), "Cart icon is NOT presented");
    }

    @Test(priority = 6)
    public void ifTheHamburgerMenuButtonIsEnabled() {
        topNavPage.clickOnShoppingCart();
        topNavPage.clickOnBurgerMenu();
        Assert.assertTrue(topNavPage.isNavigationMenuOpened(), "Hamburger menu buttons is not functional");
    }

}