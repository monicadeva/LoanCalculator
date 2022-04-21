package com.loancalculator.base;

import com.loancalculator.utilities.Log;
import com.loancalculator.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class BaseClass {

    ReadConfig readConfig = new ReadConfig();

    public String baseURL = readConfig.getApplicationURL();
    public String loanAmt = readConfig.getLoanAmount();
    public String loanPeriod = readConfig.getLoanPeriod();
    public static WebDriver driver;
    public static String projectPath = System.getProperty("user.dir");

    @Parameters("browser")
    @BeforeClass
    public void launchApp(String browserName) {
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
            driver = new ChromeDriver();
            ChromeOptions opt = new ChromeOptions();
            opt.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        }

        if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
            driver = new FirefoxDriver();
        }

        if (browserName.equals("edge")) {
            System.setProperty("webdriver.edge.driver", readConfig.getEdgePath());
            driver = new EdgeDriver();
            EdgeOptions opt = new EdgeOptions();
            opt.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
            opt.setExperimentalOption("useAutomationExtension", false);
            opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(baseURL);
    }

    public void implicitWait(int timeOut) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
    }

    public void explicitWait(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void explicitWaitAttribute(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until((ExpectedCondition<Boolean>) driver1 -> element.getText().length() > 1);
    }

    public String screenShot(String filename) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dst = new File(projectPath + "/Screenshots/" + filename + ".png");
        FileUtils.copyFile(src, dst);
        Log.info("Screenshot Taken and placed in following path:" + dst.getAbsolutePath());
        return filename;
    }

    public void scrollByVisibilityOfElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public void repeatKeyStroke(WebElement element, int repeat, Keys key) {
        for (int i = 0; i < repeat; i++) {
            element.sendKeys(key);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

