package main.com.chess.engine.pieces;

import com.google.common.collect.ImmutableSet;
import main.com.chess.engine.board.Board;
import main.com.chess.engine.board.BoardSquare;
import main.com.chess.engine.board.BoardUtils;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.common.Side;
import main.com.chess.engine.moves.CaptureMove;
import main.com.chess.engine.moves.MajorMove;
import main.com.chess.engine.moves.Move;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents pawn piece in chess game.
 */
public class PawnChessPiece extends AbstractChessPiece implements Jumpable{
  private static final int BOTTOM = 8;
  private static final int BOTTOM_LEFT = 7;
  private static final int BOTTOM_RIGHT = 9;
  private static final int[] OFFSETS = {BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT};

  public PawnChessPiece(Position position, Side side) {
    super(position, side, PieceType.PAWN);
  }

  public PawnChessPiece(Position position, Side side, boolean moved) {
    super(position, side, PieceType.PAWN, moved);
  }

  /**
   *{@inheritDoc}
   */
  @Override
  public boolean canJumpToSquareFromCurrentPosition(Position currPos, int offset) {
    int colIndex = BoardUtils.getColumnIndexFromPosition(currPos);

    if(colIndex == 0 && ((offset == BOTTOM_RIGHT && side.isWhite()) || (offset == BOTTOM_LEFT && side.isBlack()))) return false;
    else if (colIndex == 7 && ((offset==BOTTOM_LEFT && side.isWhite()) || (offset == BOTTOM_RIGHT && side.isBlack()))) return false;

    return true;
  }

  /**
   *{@inheritDoc}
   */
  @Override
  public Collection<Move> generateAllMoves(Board board) {
    Set<Move> moves = new HashSet<>();
    int pawnMovingDirection = side == Side.BLACK ? 1 : -1;

    for(final int offset: OFFSETS){
      Position currPos = position;
      int pawnMovingOffset = pawnMovingDirection * offset;
      Position computedPos = new Position(currPos.getX() + pawnMovingOffset);

      if(BoardUtils.isSquareValidInBoard(computedPos) && canJumpToSquareFromCurrentPosition(currPos, offset)){
        final BoardSquare computedSquare = BoardUtils.getSquare(board, computedPos);

        if(offset == BOTTOM && computedSquare.isEmpty()){
          moves.add(new MajorMove(board,this, computedPos));

          // check for double pawn which is only possible if lower square is empty
          // double jump square is empty
          // and pawn is making its first move
          final Position doubleComputedPos = new Position(computedPos.getX() + pawnMovingOffset);
          final BoardSquare doubleComputedSquare = BoardUtils.getSquare(board, doubleComputedPos);
          if(!moved && doubleComputedSquare != null && doubleComputedSquare.isEmpty()){
            moves.add(new MajorMove(board, this, doubleComputedPos));
          }
        }
        else if (!computedSquare.isEmpty()){
          // pawn can only move diagonally if there is an opponent piece to be captured or during
          // en-passant move (which is not implemented here).
          final Piece computedSquarePiece = computedSquare.getOccupiedBy();
          final Side computedSquarePieceSide = computedSquarePiece.getSide();
          if(side != computedSquarePieceSide){
            moves.add(new CaptureMove(board, this, computedSquarePiece));
          }
        }
      }
    }

    return ImmutableSet.copyOf(moves);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PawnChessPiece move(final Move move){
    return new PawnChessPiece(move.getDestination(), move.getMovingPiece().getSide(), true);
  }
}
