package nl.jp.location.location;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class DbHandlerTest {
	private DbHandler dbHandler;
	
	@Before
	public void setUp() throws SQLException {
		dbHandler = new DbHandler();
	}
	
    @Test
    public void testInsert() throws SQLException {
    	dbHandler.insert(new Location(100,32.12,32.14000));
    }
    
    @Test
    public void testTotalSize() throws SQLException {
    	System.out.println("Total count employee: " + dbHandler.countTotalIn("people"));
    	System.out.println("Total count location: " + dbHandler.countTotalIn("location"));
    }
}
