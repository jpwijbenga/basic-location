package nl.jp.location.location;

import java.sql.SQLException;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class MyResourceTest {
    private static HttpServer server;
    private static WebTarget target;

    @BeforeAll
    public static void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        var c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @AfterAll
    public static void tearDown() throws Exception {
        if (server != null) {
            server.shutdownNow();
        }
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        var responseMsg = target.path("/getit").request().get(String.class);
        Assertions.assertEquals("Got it!", responseMsg);
    }
    
    @Test
    public void testGetThis() {
    	target.path("/this").request().get(String.class);
    }
    
    @Test
    public void testPostThis() throws SQLException {
    	var dbHandler = new DbHandler();
		int before = dbHandler.countTotalIn("location");
    	var input = "{\"dateTime\":{\"iMillis\":100,\"iChronology\":{\"iBase\":{\"iMinDaysInFirstWeek\":4}}},\"xLocation\":15.12,\"yLocation\":14.2}";
    	var responseMsg = target.path("/store").
    			request().
    			post(Entity.entity(input, MediaType.APPLICATION_JSON),Response.class);
    	System.out.println(responseMsg.toString());

    	var location = new Location(101,41.233,40.233);
    	var responseMsg2 = target.path("/store").
    			request().
    			post(Entity.entity(location, MediaType.APPLICATION_JSON),Response.class);
    	System.out.println(responseMsg2.toString());

    	int after = dbHandler.countTotalIn("location");
        System.out.println("L " + after + " - " + before);
        Assertions.assertEquals(2, after - before);
    }
}
