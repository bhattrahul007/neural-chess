package main.com.chess.engine.board;

import main.com.chess.engine.common.Position;

/**
 * Utility class for working with chess board positions.
 */
public class BoardUtils {

  private static final int BOARD_ROWS = 8;
  private static final int BOARD_COLS = 8;

  /**
   * Checks if a given position is within the bounds of the chess board.
   *
   * @param pos The position to check.
   * @return {@code true} if the position is within the board bounds, {@code false} otherwise.
   */
  private static boolean isPositionInBoardRange(final Position pos) {
    int posx = pos.getX();
    int rowIndex = posx / BOARD_ROWS;
    int colIndex = posx % BOARD_COLS;
    return rowIndex >= 0 && rowIndex < BOARD_ROWS && colIndex >= 0 && colIndex < BOARD_COLS;
  }

  /**
   * Checks if a given position corresponds to a valid square on the chess board.
   *
   * @param pos The position to check.
   * @return {@code true} if the position corresponds to a valid square on the board, {@code false} otherwise.
   */
  public static boolean isSquareValidInBoard(final Position pos) {
    return isPositionInBoardRange(pos);
  }

  /**
   * Gets the column index of a given position on the chess board.
   *
   * @param pos The position for which to retrieve the column index.
   * @return The column index of the position.
   */
  public static int getColumnIndexFromPosition(final Position pos) {
    int posx = pos.getX();
    return posx % BOARD_COLS;
  }

  /**
   * Gets the board square at the specified position on the chess board.
   *
   * @param board The chess board.
   * @param pos   The position of the square to retrieve.
   * @return The board square at the specified position, or {@code null} if the position is invalid.
   */
  public static BoardSquare getSquare(final Board board, final Position pos) {
    if (isPositionInBoardRange(pos))
      return board.getSquare(pos);
    return null;
  }
}
