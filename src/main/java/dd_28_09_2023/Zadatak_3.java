package dd_28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak_3 {
    public static void main(String[] args) throws InterruptedException {

        //Problematican zadatak sa drajva koji delimicno radi


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://seleniumdemo.com/?product=git-basics");

        WebElement cartProductCount = driver.findElement(By.cssSelector(".nav__woocart .count"));

        WebElement addToCartBtn = driver.findElement(By.name("add-to-cart"));

        addToCartBtn.click();

        Thread.sleep(1000); // cekaj da se osvezi stranica

//        wait.until(ExpectedConditions.textToBePresentInElement(cartProductCount, "1"));
        // umesto koda ispisanog iznad, iskucacemo sledeci kod i program ce raditi kako treba
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".nav__woocart .count"), "1"));
        System.out.println("KRAJ");

        driver.quit();
    }
}

