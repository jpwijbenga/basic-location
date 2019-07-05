package nl.jp.location.location;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

/**
 * Root resource (exposed at "" path)
 */
@Path("")
@Log4j2
public class MyResource {
	Gson gson = new Gson();
	DbHandler dbHandler = new DbHandler();

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Path("/getit")
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Path("/this")
	public Response getThis() {
		return Response.ok(new Location(100l, 15.12d, 14.2d)).build();
	}

	@POST
	@Path("/store")
	public Response store(Location location) {
		try {
			dbHandler.insert(location);
		} catch (SQLException e) {
			log.error("Error with state " + e.getSQLState() + " and error code " + e.getErrorCode() + " and message: "
					+ e.getMessage());
			return Response.status(500).entity(e.getSQLState()).build();
		} catch (Exception e) {
			log.error("Other error: ", e);
			return Response.status(500).build();
		}
		return Response.ok(new Location(100l, 15.12d, 14.2d)).build();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return Response.
	 */
	@GET
	@Path("/reset")
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize() {
		try {
			dbHandler.prepareLocationTable();
		} catch (SQLException e) {
			log.error("Error with state " + e.getSQLState() + " and error code " + e.getErrorCode() + " and message: "
					+ e.getMessage());
			return Response.status(500).entity(e.getSQLState()).build();
		} catch (Exception e) {
			log.error("Other error: ", e);
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.ok(new Location(100l, 15.12d, 14.2d)).build();
	}

	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public Response count() {
		int i = 0;
		try {
			i = dbHandler.countTotalIn("location");
		} catch (SQLException e) {
			log.error("Error with state " + e.getSQLState() + " and error code " + e.getErrorCode() + " and message: "
					+ e.getMessage());
			return Response.status(500).entity(e.getSQLState()).build();
		} catch (Exception e) {
			log.error("Other error: ", e);
		}
		return Response.ok("Count: " + i).build();
	}
}
