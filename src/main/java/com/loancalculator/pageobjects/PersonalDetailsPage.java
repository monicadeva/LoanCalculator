package com.loancalculator.pageobjects;

import com.loancalculator.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PersonalDetailsPage extends BaseClass {

    public PersonalDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstNameField")
    WebElement txtBoxFirstName;

    @FindBy(id = "surnameField")
    WebElement txtBoxSurName;

    @FindBy(id = "personalIdentityCodeField")
    WebElement txtBoxSSN;

    @FindBy(id = "emailField")
    WebElement txtBoxEmail;

    @FindBy(xpath = "//input[@name='phone']")
    WebElement txtBoxMobileNumber;

    @FindBy(id = "loanPurposeField")
    WebElement listBoxLoanPurpose;

    @FindBy(xpath = "//*[@class='bb-checkbox__text']")
    WebElement chkBoxConsent;

    @FindBy(id = "personalDetailsForm-submit")
    WebElement btnSubmit;

    @FindBy(xpath = "//div[@class='bb-edit-amount__amount']")
    WebElement btnCalculator;

    public void setTxtBoxFirstName(String fName) {
        txtBoxFirstName.sendKeys(fName);
    }

    public void setTxtBoxSurName(String sName) {
        txtBoxSurName.sendKeys(sName);
    }

    public void setTxtBoxSSN(String ssn) {
        txtBoxSSN.sendKeys(ssn);
    }

    public void setTxtBoxEmail(String mail) {
        txtBoxEmail.sendKeys(mail);
    }

    public void setTxtBoxMobileNumber(String mobileNumber) {
        txtBoxMobileNumber.sendKeys(mobileNumber);
    }

    public void setListBoxLoanPurpose(int i) {
        Select lp = new Select(listBoxLoanPurpose);
        lp.selectByIndex(i);
    }

    public void setChkBoxConsent() {
        scrollByVisibilityOfElement();
        chkBoxConsent.click();
    }

    public void clickSubmit() {
        btnSubmit.click();
    }

    public void setBtnCalculator() {
        btnCalculator.click();
    }

    public String getSavedLoanAmt() {
        return btnCalculator.getText();
    }
}


