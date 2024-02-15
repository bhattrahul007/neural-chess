package main.com.chess.engine.player;

import com.google.common.collect.ImmutableList;
import main.com.chess.engine.board.Board;
import main.com.chess.engine.board.BoardSquare;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.common.Side;
import main.com.chess.engine.moves.KingSideCastleMove;
import main.com.chess.engine.moves.Move;
import main.com.chess.engine.moves.QueenSideCastleMove;
import main.com.chess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

  /**
   * {@inheritDoc}
   */
  @Override
  public Side getSide(){
    return Side.BLACK;
  }

  @Override
  public Collection<Move> getAllCastlingMoves(final Collection<Move> legalMoves, final Collection<Move> opponentLegalMoves){
    final List<Move> castleMoves = new ArrayList<>();

    if(!king.hasMoved() && !inCheck){

      // king side castle calculation for white player
      if(gameBoard.getSquare(new Position(5)).isEmpty() && gameBoard.getSquare(new Position(6)).isEmpty()){
        final BoardSquare kingSideRookSquare = gameBoard.getSquare(new Position(7));
        if(!kingSideRookSquare.isEmpty()){
          final Piece kingSideRook = kingSideRookSquare.getOccupiedBy();
          if(kingSideRook.getType().isRook() && !kingSideRook.hasMoved()){
            // now check if king side squares are not being attacked by
            // opponent pieces
            if(getAllAttackOnSquare(new Position(5), opponentLegalMoves).isEmpty() &&
              getAllAttackOnSquare(new Position(6), opponentLegalMoves).isEmpty()){
              castleMoves.add(new KingSideCastleMove(gameBoard, king, new Position(6),
                              kingSideRook, new Position(7), new Position(5)));
            }
          }
        }
      }

      // queen side castle calculation for white player

      if( gameBoard.getSquare(new Position(3)).isEmpty() &&
        gameBoard.getSquare(new Position(2)).isEmpty() &&
        gameBoard.getSquare(new Position(1)).isEmpty()){

        final BoardSquare queenSideRookSquare = gameBoard.getSquare(new Position(0));
        if(!queenSideRookSquare.isEmpty()){
          final Piece queenSideRook = queenSideRookSquare.getOccupiedBy();
          if(queenSideRook.getType().isRook() && !queenSideRook.hasMoved()){
            if(getAllAttackOnSquare(new Position(3), opponentLegalMoves).isEmpty() &&
              getAllAttackOnSquare(new Position(2), opponentLegalMoves).isEmpty() &&
              getAllAttackOnSquare(new Position(1), opponentLegalMoves).isEmpty()){
              castleMoves.add(new QueenSideCastleMove(gameBoard, king, new Position(2),
                              queenSideRook, new Position(0), new Position(3)));
            }
          }
        }
      }
    }

    return ImmutableList.copyOf(castleMoves);
  }
}
