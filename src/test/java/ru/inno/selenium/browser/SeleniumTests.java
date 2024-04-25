package ru.inno.selenium.browser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.inno.seleium.hmwrk.URLWork;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {

    private WebDriver driver;

    @BeforeEach
    public void SetDriver() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void authorize() {
        driver.get(URLWork.URLAuthorize);

        driver.findElement(By.cssSelector("input[name=UserName]")).sendKeys("inno");
        driver.findElement(By.cssSelector("input[name=Password]")).sendKeys("pwd");

        driver.findElement(By.cssSelector("#login")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        String authorizeText = driver.findElement(By.cssSelector("#loginstatus")).getText();
        assertEquals("Welcome, inno!", authorizeText);

    }

    @Test
    public void renameButton() {
        driver.get(URLWork.URLButton);
        driver.findElement(By.cssSelector("#newButtonName")).sendKeys("Привет");

        driver.findElement(By.cssSelector("#updatingButton")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        String newNameButton = driver.findElement(By.cssSelector("#updatingButton")).getText();
        assertEquals("Привет", newNameButton);
    }

    @Test
    public void dataTypes() {
        driver.get(URLWork.URLForms);

        driver.findElement(By.cssSelector("input[name=first-name]")).sendKeys("Michael");
        driver.findElement(By.cssSelector("input[name=last-name]")).sendKeys("Jordan");
        driver.findElement(By.cssSelector("input[name=address]")).sendKeys("West Madison Street");
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys("Chicago");
        driver.findElement(By.cssSelector("input[name=country]")).sendKeys("USA");
        driver.findElement(By.cssSelector("input[name=job-position]")).sendKeys("shooting guard");
        driver.findElement(By.cssSelector("input[name=company]")).sendKeys("Chicago Bulls");

        driver.findElement(By.cssSelector("button[type=submit]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        String colorBackAFalseEmail = driver.findElement(By.cssSelector("#e-mail")).getCssValue("background-color");
        String colorBackFalseZipCode = driver.findElement(By.cssSelector("#zip-code")).getCssValue("background-color");
        String colorBackFalsePhone = driver.findElement(By.cssSelector("#phone")).getCssValue("background-color");

        assertEquals(URLWork.colorBackFalse, colorBackAFalseEmail);
        assertEquals(URLWork.colorBackFalse, colorBackFalseZipCode);
        assertEquals(URLWork.colorBackFalse, colorBackFalsePhone);

        String colorBackTrueFirstName = driver.findElement(By.cssSelector("#first-name")).getCssValue("background-color");
        String colorBackTrueLastName = driver.findElement(By.cssSelector("#last-name")).getCssValue("background-color");
        String colorBackTrueAddress = driver.findElement(By.cssSelector("#address")).getCssValue("background-color");
        String colorBackTrueCity = driver.findElement(By.cssSelector("#city")).getCssValue("background-color");
        String colorBackTrueCountry = driver.findElement(By.cssSelector("#country")).getCssValue("background-color");
        String colorBackTrueJob = driver.findElement(By.cssSelector("#job-position")).getCssValue("background-color");
        String colorBackTrueComp = driver.findElement(By.cssSelector("#job-position")).getCssValue("background-color");

        assertEquals(URLWork.colorBackTrue, colorBackTrueFirstName);
        assertEquals(URLWork.colorBackTrue, colorBackTrueLastName);
        assertEquals(URLWork.colorBackTrue, colorBackTrueAddress);
        assertEquals(URLWork.colorBackTrue, colorBackTrueCity);
        assertEquals(URLWork.colorBackTrue, colorBackTrueCountry);
        assertEquals(URLWork.colorBackTrue, colorBackTrueJob);
        assertEquals(URLWork.colorBackTrue, colorBackTrueComp);
    }

    @Test
    public void imageAttribute() {
        driver.get(URLWork.URLImage);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String thirdImageAttribute = driver.findElement(By.cssSelector("#award")).getAttribute("src");
        System.out.println(thirdImageAttribute);

        assertEquals(thirdImageAttribute.endsWith("award.png"), true);
    }

}
