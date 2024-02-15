package main.com.chess.engine.board;

import main.com.chess.engine.common.Position;

/**
 * Interface representing a chess board.
 */
public interface Board {

  /**
   * Gets the square at the specified position on the board.
   *
   * @param pos The position of the square to retrieve.
   * @return The square at the specified position, or {@code null} if the position is invalid.
   */
  BoardSquare getSquare(final Position pos);
}
