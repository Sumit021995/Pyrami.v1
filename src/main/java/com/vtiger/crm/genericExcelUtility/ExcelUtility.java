package com.vtiger.crm.genericExcelUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.vtiger.crm.genericIPathUtility.IPathUtility;



public class ExcelUtility {

	public String getDataFromExcelFile(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {
		String value = "";
		FileInputStream file = new FileInputStream(IPathUtility.excelFile);
		Workbook wb = WorkbookFactory.create(file);
		try {
			value = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		} catch (Exception e) {
			System.out.println(value);

		}
//		String value = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		return value;

	}

	public List<String> getMultipleDataFromExcelFileFirstColumn(String sheetName, int firstRowNum, int firstCellNum)
			throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(IPathUtility.excelFile);
		Workbook wb = WorkbookFactory.create(file);
		ArrayList<String> multipleData = new ArrayList<String>();

		Sheet sheet = wb.getSheet(sheetName);
		int lastRowCount = sheet.getLastRowNum();

		for (int i = 0; i < lastRowCount; i++) {
			String value = "";
			try {
				value = sheet.getRow(i).getCell(0).toString();

			} catch (Exception e) {

//					multipleData.add(new String("null"));
			}
			multipleData.add(value);
		}

		return multipleData;
	}

	public List<String> getMultipleDataFromExcelFile(String sheetName, int startRowNum) throws Exception {
		List<String> excelDataList = new ArrayList<String>();
		FileInputStream file = new FileInputStream(IPathUtility.excelFile);
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheet(sheetName);
		DataFormatter stringFormatExcelData = new DataFormatter();
		for (int i = startRowNum; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				String data = stringFormatExcelData.formatCellValue(cell);
				excelDataList.add(data);
			}
		}

		return excelDataList;
	}

	public List<String> getMultipleDataFromExcelFileWithFixedRow(String sheetName, int fixedRowIndex, int startCellNum)
			throws Exception {
		List<String> excelDataList = new ArrayList<String>();
		FileInputStream file = new FileInputStream(IPathUtility.excelFile);
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheet(sheetName);

		DataFormatter stringFormatExcelData = new DataFormatter();
		Row row = sheet.getRow(fixedRowIndex);
		for (int j = startCellNum; j < row.getLastCellNum(); j++) {
			Cell cell = row.getCell(j);
			String data = stringFormatExcelData.formatCellValue(cell);
			excelDataList.add(data);
		}
		return excelDataList;
	}

	public List<String> getMultipleDataOfColumnFromExcelFile(String sheetName, int fixedCellIndex) throws Exception {
		List<String> excelDataList = new ArrayList<String>();
		FileInputStream file = new FileInputStream(IPathUtility.excelFile);
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheet(sheetName);
		DataFormatter stringFormatExcelData = new DataFormatter();
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Cell cell = sheet.getRow(i).getCell(fixedCellIndex);
			String data = stringFormatExcelData.formatCellValue(cell);
			excelDataList.add(data);
		}
		return excelDataList;
	}


	public void setDataInExcelFile(String sheetName,int rowNum,int CellNum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream file = new FileInputStream(IPathUtility.excelFile);
		Workbook wb = WorkbookFactory.create(file);
		Cell cell = wb.getSheet(sheetName).getRow(rowNum).createCell(CellNum);
		cell.setCellValue(data);
		FileOutputStream fos = new FileOutputStream(IPathUtility.excelFile);
		wb.write(fos);
		wb.close();
	}
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream file = new FileInputStream(IPathUtility.excelFile);
		Workbook wb = WorkbookFactory.create(file);
		return wb.getSheet(sheetName).getLastRowNum();
	}
	public static void main(String[] args) throws Exception {
		ExcelUtility excelUtility = new ExcelUtility();
		List<String> data = excelUtility.getMultipleDataFromExcelFile("Sheet2", 0);
		System.out.println(data);
		List<String> singleColumnData = excelUtility.getMultipleDataOfColumnFromExcelFile("Sheet2", 0);
		System.out.println(singleColumnData);
		List<String> firstColumnData = excelUtility.getMultipleDataFromExcelFileFirstColumn("Contacts", 0, 0);
		System.out.println(firstColumnData);
		List<String> fixedRowData = excelUtility.getMultipleDataFromExcelFileWithFixedRow("Contacts", 8, 0);
		System.out.println(fixedRowData);
		
		excelUtility.setDataInExcelFile("Contacts", 2, 8, "PASS");
		
		
		

	}

}
