package nl.jp.location.location;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DbHandler {
	private Connection connection;

	public DbHandler() {
		try {
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Can't find JDBC Driver, not inserting." + e);
			return;
		}
	}
	
	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection("jdbc:sqlite:test.db");
		}
		if (connection.isValid(10)) {
			log.info("Connection is valid");
		}
		return connection;
	}
	
	public void insert(Location location) throws SQLException {
        var prep = getConnection().prepareStatement("insert into location values (?, ?, ?);");
        prep.setDouble(1, location.xLocation);
        prep.setDouble(2, location.yLocation);
        prep.setLong(3,  location.getMillis());
        prep.addBatch();
        getConnection().setAutoCommit(false);
        prep.executeBatch();
        getConnection().setAutoCommit(true);
    }

	public void prepareLocationTable() throws SQLException {
		var stat = getConnection().createStatement();
        stat.executeUpdate("drop table if exists location;");
        stat.executeUpdate("create table location (x, y, millis);");
	}

	public int countTotalIn(String tableName) throws SQLException {
		var stat = getConnection().createStatement();
		var rs = stat.executeQuery(String.format("select * from %s;",tableName));
		int count = 0;
		while (rs.next()) {
			count++;
		}
        rs.close();
        return count;
	}
}
