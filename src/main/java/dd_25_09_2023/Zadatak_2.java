package dd_25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak_2 {
    public static void main(String[] args) throws InterruptedException {

//        Zadatak
//        Niz todo-a (niz stringova) koje treba da uneti. Niz je:
//        Visit Paris
//        Visit Prague
//        Visit London
//        Visit New York
//        Visit Belgrade
//        Maksimizirati prozor
//        Ucitati stranicu https://example.cypress.io/todo
//        Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
//        Nakon svakog unosa todo-a, unosi se enter. Koristan link
//        Nakon svih unosa proci petljom kroz svaki todo koji je na stranici i za svaki cekirati da je completed.
//                Cekanje od 5s
//        Zatvorite pretrazivac


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        ArrayList<String> todo = new ArrayList<>();

        todo.add("Visit Paris");
        todo.add("Visit Prague");
        todo.add("Visit London");
        todo.add("Visit New York");
        todo.add("Visit Belgrade");

        driver.manage().window().maximize();

        driver.get("https://example.cypress.io/todo");


        for (int i = 0; i < todo.size(); i++) {
            WebElement unos = driver.findElement(By.xpath("/html/body/section/div/header/input"));
            unos.clear();
            unos.sendKeys(todo.get(i));
            unos.sendKeys(Keys.ENTER);
        }

        for (int i = 0; i < todo.size(); i++) {
            WebElement cekirano = driver.findElement(By.xpath("/html/body/section/div/section/ul/li[" + (i + 3) + "]/div/input"));
            cekirano.click();
        }

        Thread.sleep(5000);

        driver.quit();

    }
}
