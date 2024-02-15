package main.com.chess.engine.common;

import java.util.Objects;

/**
 * Represents a position on a chessboard using a single coordinate (x).
 */
public class Position {
  private final int x;

  /**
   * Constructs a new Position object with the specified x-coordinate.
   *
   * @param x The x-coordinate of the position.
   */
  public Position(final int x) {
    this.x = x;
  }

  /**
   * Gets the x-coordinate of the position.
   *
   * @return The x-coordinate.
   */
  public int getX() {
    return x;
  }

  /**
   * Checks if this Position is equal to another object.
   *
   * @param o The object to compare with.
   * @return {@code true} if the objects are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Position position = (Position) o;
    return x == position.x;
  }

  /**
   * Computes the hash code of this Position.
   *
   * @return The hash code value for this Position.
   */
  @Override
  public int hashCode() {
    return Objects.hash(x);
  }

  /**
   * Returns a string representation of this Position.
   *
   * @return A string representation of this Position.
   */
  @Override
  public String toString() {
    return "Position{x=" + x + '}';
  }
}
