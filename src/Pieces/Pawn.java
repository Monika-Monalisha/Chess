package Pieces;

import java.util.ArrayList;
import Game.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Pawn extends Piece {

    public Pawn(String id, int color) {
        super.setColor(color);
        super.setId(id);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] chessBoard, int x, int y) {

        validMoves.clear();

        if(this.getColor() == 0) {

            //Can implement evaluation in this case
            if(x == 0) {
                return validMoves;
            }

            if(chessBoard[x - 1][y].getPiece() == null){

                validMoves.add(chessBoard[x - 1][y]);
                if(x == 6 && chessBoard[x - 2][y].getPiece() == null) {

                    validMoves.add(chessBoard[x - 2][y]);
                }
            }

            if(y > 0 && chessBoard[x - 1][y - 1].getPiece() != null && chessBoard[x-1][y-1].getPiece().getColor() != this.getColor()) {

                validMoves.add(chessBoard[x - 1][y - 1]);
            }

            if(y < 7 && chessBoard[x - 1][y + 1].getPiece() != null && chessBoard[x-1][y+1].getPiece().getColor() != this.getColor()) {
                validMoves.add(chessBoard[x - 1][y + 1]);
            }
        }

        else {

            //Can implement evaluation in this case
            if(x == 7) {
                return validMoves;
            }

            if(chessBoard[x + 1][y].getPiece() == null){

                validMoves.add(chessBoard[x + 1][y]);
                if(x == 1 && chessBoard[x + 2][y].getPiece() == null) {

                    validMoves.add(chessBoard[x + 2][y]);
                }
            }

            if(y > 0 && chessBoard[x + 1][y - 1].getPiece() != null && chessBoard[x+1][y-1].getPiece().getColor() != this.getColor()) {

                validMoves.add(chessBoard[x + 1][y - 1]);
            }

            if(y < 7 && chessBoard[x + 1][y + 1].getPiece() != null && chessBoard[x+1][y+1].getPiece().getColor() != this.getColor()) {
                validMoves.add(chessBoard[x + 1][y + 1]);
            }
        }

        return validMoves;
    }
}
