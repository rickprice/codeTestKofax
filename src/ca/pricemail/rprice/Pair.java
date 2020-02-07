package ca.pricemail.rprice;

import java.util.Objects;

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