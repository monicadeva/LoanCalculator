package com.loancalculator.testCases;

import com.loancalculator.base.BaseClass;
import com.loancalculator.pageobjects.LoanCalculatorModal;
import com.loancalculator.pageobjects.PersonalDetailsPage;
import com.loancalculator.utilities.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TC02LoanCalculator extends BaseClass {

    @Test
    public void loanCalculator() throws IOException, UnsupportedFlavorException {
        LoanCalculatorModal l = new LoanCalculatorModal(driver);
        PersonalDetailsPage p = new PersonalDetailsPage(driver);
        p.setBtnCalculator();
        Log.info("Calculator Modal opened.");

        l.setTxtBoxLoanAmount(loanAmt);
        Log.info("Loan Amount entered.");

        l.setTxtBoxLoanPeriod(loanPeriod);
        Log.info("Loan Period entered.");
        Log.info("Estimated Monthly Cost calculated.");

        Assert.assertEquals(loanAmt, l.getTxtBoxLoanAmount());
        Assert.assertEquals(loanPeriod, l.getTxtBoxLoanPeriod());
        Assert.assertEquals("224,39 SEK", l.getMonthlyCost());

        l.btnApplyNow();
        screenShot("TC02LoanCalculator");
        Assert.assertEquals(loanAmt, p.getSavedLoanAmt());
        Log.info("Loan Amount is saved.");
    }
}
