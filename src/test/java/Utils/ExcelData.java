package Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelData {
    public String[][] getData(String fileName, String sheetName){
        String[][] data = null;
        try
        {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRows-1][noOfCols];
            for(int i =1; i<noOfRows;i++){
                for(int j=0;j<noOfCols;j++){
                    row = sh.getRow(i);
                    cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                data[i - 1][j] = cell.getRichStringCellValue().getString();
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    data[i - 1][j] = String.valueOf(cell.getDateCellValue());
                                } else {
                                    data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                data[i - 1][j] = cell.getCellFormula();
                                break;
                            case BLANK:
                                System.out.println("Please check the Sheet for invalid values.");
                                break;
                            default:
                                System.out.println("Data provider unknown error.");
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("The exception is: " +e.getMessage());
        }
        return data;
    }
}
