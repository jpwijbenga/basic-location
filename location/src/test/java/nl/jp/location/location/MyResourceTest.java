package nl.jp.location.location;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource/getit").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
    
    @Test
    public void testGetThis() {
    	String responseMsg = target.path("myresource/this").request().get(String.class);
    	assertEquals("{\"dateTime\":{\"iMillis\":100,\"iChronology\":{\"iBase\":{\"iMinDaysInFirstWeek\":4}}},\"xLocation\":15.12,\"yLocation\":14.2}", responseMsg);
    }
    
    @Test
    public void testPostThis() throws SQLException {
    	DbHandler dbHandler = new DbHandler();
		int before = dbHandler.countTotalIn("location");
    	String input = "{\"dateTime\":{\"iMillis\":100,\"iChronology\":{\"iBase\":{\"iMinDaysInFirstWeek\":4}}},\"xLocation\":15.12,\"yLocation\":14.2}";
    	Response responseMsg = target.path("myresource/store").
    			request().
    			post(Entity.entity(input, MediaType.APPLICATION_JSON),Response.class);
    	System.out.println(responseMsg.toString());

    	Location location = new Location(101,41.233,40.233);
    	Response responseMsg2 = target.path("myresource/store").
    			request().
    			post(Entity.entity(location, MediaType.APPLICATION_JSON),Response.class);
    	System.out.println(responseMsg2.toString());

    	
    	int after = dbHandler.countTotalIn("location");
    	System.out.println();
    }
}
