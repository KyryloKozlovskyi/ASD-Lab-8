package ie.atu.sw;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

public record Artist(String id, String name, String genre) {
	enum Genre {
		BLUES, COUNTRY, FOLK, HIP_HOP, JAZZ, METAL, POP, PUNK, ROCK, SOUL
	}

	public Artist(String name, Genre genre) {
		this(UUID.randomUUID().toString(), name, genre.name());
	}

	public Artist {
		requireNonNull(id);
		requireNonNull(name);
		requireNonNull(genre);
	}
}
