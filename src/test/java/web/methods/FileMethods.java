package web.methods;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.pages.ResultPage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileMethods {
    public static List<String> readFile(XSSFSheet sheet) throws Exception {
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

    public static void writeInFile(String pathFoOutputFile,int countOfRows, WebDriver driver, WebDriverWait wait) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Результат");

        List<DataModel> dataList = fillData(countOfRows,driver,wait);

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Основная информация");
        row.createCell(1).setCellValue("Адрес");
        row.createCell(2).setCellValue("Цена");
        row.createCell(3).setCellValue("Описание");

        for (DataModel dataModel : dataList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }

        try (FileOutputStream out = new FileOutputStream(new File(pathFoOutputFile))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");

    }

    private static void createSheetHeader(HSSFSheet sheet, int rowNum, DataModel dataModel) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.getBasicInfo());
        row.createCell(1).setCellValue(dataModel.getAddress());
        row.createCell(2).setCellValue(dataModel.getPrice());
        row.createCell(3).setCellValue(dataModel.getDescription());
    }

    private static List<DataModel> fillData(int countOfRows, WebDriver driver, WebDriverWait wait) {
        ResultPage resultPage= new ResultPage(driver, wait);
        List<DataModel> dataModels = new ArrayList<>();
        int i = 0;
        while (i<countOfRows) {
            dataModels.add(resultPage.getDataOfAnObject(i));
            i++;
        }
        return dataModels;
    }
}
