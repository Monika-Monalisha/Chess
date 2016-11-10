package Pieces;

import java.util.ArrayList;
import Game.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Queen extends Piece {

    public Queen(String id, int color) {
        super.setColor(color);
        super.setId(id);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] chessBoard, int x, int y) {
        validMoves.clear();

        Bishop b = new Bishop("tempBishop", this.getColor());
        Rook r = new Rook("tempRook", this.getColor());
        validMoves.addAll(r.move(chessBoard,x,y));
        validMoves.addAll(b.move(chessBoard,x,y));
        return validMoves;
    }
}
