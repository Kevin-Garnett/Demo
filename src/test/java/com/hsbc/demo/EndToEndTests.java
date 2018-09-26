package com.hsbc.demo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndToEndTests {

    static ChromeDriverService chromeDriverService;
    static ChromeDriver chromeDriver;

    @LocalServerPort
    int port;

    @BeforeClass
    public static void setUp() throws IOException{
        System.setProperty("webdriver.chrome.driver", "ext/chromedriver");
        chromeDriverService = ChromeDriverService.createDefaultService();
        chromeDriver = new ChromeDriver(chromeDriverService);
        Path testResults = Paths.get("build", "test-results");
        if (!Files.exists(testResults)){
            Files.createDirectory(testResults);
        }
    }

    @AfterClass
    public static void tearDown(){
        chromeDriverService.stop();
    }

    @Test
    public void homePageShouldWork() throws IOException{
        chromeDriver.get("http://localhost:" + port);

        takeScreenShot("homePageShouldWork-1");

        assertThat(chromeDriver.getTitle())
            .isEqualTo("Index");

        String pageContent = chromeDriver.getPageSource();

        assertThat(pageContent)
                .contains("<a href=\"/image/Kevin.jpg/raw\">");

        WebElement element = chromeDriver.findElement(By.cssSelector("a[href*=\"Kevin.jpg\"]"));

        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(element).click().perform();

        takeScreenShot("homePageShouldWork-2");

        chromeDriver.navigate().back();
    }

    private void takeScreenShot(String name) throws IOException{
        FileCopyUtils.copy(
                chromeDriver.getScreenshotAs(OutputType.FILE),
                new File("build/test-results/TEST-" + name + ".jpg")
        );
    }

}
