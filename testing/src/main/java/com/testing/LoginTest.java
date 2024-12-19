package com.testing;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class LoginTest {

    public static void main(String[] args) {

        // Configura ChromeDriver automáticamente
        System.setProperty("webdriver.chrome.driver","src/main/java/driver/chromedriver.exe");

       //WebDriverManager.chromedriver().setup();

        // Inicia el navegador

        WebDriver driver = new ChromeDriver();

        try {

            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            WebElement searchBox = driver.findElement(By.name("q"));

            searchBox.sendKeys("Synopsis");

            searchBox.submit();
           //searchButton.click();

            Thread.sleep(3000);

            if (driver.getTitle().contains("Synopsis")) {
                System.out.println("✅ Búsqueda exitosa");
            } else {
                System.out.println("❌ Búsqueda fallida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra el navegador
            //driver.quit();
        }

    }

}