package main.com.chess.engine.board;

import main.com.chess.engine.pieces.Piece;

/**
 * Interface representing a square on a chess board.
 */
public interface BoardSquare {

  /**
   * Checks if the square is empty (not occupied by any piece).
   *
   * @return {@code true} if the square is empty, {@code false} otherwise.
   */
  boolean isEmpty();

  /**
   * Gets the piece occupying this square, if any.
   *
   * @return The piece occupying this square, or {@code null} if the square is empty.
   */
  Piece getOccupiedBy();
}
