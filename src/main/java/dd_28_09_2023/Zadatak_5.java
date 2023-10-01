package dd_28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak_5 {
    public static void main(String[] args) throws InterruptedException {

//        5. Zadatak
//        Ucitati stranicu http:
//        seleniumdemo.com/?product=bdd-cucumber
//        Klik na korpu iz gornjeg desnog ugla
//        Sacekati da naslov stranice bude Cart – Selenium Demo Page
//        Proveriti da li element koji prikazuje stanje korpe ima tekst Your cart is currently empty.


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://seleniumdemo.com/?product=bdd-cucumber");
        Thread.sleep(2000);

        WebElement korpa = driver.findElement(By.cssSelector(".icn-shoppingcart:nth-child(1)"));
        korpa.click();
        korpa.click();

        WebElement title = driver.findElement(By.cssSelector(".entry-title"));
        wait.until(ExpectedConditions.titleIs("Cart – Selenium Demo Page"));

        String praznaKorpa = driver.findElement(By.cssSelector(".cart-empty")).getText();

        if (praznaKorpa.equals("Your cart is currently empty.")) {
            System.out.println("Tekst se podudara");
        }

        Thread.sleep(2000);
        driver.quit();

    }
}
