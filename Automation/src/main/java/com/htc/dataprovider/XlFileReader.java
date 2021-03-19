package com.htc.dataprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlFileReader {
	private Sheet xlsheet;
	private Workbook xlbook;
	public Object[][] getDataArray(String path,String sheetname) throws IOException{
		Object[][] tabarray=null;
		try {
			FileInputStream fis = new FileInputStream(path);
			xlbook= new XSSFWorkbook(fis);
			xlsheet = xlbook.getSheet(sheetname);
		}catch(FileNotFoundException e) {
			System.out.println("error "+e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		int totalrow=xlsheet.getLastRowNum();
		int totalcol=xlsheet.getRow(1).getPhysicalNumberOfCells();
		tabarray=new Object[totalrow][1];
		try {
			for(int i=0;i<totalrow;i++) {
				Map<Object,Object> datamap=new HashMap<>();
				for(int j=0;j<totalcol;j++) {
					datamap.put(xlsheet.getRow(0).getCell(j).toString(), xlsheet.getRow(i+1).getCell(j).getStringCellValue());
				}
				tabarray[i][0]=datamap;
				System.out.println(tabarray[i][0]);
			}
		}catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
	return tabarray;
	}
}
