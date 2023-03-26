package org.alerts;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class jsAlerts {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass(){
        if (driver != null){
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get("http://localhost:7080/javascript_alerts");
    }

    @Test
    public void jsAlertTest1_1(){

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String script = "jsConfirm()";
        javascriptExecutor.executeScript(script);

        Alert alert = driver.switchTo().alert();
        alert.accept();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You clicked: Ok");
    }

    @Test
    public void jsAlertTest1_2() {

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String script = "jsConfirm()";
        javascriptExecutor.executeScript(script);

        Alert alert = driver.switchTo().alert();

        alert.dismiss();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You clicked: Cancel");
    }

    @Test
    public void jsAlertTest1_3(){

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String script = "jsPrompt()";
        javascriptExecutor.executeScript(script);

        Alert alert = driver.switchTo().alert();

        alert.sendKeys("Hello!");

        alert.accept();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You entered: Hello!");
    }

    @Test
    public void jsAlertTest1_4(){

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String script = "jsPrompt()";
        javascriptExecutor.executeScript(script);

        Alert alert = driver.switchTo().alert();

        alert.accept();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You entered:");

    }

    @Test
    public void jsAlertTest1_5() {

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String script = "jsPrompt()";
        javascriptExecutor.executeScript(script);

        Alert alert = driver.switchTo().alert();

        alert.sendKeys("Bye!");

        alert.dismiss();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You entered: null");

    }

    @Test
    public void jsAlertTest1_6(){

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String script = "jsPrompt()";
        javascriptExecutor.executeScript(script);

        Alert alert = driver.switchTo().alert();

        alert.dismiss();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You entered: null");
    }

    @Test
    public void jsAlertTest2_1(){

        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", button);

        Alert alert = driver.switchTo().alert();

        alert.accept();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You clicked: Ok");
    }

    @Test
    public void jsAlertTest2_2() {

        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", button);

        Alert alert = driver.switchTo().alert();

        alert.dismiss();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You clicked: Cancel");
    }

    @Test
    public void jsAlertTest2_3(){

        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", button);

        Alert alert = driver.switchTo().alert();

        alert.sendKeys("Hello!");

        alert.accept();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You entered: Hello!");
    }

    @Test
    public void jsAlertTest2_4(){

        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", button);

        Alert alert = driver.switchTo().alert();

        alert.accept();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You entered:");

    }

    @Test
    public void jsAlertTest2_5() {

        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", button);

        Alert alert = driver.switchTo().alert();

        alert.sendKeys("Bye!");

        alert.dismiss();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You entered: null");

    }

    @Test
    public void jsAlertTest2_6(){

        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", button);

        Alert alert = driver.switchTo().alert();

        alert.dismiss();

        String expectedText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(expectedText, "You entered: null");
    }
}