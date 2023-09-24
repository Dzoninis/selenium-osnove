package dd_22_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak_1 {

    public static void main(String[] args) throws InterruptedException {

//        Napisati program koji:
//        ima niz od 5 stringova. Svaki element u nizu je url stranice:
//        https://www.google.com/
//        https://www.facebook.com/
//        https://www.youtube.com/
//        https://www.ebay.com/
//        https://www.katalon.com/
//        kreira testnu infrastukturu
//        zatim koristeci for petlju otvara svaku stranicu iz niza u pretrazivacu i pritom pravi pauzu od 2 sekunde izmedju svaka dva ucitanja stranice
//        Na kraju program ponisava testnu ifrastukturu

        WebDriverManager.chromedriver().setup();
        // Kreiranje instance ChromeDriver-a
        WebDriver driver = new ChromeDriver();
        ArrayList<String> stranice = new ArrayList<>();

        stranice.add("https://google.com");
        stranice.add("https://www.facebook.com");
        stranice.add("https://www.youtube.com");
        stranice.add("https://www.ebay.com");
        stranice.add("https://www.katalon.com");

        // Otvaranje veb stranice
        for (int i = 0; i < stranice.size(); i++) {
            driver.get(stranice.get(i));
            if (i % 2 != 0) {
                Thread.sleep(2000);
            }
        }
        // Zatvoranje pretrazivaca nakon sto se zavrsi testiranje
        driver.quit();
    }
}
