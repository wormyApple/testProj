package web.tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.methods.FileMethods;
import web.methods.PagesSwitcher;
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
        List<String> data = FileMethods.readFile(sh1);

        PagesSwitcher pagesSwitcher = new PagesSwitcher();
        pagesSwitcher.SwitchPageAndSetFilters(data,driver,wait);

        ResultPage resultPage = new ResultPage(driver, wait);
        FileMethods.writeInFile("./src/data/outputData/output.xls", resultPage.countOfRows(), driver, wait);
    }
}
