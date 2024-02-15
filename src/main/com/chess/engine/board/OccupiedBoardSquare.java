package main.com.chess.engine.board;

import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

/**
 * Represents an occupied square on the chess board.
 */
public class OccupiedBoardSquare extends AbstractBoardSquare{
  protected Piece occupier;

  /**
   * Constructs an occupied square with the given position and piece.
   * @param pos The position of the square.
   * @param piece  The piece that is present on the square.
   */
  public OccupiedBoardSquare(final Position pos, final Piece piece){
    super(pos);
    this.occupier = piece;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty(){
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Piece getOccupiedBy(){
    return occupier;
  }

  @Override
  public String toString(){
    return String.format("%10s", occupier.toString());
  }
}
