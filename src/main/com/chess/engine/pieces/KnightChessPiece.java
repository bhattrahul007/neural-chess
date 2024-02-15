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
 * Represents a knight chess piece.
 */
public class KnightChessPiece extends AbstractChessPiece implements Jumpable {
  // Constants representing possible knight moves
  private static final int OFFSET_TOP_LEFT = -17;
  private static final int OFFSET_TOP_RIGHT = -15;
  private static final int OFFSET_LEFT_TOP = -10;
  private static final int OFFSET_RIGHT_TOP = -6;
  private static final int OFFSET_LEFT_BOTTOM = 6;
  private static final int OFFSET_RIGHT_BOTTOM = 10;
  private static final int OFFSET_BOTTOM_LEFT = 15;
  private static final int OFFSET_BOTTOM_RIGHT = 17;

  private static final int[] OFFSETS = {
    OFFSET_TOP_LEFT, OFFSET_TOP_RIGHT, OFFSET_LEFT_TOP, OFFSET_RIGHT_TOP,
    OFFSET_LEFT_BOTTOM, OFFSET_RIGHT_BOTTOM, OFFSET_BOTTOM_LEFT, OFFSET_BOTTOM_RIGHT
  };

  /**
   * Constructs a knight chess piece with the given position and side.
   *
   * @param position The position of the knight on the board.
   * @param side     The side (color) of the knight.
   */
  public KnightChessPiece(Position position, Side side) {
    super(position, side, PieceType.KNIGHT);
  }

  public KnightChessPiece(Position position, Side side, boolean moved) {
    super(position, side, PieceType.KNIGHT, moved);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canJumpToSquareFromCurrentPosition(final Position currPos, final int offset) {
    int colIndex = BoardUtils.getColumnIndexFromPosition(currPos);

    if(colIndex == 0 && ( offset == OFFSET_TOP_LEFT || offset == OFFSET_LEFT_TOP ||
                          offset == OFFSET_LEFT_BOTTOM || offset == OFFSET_BOTTOM_LEFT)) return false;

    else if (colIndex == 1 && (offset == OFFSET_LEFT_TOP || offset == OFFSET_LEFT_BOTTOM)) return false;

    else if (colIndex == 6 && (offset == OFFSET_RIGHT_BOTTOM || offset == OFFSET_RIGHT_TOP)) return false;

    else return colIndex != 7 || (offset != OFFSET_BOTTOM_RIGHT && offset != OFFSET_RIGHT_BOTTOM &&
                                  offset != -OFFSET_RIGHT_TOP && offset != -OFFSET_TOP_RIGHT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Move> generateAllMoves(Board board) {
    Set<Move> moves = new HashSet<>();

    for (final int offset : OFFSETS) {
      Position currPos = this.position;
      Position computedPos = new Position(currPos.getX() + offset);

      // Check if the square is valid and if the knight can jump to it
      if (BoardUtils.isSquareValidInBoard(computedPos) && canJumpToSquareFromCurrentPosition(currPos, offset)) {
        final BoardSquare computedSquare = BoardUtils.getSquare(board, computedPos);

        if (computedSquare.isEmpty()) {
          // Add move for an empty square
          moves.add(new MajorMove(this, computedPos)); // Replace null with actual move implementation
        } else {
          // Add move for capturing opponent's piece
          final Piece computedSquarePiece = computedSquare.getOccupiedBy();
          final Side computedSquarePieceSide = computedSquarePiece.getSide();
          if (side != computedSquarePieceSide) {
            moves.add(new CaptureMove(this, computedSquarePiece)); // Replace null with actual move implementation
          }
        }
      }
    }
    return ImmutableSet.copyOf(moves);
  }
}
