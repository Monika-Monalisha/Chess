package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Pieces.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Main extends JFrame implements MouseListener{

    private static final int height = 1000;
    private static final int widtg = 1000;
    private static Rook whiteRook1, whiteRook2, blackRook1, blackRook2;
    private static Bishop whiteBishop1, whiteBishop2, blackBishop1, blackBishop2;
    private static Knight whiteKnight1, whiteKnight2, blackKnight1, blackKnight2;
    private static Queen whiteQueen, blackQueen;
    private static King whiteKing, blackKing;
    private static Pawn whitePawns[], blackPawns[];

    private Cell current, previous;
    private int chance = 0;
    private Cell chessBoard[][];
    private ArrayList<Cell> destinationCells = new ArrayList<>();
    private Player whitePlayer, blackPlayer;

    private JPanel board = new JPanel(new GridLayout(8,8));
    private JPanel whiteDetials = new JPanel(new GridLayout(3,3));
    private JPanel blackDetials = new JPanel(new GridLayout(3,3));
    private JPanel whiteComboPanel = new JPanel();
    private JPanel blackComboPanel = new JPanel();
    private JPanel controlPanel,WhitePlayer,BlackPlayer,temp,displayTime,showPlayer,time;

    private JSplitPane split;

    private JLabel label,moveLabel;
    private static JLabel chanceLabel;

    private Time timer;
    public static Main mainBoard;
    private boolean selected = false, end = false;
    private Container content;

    private ArrayList<Player> whitePlayers, blackPlayers;
    private ArrayList<String> whitePlayerNames = new ArrayList<String>();
    private ArrayList<String> blackPlayerNames = new ArrayList<String>();

    private JComboBox<String> wcombo,bcombo;
    private String whiteName = null, blackName = null, winner = null;

    static String move;
    private Player tempPlayer;

    private JScrollPane wscroll,bscroll;
    private String[] whiteNames = {}, blackNames = {};
    private JSlider timeSlider;
    private BufferedImage image;
    private Button start,whiteSelect, blackSelect, whiteNewPlayer , blackNewPlayer;
    public static int remaningTime = 60;

    public static void main(String[] args) {

        whiteBishop1 = new Bishop("WB1", "White_Bishop.png", 0);
        whiteBishop2 = new Bishop("WB2", "White_Bishop.png", 0);
        blackBishop1 = new Bishop("BB1", "Black_Bishop.png", 1);
        blackBishop2 = new Bishop("BB2", "Black_Bishop.png", 1);

        whiteRook1 = new Rook("WR1", "White_Rook.png", 0);
        whiteRook2 = new Rook("WR2", "White_Rook.png", 0);
        blackRook1 = new Rook("BR1", "Black_Rook.png", 1);
        blackRook2 = new Rook("BR2", "Black_Rook.png", 1);

        whiteKnight1 = new Knight("WK1", "White_Knight.png", 0);
        whiteKnight2 = new Knight("WK2", "White_Knight.png", 0);
        blackKnight1 = new Knight("BK1", "Black_Knight.png", 1);
        blackKnight2 = new Knight("BK2", "Black_Knight.png", 1);

        whiteKing = new King("WhiteKing", "White_King.png", 0, 7, 3);
        blackKing = new King("BlackKing", "Black_King.png", 1, 0 , 3);

        whiteQueen = new Queen("WhiteQueen", "White_Queen.png", 0);
        blackQueen = new Queen("BlackQueen", "Black_Queen.png", 1);

        whitePawns = new Pawn[8];
        blackPawns = new Pawn[8];
        for(int i = 0; i < 8; i++) {

            whitePawns[i] = new Pawn("WP0" + i, "White_Pawn.png", 0 );
            blackPawns[i] = new Pawn("BP0" + i, "Black_Pawn.png", 1 );
        }

        mainBoard = new Main();
        mainBoard.setVisible(true);
        mainBoard.setResizable(true);
    }




    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
