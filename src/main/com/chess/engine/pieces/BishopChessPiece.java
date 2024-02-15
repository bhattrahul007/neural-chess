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
 * Represents the {@code Bishop} piece in our chess game.
 */
public class BishopChessPiece extends AbstractChessPiece implements Jumpable {
  private static final int OFFSET_TOP_LEFT = -9;
  private static final int OFFSET_TOP_RIGHT = -7;
  private static final int OFFSET_BOTTOM_LEFT = 7;
  private static final int OFFSET_BOTTOM_RIGHT = 9;
  private static final int[] OFFSETS = {OFFSET_TOP_LEFT, OFFSET_TOP_RIGHT, OFFSET_BOTTOM_LEFT, OFFSET_BOTTOM_RIGHT};

  public BishopChessPiece(Position position, Side side) {
    super(position, side, PieceType.BISHOP);
  }

  public BishopChessPiece(Position position, Side side, boolean moved) {
    super(position, side, PieceType.BISHOP, moved);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canJumpToSquareFromCurrentPosition(Position currPos, int offset) {
    int colIndex = BoardUtils.getColumnIndexFromPosition(currPos);

    if(colIndex == 0 && (offset == OFFSET_TOP_LEFT || offset == OFFSET_BOTTOM_LEFT)) return false;
    else if (colIndex == 7 && (offset == OFFSET_TOP_RIGHT || offset == OFFSET_BOTTOM_RIGHT)) return false;

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Move> generateAllMoves(Board board) {
    Set<Move> moves = new HashSet<>();

    for(final int offset: OFFSETS){
      Position currPos = position;
      Position computedPos = new Position(currPos.getX() + offset);

      // iterate for given offset until the square at that offset is occupied or the piece cannot jump
      // to that square.
      while(BoardUtils.isSquareValidInBoard(computedPos) && canJumpToSquareFromCurrentPosition(currPos, offset)){
        final BoardSquare computedSquare = BoardUtils.getSquare(board, computedPos);
        // if square at offset from current position is empty then
        // add it as progression move
        if(computedSquare.isEmpty()){
          moves.add(new MajorMove(this, computedPos));
        }else{
          // if the square at offset from current position is not empty then
          // only add move if the piece on that square if of different side
          final Piece computedSquarePiece = computedSquare.getOccupiedBy();
          final Side computedSquarePieceSide = computedSquarePiece.getSide();
          if(side != computedSquarePieceSide){
            moves.add(new CaptureMove(this, computedSquarePiece));
          }
          // break out of recursive offset loop because the subsequent squares are blocked
          // by same or different side
          break;
        }

        currPos = computedPos;
        computedPos = new Position(currPos.getX() + offset);
      }
    }

    return ImmutableSet.copyOf(moves);
  }
}
