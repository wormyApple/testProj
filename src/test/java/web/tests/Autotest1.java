package web.tests;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.methods.fileMethods;
import web.pages.BuyPage;
import web.pages.ResultPage;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Autotest1 {

    @Test
    public void test1() throws Exception {
        System.setProperty("webdriver.chrome.driver", "\\bin\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://voronezh.cian.ru/");
        File src = new File("./src/data/inputData/input.xlsx");

        FileInputStream fis = new FileInputStream(src);

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sh1 = wb.getSheetAt(0);
        List<String> data = fileMethods.readFile(sh1);

        BuyPage buyPage = new BuyPage(driver, wait);
        buyPage.selectParametersInPage(data);

        ResultPage resultPage = new ResultPage(driver,wait);
        fileMethods.writeInFile("./src/data/outputData/output.xls",resultPage.countOfRows(),driver,wait);
    }
}
