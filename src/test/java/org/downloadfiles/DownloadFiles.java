package org.downloadfiles;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;

public class DownloadFiles {

    String generateUrl = "https://www.webfx.com/tools/lorem-ipsum-generator/";
    String downloadUrl = "http://demo.seleniumeasy.com/generate-file-to-download-demo.html";

    public static WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = initDriver();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeTest() {
        MyFileUtils.createDownloadDirectory();
    }

    @DataProvider(name = "data-amount-type")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {10, TypeEnum.sentences},
                {2, TypeEnum.paragraphs},
                {70, TypeEnum.words},
        };
    }

    @Test(dataProvider = "data-amount-type")
    public void downloadFilePO(int amount, TypeEnum type) throws IOException {
        goToUrl(generateUrl);
        String generatedText = new GenerateTextPage(driver)
                .setType(type)
                .setAmount(amount)
                .generateText()
                .getGeneratedText();
        goToUrl(downloadUrl);
        File file = new DownloadPage(driver)
                .insertText(generatedText)
                .generateFile()
                .downloadFile();
        Assert.assertEquals(FileUtils.readFileToString(file, Charset.defaultCharset()), generatedText);

    }


    private void goToUrl(String url) {
        driver.get(url);
    }

    private WebDriver initDriver() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory",
                new File(MyFileUtils.DirectoryFor.DOWNLOAD.getDirName()).getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}