package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {

	public static String excel_path="c:\\Users\\sys\\eclipse-workspace\\apitest\\src\\main\\java\\com\\qa\\data\\TestData.xlsx";
	
	static int rowcount;
	static Row row;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static FileInputStream fis;
	static FileOutputStream fos;
	public static Object[][] data;
	
	public static String getValueByJPath(JSONObject responsejson,String jpath){
		Object obj = responsejson;
		for(String s : jpath.split("/")) 
			if(!s.isEmpty()) 
				if(!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if(s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}
	
	public static Object[][] getTestPRData() throws IOException {
		
		try {
			
			fis = new FileInputStream(excel_path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			rowcount = sheet.getLastRowNum();
			System.out.println("Row Count:" + ((rowcount)+1));
			
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<=sheet.getLastRowNum();i++) {
				
				for(int j=0;j<sheet.getRow(i+1).getLastCellNum();j++) {
					
		
					Cell cell=sheet.getRow(i+1).getCell(j);
			
					switch (cell.getCellType())               
					{  
					case STRING:    //field that represents string cell type  CELL_TYPE_STRING
					data[i][j]=cell.getStringCellValue();		
					System.out.println("String: "+data[i][j] + "\t"); 
					break;  
					
					case NUMERIC:    //field that represents number cell type  
					data[i][j]=NumberToTextConverter.toText(cell.getNumericCellValue());
					System.out.println("Numeric:"+data[i][j] + "\t");
					break;  
					
					case BLANK:
					data[i][j]=cell.getStringCellValue();
					System.out.println("Blank:"+data[i][j] + "\t"); 
                    break;  
					
					default:  
					
					} 
				
						
					
				}
			
			}
			
		}catch(Exception e) {
			
		}
		return data;

	}
		
}
