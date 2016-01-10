package nl.jp.location.location;

import com.google.gson.Gson;

public class SqlResponse {
	public final ResponseStatus responseStatus;
	public final String responseText;
	
	public SqlResponse(ResponseStatus status, String text) {
		responseStatus = status;
		responseText = text;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
