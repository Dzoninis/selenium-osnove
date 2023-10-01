package dd_28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak_4 {
    public static void main(String[] args) throws InterruptedException {

//        4.Zadatak
//        Ucitati stranicu http://seleniumdemo.com/?post_type=product
//        Klik na search dugme u gornjem desnom uglu
//        Cekati da forma za pretragu bude vidljiva
//        Uneti sledeci tekst za pretragu BDD Cucumber i ENTER
//        Dohvatiti prvi rezultat pretrage i proveriti da li u nazivu sadrzi tekst koji je unet za pretragu. Ispisati odgovarajuce poruke u terminalu


        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://seleniumdemo.com/?post_type=product");
        Thread.sleep(2000);

        WebElement searchDugme = driver.findElement(By.cssSelector(".topbar-nav__utils ul li:nth-child(1) a"));
        searchDugme.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("s-651536d633c09")));

        String poljeZaPretragu = "BDD Cucumber";

        driver.findElement(By.id("s-651536d633c09")).sendKeys(poljeZaPretragu);
        driver.findElement(By.id("s-651536d633c09")).sendKeys(Keys.ENTER);

        List<WebElement> listaPretrage = driver.findElements(By.id("post-29"));

        for (int i = 0; i < listaPretrage.size(); i++) {
            listaPretrage.get(i);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("czr-title"), "BDD Cucumber"));
            wait.withMessage("Naziv artikla u pretrazi se ne podudara sa artiklom u rezultatu");

            System.out.println("Izabran je odgovarajuci artikal");
        }

        Thread.sleep(2000);
        driver.quit();

    }
}
