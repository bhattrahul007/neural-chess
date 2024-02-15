package main.com.chess.engine.board;

import com.google.common.collect.ImmutableList;
import main.com.chess.engine.common.Position;
import main.com.chess.engine.common.Side;
import main.com.chess.engine.pieces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the chess board.
 */
public class ChessBoard implements Board {
  private final List<BoardSquare> squares;

  private ChessBoard(Builder builder) {
    this.squares = initChessBoardSquares(builder);
  }

  /**
   * Retrieves the square at the specified position on the board.
   *
   * @param pos The position of the square to retrieve.
   * @return The board square at the specified position.
   */
  @Override
  public BoardSquare getSquare(Position pos) {
    try{
      return this.squares.get(pos.getX());
    }catch(IndexOutOfBoundsException e){
      return null;
    }
  }

  /**
   * Initializes the chess board squares based on the provided builder.
   *
   * @param builder The builder containing information about pieces and their positions.
   * @return The list of initialized board squares.
   */
  public static List<BoardSquare> initChessBoardSquares(Builder builder) {
    final int TOTAL_SIZE = BoardUtils.BOARD_ROWS * BoardUtils.BOARD_COLS;
    final BoardSquare[] squares = new BoardSquare[TOTAL_SIZE];
    for (int i = 0; i < TOTAL_SIZE; i++) {
      squares[i] = AbstractBoardSquare.createSquare(i, builder.pieces.get(i));
    }
    return ImmutableList.copyOf(squares);
  }


  /**
   * Initializes a standard chess board with all pieces in their starting positions.
   *
   * @return The initialized standard chess board.
   */
  public static ChessBoard initStandardChessBoard(){
    Builder builder = new Builder();
    // initialize black pieces
    builder
      .setPiece(new RookChessPiece(new Position(0), Side.BLACK))
      .setPiece(new KnightChessPiece(new Position(1), Side.BLACK))
      .setPiece(new BishopChessPiece(new Position(2), Side.BLACK))
      .setPiece(new QueenChessPiece(new Position(3), Side.BLACK))
      .setPiece(new KingChessPiece(new Position(4), Side.BLACK))
      .setPiece(new BishopChessPiece(new Position(5), Side.BLACK))
      .setPiece(new KnightChessPiece(new Position(6), Side.BLACK))
      .setPiece(new RookChessPiece(new Position(7), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(8), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(9), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(10), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(11), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(12), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(13), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(14), Side.BLACK))
      .setPiece(new PawnChessPiece(new Position(15), Side.BLACK));

    // white pieces
    builder
      .setPiece(new RookChessPiece(new Position(56), Side.WHITE))
      .setPiece(new KnightChessPiece(new Position(57), Side.WHITE))
      .setPiece(new BishopChessPiece(new Position(58), Side.WHITE))
      .setPiece(new QueenChessPiece(new Position(59), Side.WHITE))
      .setPiece(new KingChessPiece(new Position(60), Side.WHITE))
      .setPiece(new BishopChessPiece(new Position(61), Side.WHITE))
      .setPiece(new KnightChessPiece(new Position(62), Side.WHITE))
      .setPiece(new RookChessPiece(new Position(63), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(48), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(49), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(50), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(51), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(52), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(53), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(54), Side.WHITE))
      .setPiece(new PawnChessPiece(new Position(55), Side.WHITE));

    // side who is allowed to make move
    builder.setNextMoveMaker(Side.WHITE);

    return builder.build();
  }

  /**
   * Creates a new instance of the ChessBoard.Builder.
   *
   * @return A new ChessBoard builder.
   */
  public static Builder builder(){
    return new Builder();
  }

  /**
   * Generates a string representation of the chess board.
   *
   * @return A string representation of the chess board.
   */
  @Override
  public String toString(){
    final int TOTAL_SIZE = BoardUtils.BOARD_ROWS * BoardUtils.BOARD_COLS;
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<TOTAL_SIZE; i++){
      sb.append(this.squares.get(i).toString());
      if(((i+1)%BoardUtils.BOARD_ROWS) == 0){
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * Builder class for creating instances of ChessBoard.
   */
  public static class Builder {
    private final Map<Integer, Piece> pieces;
    private Side nextMoveMaker;

    /**
     * Constructs a new ChessBoard builder.
     */
    private Builder() {
      this.pieces = new HashMap<>();
    }

    /**
     * Sets the side of the player to make the next move.
     *
     * @param side The side of the player to make the next move.
     * @return The builder instance.
     */
    public Builder setNextMoveMaker(final Side side) {
      this.nextMoveMaker = side;
      return this;
    }

    /**
     * Sets a piece on the board.
     *
     * @param piece The piece to set on the board.
     * @return The builder instance.
     */
    public Builder setPiece(final Piece piece) {
      this.pieces.put(piece.getPosition().getX(), piece);
      return this;
    }

    /**
     * Builds and returns an instance of ChessBoard based on the builder's configuration.
     *
     * @return An instance of ChessBoard.
     */
    public ChessBoard build() {
      return new ChessBoard(this);
    }
  }
}
