package Pieces;

import java.util.ArrayList;
import Game.*;
/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Rook extends Piece {

    public Rook(String id, int color) {
        super.setColor(color);
        super.setId(id);
    }

    public boolean checkValidCellRook(Cell currentCell) {

        if(currentCell.getPiece() == null){
            validMoves.add(currentCell);
        }
        else if(currentCell.getPiece().getColor() != this.getColor()) {
            validMoves.add(currentCell);
            return false;
        }
        else {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Cell> move(Cell[][] chessBoard, int x, int y) {
        validMoves.clear();

        int tempX = x - 1;
        while(tempX >= 0 ) {
            Cell currentCell = chessBoard[tempX][y];
            if(!checkValidCellRook(currentCell)) {
                break;
            }
            tempX--;
        }

        tempX = x + 1;
        while(tempX < 8){
            Cell currentCell = chessBoard[tempX][y];
            if(!checkValidCellRook(currentCell)) {
                break;
            }
            tempX++;
        }

        int tempY = y - 1;
        while(tempY >= 0 ) {
            Cell currentCell = chessBoard[x][tempY];
            if(!checkValidCellRook(currentCell)) {
                break;
            }
            tempY--;
        }

        tempY = y + 1;
        while(tempY < 8){
            Cell currentCell = chessBoard[x][tempY];
            if(!checkValidCellRook(currentCell)) {
                break;
            }
            tempY++;
        }
        return validMoves;
    }
}
