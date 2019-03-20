package nl.jp.location.location;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Location {
	@Getter
	public ZonedDateTime dateTime;
	public double xLocation;
	public double yLocation;

	public Location(long dateTimeMillisUtc, double x, double y) {
		dateTime = ZonedDateTime.now(ZoneId.of("Z"));
		xLocation = x;
		yLocation = y;
	}

	public long getMillis() {
		if (dateTime == null) {
			return 0;
		}
		return dateTime.toInstant().toEpochMilli();
	}
}
