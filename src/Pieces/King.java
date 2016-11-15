package Pieces;

import java.util.ArrayList;
import java.util.Iterator;

import Game.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class King extends Piece {


    int x , y;

    public King(String id, String path, int color, int x, int y) {

        super.setColor(color);
        super.setId(id);
        super.setFilePath(path);
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


    public static void arrayCopy (Cell[][] chessBoard, Cell[][] tempBoardSet) {
        for (int i = 0; i < chessBoard.length; i++) {
            System.arraycopy(chessBoard[i], 0, tempBoardSet[i], 0, chessBoard[i].length);
        }
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

        Iterator<Cell> cellIterator = validMoves.iterator();
        while(cellIterator.hasNext()) {
            Cell[][] tempBoardSet = new Cell[8][8];
            arrayCopy(chessBoard, tempBoardSet);
            Cell validMoveCell = cellIterator.next();

            int tempX = validMoveCell.getCellX(), tempY = validMoveCell.getCellY();

            if (tempBoardSet[tempX][tempY].getPiece() != null) {
                tempBoardSet[tempX][tempY].removePiece();
            }
            tempBoardSet[tempX][tempY].setPiece(this);
            if(this.isOnCheck(tempBoardSet)) {
                validMoves.remove(validMoveCell);
            }
        }

        return validMoves;
    }

    public boolean isOnCheck(Cell chessBoard[][]) {

        // check for horizontal danger

        if(isOnCheckHorizontalAndVertical(chessBoard) && isOnCheckDiagonally(chessBoard) && isOnCheckWithKnight(chessBoard) ) {
            return true;
        }

        return false;
    }

    public boolean isOnCheckHorizontalAndVertical(Cell chessBoard[][]) {

        for(int i = x + 1; i < 8; i++)
        {
            if(chessBoard[i][y].getPiece() == null) {
                continue;
            }
            else if(chessBoard[i][y].getPiece().getColor() == this.getColor()) {
                break;
            }
            else
            {
                if ((chessBoard[i][y].getPiece() instanceof Rook) || (chessBoard[i][y].getPiece() instanceof Queen)) {
                    return true;
                }
                else {
                    break;
                }
            }
        }

        for(int i = x - 1; i >=  0; i--)
        {
            if(chessBoard[i][y].getPiece() == null) {
                continue;
            }
            else if(chessBoard[i][y].getPiece().getColor() == this.getColor()) {
                break;
            }
            else
            {
                if ((chessBoard[i][y].getPiece() instanceof Rook) || (chessBoard[i][y].getPiece() instanceof Queen)) {
                    return true;
                }
                else {
                    break;
                }
            }
        }

        for(int i = y + 1; i < 8; i++)
        {
            if(chessBoard[x][i].getPiece() == null) {
                continue;
            }
            else if(chessBoard[x][i].getPiece().getColor() == this.getColor()) {
                break;
            }
            else
            {
                if ((chessBoard[x][i].getPiece() instanceof Rook) || (chessBoard[x][i].getPiece() instanceof Queen)) {
                    return true;
                }
                else {
                    break;
                }
            }
        }

        for(int i = y - 1; i >=  0; i--)
        {
            if(chessBoard[x][i].getPiece() == null) {
                continue;
            }
            else if(chessBoard[x][i].getPiece().getColor() == this.getColor()) {
                break;
            }
            else
            {
                if ((chessBoard[x][i].getPiece() instanceof Rook) || (chessBoard[x][i].getPiece() instanceof Queen)) {
                    return true;
                }
                else {
                    break;
                }
            }
        }
        return false;
    }

    public boolean isOnCheckDiagonally(Cell chessBoard[][]) {

        int tempX = x + 1;
        int tempY = y - 1;

        while(tempX < 8 && tempY >= 0) {

            if(chessBoard[tempX][tempY].getPiece() == null) {

                tempX++;
                tempY--;
            }
            else if(chessBoard[tempX][tempY].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if(chessBoard[tempX][tempX].getPiece() instanceof Bishop || chessBoard[tempX][tempY].getPiece() instanceof Queen) {
                return true;
            }

        }

        tempX = x - 1;
        tempY = y + 1;

        while(x >= 0 && y < 8) {

            if(chessBoard[tempX][tempY].getPiece() == null) {
                tempX--;
                tempY++;
            }
            else if(chessBoard[tempX][tempY].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if(chessBoard[tempX][tempX].getPiece() instanceof Bishop || chessBoard[tempX][tempY].getPiece() instanceof Queen) {
                return true;
            }
        }

        tempX = x + 1;
        tempY = y + 1;
        while(tempX < 8 && tempY < 8) {
            if(chessBoard[tempX][tempY].getPiece() == null) {
                tempX++;
                tempY++;
            }
            else if(chessBoard[tempX][tempY].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if(chessBoard[tempX][tempX].getPiece() instanceof Bishop || chessBoard[tempX][tempY].getPiece() instanceof Queen) {
                return true;
            }
        }

        tempX = x - 1;
        tempY = y - 1;

        while (tempX >= 0 && tempY >= 0) {
            if(chessBoard[tempX][tempY].getPiece() == null) {
                tempX--;
                tempY--;
            }
            else if(chessBoard[tempX][tempY].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if(chessBoard[tempX][tempX].getPiece() instanceof Bishop || chessBoard[tempX][tempY].getPiece() instanceof Queen) {
                return true;
            }

        }
        return false;
    }

    private boolean isOnCheckWithKnight(Cell[][] chessBoard) {

        int[] validX = { x + 2, x + 2, x + 1, x + 1, x - 1, x - 1, x - 2, x - 2};
        int[] validY = { y + 1, y - 1, y + 2, y - 2, y + 2, y - 2, y + 1, y - 1};



        for(int i = 0 ; i < 8; i++) {

            int tempX = validX[i];
            int tempY = validY[i];
            if(tempX < 8 && tempX >= 0 && tempY >= 0 && tempY < 8) {

                if(chessBoard[tempX][tempY].getPiece() != null || chessBoard[tempX][tempY].getPiece().getColor() != this.getColor() && chessBoard[tempX][tempY].getPiece() instanceof Knight ) {
                    return true;
                }

            }
        }

        return false;

    }

//    private boolean isOnCheckWithPawn(Cell[][] chessBoard) {
//
//        if(this.getColor() == 0) {
//
//            //Can implement evaluation in this case
//            if(x == 0) {
//                return validMoves;
//            }
//
//            if(chessBoard[x - 1][y].getPiece() == null){
//
//                validMoves.add(chessBoard[x - 1][y]);
//                if(x == 6 && chessBoard[x - 2][y].getPiece() == null) {
//
//                    validMoves.add(chessBoard[x - 2][y]);
//                }
//            }
//
//            if(y > 0 && chessBoard[x - 1][y - 1].getPiece() != null && chessBoard[x-1][y-1].getPiece().getColor() != this.getColor()) {
//
//                validMoves.add(chessBoard[x - 1][y - 1]);
//            }
//
//            if(y < 7 && chessBoard[x - 1][y + 1].getPiece() != null && chessBoard[x-1][y+1].getPiece().getColor() != this.getColor()) {
//                validMoves.add(chessBoard[x - 1][y + 1]);
//            }
//        }
//
//        else {
//
//            //Can implement evaluation in this case
//            if(x == 7) {
//                return validMoves;
//            }
//
//            if(chessBoard[x + 1][y].getPiece() == null){
//
//                validMoves.add(chessBoard[x + 1][y]);
//                if(x == 1 && chessBoard[x + 2][y].getPiece() == null) {
//
//                    validMoves.add(chessBoard[x + 2][y]);
//                }
//            }
//
//            if(y > 0 && chessBoard[x + 1][y - 1].getPiece() != null && chessBoard[x+1][y-1].getPiece().getColor() != this.getColor()) {
//
//                validMoves.add(chessBoard[x + 1][y - 1]);
//            }
//
//            if(y < 7 && chessBoard[x + 1][y + 1].getPiece() != null && chessBoard[x+1][y+1].getPiece().getColor() != this.getColor()) {
//                validMoves.add(chessBoard[x + 1][y + 1]);
//            }
//        }
//
//    }



}
