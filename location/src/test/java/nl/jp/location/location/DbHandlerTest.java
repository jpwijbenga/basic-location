package nl.jp.location.location;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DbHandlerTest {
	private static DbHandler dbHandler;
	
	@BeforeAll
	public static void setUp() throws SQLException {
		dbHandler = new DbHandler();
	}
	
    @Test
    public void testInsert() throws SQLException {
    	dbHandler.prepareLocationTable();
    	dbHandler.insert(new Location(123452300,32.12,32.14000));
    }
    
    @Test
    public void testTotalSize() throws SQLException {
    	log.info("Total count location: " + dbHandler.countTotalIn("location"));
    }
}
