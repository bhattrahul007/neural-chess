package main.com.chess.engine.board;

import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

/**
 * Represents an empty square on the chess board.
 */
public class EmptyBoardSquare extends AbstractBoardSquare {
  /**
   * Constructs an empty board square with the given position.
   *
   * @param pos The position of the empty square.
   */
  public EmptyBoardSquare(Position pos) {
    super(pos);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Piece getOccupiedBy() {
    return null;
  }


  @Override
  public String toString() {
    return String.format("%10s", "EMPTY");
  }
}
