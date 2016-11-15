package Game;

import Pieces.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Cell extends JPanel {

    private Piece piece;
    private JLabel content;
    int x, y;
    private boolean isSelected = false;
    private boolean isCheck = false;
    private boolean isPossibleDestination = false;

    public int getCellX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getCellY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Cell(Cell cell) throws CloneNotSupportedException {
        this.x = cell.x;
        this.y = cell.y;
        setLayout(new BorderLayout());
        if((x + y) % 2 == 0) {
            setBackground(new Color(113,198,113));
        }
        else {
            setBackground(Color.white);
        }
        if(cell.getPiece() != null)
        {
            setPiece(cell.getPiece().getcopy());
        }
        else
            piece=null;
    }

    public Cell(int x, int y, Piece p)
    {
        this.x = x;
        this.y = y;

        setLayout(new BorderLayout());

        if((x + y) % 2 == 0)
            setBackground(new Color(113,198,113));

        else
            setBackground(Color.white);

        if(p != null)
            setPiece(p);
    }


    // Function to set a piece in any cell

    public void setPiece(Piece p)
    {
        piece=p;
        ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getFilePath()));
        content=new JLabel(img);
        this.add(content);
    }

    // Function to get the remove piece from any cell

    public void removePiece()
    {
        if (piece instanceof King)
        {
            piece = null;
            this.remove(content);
        }
        else
        {
            piece = null;
            this.remove(content);
        }
    }

    public void select()       //Function to mark a cell indicating it's selection
    {
        this.setBorder(BorderFactory.createLineBorder(Color.red,6));
        this.isSelected=true;
    }

    // Function to get the current piece in any cell
    public Piece getPiece()
    {
        return this.piece;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isPossibleDestination() {
        return isPossibleDestination;
    }

    public void setPossibleDestination() {
        this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
        this.isPossibleDestination=true;
    }

    public void removePossibleDestination()      //Remove the cell from the list of possible moves
    {
        this.setBorder(null);
        this.isPossibleDestination = false;
    }

    public void deSelect() {

        this.setBorder(null);
        this.isSelected=false;

    }

    @Override
    public String toString() {
        return "Cell{" +
                "piece=" + piece +
                ", x=" + x +
                ", y=" + y +
                ", isSelected=" + isSelected +
                ", isCheck=" + isCheck +
                ", isPossibleDestination=" + isPossibleDestination +
                '}';
    }
}
