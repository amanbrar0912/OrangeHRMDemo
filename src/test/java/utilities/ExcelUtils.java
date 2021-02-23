package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wBook;
	public static XSSFSheet wSheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xlFile, String xlSheet) throws IOException {
		fis = new FileInputStream(xlFile);
		wBook = new XSSFWorkbook(fis);
		wSheet = wBook.getSheet(xlSheet);
		int rowCount = wSheet.getLastRowNum();
		wBook.close();
		fis.close();
		return rowCount;
	}

	public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException {
		fis = new FileInputStream(xlFile);
		wBook = new XSSFWorkbook(fis);
		wSheet = wBook.getSheet(xlSheet);
		row = wSheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wBook.close();
		fis.close();
		return cellCount;
	}

	public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException {
		fis = new FileInputStream(xlFile);
		wBook = new XSSFWorkbook(fis);
		wSheet = wBook.getSheet(xlSheet);
		row = wSheet.getRow(rowNum);
		cell = row.getCell(colNum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		wBook.close();
		fis.close();
		return data;
	}

	public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data)
			throws IOException {
		fos = new FileOutputStream(xlFile);
		fis = new FileInputStream(xlFile);
		wBook = new XSSFWorkbook(fis);
		wSheet = wBook.getSheet(xlSheet);
		row = wSheet.getRow(rowNum);
		cell = row.getCell(colNum);
		cell.setCellValue(data);
		wBook.write(fos);
		wBook.close();
		fos.close();
		fis.close();
	}

	public static Object[][] getData(String xlFile, String xlSheet) throws IOException {
		int rowCount = ExcelUtils.getRowCount(xlFile, xlSheet);
		int colCount = ExcelUtils.getCellCount(xlFile, xlSheet, 1);
		Object data[][] = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i-1][j] = ExcelUtils.getCellData(xlFile, xlSheet, i, j);
			}
		}
		return data;
	}
}
