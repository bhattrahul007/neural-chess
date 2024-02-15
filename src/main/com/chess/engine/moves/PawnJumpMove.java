package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.board.ChessBoard;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;

import java.util.Objects;

/**
 * Represents the double jump move by pawn on board.
 */
public class PawnJumpMove extends Move{
  /**
   * Constructs a move with the given moving piece and destination position.
   *
   * @param board
   * @param movingPiece The piece making the move.
   * @param destination The destination position of the move.
   */
  public PawnJumpMove(Board board, Piece movingPiece, Position destination) {
    super(board, movingPiece, destination);
  }

  @Override
  public Board execute(){
    ChessBoard.Builder builder = ChessBoard.builder();
    for(final var piece: board.getCurrentPlayer().getAllActivePieces()){
      if(!Objects.equals(piece, movingPiece)){
        builder.setPiece(piece);
      }
    }
    for(final var piece: board.getCurrentPlayer().getOpponent().getAllActivePieces()){
      builder.setPiece(piece);
    }
    Piece movedPawnInstance = movingPiece.move(this);
    builder
      .setPiece(movedPawnInstance)
      .setEnPassant(movedPawnInstance)
      .setNextMoveMaker(board.getCurrentPlayer().getOpponent().getSide());
    return builder.build();
  }
}
