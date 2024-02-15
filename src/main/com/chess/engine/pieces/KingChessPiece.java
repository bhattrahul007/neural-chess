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
 * Represents a King chess piece.
 */
public class KingChessPiece extends AbstractChessPiece implements Jumpable {
  // Constants representing possible King moves
  private static final int OFFSET_TOP_LEFT = -9;
  private static final int OFFSET_TOP = -8;
  private static final int OFFSET_TOP_RIGHT = -7;
  private static final int OFFSET_LEFT = -1;
  private static final int OFFSET_RIGHT = 1;
  private static final int OFFSET_BOTTOM_LEFT = 7;
  private static final int OFFSET_BOTTOM = 8;
  private static final int OFFSET_BOTTOM_RIGHT = 9;

  private static final int[] OFFSETS = {
    OFFSET_TOP_LEFT, OFFSET_TOP, OFFSET_TOP_RIGHT, OFFSET_LEFT, OFFSET_RIGHT,
    OFFSET_BOTTOM_LEFT, OFFSET_BOTTOM, OFFSET_BOTTOM_RIGHT
  };

  /**
   * Constructs a King chess piece with the given position and side.
   *
   * @param position The position of the King on the board.
   * @param side     The side (color) of the King.
   */
  public KingChessPiece(Position position, Side side) {
    super(position, side, PieceType.KING);
  }

  public KingChessPiece(Position position, Side side, boolean moved) {
    super(position, side, PieceType.KING, moved);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canJumpToSquareFromCurrentPosition(final Position currPos, final int offset) {
    int colIndex = BoardUtils.getColumnIndexFromPosition(currPos);

    if(colIndex == 0 && (offset == OFFSET_TOP_LEFT || offset == OFFSET_LEFT || offset == OFFSET_BOTTOM_LEFT)) return false;
    if(colIndex == 7 && (offset == OFFSET_TOP_RIGHT || offset == OFFSET_RIGHT || offset == OFFSET_BOTTOM_RIGHT)) return false;
    return true;
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

      // Check if the square is valid and if the King can jump to it
      if (BoardUtils.isSquareValidInBoard(computedPos) && canJumpToSquareFromCurrentPosition(currPos, offset)) {
        final BoardSquare computedSquare = BoardUtils.getSquare(board, computedPos);

        if (computedSquare.isEmpty()) {
          // Add move for an empty square
          moves.add(new MajorMove(this, computedPos));
        } else {
          // Add move for capturing opponent's piece
          final Piece computedSquarePiece = computedSquare.getOccupiedBy();
          final Side computedSquarePieceSide = computedSquarePiece.getSide();
          if (side != computedSquarePieceSide) {
            moves.add(new CaptureMove(this, computedSquarePiece));
          }
        }
      }
    }
    return ImmutableSet.copyOf(moves);
  }
}
