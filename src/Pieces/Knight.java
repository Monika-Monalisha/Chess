package Pieces;


import java.util.ArrayList;
import Game.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Knight extends Piece {

    public Knight( String id, String path, int color) {

        super.setColor(color);
        super.setId(id);
        super.setFilePath(path);
    }

    @Override
    public ArrayList<Cell> move(Cell[][] chessBoard, int x, int y) {

        validMoves.clear();
        int[] validX = { x + 2, x + 2, x + 1, x + 1, x - 1, x - 1, x - 2, x - 2};
        int[] validY = { y + 1, y - 1, y + 2, y - 2, y + 2, y - 2, y + 1, y - 1};


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
}
