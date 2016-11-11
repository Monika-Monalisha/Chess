package Pieces;

import java.util.ArrayList;
import Game.*;

/**
 * Created by shubham.goyal on 11/10/16.
 *
 * This is an abstract class to from which all the other pieces will be inherited. This will contain all the methods
 * which are common across all the pieces.
 *
 * Move() will be an abstract function which will be implemented based on the specific piece.
 */
public abstract class Piece implements Cloneable{

    private int color;
    private String id = null;
    private String filePath = null;

    protected ArrayList<Cell> validMoves = new ArrayList<>();

    public abstract ArrayList<Cell> move(Cell chessBoard[][], int x, int y);

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //This function will return same piece with different reference
    public Piece getcopy() throws CloneNotSupportedException
    {
        return (Piece) this.clone();
    }
}
