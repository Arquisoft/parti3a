package es.uniovi.asw.dbupdate;



public class InsertTest {
	
	/*private RList rList = new RList();
	DeleteP deleted =new DeleteP();
	private Ciudadano c1;
	private Ciudadano c2;
	private Ciudadano c3;
	
	private Ciudadano ciud1;
	private Ciudadano ciud2;
	private Ciudadano ciud3;
	private Ciudadano ciudInex;

	@Test
	public void testCargaCiudadanos() {
		c1 = new Ciudadano("Juan", "Torres Pardo", "juan@example.com", null, null, "Español", "90500084Y");
		c2 = new Ciudadano("Luis", "López Fernando", "luis@example.com", null, null, "Español", "19160962F");
		c3 = new Ciudadano("Ana", "Torres Pardo", "ana@example.com", null, null, "Francés", "09940449X");
		
		rList.setReader(new ExcelReader());
		//Metodo encargado de leer el fichero, de crear a los ciudadanos y de llamar al insert
		rList.read("test.xlsx");
	}
	
	@Test
	public void testCiudadano1(){
		try {
			new CommandExecutor<Void>().execute(new Command<Void>() {
				@Override
				public Void execute() throws BusinessException {
					ciud1 = CiudadanoFinder.findByDni("90500084Y");
					deleted.delete(ciud1);
					return null;
				}
			});
		} catch (BusinessException e) {
		}
		
		assertFalse(ciud1 == null);
		assert(!ciud1.equals(c1));
	}
	
	@Test
	public void testCiudadano2(){
		try {
			new CommandExecutor<Void>().execute(new Command<Void>() {
				@Override
				public Void execute() throws BusinessException {
					ciud2 = CiudadanoFinder.findByDni("19160962F");
					deleted.delete(ciud2);
					return null;
				}
			});
		} catch (BusinessException e) {
		}
		
		assertFalse(ciud2 == null);
		assert(!ciud2.equals(c2));
	}
	
	@Test
	public void testCiudadano3(){
		try {
			new CommandExecutor<Void>().execute(new Command<Void>() {
				@Override
				public Void execute() throws BusinessException {
					ciud3 = CiudadanoFinder.findByDni("09940449X");
					deleted.delete(ciud3);
					return null;
				}
			});
		} catch (BusinessException e) {
		}
		
		assertFalse(ciud3 == null);
		assert(!ciud3.equals(c3));
	}
	
	@Test
	public void testCiudadanoInexistente(){
		try {
			new CommandExecutor<Void>().execute(new Command<Void>() {
				@Override
				public Void execute() throws BusinessException {
					ciudInex = CiudadanoFinder.findByDni("01234567J");
					return null;
				}
			});
		} catch (BusinessException e) {
		}
		
		assertTrue(ciudInex == null);
	}*/
}
