package DataManager;

import org.apache.poi.ss.usermodel.*;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelFileManager {
    String excelFilePath;
    FileInputStream file;
    Sheet sheet;
    public ExcelFileManager(String excelFile) {
        this.excelFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/"+ excelFile;
        try {
            file = new FileInputStream(excelFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    public ExcelFileManager(String excelFile,String sheetName) {
        this(excelFile);
        this.switchToSheet(sheetName);
    }
    public ExcelFileManager(String excelFile,int sheetIndex) {
        this(excelFile);
        this.switchToSheet(sheetIndex);
    }
    public ExcelFileManager switchToSheet(Object sheetNameOrInx){
        if (sheetNameOrInx instanceof String){
            try (Workbook workbooks = WorkbookFactory.create(file)){
                sheet = workbooks.getSheet(((String) sheetNameOrInx));
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage() +" --- " + "Cannot switch to sheet with name ["+sheetNameOrInx+"]");
            }
        } else if (sheetNameOrInx instanceof Integer) {
            try (Workbook workbooks = WorkbookFactory.create(new File(excelFilePath))){
                sheet = workbooks.getSheetAt((Integer) sheetNameOrInx);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage() +" --- " + "Cannot switch to sheet with index ["+sheetNameOrInx+"]");
            }
        }
        return this;
    }

    public List<Object> getData(String dataOfWhatTC){
        List<Object> data = new ArrayList<>();

        //first, switching to the sheet you want
        Iterator<Row> rowsIterator = sheet.iterator();
        //second, getting the wanted row by iterator overs all rows
        //and compare if the cell value at column index zero ie equals to dataOfWhatTC
        Row wantedRow = null;
        int i = 0;
        while (rowsIterator.hasNext()){
            Cell cell = rowsIterator.next().getCell(i);
            if (cell.getStringCellValue().equalsIgnoreCase(dataOfWhatTC)){
                wantedRow = cell.getRow();
                break;
            }
        }
        assert wantedRow != null;
        //then get all the cells values of that row and add them to a list
        Iterator<Cell> cellIterator = wantedRow.cellIterator();
        cellIterator.next();
        while(cellIterator.hasNext()){
            Cell cel = cellIterator.next();
            switch (cel.getCellType()){
                case STRING -> data.add(cel.getStringCellValue());
                case NUMERIC ->  data.add(cel.getNumericCellValue());
                default -> {
                    Assert.fail("non matching cell value, it should be string or numeric");
                }
            }
        }
        return data;
    }
}
