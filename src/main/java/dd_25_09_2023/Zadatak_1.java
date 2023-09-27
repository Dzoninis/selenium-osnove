package dd_25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak_1 {
    public static void main(String[] args) throws InterruptedException {

//        Zadatak
//        Maksimizirati prozor
//        Ucitati stranicu https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
//        Prijavite se na sistem
//        Username: Admin
//        Password: admin123
//        Cekanje od 5s
//        U input za pretragu iz navigacije unesite tekst Me
//        Kliknite na prvi rezultat pretrage (to ce biti Time)
//        Cekanje od 1s
//        Kliknite u headeru na svog avatara (to ce biti na ime: Paul Collings)
//        Klinkite na logout
//        Cekanje od 5s
//        Zatvorite pretrazivac

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(1000);

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("Admin");

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin123");


        WebElement prijava = driver.findElement(By.xpath("//button[@type='submit']"));
        prijava.click();

        Thread.sleep(5000);

        WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        search.clear();
        search.sendKeys("Me");


        WebElement time = driver.findElement(By.xpath("//span"));
        time.click();

        Thread.sleep(1000);

        WebElement avatar = driver.findElement(By.xpath("//img[@alt='profile picture']"));
        avatar.click();

        WebElement logout = driver.findElement(By.xpath("//*[contains(text(),'Logout')]"));
        logout.click();

        Thread.sleep(5000);

        driver.quit();

    }
}