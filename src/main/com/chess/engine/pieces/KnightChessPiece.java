package main.com.chess.engine.pieces;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.common.Side;
import main.com.chess.engine.moves.Move;

import java.util.Collection;

public class KnightChessPiece extends AbstractChessPiece{

  protected KnightChessPiece(Position position, Side side) {
    super(position, side, PieceType.KNIGHT);
  }


  protected KnightChessPiece(Position position, Side side, boolean moved) {
    super(position, side, PieceType.KNIGHT, moved);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Move> generateMoves(Board board) {
    return null;
  }
}
