package Utility;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

	WebDriver driver;
	Workbook workbook;
	Sheet sheet;
	String filePath;

//	public ExcelUtility(WebDriver driver) {
//		this.driver = driver;
//		PageFactory.initElements(driver, this);
//	}
	public int rowCount() {
		return sheet.getLastRowNum();
	}
	public ExcelUtility(String filePath) {
		this.filePath = filePath;
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			this.workbook = WorkbookFactory.create(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSheet(String sheetName) {
		this.sheet = workbook.getSheet(sheetName);
		if (this.sheet == null) {
			throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the Excel file.");
		}
	}

	public void setSheet(int sheetIndex) {
		this.sheet = workbook.getSheetAt(sheetIndex);
		if (this.sheet == null) {
			throw new IllegalArgumentException("Sheet at index " + sheetIndex + " not found in the Excel file.");
		}
	}

	public String readData(int rowNum, int colNum) {
		Row row = sheet.getRow(rowNum);
		if (row == null) {
			return "";
		}
		Cell cell = row.getCell(colNum);
		if (cell == null) {
			return "";
		}
		return cell.toString();
	}
	
	public void writeDataOnSefColRow(int rowNum, int colNum, String data) {
		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}
		Cell cell = row.getCell(colNum);
		if(cell == null) {
			cell = row.createCell(colNum);
		}
		cell.setCellValue(data);
		
		try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void writeData(int startCol, String[] data) {
	    int rowNum = findNextEmptyRow();
	    
	    Row row = sheet.getRow(rowNum);
	    if (row == null) {
	        row = sheet.createRow(rowNum);
	    }

	    for (int i = 0; i < data.length; i++) {
	        Cell cell = row.getCell(startCol + i);
	        if (cell == null) {
	            cell = row.createCell(startCol + i);
	        }
	        cell.setCellValue(data[i]);
	    }

	    try {
	        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
	        workbook.write(fileOutputStream);
	        fileOutputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private int findNextEmptyRow() {
	    int rowNum = 0;
	    while (sheet.getRow(rowNum) != null) {
	        rowNum++;
	    }
	    return rowNum;
	}

	public void close() {
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
