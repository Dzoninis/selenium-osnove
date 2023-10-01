package dd_29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak_3 {
    public static void main(String[] args) throws InterruptedException {

//        3. Zadatak
//        Napisati program koji:
//        Pre nego sto krenete u automatizaciju prvo sve korake uradite rucno
//        Implicitno cekanje za trazenje elemenata je maksimalno 10s
//        Implicitno cekanje za ucitavanje stranice je 5s
//        Ucitava stranicu https://docs.katalon.com/
//        Maksimizuje prozor
//        Od html elementa cita data-theme atribut.
//                Proverava da li je sadrzaj u tom atributu light i ispisuje odgovarajuce poruke
//        Klikce na dugme za zamenu tema
//        Ponovo cita data-theme atribut html elementa i validira da u atributu stoji vrednost dark
//        Izvrsava kombinaciju tastera CTRL + K. Koristan link  za keyboard actions…kako izvrsavati precice preko Actions objekta
//        Ceka da se dijalog za pretragu pojavi
//        Zatim od inputa za pretragu cita atribut type i proverava da je vrednost tog atributa search
//        Zatvara pretrazivac


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://docs.katalon.com/");
        Thread.sleep(2000);


        String temaStranice = driver.findElement(By.tagName("html")).getAttribute("data-theme");

        if (temaStranice.equals("light")) {
            System.out.println("Ukljucena je light tema");
        } else {
            System.out.println("Ukljucena je dark tema");
        }

        WebElement dugmeZaPromenuTeme = driver.findElement(By.cssSelector(".toggleButton_rCf9"));
        dugmeZaPromenuTeme.click();

        wait.withMessage("Nije promenjena tema");
        wait.until(ExpectedConditions.attributeToBe(By.tagName("html"), "data-theme", "dark"));
        System.out.println("Dark tema je aktivna");

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("K");
        actions.perform();

        wait.withMessage("Dijalog za pretragu se nije pojavio");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#autocomplete-0-input")));

        String typeAtribut = driver.findElement(By.cssSelector("input#autocomplete-0-input")).getAttribute("type");

        if (typeAtribut.equals("search")) {
            System.out.println("Type atribut je search");
        } else {
            System.out.println("Type attribute nije search");
        }

        Thread.sleep(3000);
        driver.quit();

    }
}
