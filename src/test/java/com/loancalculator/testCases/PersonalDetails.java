package com.loancalculator.testCases;

import com.loancalculator.base.BaseClass;
import com.loancalculator.pageobjects.PersonalDetailsPage;
import com.loancalculator.utilities.Log;
import org.testng.annotations.Test;

import java.io.IOException;

public class PersonalDetails extends BaseClass {
    @Test
    public void personalDetails() throws IOException {
        PersonalDetailsPage pd = new PersonalDetailsPage(driver);
        pd.setTxtBoxFirstName("Monica");
        pd.setTxtBoxSurName("Dev");
        pd.setTxtBoxSSN("940425-9138");
        pd.setTxtBoxEmail("monica@gmail.com");
        pd.setTxtBoxMobileNumber("784329192");
        pd.setListBoxLoanPurpose(2);
        pd.setChkBoxConsent();
        pd.setBtnCalculator();
        Log.info("All the page basic elements available");
        screenShot("PersonalDetails");
    }
}
