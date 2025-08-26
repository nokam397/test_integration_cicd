package com.logwire;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {

    private WebDriver driver;
    private By usernameBy = By.id("user-name");
    private By passwordBy = By.id("password");
    private By loginButtonBy = By.id("login-button");
    private By errorMessageBy = By.cssSelector("[data-test='error']");

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    @Tag("TC001") // Username incorrect, mot de passe correct
    public void testUsernameIncorrect() {
        WebElement usernameElement = driver.findElement(usernameBy);
        usernameElement.sendKeys("pilote");

        WebElement passwordElement = driver.findElement(passwordBy);
        passwordElement.sendKeys("secret_sauce");

        driver.findElement(loginButtonBy).click();

        WebElement erreur = driver.findElement(errorMessageBy);
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                     erreur.getText());
    }

    @Test
    @Tag("TC002") // Username correct, mot de passe incorrect
    public void testPasswordIncorrect() {
        WebElement usernameElement = driver.findElement(usernameBy);
        usernameElement.sendKeys("standard_user");

        WebElement passwordElement = driver.findElement(passwordBy);
        passwordElement.sendKeys("mon frere");

        driver.findElement(loginButtonBy).click();

        WebElement erreur = driver.findElement(errorMessageBy);
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                     erreur.getText());
    }

    @Test
    @Tag("TC003") // Username correct, mot de passe correct
    public void testLoginCorrect() {
        WebElement usernameElement = driver.findElement(usernameBy);
        usernameElement.sendKeys("standard_user");

        WebElement passwordElement = driver.findElement(passwordBy);
        passwordElement.sendKeys("secret_sauce");

        driver.findElement(loginButtonBy).click();

        String currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentURL);
    }
}
