package ca.pricemail.rprice;

import java.util.Objects;

// This is a Pair class that I put together from examples on Stack Overflow
// https://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples
// I took two answers that seemed good, and paired them together...
// Its tragic that Java doesn't have a proper pair class in Java8

public class Pair<L, R> {

	public final L left;
	public final R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public boolean equals(Object o) {
		return o instanceof Pair && Objects.equals(left, ((Pair<?, ?>) o).left)
				&& Objects.equals(right, ((Pair<?, ?>) o).right);
	}

	public int hashCode() {
		return 31 * Objects.hashCode(left) + Objects.hashCode(right);
	}

	public String toString() {
		return "(" + left + "," + right + ")";
	}

	static <L, R> Pair<L, R> of(L left, R right) {
		return new Pair<L, R>(left, right);
	}
}