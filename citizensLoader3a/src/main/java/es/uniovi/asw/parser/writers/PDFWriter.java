package es.uniovi.asw.parser.writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.uniovi.asw.model.Citizen;

public class PDFWriter implements Writer {
	
	private final static String PATH = "src/main/resources/emails/";

	@SuppressWarnings("unused")
	@Override
	public void write(Citizen ciudadano) {		
		try{
			Document documento = new Document();			
			PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(new File(PATH+ciudadano.getDni()+".pdf")));
			documento.open();
			documento.add(new Paragraph("Hola " + ciudadano.getName() + " " + ciudadano.getSurname() + ","));
			documento.add(new Paragraph("Este correo es para informarle de que ha sido dado de alta correctamente en el sistema de participación "
					+ "ciudadana. A continuación, le comunicamos su usuario y contraseña: "));
			documento.add(new Paragraph("\t\tUsuario: "+ ciudadano.getUser().getUsername()));
			documento.add(new Paragraph("\t\tContraseña: "+ ciudadano.getUser().getPassword()));
			documento.close();			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} 		
	}
}