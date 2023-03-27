package org.downloadfiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GenerateTextPage {
    WebDriver driver;

    public GenerateTextPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitTillPageIsLoaded();
    }

    @FindBy(css = "select.form-control.type-select")
    WebElement typeSelectElement;

    @FindBy(id = "amount_generator")
    WebElement amountElement;

    @FindBy(css = "input.btn.form-submit")
    WebElement generateButton;

    @FindBy(id = "result_field")
    WebElement textArea;

    public GenerateTextPage setType(TypeEnum type){
        waitTillPageIsLoaded();
        new Select(typeSelectElement)
                .selectByValue(type.name());
        return new GenerateTextPage(driver);
    }

    public GenerateTextPage setAmount(int amount){
        amountElement.clear();
        amountElement.sendKeys(Integer.toString(amount));
        return new GenerateTextPage(driver);
    }

    public String getGeneratedText(){
        return textArea.getText();
    }

    public GenerateTextPage generateText(){
        generateButton.click();
        return new GenerateTextPage(driver);
    }

    private void waitTillPageIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.elementToBeClickable(typeSelectElement));
    }
}
