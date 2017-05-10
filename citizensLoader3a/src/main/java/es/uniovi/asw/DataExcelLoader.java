package es.uniovi.asw;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataExcelLoader {

	private final static String PATH = "src/main/resources/test.xlsx";
	
	 public static void main(String[] args) throws ParseException {
         
	        generaXlsx();
	 
	 }

	private static void generaXlsx() throws ParseException {
		
        @SuppressWarnings("resource")
		XSSFWorkbook libroTrabajo = new XSSFWorkbook();
        XSSFSheet hoja1 = libroTrabajo.createSheet() ;
 
 
        //iterando numero de filas (r)
        for (int r = 0; r < 10; r++ ) {
            XSSFRow row = hoja1.createRow(r);
             
            //iterando numero de columnas (c)
            for (int c = 0; c < 7; c++ ){
            	XSSFCell cell = row.createCell(c);
            	if (r == 0) {
            		switch(c){
	            		case 0:		            		
		                    cell.setCellValue("Nombre");
		                    break;
	            		case 1:	            			
		                    cell.setCellValue("Apellidos");
		                    break;
	            		case 2:
	            			cell.setCellValue("Correo electrónico");
	            			break;
	            		case 3:
	            			cell.setCellValue("Fecha nacimiento");
	            			break;
	            		case 4:
	            			cell.setCellValue("Dirección postal");
	            			break;
	            		case 5:
	            			cell.setCellValue("Nacionalidad");
	            			break;
	            		case 6:
	            			cell.setCellValue("DNI");	
	            			break;
            		}
            	}            	
            	else {
            		switch (c) {
		            	case 0:		            		
		                    cell.setCellValue("NombreUser"+RandomStringUtils.randomAlphabetic(4));
		                    break;
		        		case 1:	            			
		                    cell.setCellValue("ApellidosUser"+RandomStringUtils.randomAlphabetic(4));
		                    break;
		        		case 2:		        			
		        			cell.setCellValue("emailUser"+r+"@example.com");
		        			break;
		        		case 3:	
		        			SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		        			cell.setCellType(CellType.NUMERIC);
		        			cell.setCellValue(formateador.parse("01-01-1994"));
		        			break;
		        		case 4:
		        			cell.setCellValue("Dirección postalUser"+r);
		        			break;
		        		case 5:
		        			cell.setCellValue("NacionalidadUser"+RandomStringUtils.randomAlphabetic(4));
		        			break;
		        		case 6:
		        			cell.setCellValue(RandomStringUtils.randomNumeric(8)+RandomStringUtils.randomAlphabetic(1).toUpperCase());	
		        			break;
            		}
            	}
            }
        }
 
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(PATH);
			
	        libroTrabajo.write(fileOut);
	        fileOut.flush();
	        fileOut.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
 
        
	}
}
