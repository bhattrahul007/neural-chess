package main.com.chess.engine.moves;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.board.ChessBoard;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.pieces.Piece;
import main.com.chess.engine.pieces.RookChessPiece;

import java.util.Objects;

/**
 * An abstract base class for castle move on board.
 */
public abstract class CastleMove extends Move{
  protected final Piece rook;
  protected final Position rookOrigin;
  protected final Position rookDestination;

  /**
   * Constructs a move with the given moving piece and destination position.
   *
   * @param board
   * @param movingPiece The piece making the move.
   * @param destination The destination position of the move.
   */
  public CastleMove(final Board board, final Piece movingPiece, final Position destination,
                    final Piece rook, final Position rookOrigin, final Position rookDestination) {
    super(board, movingPiece, destination);
    this.rook = rook;
    this.rookOrigin = rookOrigin;
    this.rookDestination = rookDestination;
  }

  @Override
  public boolean isCastlingMove(){
    return true;
  }

  public Piece getRook() {
    return rook;
  }

  public Position getRookOrigin() {
    return rookOrigin;
  }

  public Position getRookDestination() {
    return rookDestination;
  }

  @Override
  public Board execute() {
    ChessBoard.Builder builder = ChessBoard.builder();
    for(final var piece: board.getCurrentPlayer().getAllActivePieces()){
      if(!Objects.equals(movingPiece, piece) && !Objects.equals(rook, piece)){
        builder.setPiece(piece);
      }
    }
    for(final var piece: board.getCurrentPlayer().getOpponent().getAllActivePieces()){
      builder.setPiece(piece);
    }
    builder
      .setPiece(movingPiece.move(this))
      .setPiece(new RookChessPiece(new Position(rookDestination.getX()), rook.getSide(), true))
      .setNextMoveMaker(board.getCurrentPlayer().getOpponent().getSide());
    return builder.build();
  }
}
