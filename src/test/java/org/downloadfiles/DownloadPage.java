package org.downloadfiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class DownloadPage {
    WebDriver driver;

    public DownloadPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "textbox")
    WebElement textArea;

    @FindBy(id = "create")
    WebElement generateButton;

    @FindBy(id = "link-to-download")
    WebElement downloadLink;

    public DownloadPage insertText(String text) {
        textArea.clear();
        textArea.sendKeys(text);
        return new DownloadPage(driver);
    }

    public DownloadPage generateFile() {
        generateButton.click();
        return new DownloadPage(driver);
    }

    public File downloadFile() {
        downloadLink.click();
        File file = new File("download", "easyinfo.txt");
        return MyFileUtils.waitTillFileDownloaded(file);
    }

}
