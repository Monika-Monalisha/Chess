package Pieces;

import java.util.ArrayList;
import Game.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Bishop extends Piece {

    public Bishop( String id, String path, int color) {

        super.setColor(color);
        super.setId(id);
        super.setFilePath(path);
    }

    // Function to check a particular cell if the move is valid for Bishop
    public void checkValidCellBishop(Cell chessBoard[][], int tempX, int tempY) {

        if(chessBoard[tempX][tempY].getPiece() == null) {

            validMoves.add(chessBoard[tempX][tempY]);
        }
        else if(chessBoard[tempX][tempY].getPiece().getColor() == this.getColor()) {
            return;
        }
        else {
            validMoves.add(chessBoard[tempX][tempY]);
            return;
        }
    }


    // This function implements the list of valid moves that can be taken by bishop in chess board
    // Bishop Move : Can move diagonallay in all our directions

    @Override
    public ArrayList<Cell> move(Cell chessBoard[][], int x, int y) {

        validMoves.clear();

        int tempX = x + 1;
        int tempY = y - 1;

        while(tempX < 8 && tempY >= 0) {

            checkValidCellBishop(chessBoard, tempX, tempY);
            tempX++;
            tempY--;
        }

        tempX = x - 1;
        tempY = y + 1;

        while(x >= 0 && y < 8) {

            checkValidCellBishop(chessBoard, tempX, tempY);
            tempX--;
            tempY++;
        }

        tempX = x + 1;
        tempY = y + 1;
        while(tempX < 8 && tempY < 8) {
            checkValidCellBishop(chessBoard, tempX, tempY);
            tempX++;
            tempY++;
        }

        tempX = x - 1;
        tempY = y - 1;

        while (tempX >= 0 && tempY >= 0) {
            checkValidCellBishop(chessBoard, tempX, tempY);
            tempX--;
            tempY--;
        }

        return validMoves;
    }

}
