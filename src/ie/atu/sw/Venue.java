package ie.atu.sw;

import static java.util.Objects.requireNonNull;

public record Venue(String id, String name, VenueType type, Country country, int capacity) {
	public static final int MIN_CAPACITY = 100;

	public Venue(String name, VenueType type, Country country, int capacity) {
		this(java.util.UUID.randomUUID().toString(), name, type, country, capacity);
	}

	public Venue {
		requireNonNull(id);
		requireNonNull(name);
		requireNonNull(type);
		requireNonNull(country);

		// A venue capacity must be at lease 100.
		if (capacity < MIN_CAPACITY) {
			throw new IllegalArgumentException("Venue capacity must be at least 100.");
		}
	}
}
