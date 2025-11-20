package ie.atu.sw;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TicketSystem {

	public static Ticket purchase(TicketOperation.Purchase pur) {
		return purchase(pur.attendee(), pur.concert(), pur.price());
	}

	public static Ticket purchase(Attendee attendee, Concert concert, double price) {
		var advisory = advisory(new TicketOperation.Advisory(concert.venue()));
		return new Ticket(concert, attendee, price, advisory);
	}

	// Refactor to use Miscellaneous
	public static String advisory(TicketOperation.Advisory adv) {
		var result = advisory(adv.venue());
		return switch (result) {
		case Miscellaneous.Element<String> element -> element.value();
		case Miscellaneous.None<String> none -> "Enjoy the concert.";
		};
	}

	/*
	 * Original Version, uncomment to run public static String
	 * advisory(TicketOperation.Advisory adv) { var opt = advisory(adv.venue());
	 * return opt.isPresent() ? opt.get() : "Enjoy the concert."; }
	 */

	// Refactor to use Miscellaneous
	public static Miscellaneous<String> advisory(Venue venue) {

		var sb = new StringBuilder();
		// Bring a passport if traveling
		if (!venue.country().isEUMember())
			sb.append("Bring a passport if traveling.");
		// Bring light clothing
		if (venue.country().temperature() > 13.00d)
			sb.append("Bring light clothing.");
		// Bring waterproof clothing
		if (venue.country().precipitation() > 1000)
			sb.append("Bring waterproof clothing.");
		// Large crowds expected
		if (venue.capacity() > 10000)
			sb.append("Large crowds expected.");

		sb.append(switch (venue.type()) {
		case AMPHITHEATER, ARENA, STADIUM -> "This venue contains tiered seating.";
		default -> "";
		});

		return sb.length() > 0 ? new Miscellaneous.Element<>(sb.toString()) : new Miscellaneous.None<>();
	}

	/*
	 * Original Version, uncomment to run public static Optional<String>
	 * advisory(Venue venue) {
	 * 
	 * var sb = new StringBuilder(); // Bring a passport if traveling if
	 * (!venue.country().isEUMember()) sb.append("Bring a passport if traveling.");
	 * // Bring light clothing if (venue.country().temperature() > 13.00d)
	 * sb.append("Bring light clothing."); // Bring waterproof clothing if
	 * (venue.country().precipitation() > 1000)
	 * sb.append("Bring waterproof clothing."); // Large crowds expected if
	 * (venue.capacity() > 10000) sb.append("Large crowds expected.");
	 * 
	 * sb.append(switch (venue.type()) { case AMPHITHEATER, ARENA, STADIUM ->
	 * "This venue contains tiered seating."; default -> ""; });
	 * 
	 * return sb.length() > 0 ? Optional.of(sb.toString()) : Optional.of(null); }
	 */

	public static Collection<Concert> search(TicketOperation.Search sch) {
		return search(sch.concerts(), sch.criteria());
	}

	public static Collection<Concert> search(Collection<Concert> concerts, Predicate<Concert> criteria) {
		return concerts.stream().filter(criteria).collect(Collectors.toList());
	}
}
