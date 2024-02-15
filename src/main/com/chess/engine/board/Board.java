package main.com.chess.engine.board;

import main.com.chess.engine.common.Position;
import main.com.chess.engine.moves.Move;
import main.com.chess.engine.pieces.Piece;
import main.com.chess.engine.player.Player;

import java.util.Collection;

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

  /**
   * Retrieves all active white pieces on the board.
   *
   * @return A collection of all active white pieces.
   */
  public Collection<Piece> getAllActiveWhitePieces();

  /**
   * Retrieves all active black pieces on the board.
   *
   * @return A collection of all active black pieces.
   */
  public Collection<Piece> getAllActiveBlackPieces();

  /**
   * Retrieves the white player in the chess game.
   *
   * @return The white player.
   */
  public Player getWhitePlayer();

  /**
   * Retrieves the black player in the chess game.
   *
   * @return The black player.
   */
  public Player getBlackPlayer();

  public Player getCurrentPlayer();

  /**
   * Retrieves all the move that can be played by both the players on board.
   * @return The collection of moves.
   */
  public Iterable<Move> getAllLegalMoves();
}
