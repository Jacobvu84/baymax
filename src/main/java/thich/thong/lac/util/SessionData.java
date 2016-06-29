package thich.thong.lac.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import net.serenitybdd.core.Serenity;

/**
 * @Description Table data, Excel data utility for data table of cucumber
 *              feature file and CRUD from Excel File(.xls only) for using in
 *              everywhere of FW. WARNING: for more performance and easy to
 *              maintain data, please follow the rule: 1. All column data must
 *              have unique name 2. Data ranger in each sheet must be
 *              defined(Example : Set format to text) 3. Key File Name must be
 *              unique.
 * @author Vincent
 * @version: 20160314
 */
public class SessionData {

	// Name key contains excel file in session variable
	private static final String EXCEL_FILE_LIST = "#ExcelFiles#";
	private static final String TABLE_DATA_LIST = "#TableDatas#";

	// #Region# Session Data Variable : List<String>
	/**
	 * @Description Add new string for List<String> via Session Data
	 * @author Vincent
	 * @param dataKey
	 * @param value
	 */
	public static void addToListString(String dataKey, String value) {
		try {
			List<String> getlString = Serenity.sessionVariableCalled(dataKey);
			getlString.add(value);
			Serenity.setSessionVariable(dataKey).to(getlString);
		} catch (NullPointerException e) {
			System.out.println("***** WARNING ***** : Session Data does not exist: " + dataKey);
			List<String> getlString = new ArrayList<String>();
			getlString.add(value);
			Serenity.setSessionVariable(dataKey).to(getlString);
		}
	}

	/**
	 * @Description Clear all string for List<String> via Session Data
	 * @author Vincent
	 * @param dataKey
	 */
	public static void clearListString(String dataKey) {
		try {
			List<String> getlString = Serenity.sessionVariableCalled(dataKey);
			getlString.clear();
			Serenity.setSessionVariable(dataKey).to(getlString);
		} catch (NullPointerException e) {
			System.out.println("***** WARNING ***** : Session Data List String does not exist: " + dataKey);
		}
	}

	/**
	 * @Description Get the last added string for List<String> via Session Data
	 * @author Vincent
	 * @param dataKey
	 */
	public static String getListStringLastValue(String dataKey) {
		try {
			List<String> getlString = Serenity.sessionVariableCalled(dataKey);
			return getlString.get(getlString.size() - 1);
		} catch (NullPointerException e) {
			System.out.println("***** WARNING ***** : Session Data List String does not exist: " + dataKey);
			return null;
		}
	}

	/**
	 * @Description Get value string for List<String> via Session Data. Negative
	 *              number for get from the last.
	 * @author Vincent
	 * @param dataKey
	 * @param index
	 */
	public static String getListStringByIndex(String dataKey, int index) {
		try {
			List<String> getlString = Serenity.sessionVariableCalled(dataKey);
			if (index >= 0) {
				return getlString.get(index - 1);
			} else {
				System.out.println("***** INFO ***** : Session Data [" + dataKey + "] "
						+ System.getProperty("line.separator") + getlString);
				return getlString.get((getlString.size() - 1) + index);
			}
		} catch (NullPointerException e) {
			System.out.println("***** WARNING ***** : Session Data List String does not exist: " + dataKey);
			return null;
		}
	}

	// #EndRegion# Session Data Variable : List<String>
	// #Region# Report
	/**
	 * @Description Generate Table Data Report as String
	 * @author Vincent
	 * @param reportKey
	 * @return String
	 */
	public static String generateTbDataReport(String reportKey) {
		List<List<String>> tbReport = Serenity.sessionVariableCalled(reportKey);
		List<List<String>> tbReport1 = new ArrayList<List<String>>();
		for (List<String> temp : tbReport) {
			tbReport1.add(new ArrayList<String>(temp));
		}
		return generateTbDataReport(tbReport1);
	}

	/**
	 * @Description Clear data in Table Data Report
	 * @author Vincent
	 * @param reportKey
	 * @return String
	 */
	public static void clearTbDataReport(String reportKey) {
		try {
			List<List<String>> tbReport = Serenity.sessionVariableCalled(reportKey);
			tbReport.clear();
			Serenity.setSessionVariable(reportKey).to(tbReport);
		} catch (NullPointerException e) {
			System.out.println("***** WARNING ***** : Table Data Report does not exist: " + reportKey);
		}
	}

	/**
	 * @Description Add data row to Table Data Report
	 * @author Vincent
	 * @param reportKey
	 * @param rowData
	 */
	public static void addRowToTbDataReport(String reportKey, List<String> rowData) {
		List<List<String>> tbReport = new ArrayList<List<String>>();
		try {
			tbReport = Serenity.sessionVariableCalled(reportKey);
			tbReport.add(rowData);
			Serenity.setSessionVariable(reportKey).to(tbReport);
		} catch (NullPointerException e) {
			Serenity.setSessionVariable(reportKey).to(tbReport);
			tbReport = new ArrayList<List<String>>();
			tbReport.add(rowData);
			Serenity.setSessionVariable(reportKey).to(tbReport);
		}
	}

	/**
	 * @Description Generate table data view
	 * @author Vincent
	 * @param rawData
	 * @return
	 */
	private static String generateTbDataReport(List<List<String>> rawData) {
		// Get max length of each column
		List<Integer> columnLength = new ArrayList<Integer>();
		for (List<String> row : rawData) {
			// Set max length for each column
			for (int i = 0; i < row.size(); i++) {
				int length = 0;
				if (row.get(i) != null) {
					length = row.get(i).length();
				} else {
					length = 4;
				}

				if (columnLength.size() < i + 1) {// New column
					columnLength.add(length);
				} else {// existed column
					if (columnLength.get(i) < length) {
						columnLength.set(i, length);
					}
				}
			}
		}
		// Set table data as String
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		for (List<String> row : rawData) {
			String rowContent = "";
			// Set max length for each column
			for (int i = 0; i < row.size(); i++) {
				String cell = row.get(i);
				if (cell == null) {
					cell = "null";
				}
				int length = columnLength.get(i);
				if (i == 0) {
					row.set(i, "|" + padRight(cell, length) + " | ");
				} else {
					row.set(i, padRight(cell, length) + " | ");
				}
				rowContent += row.get(i);
			}
			sb.append(rowContent);
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	/**
	 * @author Vincent
	 * @param s
	 * @param n
	 * @return
	 */
	private static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	public static boolean isSameDecimalValue(String _actual, String _expected) {
		System.out.println("Actual: " + _actual + " Expected: " + _expected);
		int maxlength = 0;
		if (!_actual.contains(".")) {
			_actual += ".";
		}
		if (!_expected.contains(".")) {
			_expected += ".";
		}
		if (_actual.length() >= _expected.length()) {
			maxlength = _actual.length();
		} else {
			maxlength = _expected.length();
		}
		_actual = padRight(_actual, maxlength).replace(' ', '0');
		_expected = padRight(_expected, maxlength).replace(' ', '0');
		if (_actual.equals(_expected)) {
			return true;
		} else {
			return false;
		}
	}
	// #EndRegion# Report

	// Region#Data Table
	/**
	 * @Description Store data table (description in feature file) to Session
	 *              Data variable.
	 * @author Vincent
	 * @param _keyDataTableName
	 * @param _dataTable
	 */
	public static void addDataTable(String _keyDataTableName, List<List<String>> _dataTable, boolean bOnceTime) {
		// Get session data
		HashMap<String, LinkedHashMap<Integer, List<String>>> hSession_DataTable = new HashMap<>();
		hSession_DataTable = Serenity.sessionVariableCalled(TABLE_DATA_LIST);
		// If session data not exist then initialize
		if (hSession_DataTable == null) {
			HashMap<String, LinkedHashMap<Integer, List<String>>> hDataTable = new HashMap<>();
			Serenity.setSessionVariable(TABLE_DATA_LIST).to(hDataTable);
			hSession_DataTable = Serenity.sessionVariableCalled(TABLE_DATA_LIST);
		}
		// Check Data Table exist or not in Session Data
		boolean bExist = true;
		if (hSession_DataTable.get(_keyDataTableName) == null) {
			bExist = false;
		}

		if (((bExist) && (!bOnceTime)) || (!bExist)) {
			// Add data for session data
			LinkedHashMap<Integer, List<String>> mAddDataTable = new LinkedHashMap<>();
			System.out.println("***** INFO ***** Loading data from Data Table into [" + _keyDataTableName + "]");
			mAddDataTable = loadDataTable(_dataTable);
			hSession_DataTable.put(_keyDataTableName, mAddDataTable);
			Serenity.setSessionVariable(TABLE_DATA_LIST).to(hSession_DataTable);
		}
	}

	/**
	 * @Description Store data table one times (description in feature file) to
	 *              Session Data variable.
	 * @author Vincent
	 * @param _keyDataTableName
	 * @param _dataTable
	 */
	public static void addDataTable(String _keyDataTableName, List<List<String>> _dataTablee) {
		addDataTable(_keyDataTableName, _dataTablee, true);
	}

	/**
	 * @Description Remove data table (description in feature file) from Session
	 *              Data variable.
	 * @author Vincent
	 * @param _keyDataTableName
	 */
	public static void removeDataTable(String _keyDataTableName) {
		// Remove data data table
		HashMap<String, LinkedHashMap<Integer, List<String>>> hSessionDataTable = new HashMap<>();
		hSessionDataTable = Serenity.sessionVariableCalled(TABLE_DATA_LIST);
		hSessionDataTable.remove(_keyDataTableName);
		Serenity.setSessionVariable(TABLE_DATA_LIST).to(hSessionDataTable);
		System.out.println("***** INFO ***** Remove Data Table [" + _keyDataTableName + "] from Session Data");
	}

	/**
	 * @Description Get data table rows (data table description in feature file)
	 *              from Session Data variable by added Data Table Key.
	 * @author Vincent
	 * @param _dataTableKey
	 * @return
	 */
	public static LinkedHashMap<Integer, List<String>> getDataTbRows(String _dataTableKey) {
		HashMap<String, LinkedHashMap<Integer, List<String>>> hSessionDataTable = new HashMap<>();
		hSessionDataTable = Serenity.sessionVariableCalled(TABLE_DATA_LIST);
		return hSessionDataTable.get(_dataTableKey);
	}

	/**
	 * @Description Get data table rows (data table description in feature file)
	 *              without header (without first row) from Session Data
	 *              variable by added Data Table Key.
	 * @author Vincent
	 * @param _dataTableKey
	 * @return
	 */
	public static LinkedHashMap<Integer, List<String>> getDataTbRowsNoHeader(String _dataTableKey) {
		HashMap<String, LinkedHashMap<Integer, List<String>>> hSessionDataTable = Serenity
				.sessionVariableCalled(TABLE_DATA_LIST);
		LinkedHashMap<Integer, List<String>> ret = new LinkedHashMap<Integer, List<String>>(
				hSessionDataTable.get(_dataTableKey));
		ret.remove(0);
		return ret;
	}

	/**
	 * @Description Get data table row (data table description in feature file)
	 *              from Session Data variable by Row Index.
	 * @author Vincent
	 * @param _dataTableKey
	 * @param _row
	 * @return List<String>
	 */
	public static List<String> getDataTbRowByRowIndex(String _dataTableKey, int _row) {
		return getDataTbRows(_dataTableKey).get(_row);
	}

	/**
	 * @Description Get index of data table column (data table description in
	 *              feature file) from Session Data variable by Column Name.
	 * @author Vincent
	 * @param _dataTableKey
	 * @param _colName
	 * @return
	 */
	public static int getDataTbColIndex(String _dataTableKey, String _colName) {
		int colIndex = getDataTbRows(_dataTableKey).get(0).indexOf(_colName);
		if (colIndex == -1) {
			System.out
					.println("***ERROR*** Column [" + _colName + "] not found in Data Table [" + _dataTableKey + "].");
		}
		return colIndex;
	}

	/**
	 * @Description Get data rows (data table description in feature file) from
	 *              Session Data variable by given Value equals in specific
	 *              Column Name.
	 * @author Vincent
	 * @param _dataTableKey
	 * @param _colName
	 * @param _givenVal
	 * @return
	 */
	public static LinkedHashMap<Integer, List<String>> getDataTbRowsByValEqualInCol(String _dataTableKey,
			String _colName, String _givenVal) {
		LinkedHashMap<Integer, List<String>> lhTempSessionDataTable = getDataTbRows(_dataTableKey);
		LinkedHashMap<Integer, List<String>> lhOuput = new LinkedHashMap<>();
		int colIndex = lhTempSessionDataTable.get(0).indexOf(_colName);
		if (colIndex == -1) {
			System.out
					.println("***ERROR*** Column [" + _colName + "] not found in Data Table [" + _dataTableKey + "].");
		} else {
			// Loop through data in data table and add match data row
			for (int key : lhTempSessionDataTable.keySet()) {
				List<String> lTemp = lhTempSessionDataTable.get(key);
				if (!(lTemp.size() == 0)) {
					if (lTemp.get(colIndex).equals(_givenVal)) {
						lhOuput.put(key, lTemp);
					}
				} else {
					System.out.println(
							"***ERROR*** Data Row not found in Data Table [" + _dataTableKey + "].[" + key + "].");
				}
			}
		}
		return lhOuput;
	}

	/**
	 * @Description Get data rows (data table description in feature file) from
	 *              Session Data variable by given Value is contained in
	 *              specific Column Name.
	 * @author Vincent
	 * @param _dataTableKey
	 * @param _colName
	 * @param _givenVal
	 * @return
	 */
	public static LinkedHashMap<Integer, List<String>> getDataTbRowsByValContainsInCol(String _dataTableKey,
			String _colName, String _givenVal) {
		LinkedHashMap<Integer, List<String>> lhTempSessionDataTable = getDataTbRows(_dataTableKey);
		LinkedHashMap<Integer, List<String>> lhOuput = new LinkedHashMap<>();
		int colIndex = lhTempSessionDataTable.get(0).indexOf(_colName);
		if (colIndex == -1) {
			System.out
					.println("***ERROR*** Column [" + _colName + "] not found in Data Table [" + _dataTableKey + "].");
		} else {
			// Loop through data in data table and add match data row
			for (int key : lhTempSessionDataTable.keySet()) {
				List<String> lTemp = lhTempSessionDataTable.get(key);
				if (!(lTemp.size() == 0)) {
					if (lTemp.get(colIndex).contains(_givenVal)) {
						lhOuput.put(key, lTemp);
					}
				} else {
					System.out.println("***ERROR*** There are unexpected errors in Data Table [" + _dataTableKey + "].["
							+ key + "].");
				}
			}
		}
		return lhOuput;
	}

	/**
	 * @Description Get cell data value (data table description in feature file)
	 *              from Session Data variable by index of Row and Column Name.
	 * @author Vincent
	 * @param _dataTableKey
	 * @param _rowIndex
	 * @param _colName
	 * @return
	 */
	public static String getDataTbVal(String _dataTableKey, int _rowIndex, String _colName) {
		String ret = "";
		LinkedHashMap<Integer, List<String>> lhTempSessionDataTable = getDataTbRows(_dataTableKey);
		int colIndex = lhTempSessionDataTable.get(0).indexOf(_colName);
		if (colIndex == -1) {
			System.out.println(
					"***WARNING*** Column [" + _colName + "] not found in Data Table [" + _dataTableKey + "].");
			return ret;
		} else {
			try {
				ret = lhTempSessionDataTable.get(_rowIndex).get(colIndex);
			} catch (Exception e) {
				ret = "";
				System.out.println(
						"***WARNING*** Row [" + _rowIndex + "] not found in Data Table [" + _dataTableKey + "].");
			}

		}
		return ret;
	}

	/**
	 * @Description Get cell data value (data table description in feature file)
	 *              from Session Data variable by index of Row and Column.
	 * @author Vincent
	 * @param _dataTableKey
	 * @param _rowIndex
	 * @param _colIndex
	 * @return
	 */
	public static String getDataTbVal(String _dataTableKey, int _rowIndex, int _colIndex) {
		String ret = "";
		LinkedHashMap<Integer, List<String>> lhTempSessionDataTable = getDataTbRows(_dataTableKey);
		try {
			ret = lhTempSessionDataTable.get(_rowIndex).get(_colIndex);
		} catch (Exception e) {
			ret = "";
			System.out
					.println("***WARNING*** Row [" + _rowIndex + "] not found in Data Table [" + _dataTableKey + "].");
		}
		return ret;
	}

	/**
	 * @Description Get list of cell data value in specific Column Name (data
	 *              table description in feature file) from Session Data
	 *              variable by reference Value of reference ColumnName
	 * @author Vincent
	 * @param _dataTableKey
	 * @param _refColName
	 * @param _refValue
	 * @param _givenColName
	 * @return
	 */
	public static List<String> getDataTbRefDataVal(String _dataTableKey, String _refColName, String _refValue,
			String _givenColName) {
		// Get column index
		LinkedHashMap<Integer, List<String>> lhData = getDataTbRows(_dataTableKey);
		int colRefIndex = lhData.get(_dataTableKey).get(0).indexOf(_refColName);
		if (colRefIndex == -1) {
			System.out.println(
					"***WARNING*** Column [" + _refColName + "] not found in Data Table [" + _dataTableKey + "].");
			return null;
		}
		int colIndex = lhData.get(_dataTableKey).get(0).indexOf(_givenColName);
		if (colIndex == -1) {
			System.out.println(
					"***WARNING*** Column [" + _givenColName + "] not found in Data Table [" + _dataTableKey + "].");
			return null;
		}
		List<String> lResulValue = new ArrayList<String>();
		for (Integer key : lhData.keySet()) {
			if (lhData.get(key).size() == 0) {
				System.out.println("***Warning*** No data in row [" + key + "] of [" + _dataTableKey + "].");
			} else {
				try {
					if (lhData.get(key).get(colRefIndex).equals(_refValue)) {
						lResulValue.add(lhData.get(key).get(colIndex));
					}
				} catch (Exception e) {
					System.out.println(
							"***ERROR*** Incorret data format with row  [" + key + "] of [" + _dataTableKey + "].");
					throw e;
				}
			}
		}
		return lResulValue;
	}

	// #EndRegion#Data Table
	// #Region#ReadExcelData
	/**
	 * @Description Store excel data file (.xls) to session data variable.
	 * @author Vincent
	 * @param _keyFileName
	 * @param _fileName
	 */
	public static void addExcelData(String _keyFileName, String _fileName) {
		// Get session data
		File file = new File(_fileName);
		Map<String, HashMap<String, LinkedHashMap<Integer, List<String>>>> mSession_ExcelFile = new HashMap<>();
		mSession_ExcelFile = Serenity.sessionVariableCalled(EXCEL_FILE_LIST);
		// If session data not exist then initialize
		if (mSession_ExcelFile == null) {
			Map<String, HashMap<String, LinkedHashMap<Integer, List<String>>>> mExcelFile = new HashMap<>();
			Serenity.setSessionVariable(EXCEL_FILE_LIST).to(mExcelFile);
			mSession_ExcelFile = Serenity.sessionVariableCalled(EXCEL_FILE_LIST);
		}

		// Add data for session data
		HashMap<String, LinkedHashMap<Integer, List<String>>> mAddExcelFile = new HashMap<>();
		System.out.println("***** INFO ***** Loading data from [" + file + "] into [" + _keyFileName + "]");
		mAddExcelFile = loadExcelLines(file);
		mSession_ExcelFile.put(_keyFileName, mAddExcelFile);
		Serenity.setSessionVariable(EXCEL_FILE_LIST).to(mSession_ExcelFile);
		// System.out.println("Import Data: " + mAddExcelFile);

		// Add column index to map
		HashMap<String, LinkedHashMap<String, Integer>> mSheet_ColumnName_ColumnIndex = new HashMap<>();
		// Create session variable store with format "#Excel Files#Key File
		// Name#"
		// Iterator iteSheet = mAddExcelFile.entrySet().iterator();
		Iterator<HashMap.Entry<String, LinkedHashMap<Integer, List<String>>>> iterator = mAddExcelFile.entrySet()
				.iterator();
		while (iterator.hasNext()) { // loop all sheet
			HashMap.Entry<String, LinkedHashMap<Integer, List<String>>> pair = (HashMap.Entry<String, LinkedHashMap<Integer, List<String>>>) iterator
					.next();
			// Get header row
			LinkedHashMap<Integer, List<String>> dataSheet = (LinkedHashMap<Integer, List<String>>) pair.getValue();
			List<String> lColumnHeader = (List<String>) dataSheet.get(0);
			if (lColumnHeader != null) {
				int i = 0;
				LinkedHashMap<String, Integer> mColumnName_Index = new LinkedHashMap<String, Integer>();
				for (String temp : lColumnHeader) {
					mColumnName_Index.put(temp, i);
					i++;
				}
				mSheet_ColumnName_ColumnIndex.put(pair.getKey().toString(), mColumnName_Index);
			}
		}
		Serenity.setSessionVariable(EXCEL_FILE_LIST + _keyFileName + "#").to(mSheet_ColumnName_ColumnIndex);
	}

	public static void removeExcelData(String _keyFileName) {
		// Remove data
		Map<String, HashMap<String, LinkedHashMap<Integer, List<String>>>> mSession_ExcelFile = new HashMap<>();
		mSession_ExcelFile = Serenity.sessionVariableCalled(EXCEL_FILE_LIST);
		mSession_ExcelFile.remove(_keyFileName);
		Serenity.setSessionVariable(EXCEL_FILE_LIST).to(mSession_ExcelFile);
		// Remove release data
		Serenity.setSessionVariable(EXCEL_FILE_LIST + _keyFileName + "#").to(null);
	}

	public static HashMap<String, LinkedHashMap<Integer, List<String>>> getExcelData(String _keyFileName) {
		// Get session data
		Map<String, HashMap<String, LinkedHashMap<Integer, List<String>>>> mSessionExcelFile = new HashMap<>();
		mSessionExcelFile = Serenity.sessionVariableCalled(EXCEL_FILE_LIST);
		return mSessionExcelFile.get(_keyFileName);
	}

	public static LinkedHashMap<Integer, List<String>> getExcelSheetData(String _keyFileName, String _sheetName) {
		// Get session data
		Map<String, HashMap<String, LinkedHashMap<Integer, List<String>>>> mSessionExcelFile = new HashMap<>();
		mSessionExcelFile = Serenity.sessionVariableCalled(EXCEL_FILE_LIST);
		return mSessionExcelFile.get(_keyFileName).get(_sheetName);
	}

	public static LinkedHashMap<Integer, List<String>> getExcelSheetDataNoHeader(String _keyFileName,
			String _sheetName) {
		// Get session data
		LinkedHashMap<Integer, List<String>> temp = new LinkedHashMap<Integer, List<String>>(
				getExcelSheetData(_keyFileName, _sheetName));
		temp.remove(0);
		return temp;
	}

	public static int getColNameIndex(String _keyFileName, String _sheetName, String _columnName) {
		// Get session data
		Map<String, HashMap<String, Integer>> mSheet_ColumnName_ColumnIndex = new HashMap<>();
		// Generate session variable key
		mSheet_ColumnName_ColumnIndex = Serenity.sessionVariableCalled(getSessionKeyColName(_keyFileName));
		return mSheet_ColumnName_ColumnIndex.get(_sheetName).get(_columnName);
	}

	public static HashMap<String, Integer> getColNameMapBySheet(String _keyFileName, String _sheetName) {
		// Get session data
		Map<String, HashMap<String, Integer>> mSheet_ColumnName_ColumnIndex = new HashMap<>();
		// Generate session variable key
		mSheet_ColumnName_ColumnIndex = Serenity.sessionVariableCalled(getSessionKeyColName(_keyFileName));
		return mSheet_ColumnName_ColumnIndex.get(_sheetName);
	}

	public static List<Integer> getRowNumsContainValByColName(String _keyFileName, String _sheetName,
			String _columnName, String _givenValue) {
		// Get column index
		List<Integer> lResultRowNums = new ArrayList<Integer>();
		int columnIndex = getColNameIndex(_keyFileName, _sheetName, _columnName);
		// Get data sheet
		LinkedHashMap<Integer, List<String>> lhSheet_data = getExcelSheetData(_keyFileName, _sheetName);
		// Get data row list
		for (Integer key : lhSheet_data.keySet()) {
			if (lhSheet_data.get(key).size() == 0) {
				System.out.println("Warning : No data in row " + key + "of" + _keyFileName + "#" + _sheetName);
			} else {
				try {
					if (lhSheet_data.get(key).get(columnIndex).contains(_givenValue)) {
						lResultRowNums.add(key);
					}
				} catch (Exception e) {
					System.out.println(
							"Error : Incorret data format with row  " + key + "of" + _keyFileName + "#" + _sheetName);
					throw e;
				}
			}
		}
		return lResultRowNums;
	}

	public static List<Integer> getRowNumsEqualValByColName(String _keyFileName, String _sheetName, String _columnName,
			String _givenValue) {
		// Get column index
		List<Integer> lResultRowNums = new ArrayList<Integer>();
		int columnIndex = getColNameIndex(_keyFileName, _sheetName, _columnName);
		// Get data sheet
		LinkedHashMap<Integer, List<String>> lhSheet_data = getExcelSheetData(_keyFileName, _sheetName);
		// Get data row list
		for (Integer key : lhSheet_data.keySet()) {
			if (lhSheet_data.get(key).size() == 0) {
				System.out.println("Warning : No data in row " + key + "of" + _keyFileName + "#" + _sheetName);
			} else {
				try {
					if (lhSheet_data.get(key).get(columnIndex).equals(_givenValue)) {
						lResultRowNums.add(key);
					}
				} catch (Exception e) {
					System.out.println(
							"Error : Incorret data format with row  " + key + "of" + _keyFileName + "#" + _sheetName);
					throw e;
				}
			}
		}
		return lResultRowNums;
	}

	public static LinkedHashMap<Integer, List<String>> getDataRowsContainValByColName(String _keyFileName,
			String _sheetName, String _columnName, String _givenValue) {
		// Get column index
		LinkedHashMap<Integer, List<String>> lResultRowNums = new LinkedHashMap<Integer, List<String>>();
		int columnIndex = getColNameIndex(_keyFileName, _sheetName, _columnName);
		// Get data sheet
		LinkedHashMap<Integer, List<String>> lhSheet_data = getExcelSheetData(_keyFileName, _sheetName);
		// Get data row list
		for (Integer key : lhSheet_data.keySet()) {
			if (lhSheet_data.get(key).size() == 0) {
				System.out.println("Warning : No data in row " + key + "of" + _keyFileName + "#" + _sheetName);
			} else {
				try {
					if (lhSheet_data.get(key).get(columnIndex).contains(_givenValue)) {
						lResultRowNums.put(key, lhSheet_data.get(key));
					}
				} catch (Exception e) {
					System.out.println(
							"Error : Incorret data format with row  " + key + "of" + _keyFileName + "#" + _sheetName);
					throw e;
				}
			}
		}
		return lResultRowNums;
	}

	public static LinkedHashMap<Integer, List<String>> getDataRowsEqualValByColName(String _keyFileName,
			String _sheetName, String _columnName, String _givenValue) {
		// Get column index
		LinkedHashMap<Integer, List<String>> lResultRowNums = new LinkedHashMap<Integer, List<String>>();
		int columnIndex = getColNameIndex(_keyFileName, _sheetName, _columnName);
		// Get data sheet
		LinkedHashMap<Integer, List<String>> lhSheet_data = getExcelSheetData(_keyFileName, _sheetName);
		// Get data row list
		for (Integer key : lhSheet_data.keySet()) {
			if (lhSheet_data.get(key).size() == 0) {
				System.out.println("Warning : No data in row " + key + "of" + _keyFileName + "#" + _sheetName);
			} else {
				try {
					if (lhSheet_data.get(key).get(columnIndex).equals(_givenValue)) {
						lResultRowNums.put(key, lhSheet_data.get(key));
					}
				} catch (Exception e) {
					System.out.println(
							"Error : Incorret data format with row  " + key + "of" + _keyFileName + "#" + _sheetName);
					throw e;
				}
			}
		}
		return lResultRowNums;
	}

	public static List<String> getRefDataVal(String _keyFileName, String _sheetName, String _refColName,
			String _refValue, String _givenColName) {
		// Get column index
		int refColIndex = getColNameIndex(_keyFileName, _sheetName, _refColName);
		int givenColIndex = getColNameIndex(_keyFileName, _sheetName, _givenColName);
		List<String> lResulValue = new ArrayList<String>();
		// Get data sheet
		LinkedHashMap<Integer, List<String>> lhSheet_data = getExcelSheetData(_keyFileName, _sheetName);
		// Get data row list
		for (Integer key : lhSheet_data.keySet()) {
			if (lhSheet_data.get(key).size() == 0) {
				System.out.println("Warning : No data in row " + key + "of" + _keyFileName + "#" + _sheetName);
			} else {
				try {
					if (lhSheet_data.get(key).get(refColIndex).equals(_refValue)) {
						lResulValue.add(lhSheet_data.get(key).get(givenColIndex));
					}
				} catch (Exception e) {
					System.out.println(
							"Error : Incorret data format with row  " + key + "of" + _keyFileName + "#" + _sheetName);
					throw e;
				}
			}
		}
		return lResulValue;
	}

	private static String getSessionKeyColName(String _keyFileName) {
		return EXCEL_FILE_LIST + _keyFileName + "#";
	}

	private static HashMap<String, LinkedHashMap<Integer, List<String>>> loadExcelLines(File fileName) {
		// Used the LinkedHashMap and LikedList to maintain the order
		HashMap<String, LinkedHashMap<Integer, List<String>>> outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List<String>>>();
		LinkedHashMap<Integer, List<String>> hashMap = new LinkedHashMap<Integer, List<String>>();
		String sheetName = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			// Create an excel workbook from the file system
			HSSFWorkbook workBook = new HSSFWorkbook(fis);
			// Get the first sheet on the workbook.
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				HSSFSheet sheet = workBook.getSheetAt(i);
				// XSSFSheet sheet = workBook.getSheetAt(0);
				sheetName = workBook.getSheetName(i);
				int countCellInRowHeader = 0;
				int countCellInRow = 0;
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					HSSFRow row = (HSSFRow) rows.next();
					Iterator<Cell> cells = row.cellIterator();
					List<String> data = new LinkedList<String>();
					while (cells.hasNext()) {
						HSSFCell cell = (HSSFCell) cells.next();
						cell.setCellType(Cell.CELL_TYPE_STRING);
						data.add(cell.toString());
					}
					if (countCellInRowHeader == 0) { // Get the header size
						countCellInRowHeader = data.size();
						System.out.println("HEADER COLUMN NAME of SHEET : [" + sheet.getSheetName() + "]");
						System.out.println(data);
					}
					countCellInRow = data.size();
					if (countCellInRowHeader != countCellInRow) {
						System.out.println(
								"***WARNING***: BELOW DATA ROW is not have the same size with HEADER ROW (Size = "
										+ countCellInRowHeader + ").***WARNING***:");
						System.out.println("ROW[" + row.getRowNum() + "] : " + data);
					}
					hashMap.put(row.getRowNum(), data);
				}
				outerMap.put(sheetName, hashMap);
				hashMap = new LinkedHashMap<Integer, List<String>>();
				workBook.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return outerMap;
	}

	private static LinkedHashMap<Integer, List<String>> loadDataTable(List<List<String>> _dataTable) {
		// Used the LinkedHashMap to maintain the order
		LinkedHashMap<Integer, List<String>> hashMap = new LinkedHashMap<Integer, List<String>>();
		// Add
		for (int i = 0; i < _dataTable.size(); i++) {
			hashMap.put(i, _dataTable.get(i));
		}
		return hashMap;
	}
	// #EndRegion#ReadExcelData
}
