package Pieces;

import java.util.ArrayList;
import Game.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Queen extends Piece {

    public Queen(String id, String path, int color) {
        super.setColor(color);
        super.setId(id);
        super.setFilePath(path);
    }


    @Override
    public ArrayList<Cell> move(Cell[][] chessBoard, int x, int y) {
        validMoves.clear();

        Bishop b = new Bishop("tempBishop", "path", this.getColor());
        Rook r = new Rook("tempRook", "path", this.getColor());
        validMoves.addAll(r.move(chessBoard,x,y));
        validMoves.addAll(b.move(chessBoard,x,y));
        return validMoves;
    }
}
