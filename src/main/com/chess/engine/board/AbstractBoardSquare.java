package main.com.chess.engine.board;

import com.google.common.collect.ImmutableMap;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * An abstract representation of a square on a chess board.
 */
public abstract class AbstractBoardSquare implements BoardSquare {
  /**
   * A map of cached empty squares based on their position index.
   */
  private static final Map<Integer, AbstractBoardSquare> CACHED_EMPTY_SQUARES = cacheEmptySquaresOnStartup();

  /**
   * The position of this board square.
   */
  protected Position position;

  /**
   * Constructs an abstract board square with the given position.
   *
   * @param pos The position of the board square.
   */
  protected AbstractBoardSquare(final Position pos) {
    this.position = pos;
  }

  /**
   * Retrieves the position of this board square.
   *
   * @return The position of the board square.
   */
  @Override
  public Position getPosition() {
    return this.position;
  }

  /**
   * Caches empty squares on startup to improve performance.
   *
   * @return A map of cached empty squares.
   */
  public static Map<Integer, AbstractBoardSquare> cacheEmptySquaresOnStartup() {
    final int TOTAL_SIZE = BoardUtils.BOARD_ROWS * BoardUtils.BOARD_COLS;
    Map<Integer, AbstractBoardSquare> cachedSquares = new HashMap<>();
    for (int i = 0; i < TOTAL_SIZE; i++) {
      cachedSquares.put(i, new EmptyBoardSquare(new Position(i)));
    }
    return ImmutableMap.copyOf(cachedSquares);
  }

  /**
   * Creates a board square based on the given index and piece.
   *
   * @param i     The index of the board square.
   * @param piece The piece occupying the square (or null if empty).
   * @return The board square created.
   */
  public static AbstractBoardSquare createSquare(final int i, final Piece piece) {
    return piece == null ? CACHED_EMPTY_SQUARES.get(i) : new OccupiedBoardSquare(new Position(i), piece);
  }

  /**
   * Checks if this board square is equal to another object.
   *
   * @param o The object to compare with this board square.
   * @return {@code true} if the board squares are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractBoardSquare that)) return false;
    return Objects.equals(position, that.position) && Objects.equals(getOccupiedBy(), that.getOccupiedBy())
      && Objects.equals(isEmpty(), that.isEmpty());
  }

  /**
   * Computes the hash code of this board square.
   *
   * @return The hash code of the board square.
   */
  @Override
  public int hashCode() {
    return Objects.hash(position, isEmpty(), getOccupiedBy());
  }
}
