package com.loancalculator.testCases;

import com.loancalculator.base.BaseClass;
import com.loancalculator.pageobjects.LoanCalculatorModal;
import com.loancalculator.pageobjects.PersonalDetailsPage;
import com.loancalculator.utilities.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TC01LoanCalculator extends BaseClass {

    @Test
    public void loanCalculator() throws IOException, UnsupportedFlavorException {
        LoanCalculatorModal l = new LoanCalculatorModal(driver);
        PersonalDetailsPage p = new PersonalDetailsPage(driver);
        p.setBtnCalculator();
        Log.info("Calculator Modal opened.");

        l.setSliderLoanAmount(driver, 30, 0);
        Log.info("Loan Amount Set");

        l.setSliderLoanPeriod(driver, 3, 0);
        Log.info("Loan Period Set.");
        Log.info("Estimated Monthly Cost : " + l.getMonthlyCost());

        String loanAmt = l.getTxtBoxLoanAmount();
        l.btnApplyNow();

        screenShot("TC01LoanCalculator");
        Assert.assertEquals(loanAmt, p.getSavedLoanAmt());
        Log.info("Loan Amount is saved.");
    }
}

