package com.example.expensetracker.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExpenseUITest {

    @LocalServerPort
    private int port;
    private WebDriver driver;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "http://localhost:" + port;
    }

    @Test
    void testPageTitle() {
        driver.get(baseUrl + "/");
        assertEquals("Expense Tracker", driver.getTitle());
    }

    @Test
    void testAddExpenseViaUI() {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("description")).sendKeys("UI Test Item");
        driver.findElement(By.name("amount")).sendKeys("99.99");
        driver.findElement(By.tagName("button")).click();

        // Wait for page to reload and check if the item is present
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("UI Test Item"));
        assertTrue(pageSource.contains("99.99"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
