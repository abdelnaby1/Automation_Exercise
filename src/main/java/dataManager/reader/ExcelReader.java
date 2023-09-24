package dataManager.reader;

import org.apache.poi.ss.usermodel.*;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.fail;

public class ExcelReader {
    private FileInputStream spreadsheet;
    private Sheet currentSheet;
    private final Map<String, Integer> columns;

    public ExcelReader(String excelFile) {
        String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/" + excelFile;
        try {
            spreadsheet = new FileInputStream(excelFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        columns = new HashMap<String, Integer>();
    }

    public void switchToSheet(String name) {
        try (Workbook workbooks = WorkbookFactory.create(spreadsheet)) {
            currentSheet = workbooks.getSheet(name);
            currentSheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public String getCellData(String column, int row) {
        try {
            Row dataRow = currentSheet.getRow(row - 1);
            return getCellDataAsString(dataRow.getCell(columns.get(column)));
        } catch (NullPointerException e) {
            fail("Can't find the column name [" + column + "]........" + e.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }

    public String getCellData(String column) {
        return getCellData(column, 2);
    }

    private String getCellDataAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            default -> "";
        };
    }
}
