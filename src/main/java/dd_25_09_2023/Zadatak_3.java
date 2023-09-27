package dd_25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak_3 {
    public static void main(String[] args) throws InterruptedException {

//        Zadatak
//        Napisati program koji vrsi dodavanje 5 redova
//        Maksimizirati prozor
//        Ucitati stranicu https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php
//        Dodati 5 redova sa istim podacima.Jedan red u jednoj iteraciji
//        Klik na dugme Add New
//        Unesite name,departmant i phone (uvek iste vrednost)
//        Trazenje po name atributu
//        Kliknite na zeleno Add dugme.
//                PAZNJA: Pogledajte strukturu stranice i videcete da se u svakom redu poslednje kolone javljaju dugmici edit, add, delete ali zbog prirode reda neki dugmici se vide a neki ne.
//                Morate da dohvatite uvek Add dugme iz poslednjeg reda tabele.

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php");

        for (int i = 0; i < 5; i++) {
            driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[2]/button")).click();
            WebElement ime = driver.findElement(By.name("name"));
            ime.sendKeys("Pera Peric");
            WebElement departman = driver.findElement(By.name("department"));
            departman.sendKeys("Financy");
            WebElement telefon = driver.findElement(By.name("phone"));
            telefon.sendKeys("(381)65-489-9488");

            driver.findElement(By.xpath("/html/body/div[1]/div/div/table/tbody/tr[" + (i + 4) + "]/td[4]/a[1]")).click();
        }

        Thread.sleep(500);
        driver.quit();

    }
}
