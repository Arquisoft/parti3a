package es.uniovi.asw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.associations.AsignarTest;
import es.uniovi.asw.checker.CheckerTest;
import es.uniovi.asw.parser.readers.ExcelReaderTest;
import es.uniovi.asw.parser.writers.PDFWriterTest;
import es.uniovi.asw.parser.writers.TXTWriterTest;
import es.uniovi.asw.parser.writers.WORDWriterTest;
import es.uniovi.asw.reportwriter.WreportPTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	AsignarTest.class,
	CheckerTest.class,
	ExcelReaderTest.class,
	PDFWriterTest.class,
	TXTWriterTest.class,
	WORDWriterTest.class,
	WreportPTest.class
})

public class AllTests {

}
