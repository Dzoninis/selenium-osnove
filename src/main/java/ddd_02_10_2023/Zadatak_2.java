package ddd_02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak_2 {
    public static void main(String[] args) throws InterruptedException {

//    2. Zadatak
//    Napisati program koji:
//    Ucitava stranu https://itbootcamp.rs/
//    Misem prelazi preko Vesti iz navigacionog menija
//    Ceka da se prikaze padajuci meni za Vesti
//    Misem prelazi preko Kursevi iz navigacionog menija
//    Ceka da se prikaze padajuci meni za Kursevi
//    Misem prelazi preko Prijava i pravilnik iz navigacionog menija
//    Ceka da se prikaze padajuci meni za Prijava i pravilnik
//    Koristan link. Mouse over preko seleniuma https://www.selenium.dev/documentation/webdriver/actions_api/mouse/#move-to-element

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://itbootcamp.rs/");

        WebElement newsMenu = driver.findElement(By.id("menu-item-6408"));
        Actions actions = new Actions(driver);
        actions.moveToElement(newsMenu);
        actions.perform();

        wait.withMessage("There is no drop down menu for the News");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#menu-item-6408 ul")));
        Thread.sleep(2000);

        WebElement coursesMenu = driver.findElement(By.id("menu-item-5362"));
        actions.moveToElement(coursesMenu);
        actions.perform();

        wait.withMessage("There is no drop down menu for the Courses");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#menu-item-5362 ul")));
        Thread.sleep(2000);

        WebElement applicationRegulations = driver.findElement(By.id("menu-item-5453"));
        actions.moveToElement(applicationRegulations);
        actions.perform();

        wait.withMessage("There is no drop down menu for the Application and Regulations");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#menu-item-5453 ul")));
        Thread.sleep(2000);

        driver.quit();
    }
}