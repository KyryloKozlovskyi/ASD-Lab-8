package ie.atu.sw;

import java.util.Collection;
import java.util.function.Predicate;

public sealed interface TicketOperation {
	record Purchase(Attendee attendee, Concert concert, double price) implements TicketOperation {
	};

	record Search(Collection<Concert> concerts, Predicate<Concert> criteria) implements TicketOperation {
	};

	record Advisory(Venue venue) implements TicketOperation {
	};
}
