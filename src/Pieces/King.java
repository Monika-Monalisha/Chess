package Pieces;

import java.util.ArrayList;
import Game.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class King extends Piece {


    int x , y;

    public King(String id, int color, int x, int y) {

        super.setColor(color);
        super.setId(id);
        setX(x);
        setY(y);
    }

    //Basic Constructors for King Positions

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public ArrayList<Cell> move(Cell[][] chessBoard, int x, int y) {
        validMoves.clear();

        int[] validX = {x,x,x+1,x+1,x+1,x-1,x-1,x-1};
        int[] validY = {y-1,y+1,y-1,y,y+1,y-1,y,y+1};

        for(int i = 0 ; i < 8; i++) {

            int tempX = validX[i];
            int tempY = validY[i];
            if(tempX < 8 && tempX >= 0 && tempY >= 0 && tempY < 8) {

                if(chessBoard[tempX][tempY].getPiece() == null || chessBoard[tempX][tempY].getPiece().getColor() != this.getColor()) {
                    validMoves.add(chessBoard[tempX][tempY]);
                }

            }
        }

        return validMoves;
    }

    public boolean isOnCheck(Cell chessBoard[][]) {

        return false;
    }
}
