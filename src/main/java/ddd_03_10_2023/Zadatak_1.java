package ddd_03_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pp_25_09_2023.ppp_02_09_2023.Helper;

import java.io.IOException;
import java.time.Duration;

public class Zadatak_1 {

//    1.Zadatak
//    Kreirati BootstrapTableTests klasu koja ima:
//    Base url: https://s.bootsnipp.com/iframe/K5yrx
//    Test #1: Edit Row
//    Podaci:
//    First Name: ime polaznika
//    Last Name: prezime polaznika
//    Middle Name: srednje ime polanzika
//    Koraci:
//    Ucitati stranu /iframe/K5yrx
//    Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//    Klik na Edit dugme prvog reda
//    Sacekati da dijalog za Editovanje bude vidljiv
//    Popuniti formu podacima.
//    Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//    Klik na Update dugme
//    Sacekati da dijalog za Editovanje postane nevidljiv
//    Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//    Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//    Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//    Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//    Test #2: Delete Row
//    Podaci:
//    First Name: ime polaznika
//    Last Name: prezime polaznika
//    Middle Name: srednje ime polanzika
//    Koraci:
//    Ucitati stranu /iframe/K5yrx
//    Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//    Klik na Delete dugme prvog reda
//    Sacekati da dijalog za brisanje bude vidljiv
//    Klik na Delete dugme iz dijaloga
//    Sacekati da dijalog za Editovanje postane nevidljiv
//    Verifikovati da je broj redova u tabeli za jedan manji
//    Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//    Test #3: Take a Screenshot
//    Koraci:
//    Ucitati stranu  /iframe/K5yrx
//    Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//    Kreirati screenshot stranice.
//    Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: screenshots/slike.png


    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://s.bootsnipp.com/iframe/K5yrx";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to(baseUrl);
        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com", "Title of the page is not correct");
    }

    @Test
    public void editRow() {
        String firstName = "Pera";
        String lastName = "Peric";
        String middleName = "Vlada";

        driver.findElement(By.cssSelector("#d1 .update")).click();
        wait.withMessage("Dialog for editing is not visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#edit  .modal-dialog")));

        WebElement inputFirstName = driver.findElement(By.id("fn"));
        WebElement inputLastName = driver.findElement(By.id("ln"));
        WebElement inputMiddleName = driver.findElement(By.id("mn"));

        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
        inputMiddleName.clear();
        inputMiddleName.sendKeys(middleName);

        driver.findElement(By.xpath("//button[text()='Update']")).click();
        wait.withMessage("Dialog for editing is still visible");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#edit .modal-dialog")));

        Assert.assertEquals(driver.findElement(By.id("f1")).getText(), firstName, "First name should be " + firstName);
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(), lastName, "Last name should be " + lastName);
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(), middleName, "Middle name should be " + middleName);

    }

    @Test
    public void deleteRow() {
        int redovi = driver.findElements(By.cssSelector(".table>tbody>tr")).size();

        driver.findElement(By.cssSelector("#d1 .delete")).click();
        wait.withMessage("Edit dialog is not present");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#delete .modal-dialog")));

        driver.findElement(By.id("del")).click();
        wait.withMessage("Delete dialog is still present");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#delete .modal-dialog")));

        wait.withMessage("This row was not deleted");
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".table>tbody>tr"), redovi - 1));
    }

    @Test
    public void takeAScreenshot() throws IOException {
        Helper.takeAScreenshot(driver, "screenshots/screenshot2.jpg");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}