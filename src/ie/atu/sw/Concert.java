package ie.atu.sw;

import java.time.LocalDateTime;
import static java.util.Objects.requireNonNull;

public record Concert(String id, Artist artist, LocalDateTime date, Venue venue) {

	public Concert(Artist artist, LocalDateTime date, Venue venue) {
		this(java.util.UUID.randomUUID().toString(), artist, date, venue);
	}

	public Concert {
		requireNonNull(id);
		requireNonNull(artist);
		requireNonNull(date);
		requireNonNull(venue);

		// The concert date must be in the future.
		if (date.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Concert date must be in the future.");
		}
	}
}
