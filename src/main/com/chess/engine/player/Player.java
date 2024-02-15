package main.com.chess.engine.player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.common.Side;
import main.com.chess.engine.moves.MoveStatus;
import main.com.chess.engine.moves.MoveTransition;
import main.com.chess.engine.board.Board;
import main.com.chess.engine.moves.Move;
import main.com.chess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract class representing a player in a chess game.
 */
public abstract class Player {
  /**
   * The chess board associated with the player.
   */
  protected final Board gameBoard;

  /**
   * The king piece of the player.
   */
  protected final Piece king;

  /**
   * The collection of legal moves available to the player.
   */
  protected final Collection<Move> playerLegalMoves;

  protected final boolean inCheck;

  /**
   * Constructs a player with the specified board and legal moves.
   *
   * @param board              The chess board.
   * @param playerLegalMoves   The legal moves available to the player.
   * @param opponentLegalMoves The legal moves available to the opponent player.
   */
  public Player(final Board board, final Collection<Move> playerLegalMoves, final Collection<Move> opponentLegalMoves) {
    this.gameBoard = board;
    this.king = initPlayerKing();
    this.playerLegalMoves = ImmutableList.copyOf(
                                                  Iterables.concat(playerLegalMoves,
                                                  getAllCastlingMoves(playerLegalMoves, opponentLegalMoves))
                                                );
    this.inCheck = !getAllAttackOnSquare(this.king.getPosition(), opponentLegalMoves).isEmpty();
  }

  /**
   * Retrieves the chess board associated with the player.
   *
   * @return The chess board.
   */
  public Board getGameBoard() {
    return this.gameBoard;
  }

  /**
   * Retrieves the king piece of the player.
   *
   * @return The king piece.
   */
  public Piece getKing() {
    return this.king;
  }

  /**
   * Retrieves the collection of legal moves available to the player.
   *
   * @return The collection of legal moves.
   */
  public Collection<Move> getAllLegalMoves() {
    return this.playerLegalMoves;
  }

  /**
   * Checks if a given move is legal for the current player.
   *
   * @param move The move to check.
   * @return {@code true} if the move is legal, {@code false} otherwise.
   */
  public boolean isMoveLegal(final Move move){
    return playerLegalMoves.contains(move);
  }

  /**
   * Checks if the current player is in check.
   *
   * @return {@code true} if the player is in check, {@code false} otherwise.
   */
  public boolean isInCheck(){
    return inCheck;
  }

  /**
   * Checks if the current player is in checkmate.
   *
   * @return {@code true} if the player is in checkmate, {@code false} otherwise.
   */
  public boolean isInCheckMate(){
    return inCheck && !hasEscapeMoves();
  }

  /**
   * Checks if the current player is in stalemate.
   *
   * @return {@code true} if the player is in stalemate, {@code false} otherwise.
   */
  public boolean isInStaleMate(){
    return !inCheck && !hasEscapeMoves();
  }

  /**
   * Checks if the current player has performed castling.
   *
   * @return {@code true} if the player has castled, {@code false} otherwise.
   */
  public boolean isCastled(){
    return false;
  }

  /**
   * Makes a move on the chess board and returns the transition resulting from the move.
   *
   * @param move The move to be made.
   * @return The move transition object representing the result of the move.
   */
  public MoveTransition makeMove(final Move move){
    if(!isMoveLegal(move)){
      return new MoveTransition(gameBoard, move, MoveStatus.ILLEGAL_MOVE);
    }
    final Board transitionBoard = move.execute();
    final Collection<Move> attackOnKing = getAllAttackOnSquare(
                                              transitionBoard.getCurrentPlayer().getOpponent().getKing().getPosition(),
                                              transitionBoard.getCurrentPlayer().getAllLegalMoves());
    if(!attackOnKing.isEmpty()){
      return new MoveTransition(gameBoard, move, MoveStatus.LEAVE_PLAYER_IN_CHECK);
    }
    return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
  }

  /**
   * Initializes and retrieves the player's king piece.
   *
   * @return The player's king piece.
   * @throws RuntimeException If unable to find or establish the player's king.
   */
  public Piece initPlayerKing() {
    for (final Piece piece : getAllActivePieces()) {
      if (piece.getType().isKing()) {
        return piece;
      }
    }
    throw new RuntimeException("Unable to find / establish player king.");
  }

  /**
   * Checks if the current player has any legal moves that allow them to escape from check.
   *
   * @return {@code true} if the player has escape moves, {@code false} otherwise.
   */
  public boolean hasEscapeMoves(){
    for(final Move move: playerLegalMoves){
      final MoveTransition transition = makeMove(move);
      if(transition.getStatus().isDone()){
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieves all moves from the given collection that are attacks on a specific square.
   *
   * @param pos        The position of the square being attacked.
   * @param legalMoves The collection of legal moves to check.
   * @return A collection of moves that attack the specified square.
   */
  public static Collection<Move> getAllAttackOnSquare(final Position pos, final Collection<Move> legalMoves){
    final List<Move> attackingMoves = new ArrayList<>();
    for(final Move move: legalMoves){
      if(move.isCapturingMove() && move.getDestination() == pos){
        attackingMoves.add(move);
      }
    }
    return ImmutableList.copyOf(attackingMoves);
  }

  /**
   * Retrieves the collection of all active pieces controlled by the player.
   *
   * @return The collection of all active pieces.
   */
  public abstract Collection<Piece> getAllActivePieces();

  /**
   * Retrieves the opponent player.
   *
   * @return The opponent player.
   */
  public abstract Player getOpponent();

  /**
   * Retrieves the player side
   * @return The player side {@code white, black}
   */
  public abstract Side getSide();

  public abstract Collection<Move> getAllCastlingMoves(final Collection<Move> playerLegalMoves,
                                                              final Collection<Move> opponentLegalMoves);
}
