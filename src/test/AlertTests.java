package org.ikol;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AlertTests {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
    @BeforeMethod
    public void beforeMethod() {
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
    }

    //Test1. Homework12. Alerts. Click for JS Confirm. Ok
    @Test
    public void clickForJSConfirmOk() {
        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        WebElement result =driver.findElement(By.id("result"));
        String resultText = result.getText();

        Assert.assertEquals(alertText, "I am a JS Confirm");
        Assert.assertEquals(resultText, "You clicked: Ok");
    }

    //Test2. Homework12. Alerts. Click for JS Confirm. Cansel
    @Test
    public void clickForJSConfirmCancel() {
        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.dismiss();
        WebElement result =driver.findElement(By.id("result"));
        String resultText = result.getText();

        Assert.assertEquals(alertText, "I am a JS Confirm");
        Assert.assertEquals(resultText, "You clicked: Cancel");
    }

    //Test3. Homework12. Alerts. Click for JS Prompt. Ok. Text
    @Test
    public void clickForJSPromptOkText() {
        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.sendKeys("123456 qwerty");
        alert.accept();
        WebElement result =driver.findElement(By.id("result"));
        String resultText = result.getText();

        Assert.assertEquals(alertText, "I am a JS prompt");
        Assert.assertEquals(resultText, "You entered: 123456 qwerty");
    }

    //Test4. Homework12. Alerts. Click for JS Prompt. Ok. Text. Empty
    @Test
    public void clickForJSPromptOkTextEmpty() {
        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        // alert.sendKeys("");
        alert.accept();
        WebElement result =driver.findElement(By.id("result"));
        String resultText = result.getText();

        Assert.assertEquals(alertText, "I am a JS prompt");
        Assert.assertEquals(resultText, "You entered:");
    }

    //Test5. Homework12. Alerts. Click for JS Prompt. Cancel. Text
    @Test
    public void clickForJSPromptCancelText() {
        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.sendKeys("123 qwerty 456");
        alert.dismiss();
        WebElement result =driver.findElement(By.id("result"));
        String resultText = result.getText();

        Assert.assertEquals(alertText, "I am a JS prompt");
        Assert.assertEquals(resultText, "You entered: null");
    }

    //Test6. Homework12. Alerts. Click for JS Prompt. Cancel. Text. Empty
    @Test
    public void clickForJSPromptCancelTextEmpty() {
        WebElement button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        button.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.dismiss();
        WebElement result =driver.findElement(By.id("result"));
        String resultText = result.getText();

        Assert.assertEquals(alertText, "I am a JS prompt");
        Assert.assertEquals(resultText, "You entered: null");
    }
}
