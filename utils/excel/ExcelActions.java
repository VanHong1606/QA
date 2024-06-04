package vn.vietinbank.utils.excel;

import net.serenitybdd.core.Serenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Assert;

import java.io.*;
import java.util.Arrays;
import java.util.Calendar;

import static vn.vietinbank.utils.Constants.*;

public class ExcelActions {

    private static final Logger logger = LogManager.getLogger(ExcelActions.class);

    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public ExcelActions(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] getData(String path, String sheetName, String testcaseID) {
        String[] value = null;
        try {
            //"G:\\Karate\\DemoKarate\\src\\test\\resource\\testcase.xlsx"
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);
            int num = sheet.getLastRowNum();
            logger.info("This is {}", num);
            for (int row = 1; row <= num; row++) {
                if (sheet.getRow(row).getCell(0).toString().equals(testcaseID)) {
                    value = new String[]{sheet.getRow(row).getCell(1).toString()};
                    logger.info("This is {}", (Object) value);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String excelToJsonConverter(String fileExcelData, String sheetName, String testcaseID) {
        String value = null;
        try {
            String json = Arrays.toString(getResponseInExcelFile(fileExcelData, sheetName, testcaseID));
            value = json.replace("[{", "{").replace("}]", "}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String[] getResponseInExcelFile(String fileExcelData, String sheetName, String testcaseID) {
        String[] value = null;
        try {
            String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\" + fileExcelData.split(">")[0];
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet(sheetName);
            int num = sheet.getLastRowNum();
            for (int row = 1; row <= num; row++) {
                if (sheet.getRow(row).getCell(0).toString().equals(testcaseID)) {
                    value = new String[]{sheet.getRow(row).getCell(1).toString()};
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void getDataFromExcel(String row, String sheetName, String excelPath) {
        Serenity.setSessionVariable(ROW).to(row);
        Serenity.setSessionVariable(SHEET_NAME).to(sheetName);
        Serenity.setSessionVariable(EXCEL_PATH).to(excelPath);
        ExcelActions excelReader = new ExcelActions(excelPath);
        Assert.assertTrue(excelReader.isSheetExist(sheetName));
    }

    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) return 0;
        else {
            sheet = workbook.getSheetAt(index);
            return sheet.getLastRowNum() + 1;
        }

    }

    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0) return "";
            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1) return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) col_Num = i;
            }
            if (col_Num == -1) return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null) return "";
            cell = row.getCell(col_Num);

            if (cell == null) return "";
            logger.info(String.valueOf(cell.getCellType()));
            if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {

                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

                    logger.info(cellText);

                }

                return cellText;
            } else if (cell.getCellType() == CellType.BLANK) return "";
            else return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {

            e.printStackTrace();
            return String.format("row %s or column %s does not exist in xls", rowNum, colName);
        }
    }

    // returns the data from a cell
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0) return "";

            int index = workbook.getSheetIndex(sheetName);

            if (index == -1) return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null) return "";
            cell = row.getCell(colNum);
            if (cell == null) return "";

            if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
            else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

                    logger.info(cellText);

                }

                return cellText;
            } else if (cell.getCellType() == CellType.BLANK) return "";
            else return String.valueOf(cell.getBooleanCellValue());
        } catch (Exception e) {

            e.printStackTrace();
            return String.format("row %s or column %s does not exist  in xls", rowNum, colNum);
        }
    }

    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0) return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1) return false;

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                logger.info(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName)) colNum = i;
            }
            if (colNum == -1) return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null) row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null) cell = row.createCell(colNum);

            // cell style
            // CellStyle cs = workbook.createCellStyle();
            // cs.setWrapText(true);
            // cell.setCellStyle(cs);
            cell.setCellValue(data);

            fileOut = new FileOutputStream(path);

            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is created successfully else false
    public boolean addSheet(String sheetname) {

        FileOutputStream fileOut;
        try {
            workbook.createSheet(sheetname);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        logger.info("setCellData setCellData******************");
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0) return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1) return false;

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                logger.info(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)) colNum = i;
            }

            if (colNum == -1) return false;
            sheet.autoSizeColumn(colNum); // ashish
            row = sheet.getRow(rowNum - 1);
            if (row == null) row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null) cell = row.createCell(colNum);

            cell.setCellValue(data);
            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            // cell style for hyperlinks
            // by default hypelrinks are blue and underlined
            CellStyle hlink_style = workbook.createCellStyle();
            XSSFFont hlink_font = workbook.createFont();
            hlink_font.setUnderline(HSSFFont.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            // hlink_style.setWrapText(true);

            XSSFHyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
            link.setAddress(url);
            cell.setHyperlink(link);
            cell.setCellStyle(hlink_style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is removed successfully else false if sheet does
    // not exist
    public boolean removeSheet(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) return false;

        FileOutputStream fileOut;
        try {
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if column is created successfully
    public boolean addColumn(String sheetName, String colName) {
        logger.info("**************addColumn*********************");
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1) return false;

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            if (row == null) row = sheet.createRow(0);

            // cell = row.getCell();
            // if (cell == null)
            logger.info(String.valueOf(row.getLastCellNum()));
            if (row.getLastCellNum() == -1) cell = row.createCell(0);
            else cell = row.createCell(row.getLastCellNum());

            cell.setCellValue(colName);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    // removes a column and all the contents
    public boolean removeColumn(String sheetName, int colNum) {
        try {
            if (!isSheetExist(sheetName)) return false;
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            //HSSFCreationHelper createHelper = workbook.getCreationHelper();
            style.setFillPattern(FillPatternType.NO_FILL);

            for (int i = 0; i < getRowCount(sheetName); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    // find whether sheets exists
    public boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1) return false;
            else return true;
        } else return true;
    }

    // returns number of columns in a sheet
    public int getColumnCount(String sheetName) {
        // check if sheet exists
        if (!isSheetExist(sheetName)) return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if (row == null) return -1;

        return row.getLastCellNum();

    }

    public int getCellRowNum(String sheetName, String colName, String cellValue) {

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }
        return -1;

    }

    /**
     * Read all rows and columns and return 2D Object array: can be used with testNG dataprovider.
     *
     * @param sheetName
     * @return
     */
    public Object[][] getSheetData(String sheetName) {
        Workbook book;
        Sheet sheet = null;

        Object data[][] = null;
        try {
            FileInputStream ip = new FileInputStream(path);
            book = WorkbookFactory.create(ip);
            sheet = book.getSheet(sheetName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
            }
        }

        return data;
    }

    // String sheetName, String testCaseName,String keyword ,String URL,String
    // message
    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {
        logger.info("ADDING addHyperLink******************");

        url = url.replace('\\', '/');
        if (!isSheetExist(sheetName)) return false;

        sheet = workbook.getSheet(sheetName);

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
                logger.info("**caught {}", i + index);
                setCellData(sheetName, screenShotColName, i + index, message, url);
                break;
            }
        }

        return true;
    }

}
