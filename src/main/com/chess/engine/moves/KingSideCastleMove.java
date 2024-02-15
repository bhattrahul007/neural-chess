package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

/**
 * Represents King side castle movement [see {@link CastleMove}].
 */
public class KingSideCastleMove extends CastleMove{
  /**
   * Constructs a move with the given moving piece and destination position.
   *
   * @param board
   * @param movingPiece The piece making the move.
   * @param destination The destination position of the move.
   */
  public KingSideCastleMove(final Board board, final Piece movingPiece, final Position destination,
                            final Piece rook, final Position rookOrigin, final Position rookDestination) {
    super(board, movingPiece, destination, rook, rookOrigin, rookDestination);
  }

  @Override
  public String toString(){
    return "0-0";
  }
}
