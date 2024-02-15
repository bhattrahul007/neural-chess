package main.com.chess.engine.pieces;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.common.Side;
import main.com.chess.engine.moves.Move;

import java.util.Collection;

/**
 * An interface representing a chess piece.
 */
public interface Piece {

  /**
   * Gets the position of the piece on the chessboard.
   *
   * @return The position of the piece.
   */
  public Position getPosition();

  /**
   * Gets the side (color) of the piece.
   *
   * @return The side of the piece (WHITE or BLACK).
   */
  public Side getSide();

  /**
   * Gets the type of the piece.
   *
   * @return The type of the piece (KING, QUEEN, ROOK, KNIGHT, BISHOP, or PAWN).
   */
  public PieceType getType();

  /**
   * checks whether the piece have moved from its starting position.
   * @return true if piece have moved false otherwise
   * */
  public boolean hasMoved();

  /**
   * Calculates the legal moves for the piece on the specified board.
   * The legality of moves is determined based on the current state of the board,
   * including the positions of other pieces and game rules.
   *
   * @param board The board on which to calculate the moves.
   * @return A Collection of legal moves that the piece can make on the board.
   */
  public Collection<Move> generateAllMoves(final Board board);

  /**
   * Executes the given move and returns the piece that was moved.
   *
   * @param move The move to execute.
   * @return The piece that was moved.
   */
  public Piece move(Move move);
}

