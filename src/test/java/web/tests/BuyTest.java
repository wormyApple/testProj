package web.tests;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.BuyPage;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BuyTest {

    public List<String> readFile(XSSFSheet sheet) throws Exception {
        List<String> listOfData = new ArrayList<String>();
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                CellType cellType = cell.getCellTypeEnum();

                switch (cellType) {
                    case NUMERIC:
                        listOfData.add(Integer.toString((int) cell.getNumericCellValue()));
                        break;
                    case STRING:
                        listOfData.add(cell.getStringCellValue());
                        break;
                    default:
                        throw new Exception("Invalid document data");
                }

            }

        }
        return listOfData;
    }

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
        List<String> data = readFile(sh1);
        BuyPage buyPage = new BuyPage(driver,wait);
        buyPage.selectParametersInPage(data);
    }
}
