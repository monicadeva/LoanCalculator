package com.loancalculator.pageobjects;

import com.loancalculator.base.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class LoanCalculatorModal extends BaseClass {

    public LoanCalculatorModal(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='calculator-amount']")
    WebElement txtBoxLoanAmount;

    @FindBy(xpath = "//div[@id='header-calculator-amount']//div[@class='vue-slider-dot-handle']")
    WebElement sliderLoanAmount;

    @FindBy(xpath = "//input[@name='calculator-period']")
    WebElement txtBoxLoanPeriod;

    @FindBy(xpath = "//div[@id='header-calculator-period']//div[@class='vue-slider-dot-handle']")
    WebElement sliderLoanPeriod;

    @FindBy(xpath = "//p[@class='bb-labeled-value__value']")
    WebElement labelMonthlyCost;

    @FindBy(xpath = "//span[@class='bb-button__label']")
    WebElement btnApplyNow;

    public void setTxtBoxLoanAmount(String loanAmt) {
        String txt = txtBoxLoanAmount.getAttribute("calculator-amount");
        for (int i = 0; i < 5; i++)
            txtBoxLoanAmount.sendKeys(Keys.BACK_SPACE);
        txtBoxLoanAmount.sendKeys(loanAmt);
    }

    public String getTxtBoxLoanAmount() throws IOException, UnsupportedFlavorException {
        String a = Keys.chord(Keys.CONTROL, "a");
        txtBoxLoanAmount.sendKeys(a);
        String c = Keys.chord(Keys.CONTROL, "c");
        txtBoxLoanAmount.sendKeys((c));
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public String getTxtBoxLoanPeriod() throws IOException, UnsupportedFlavorException {
        String a = Keys.chord(Keys.CONTROL, "a");
        txtBoxLoanPeriod.sendKeys(a);
        String c = Keys.chord(Keys.CONTROL, "c");
        txtBoxLoanPeriod.sendKeys((c));
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public void setSliderLoanAmount(WebDriver driver, int x, int y) {
        explicitWait(sliderLoanAmount, 10);
        Actions a = new Actions(BaseClass.driver);
        a.clickAndHold(sliderLoanAmount)
                .moveByOffset(x, y)
                .release().perform();
        implicitWait(10);
    }

    public void setTxtBoxLoanPeriod(String loanPeriod) {
        repeatKeyStroke(txtBoxLoanPeriod, 3, Keys.BACK_SPACE);
        txtBoxLoanPeriod.sendKeys(loanPeriod);
        repeatKeyStroke(txtBoxLoanPeriod, 2, Keys.TAB);
        explicitWaitAttribute(labelMonthlyCost, 50);

    }

    public void setSliderLoanPeriod(WebDriver driver, int x, int y) {
        explicitWait(sliderLoanPeriod, 10);
        Actions a = new Actions(BaseClass.driver);
        a.clickAndHold(sliderLoanPeriod)
                .moveByOffset(x, y)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .release().perform();
        explicitWait(labelMonthlyCost, 50);
    }

    public String getMonthlyCost() {
        explicitWaitAttribute(labelMonthlyCost, 50);
        return labelMonthlyCost.getText();
    }

    public void btnApplyNow() {
        btnApplyNow.click();
    }

    public void setSliderLoanAmount(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'left: 30%;')", sliderLoanAmount);
    }
}
