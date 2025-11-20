package ie.atu.sw;

import static java.util.Objects.requireNonNull;

public record Ticket(String id, Concert concert, Attendee attendee, double price, String advisory) {

	public Ticket(Concert concert, Attendee attendee, double price, String advisory) {
		this(java.util.UUID.randomUUID().toString(), concert, attendee, price, advisory);
	}

	public Ticket {
		requireNonNull(id);
		requireNonNull(concert);
		requireNonNull(attendee);
		requireNonNull(advisory);

		// A ticket may be given for free, but a ticket price cannot be negative.
		if (price < 0) {
			throw new IllegalArgumentException("Ticket price cannot be negative.");
		}
	}
}
