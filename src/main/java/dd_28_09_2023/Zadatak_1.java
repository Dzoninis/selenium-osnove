package dd_28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak_1 {
    public static void main(String[] args) throws InterruptedException {

//        1.Zadatak
//        Napisati program koji ucitava stranicu https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=
//        Klik na Type drawdown
//        Klik na Public iz drowdowna
//        Ceka da se Clear dugme u desnom uglu prikaze koristeci explicit wait
//        Kilk na Clear filter u desnom uglu


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=");
        Thread.sleep(2000);

        WebElement typeDrawdown = driver.findElement(By.id("type-options"));
        typeDrawdown.click();

        WebElement publicDrawdown = driver.findElement(By.cssSelector(".SelectMenu-item:nth-child(2) span"));
        publicDrawdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".issues-reset-query")));
        wait.withMessage("Dugme Clear se nije jos uvek pojavilo");

        WebElement clearDugme = driver.findElement(By.cssSelector(".issues-reset-query"));
        clearDugme.click();

        wait.until(ExpectedConditions.invisibilityOf(clearDugme));
        wait.withMessage("Dugme Clear se jos uvek vidi");

        driver.quit();

    }
}
