package dd_26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak_2 {
    public static void main(String[] args) throws InterruptedException {

//        2. Zadatak
//        Napisati program koji:
//        Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//        Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//        POMOC: Brisite elemente odozdo.
//        (ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://s.bootsnipp.com/iframe/Dq2X");

        List<WebElement> poruka = driver.findElements(By.cssSelector(".alert-dismissable"));

        for (int i = poruka.size() - 1; i >= 0; i--) {
            poruka.get(i).findElement(By.cssSelector(".close")).click();
            Thread.sleep(1000);

            List<WebElement> brisanjeRedova = driver.findElements(By.cssSelector(".alert-dismissable:nth-child(" + (i + 1) + ")"));

            if (brisanjeRedova.isEmpty()) {
                System.out.println((i + 1) + "-i red je obrisan.");
            } else {
                System.out.println((i + 1) + "-i red nije obrisan.");
            }
            Thread.sleep(2000);
        }gi

        driver.quit();
    }
}
