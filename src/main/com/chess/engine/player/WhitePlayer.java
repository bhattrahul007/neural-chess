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
 * Represents the white player in chess game.
 */
public class WhitePlayer extends Player{

  public WhitePlayer(final Board board, final Collection<Move> playerLegalMoves, final Collection<Move> opponentLegalMoves){
    super(board, playerLegalMoves, opponentLegalMoves);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Piece> getAllActivePieces(){
    return gameBoard.getAllActiveWhitePieces();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Player getOpponent(){
    return gameBoard.getBlackPlayer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Side getSide(){
    return Side.WHITE;
  }

  @Override
  public Collection<Move> getAllCastlingMoves(final Collection<Move> legalMoves, final Collection<Move> opponentLegalMoves){
    final List<Move> castleMoves = new ArrayList<>();

    if(!king.hasMoved() && !inCheck){

      // king side castle calculation for white player
      if(gameBoard.getSquare(new Position(61)).isEmpty() && gameBoard.getSquare(new Position(62)).isEmpty()){
        final BoardSquare kingSideRookSquare = gameBoard.getSquare(new Position(63));
        if(!kingSideRookSquare.isEmpty()){
          final Piece kingSideRook = kingSideRookSquare.getOccupiedBy();
          if(kingSideRook.getType().isRook() && !kingSideRook.hasMoved()){
            // now check if king side squares are not being attacked by
            // opponent pieces
            if(getAllAttackOnSquare(new Position(61), opponentLegalMoves).isEmpty() &&
               getAllAttackOnSquare(new Position(62), opponentLegalMoves).isEmpty()){
              castleMoves.add(new KingSideCastleMove(gameBoard, king, new Position(62),
                              kingSideRook, new Position(63), new Position(61)));
            }
          }
        }
      }

      // queen side castle calculation for white player

      if( gameBoard.getSquare(new Position(59)).isEmpty() &&
          gameBoard.getSquare(new Position(58)).isEmpty() &&
          gameBoard.getSquare(new Position(57)).isEmpty()){

        final BoardSquare queenSideRookSquare = gameBoard.getSquare(new Position(56));
        if(!queenSideRookSquare.isEmpty()){
          final Piece queenSideRook = queenSideRookSquare.getOccupiedBy();
          if(queenSideRook.getType().isRook() && !queenSideRook.hasMoved()){
            if(getAllAttackOnSquare(new Position(59), opponentLegalMoves).isEmpty() &&
               getAllAttackOnSquare(new Position(58), opponentLegalMoves).isEmpty() &&
               getAllAttackOnSquare(new Position(57), opponentLegalMoves).isEmpty()){
              castleMoves.add(new QueenSideCastleMove(gameBoard, king, new Position(58),
                              queenSideRook, new Position(56), new Position(59)));
            }
          }
        }
      }
    }

    return ImmutableList.copyOf(castleMoves);
  }
}
