package thich.thong.lac.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.io.*;

public class MSExcelReader
{

    private HSSFWorkbook _workBook;
    private String       _filePath;

    public MSExcelReader(String path)
    {
        _filePath = path;
        open();
    }

    public void open()
    {
        File file = new File(_filePath);
        if (file.canRead())
        {
            FileInputStream streamIn = null;
            try
            {
                streamIn = new FileInputStream(file);
                _workBook = new HSSFWorkbook(streamIn);
                streamIn.close();
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void save() throws IOException
    {
        FileOutputStream streamOut = new FileOutputStream(_filePath);
        _workBook.write(streamOut);
        streamOut.flush();
        streamOut.close();
    }

    public void saveAs(String path) throws IOException
    {
        FileOutputStream streamOut = new FileOutputStream(path);
        _workBook.write(streamOut);
        streamOut.flush();
        streamOut.close();
    }

    public HSSFSheet getSheet(String SheetName)
    {
        HSSFSheet sheet = _workBook.getSheet(SheetName);

        if (!isSheet(SheetName))
        {
            sheet = _workBook.createSheet(SheetName);
        }
        return sheet;
    }

    public boolean isSheet(String SheetName)
    {
        return _workBook.getSheetIndex(SheetName) >= 0;
    }

    public void addSheet(String SheetName)
    {
        if (!isSheet(SheetName))
        {
            _workBook.createSheet(SheetName);
        }
    }

    // 2. Remove a sheet
    public void removeSheet(int SheetIndex)
    {
        _workBook.removeSheetAt(SheetIndex);
    }

    public void removeSheet(String SheetName)
    {
        int index = _workBook.getSheetIndex(SheetName);
        removeSheet(index);
    }

    // Column

    public void addColumn(String SheetName, String ColName)
    {
        HSSFSheet sheet = getSheet(SheetName);
        addColumn(sheet, ColName);
    }

    public void addColumn(HSSFSheet sheet, String ColName)
    {

        HSSFCellStyle style = _workBook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFRow row = sheet.getRow(0);
        if (row == null)
            row = sheet.createRow(0);

        HSSFCell cell;
        if (row.getLastCellNum() == -1)
            cell = row.createCell(0);
        else
            cell = row.createCell((int) row.getLastCellNum());
        cell.setCellValue(ColName);
        cell.setCellStyle(style);
    }

    public void removeColumn(String SheetName, int colNum)
    {
        HSSFSheet sheet    = getSheet(SheetName);
        int       rowCount = sheet.getLastRowNum() + 1;

        HSSFRow row;
        for (int i = 0; i < rowCount; i++)
        {
            row = sheet.getRow(i);
            if (row != null)
            {
                HSSFCell cell = row.getCell(colNum);
                if (cell != null)
                {
                    row.removeCell(cell);
                }
            }
        }
    }

    public void removeColumn(String SheetName, String colName)
    {
        HSSFSheet sheet  = getSheet(SheetName);
        int       colNum = convertColNameToColNum(sheet, colName);
        removeColumn(SheetName, colNum);
    }

    public int convertColNameToColNum(HSSFSheet sheet, String colName)
    {

        HSSFRow row           = sheet.getRow(0);
        int     cellRowNumber = row.getLastCellNum();
        int     colNum        = -1;

        for (int i = 0; i < cellRowNumber; i++)
        {
            if (row.getCell(i).getStringCellValue().trim().equals(colName))
            {
                colNum = i;
            }
        }
        return (colNum + 1);
    }

    public void setCell(HSSFSheet sheet, int rowIndex, int colIndex, String value)
    {
        HSSFRow  row  = getRow(sheet, rowIndex);
        HSSFCell cell = getCell(row, colIndex);
        cell.setCellValue(value);
        sheet.autoSizeColumn(rowIndex);
    }

    public void setCellWithColor(HSSFSheet sheet, int rowIndex, int colIndex, String value)
    {
        HSSFRow  row  = getRow(sheet, rowIndex);
        HSSFCell cell = getCell(row, colIndex);
        cell.setCellValue(value);

        HSSFCellStyle style = _workBook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.RED.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        sheet.autoSizeColumn(rowIndex);
    }

    public HSSFRow getRow(HSSFSheet sheet, int rowIndex)
    {
        HSSFRow row = sheet.getRow(rowIndex - 1);

        if (row == null)
        {
            row = sheet.createRow(rowIndex - 1);
        }

        return row;
    }

    public int getRows(HSSFSheet sheet)
    {
        return sheet.getLastRowNum() + 1;
    }

    public int getRows(String sheetName)
    {
        HSSFSheet sheet = getSheet(sheetName);
        return sheet.getLastRowNum() + 1;
    }

    public HSSFCell getCell(HSSFRow row, int colIndex)
    {
        HSSFCell cell = row.getCell(colIndex - 1);
        if (cell == null)
        {
            cell = row.createCell(colIndex - 1);
        }
        return cell;
    }

    public void setCell(String sheetName, int rowIndex, int colIndex, String value)
    {
        HSSFSheet sheet = getSheet(sheetName);
        setCell(sheet, rowIndex, colIndex, value);
    }

    public void setCell(String sheetName, String colName, int rowIndex, String value)
    {
        HSSFSheet sheet    = getSheet(sheetName);
        int       colIndex = convertColNameToColNum(sheet, colName);
        setCell(sheet, rowIndex, colIndex, value);
    }

    public void setCellWithColor(String sheetName, String colName, int rowIndex, String value)
    {
        HSSFSheet sheet    = getSheet(sheetName);
        int       colIndex = convertColNameToColNum(sheet, colName);
        setCellWithColor(sheet, rowIndex, colIndex, value);

    }

    public String getCellValue(HSSFSheet sheet, int rowIndex, int colIndex)
    {

        HSSFRow  row  = getRow(sheet, rowIndex);
        HSSFCell cell = getCell(row, colIndex);

        FormulaEvaluator evaluator = _workBook.getCreationHelper().createFormulaEvaluator();

        if (cell != null)
        {

            switch (evaluator.evaluateInCell(cell).getCellType())
            {

                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();

                case Cell.CELL_TYPE_NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());

                case Cell.CELL_TYPE_BLANK:
                    return "";

                case Cell.CELL_TYPE_BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());

                case Cell.CELL_TYPE_ERROR:
                    return String.valueOf(cell.getErrorCellValue());

                case Cell.CELL_TYPE_FORMULA:
                    return String.valueOf(cell.getCellFormula());
            }
        }
        return null;
    }

    public String getCellValue(String sheetName, int rowIndex, int colIndex)
    {

        HSSFSheet sheet = getSheet(sheetName);
        HSSFRow   row   = getRow(sheet, rowIndex);
        HSSFCell  cell  = getCell(row, colIndex);

        FormulaEvaluator evaluator = _workBook.getCreationHelper().createFormulaEvaluator();

        if (cell != null)
        {

            switch (evaluator.evaluateInCell(cell).getCellType())
            {

                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();

                case Cell.CELL_TYPE_NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());

                case Cell.CELL_TYPE_BLANK:
                    return "";

                case Cell.CELL_TYPE_BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());

                case Cell.CELL_TYPE_ERROR:
                    return String.valueOf(cell.getErrorCellValue());

                case Cell.CELL_TYPE_FORMULA:
                    return String.valueOf(cell.getCellFormula());
            }
        }
        return null;
    }

    public String getCellValue(String sheetName, int rowIndex, String colName)
    {

        HSSFSheet sheet    = getSheet(sheetName);
        int       colIndex = convertColNameToColNum(sheet, colName);
        HSSFRow   row      = getRow(sheet, rowIndex);
        HSSFCell  cell     = getCell(row, colIndex);

        FormulaEvaluator evaluator = _workBook.getCreationHelper().createFormulaEvaluator();

        if (cell != null)
        {

            switch (evaluator.evaluateInCell(cell).getCellType())
            {

                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();

                case Cell.CELL_TYPE_NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());

                case Cell.CELL_TYPE_BLANK:
                    return "";

                case Cell.CELL_TYPE_BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());

                case Cell.CELL_TYPE_ERROR:
                    return String.valueOf(cell.getErrorCellValue());

                case Cell.CELL_TYPE_FORMULA:
                    return String.valueOf(cell.getCellFormula());
            }
        }
        return null;
    }

    public String getCellFormula(HSSFSheet sheet, int rowIndex, int colIndex)
    {

        HSSFRow  row  = getRow(sheet, rowIndex);
        HSSFCell cell = getCell(row, colIndex);

        return cell.getCellFormula();

    }

    public int getColumnIndex(HSSFSheet sheet, String columnName)
    {
        HSSFRow row    = sheet.getRow(0);
        int     colNum = row.getPhysicalNumberOfCells();

        // Find column index
        for (int inx = 0; inx < colNum; inx++)
        {
            HSSFCell cell = row.getCell(inx);
            if (cell != null)
            {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equalsIgnoreCase(columnName))
                {
                    return inx;
                }
            }
        }
        return -1;
    }

    public int getColumnIndex(String sheetName, String columnName)
    {
        HSSFSheet sheet  = getSheet(sheetName);
        HSSFRow   row    = sheet.getRow(0);
        int       colNum = row.getPhysicalNumberOfCells();

        // Find column index
        for (int inx = 0; inx < colNum; inx++)
        {
            HSSFCell cell = row.getCell(inx);
            if (cell != null)
            {
                if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equalsIgnoreCase(columnName))
                {
                    return inx;
                }
            }
        }
        return -1;
    }

}
