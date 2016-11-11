package Game;

import Pieces.King;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Cell extends JPanel {

    private Piece piece;
    private JLabel content;
    int x, y;

    public Cell(int x,int y,Piece p)
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


    // Function to get the current piece in any cell
    public Piece getPiece()
    {
        return this.piece;
    }

}
