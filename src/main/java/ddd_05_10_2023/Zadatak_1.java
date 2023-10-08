package ddd_05_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pp_25_09_2023.ppp_02_09_2023.Helper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak_1 {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://www.saucedemo.com/";
    private JavascriptExecutor javasript;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        javasript = (JavascriptExecutor) driver;
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl);
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title of the page is not correct");
    }

    @Test//#6
    public void addingProductsToCart() {

        String email = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait.withMessage("Incorrect url, it doesn't contain /inventory.html");
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        wait.withMessage("Remove button isn't visible.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "1",
                "Product should be added to cart by 1");
    }

    @Test//#7
    public void viewingProductDetails() {

        String email = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait.withMessage("Incorrect url, it doesn't contain /inventory.html");
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        List<WebElement> backpack = driver.findElements(By.className("inventory_item"));

        wait.withMessage("Backpack details aren't visible on the page.");
        wait.until(ExpectedConditions.visibilityOfAllElements(backpack));
    }

    @Test//#8
    public void removingProductsFromCart() {

        String email = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait.withMessage("Incorrect url, it doesn't contain /inventory.html");
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "1",
                "Product should be added to cart");

        driver.findElement(By.className("shopping_cart_link")).click();

        wait.withMessage("Product wasn't added to the cart.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        wait.withMessage("Product has been removed from cart");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("inventory_item_name")));
    }

    @Test//#9
    public void productCheckout() {

        String email = "standard_user";
        String password = "secret_sauce";
        String checkoutName = "Pera";
        String checkoutLastName = "Peric";
        String checkoutZip = "18000";

        driver.findElement(By.id("user-name")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait.withMessage("Incorrect url, it doesn't contain /inventory.html");
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        wait.withMessage("Checkout isn't visible.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(checkoutName);
        driver.findElement(By.id("last-name")).sendKeys(checkoutLastName);
        driver.findElement(By.id("postal-code")).sendKeys(checkoutZip);
        driver.findElement(By.id("continue")).click();

        wait.withMessage("Checkout form isn't visible.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_summary_container")));

        Assert.assertEquals(driver.findElement(By.className("summary_total_label")).getText(),
                "Total: $32.39", "Price should be $32.39");

        driver.findElement(By.id("finish")).click();
        Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(),
                "Thank you for your order!",
                "Thank you message is not visible");
    }

    @Test //#10
    public void validateSocialLinksInFooter() throws IOException {
        String username = "standard_user";
        String password = "secret_sauce";

        WebElement newUsername = driver.findElement(By.id("user-name"));
        newUsername.sendKeys(username);

        WebElement newPassword = driver.findElement(By.id("password"));
        newPassword.sendKeys(password);

        driver.findElement(By.cssSelector("#login-button")).click();
        wait.withMessage("Incorrect url, it doesn't contain /inventory.html");
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        WebElement footer = driver.findElement(By.className("footer"));

        Actions actions = new Actions(driver);
        actions.scrollToElement(footer);
        actions.perform();

        List<WebElement> links = driver.findElements(By.cssSelector(".social li a"));

        for (int i = 0; i < links.size(); i++) {
            int statusCode = Helper.getHTTPResponseStatusCode(links.get(i).getAttribute("href"));
            Assert.assertEquals(statusCode, 200, "Status code of page " +
                    links.get(i).getAttribute("href") + " should be 200");
        }
    }

    @Test//#11
    public void testDefaultNameSortA_Z() {

        String username = "standard_user";
        String password = "secret_sauce";

        WebElement newUsername = driver.findElement(By.id("user-name"));
        newUsername.sendKeys(username);

        WebElement newPassword = driver.findElement(By.id("password"));
        newPassword.sendKeys(password);

        driver.findElement(By.cssSelector("#login-button")).click();
        wait.withMessage("Incorrect url, it doesn't contain /inventory.html");
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        String nameOfProducts = "";

        for (int i = 0; i < products.size(); i++) {
            Assert.assertFalse(products.get(i).getText().compareTo(nameOfProducts) < 0,
                    "Products aren't order in (A-Z) order");
            nameOfProducts = products.get(i).getText();
        }
    }

    @Test//#12
    public void testInvertNamedSortZ_A() {
        String username = "standard_user";
        String password = "secret_sauce";

        WebElement newUsername = driver.findElement(By.id("user-name"));
        newUsername.sendKeys(username);

        WebElement newPassword = driver.findElement(By.id("password"));
        newPassword.sendKeys(password);

        driver.findElement(By.cssSelector("#login-button")).click();
        wait.withMessage("Incorrect url, it doesn't contain /inventory.html");
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        Select sortZtoA = new Select(driver.findElement(By.className("product_sort_container")));
        sortZtoA.selectByValue("za");

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        String nameOfProducts = products.get(0).getText();

        for (int i = 0; i < products.size(); i++) {
            Assert.assertFalse(products.get(i).getText().compareTo(nameOfProducts) > 0,
                    "Products aren't order in (Z-A) order");
            nameOfProducts = products.get(i).getText();
        }
    }

    @Test//#13
    public void testSortPriceLowHigh() {

        String username = "standard_user";
        String password = "secret_sauce";

        WebElement newUsername = driver.findElement(By.id("user-name"));
        newUsername.sendKeys(username);

        WebElement newPassword = driver.findElement(By.id("password"));
        newPassword.sendKeys(password);

        driver.findElement(By.cssSelector("#login-button")).click();
        wait.withMessage("Incorrect url, it doesn't contain /inventory.html");
        wait.until(ExpectedConditions.urlContains("/inventory.html"));

        Select sortByPrice = new Select(driver.findElement(By.className("product_sort_container")));
        sortByPrice.selectByValue("lohi");

        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
        double nameOfProducts = 0;

        for (int i = 0; i < products.size(); i++) {
            double price = Double.parseDouble(products.get(i).getText().substring(1));

            Assert.assertTrue(price >= nameOfProducts,
                    "Prices aren't order by Price from low to high");
            nameOfProducts = price;
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}