package nl.jp.location.location;

import com.google.gson.Gson;

public class SqlResponse {
	public final ResponseStatus responseStatus;
	public final String responseText;
	private Gson gson = new Gson();
	
	public SqlResponse(ResponseStatus status, String text) {
		responseStatus = status;
		responseText = text;
	}

	@Override
	public String toString() {
		return gson.toJson(this);
	}
}
