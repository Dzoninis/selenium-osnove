package dd_29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.channels.WritableByteChannel;
import java.time.Duration;
import java.util.List;

public class Zadatak_1 {
    public static void main(String[] args) throws InterruptedException {

//        1. Zadatak
//        Napisati program koji testira infinity scroll.
//        Ucitati stranu https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html
//        Selektujte delay od 2000ms, koristeci Select klasu.
//        Skrol do Show more dugmeta koje se nalazi na dnu stranice
//        Sacekajte da dugme bude klikljivo
//        Klik na Show more dugme
//        Sacekjate da broj elemenata bude X (X je koliko se kod vas ucitava)
//        Sacekajte da dugme vise ne bude klikljivo

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");
        Thread.sleep(2000);

        List<WebElement> listaPrviElementi = driver.findElements(By.cssSelector("div.item"));
        WebElement delay = driver.findElement(By.id("delay-select"));

        new Select(delay).selectByValue("2000");

        WebElement scroll = driver.findElement(By.cssSelector("button#infinite-scroll-button"));

        WebElement zadnjiElement = driver.findElement(By.cssSelector("#infinite-scroll-container div:nth-child(5)"));

        Actions actions = new Actions(driver);

        actions.scrollToElement(zadnjiElement);
        actions.perform();
        actions.scrollToElement(scroll);
        actions.perform();

        wait.withMessage("Dugme nije klikljivo");
        wait.until(ExpectedConditions.elementToBeClickable(scroll));
        scroll.click();

        List<WebElement> listaElemenata = driver.findElements(By.cssSelector("div.item"));

        wait.withMessage("Broj elemenata je nepromenljiv");
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.item"), listaElemenata.size()));

        System.out.println("Broj elemenata je " + (listaElemenata.size() - listaPrviElementi.size()));

        wait.withMessage("Dugme je i dalje klikljivo");
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(scroll)));

        System.out.println("Dugme vise nije klikljivo");

        Thread.sleep(3000);
        driver.quit();

    }
}
