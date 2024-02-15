package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

/**
 * Represent a invalid move on board.
 */
public class NullMove extends Move{

  public NullMove(){
    super(null, null, new Position(-1));
  }

  /**
   * Constructs a move with the given moving piece and destination position.
   *
   * @param board
   * @param movingPiece The piece making the move.
   * @param destination The destination position of the move.
   */
  public NullMove(Board board, Piece movingPiece, Position destination) {
    super(board, movingPiece, destination);
  }

  @Override
  public Board execute(){
    throw new RuntimeException("Null move cannot be executed.");
  }
}
