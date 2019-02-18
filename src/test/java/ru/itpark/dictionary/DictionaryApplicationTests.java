package ru.itpark.dictionary;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itpark.dictionary.entity.DictionaryEntity;
import ru.itpark.dictionary.repository.DictionaryRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class) // расширение для JUnit
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DictionaryApplicationTests {

    private static WebDriver webDriver;

    @LocalServerPort
    private int port;

    @Autowired
    private DictionaryRepository repository;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        webDriver = new ChromeDriver(options);
    }

    @Test
    public void displayMainPage() {
        webDriver.get("http://localhost:" + port);

        webDriver.findElement(By.className("no-items"));
    }

    @Test
    @DirtiesContext
    public void searchByName() {
        repository.saveAll(List.of(
                new DictionaryEntity(0, "Первый", Collections.emptyList()),
                new DictionaryEntity(0, "Второй", Collections.emptyList())
        ));

        webDriver.get("http://localhost:" + port);
        WebElement search = webDriver.findElement(By.className("validate"));
        search.sendKeys("Первый");
        search.sendKeys(Keys.ENTER);

        var wait = new WebDriverWait(webDriver,5);

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("collection-item"),
                1
        ));

        assertTrue(webDriver.findElement(By.className("collection-item"))
        .getText().toLowerCase()
        .contains("первый"));
    }

    @Test
    @DirtiesContext
    public void searchByNameWithLowerCase() {
        repository.saveAll(List.of(
                new DictionaryEntity(0, "Первый", Collections.emptyList()),
                new DictionaryEntity(0, "Второй", Collections.emptyList())
        ));

        webDriver.get("http://localhost:" + port);
        WebElement search = webDriver.findElement(By.className("validate"));
        search.sendKeys("первый");
        search.sendKeys(Keys.ENTER);

        var wait = new WebDriverWait(webDriver,5);

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("collection-item"),
                1
        ));

        assertTrue(webDriver.findElement(By.className("collection-item"))
                .getText().toLowerCase()
                .contains("первый"));
    }
}

