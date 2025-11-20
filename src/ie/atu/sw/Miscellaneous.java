package ie.atu.sw;

public sealed interface Miscellaneous<T> {
	record Element<T>(T value) implements Miscellaneous<T> {
	}

	record None<T>() implements Miscellaneous<T> {
	}
}
