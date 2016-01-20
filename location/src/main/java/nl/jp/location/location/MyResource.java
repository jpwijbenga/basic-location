package nl.jp.location.location;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	DbHandler dbHandler;

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
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
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Path("/this")
	@Produces(MediaType.APPLICATION_JSON)
	public String getThis() {
		return new Gson().toJson(new Location(100l, 15.12d, 14.2d));
	}

	/**
	 * Method handling HTTP POST requests. The returned object will be sent to
	 * the client as "json" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@POST
	@Path("/store")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String store(Location location) {
		try {
			dbHandler.insert(location);
		} catch (SQLException e) {
			return new SqlResponse(ResponseStatus.FAILURE, e.getMessage()).toString();
		}
		return new Gson().toJson(new Location(100l, 15.12d, 14.2d));
	}

	/*
	@Path("/fileUpload")
	@Consumes("multipart/form-data")
	@POST
	public void handleUpload(@FormParam("file") InputStream inputStream) throws Exception {
		OutputStream outputStream = null;
		try {
			// write the inputStream to a FileOutputStream
			outputStream = new FileOutputStream(new File("uploadedFile"));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			System.out.println("Done!");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}**/
}