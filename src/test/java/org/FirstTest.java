package org;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstTest {

    @Test
    public void test(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys("selenium.dev");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"btnK\"]")));
        driver.findElement(By.xpath("//input[@name=\"btnK\"]")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//a[@href=\"https://www.selenium.dev/\"]")).click();
        WebElement title = driver.findElement(By.xpath("//h1[@class=\"display-1 mt-0 mt-md-5 pb-1\"]"));
        String actualText = title.getText();
        Assert.assertEquals(actualText,"Selenium automates browsers. That's it!");
        driver.quit();
    }
}