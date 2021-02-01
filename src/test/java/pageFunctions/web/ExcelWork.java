package pageFunctions.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWork {

	@SuppressWarnings("resource")
	public Map<String, HashMap<String, String>> ReadTestData(String SheetName) throws IOException
	{
		Map<String, HashMap<String, String>> DataSet=new HashMap<String,HashMap<String, String>>();
		File file = new File("src\\test\\resources\\dataSource\\SWTestData.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(SheetName);
		int rowCountForMap = 0;
		for (int k = 1; k <= sheet.getLastRowNum(); k++) 
		{
			XSSFRow counter = (XSSFRow) sheet.getRow(k);
			if (counter.getCell(1).toString().equalsIgnoreCase("Yes")) 
			{
				rowCountForMap = rowCountForMap + 1;
			}
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(rowCountForMap).getLastCellNum();
			for (int i = 0; i < rowCount; i++) 
			{
				
				if (sheet.getRow(i + 1).getCell(1).toString().equalsIgnoreCase("Yes")) 
				{
					String TestName=sheet.getRow(i + 1).getCell(0).toString();
					Map<String, String>map=new HashMap<String,String>();
					for (int j = 0; j < colCount; j++) 
					{
						System.out.println("");
						System.out.println(sheet.getRow(0).getCell(j).toString());
						System.out.println(sheet.getRow(i + 1).getCell(j).toString());
						map.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
					}
					DataSet.put(TestName, (HashMap<String, String>) map);
				} 
				
			}
		}
		
		
		return DataSet;
		
	}
	
	public HashMap<String, String> TestData(String SheetName, String TestCaseName) throws IOException
	{
		try
		{
		Map<String, HashMap<String, String>> DataSet=ReadTestData(SheetName);
		System.out.println("");
		System.out.println(DataSet.size());
		HashMap<String, String>map=DataSet.get(TestCaseName);
		return map;
		}
		catch (Exception ex) 
		{
			System.out.println("Excel reading issue with Sheet : "+SheetName +" and for Test Cases "+TestCaseName);
			System.out.println(ex.getMessage().toString());
			return null;
		}
	
	}
	
	public void writeCustomerID(String CustomerID, String PaymentReference) throws IOException {

		String path = "src//test//resources//dataSource//CustomerID.xlsx";
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
				createFile(path);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		appendData(path, CustomerID, PaymentReference);
	}

	public void createFile(String Path) throws IOException {
		FileOutputStream fos = new FileOutputStream(Path);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("MoveInCustomerID");
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("CustomerID");

		Cell cell1 = row.createCell(1);
		cell1.setCellValue("Payment Reference");

		Cell cell2 = row.createCell(2);
		cell2.setCellValue("CreatedDate");
		workbook.write(fos);
		fos.flush();
		fos.close();
	}

	public void appendData(String Path, String CustomerID, String PaymentReference) throws FileNotFoundException {
		try {

			FileInputStream fis = new FileInputStream(new File(Path));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// XSSFSheet sheet = workbook.
			int i = workbook.getNumberOfSheets();
			System.out.println(i);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int num = sheet.getLastRowNum();
			Row row = sheet.createRow(++num);

			row.createCell(0).setCellValue(CustomerID);
			row.createCell(1).setCellValue(PaymentReference);
			row.createCell(2).setCellValue(currentDate());
			fis.close();
			FileOutputStream fos = new FileOutputStream(Path);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String currentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return (formatter.format(date));

	}

}
