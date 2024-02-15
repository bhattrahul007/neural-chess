package main.com.chess.engine.player;

import main.com.chess.engine.board.Board;
import main.com.chess.engine.moves.Move;
import main.com.chess.engine.pieces.Piece;

import java.util.Collection;

/**
 * Represents the black player in chess game.
 */
public class BlackPlayer extends Player{
  public BlackPlayer(final Board board, final Collection<Move> playerLegalMoves, final Collection<Move> opponentLegalMoves){
    super(board, playerLegalMoves, opponentLegalMoves);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Piece> getAllActivePieces(){
    return gameBoard.getAllActiveBlackPieces();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getOpponent(){
    return gameBoard.getWhitePlayer();
  }
}
