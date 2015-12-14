package nl.jp.location.location;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public class Location  {
	
	private static final LocalDateTime JAN_1_1970 = new LocalDateTime(1970, 1, 1, 0, 0);
	
	private final DateTime dateTime;
	public final double xLocation;
	public final double yLocation;

	public Location(long dateTimeMillisUtc, double x, double y) {
		dateTime = JAN_1_1970.toDateTime(DateTimeZone.UTC).plus(dateTimeMillisUtc);
		xLocation = x;
		yLocation = y;
	}
	
	public DateTime getDateTime() {
		return dateTime;
	}

	public long getMillis() {
		return dateTime.getMillis();
	}
}