package ddd_02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak_1 {
    public static void main(String[] args) throws InterruptedException {

        //    1.Zadatak
//    Napisati program koji:
//    Podesava:
//    implicitno cekanje za trazenje elemenata od 10s
//    implicitno cekanje za ucitavanje stranice od 10s
//    eksplicitno cekanje podeseno na 10s
//    Podaci:
//    Potrebno je u projektu ukljuciti 4 slike:
//    front.jpg
//    left.jpg
//    right.jpg
//    back.jpg
//    Koraci:
//    Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//    Maksimizuje prozor
//    Klik na edit ikonicu
//    Klik na delete iz iskacuceg dijaloga
//    Klik na Add Image dugme
//    Sacekajte da se pojavi desni meni
//    Uploadujte front.jpg sliku
//    Sacekajte da je ispod uploada slike, broj slika 1.
//    Klik na sliku
//    Klik na Done dugme
//    Sacekajte 2s
//    Klik na Add Image dugme
//    Sacekajte da se pojavi desni meni
//    Uploadujte right.jpg sliku
//    Sacekajte da je ispod uploada slike, broj slika 2.
//    Klik na sliku
//    Klik na Done dugme
//    Sacekajte 2s
//    Klik na Add Image dugme
//    Sacekajte da se pojavi desni meni
//    Uploadujte back.jpg sliku
//    Sacekajte da je ispod uploada slike, broj slika 3.
//    Klik na sliku
//    Klik na Done dugme
//    Sacekajte 2s
//    Klik na Add Image dugme
//    Sacekajte da se pojavi desni meni
//    Uploadujte back.jpg sliku
//    Sacekajte da je ispod uploada slike, broj slika 3.
//    Klik na sliku
//    Klik na Done dugme
//    Sacekajte 2s
//    Sacekajte da Next dugme bude klikljivo
//    Klik na Next dugme
//    Unesite tekst
//    Klik na Next
//    Klik na Preview
//    Klik na Add to cart
//    Sacekajte 5s
//            Quit

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");

        driver.findElement(By.cssSelector("img.edit-image")).click();
        driver.findElement(By.cssSelector("#image-option-remove svg")).click();

        for (int i = 0; i < 4; i++) {
            driver.findElement(By.cssSelector("img.edit-image")).click();

            wait.withMessage("Image menu was not showned.");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("imageUpload")));

            File uploadFile = new File("test_data/priroda_4.jpeg");

            driver.findElement(By.id("imageUpload")).sendKeys(uploadFile.getAbsolutePath());

            wait.withMessage("Image was not uploaded");
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.sc-gLDzan"), (i + 1)));

            driver.findElement(By.id("image-option-" + i)).click();
            driver.findElement(By.cssSelector("button.sc-beqWaB")).click();

            Thread.sleep(2000);
        }

        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("textareaID")).click();
        driver.findElement(By.id("textareaID")).sendKeys("Srecna kocka!");

        for (int i = 0; i < 4; i++) {
            driver.findElement(By.id("next-button")).click();
            Thread.sleep(2500);
        }

        Thread.sleep(5000);
        driver.quit();
    }
}