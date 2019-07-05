package nl.jp.location.location;

import java.time.Instant;
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
		dateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(dateTimeMillisUtc), ZoneId.of("Z"));
		xLocation = x;
		yLocation = y;
	}

	public Long getMillis() {
		if (dateTime == null) {
			return null;
		}
		return dateTime.toInstant().toEpochMilli();
	}
}
