package nl.jp.location.location;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHandler {
	public static void insert(Location location) throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        Statement stat = conn.createStatement();
        stat.executeUpdate("drop table if exists location;");
        stat.executeUpdate("create table location (x, y, millis);");
        PreparedStatement prep = conn.prepareStatement("insert into location values (?, ?, ?);");

        prep.setDouble(1, location.xLocation);
        prep.setDouble(2, location.yLocation);
        prep.setLong(3,  location.getMillis());
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);

        showResults(stat);
        conn.close();
    }

	private static void showResults(Statement stat) throws SQLException {
		ResultSet rs = stat.executeQuery("select * from people;");
        while (rs.next()) {
            System.out.println("name = " + rs.getString("name"));
            System.out.println("job = " + rs.getString("occupation"));
        }
        rs.close();
	}
}
