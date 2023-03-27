package org.frames;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Objects;

public class Frames {

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
        driver.get("https://the-internet.herokuapp.com/nested_frames");
    }

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {"frame-top","frame-left", "LEFT"},
                {"frame-top","frame-middle", "MIDDLE"},
                {"frame-top","frame-right", "RIGHT"},
                {"", "frame-bottom", "BOTTOM"},
        };
    }

    @Test(dataProvider = "test-data")
    public void frameTest(String mainFrame, String ordinaryFrame, String frameText) {

        if (!Objects.equals(mainFrame, "")) {
            driver.switchTo()
                    .frame(mainFrame)
                    .switchTo()
                    .frame(ordinaryFrame);
        }
        else driver.switchTo().frame(ordinaryFrame);

        String text = driver.findElement(By.xpath("/html[1]/body[1]")).getText();

        Assert.assertEquals(text,frameText);
    }
}