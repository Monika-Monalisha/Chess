package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import Pieces.*;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Main extends JFrame implements MouseListener{

    private static final int height = 700;
    private static final int width = 1200;
    private static Rook whiteRook1, whiteRook2, blackRook1, blackRook2;
    private static Bishop whiteBishop1, whiteBishop2, blackBishop1, blackBishop2;
    private static Knight whiteKnight1, whiteKnight2, blackKnight1, blackKnight2;
    private static Queen whiteQueen, blackQueen;
    private static King whiteKing, blackKing;
    private static Pawn whitePawns[], blackPawns[];

    //To be used by mouse click function
    private Cell current, previous;


    private int currentChance = 0;

    private Cell chessBoard[][];

    private ArrayList<Cell> destinationCells = new ArrayList<>();
    private Player whitePlayer = null, blackPlayer = null;

    private JPanel board = new JPanel(new GridLayout(8,8));
    private JPanel whiteComboPanel = new JPanel();
    private JPanel blackComboPanel = new JPanel();
    private JPanel controlPanel, whitePlayerPanel, blackPlayerPanel,temp,displayTime,showPlayer,time;

    private JSplitPane split;

    private JLabel timeLabel, moveLabel;
    private static JLabel chanceLabel;

    private Time timer;
    public static Main mainBoard;
    private boolean selected = false, end = false;
    private Container content;

    private ArrayList<Player> whitePlayers, blackPlayers;
    private ArrayList<String> whitePlayerNames = new ArrayList<String>();
    private ArrayList<String> blackPlayerNames = new ArrayList<String>();

    private JComboBox<String> whiteComboBox, blackComboBox;
    private String whiteName = null, blackName = null, winner = null;

    static String currentMove;
    private Player tempPlayer;

    private JScrollPane whiteScroll, blackScroll;
    private String[] whiteNames = {}, blackNames = {};
    private JSlider timeSlider;
    private BufferedImage image;
    private Button startGame, whiteSelect, blackSelect, whiteNewPlayer , blackNewPlayer;
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

    public void resetChessBoard() {
        chessBoard = new Cell[8][8];
        for(int i = 0; i < 8; i++) {
            Cell cell;
            Piece p;

            for(int j = 0; j < 8; j++) {

                p = null;
                if(i == 0) {

                    if(j == 0) {
                        p = blackRook1;
                    }
                    else if(j == 1) {
                        p = blackKnight1;
                    }
                    else if(j == 2) {
                        p = blackBishop1;
                    }
                    else if(j == 3){
                        p = blackKing;
                    }
                    else if(j == 4) {
                        p = blackQueen;
                    }
                    else if( j == 5) {
                        p = blackBishop2;
                    }
                    else if(j == 6) {
                        p = blackKnight2;
                    }
                    else {
                        p = blackRook2;
                    }
                }
                else if(i == 1) {
                    p = blackPawns[j];
                }
                else if(i == 6) {
                    p = whitePawns[i];
                }
                else if(i == 7) {
                    if(j == 0) {
                        p = whiteRook1;
                    }
                    else if( j == 1){
                        p = whiteKnight1;
                    }
                    else if(j == 2) {
                        p = whiteBishop1;
                    }
                    else if(j == 3) {
                        p = whiteKing;
                    }
                    else if(j == 4){
                        p = whiteQueen;
                    }
                    else if(j == 5){
                        p = whiteBishop2;
                    }
                    else if(j == 6){
                        p = whiteKnight2;
                    }
                    else {
                        p = whiteRook2;
                    }
                }
                cell = new Cell(i , j, p);
                cell.addMouseListener(this);
                board.add(cell);
                chessBoard[i][j] = cell;
            }

        }
    }


    //Constructor for UI elements and Listeners
    private Main() {

        remaningTime = 60;
        timeSlider = new JSlider();
        currentMove = "White";

        board = new JPanel(new GridLayout(8,8));
        board.setMinimumSize(new Dimension(800, 800));
        board.setBorder(BorderFactory.createLoweredBevelBorder());

        ImageIcon icon = new ImageIcon(this.getClass().getResource("icon.png"));
        this.setIconImage(icon.getImage());


        whitePlayers = Player.fetch_players();
        Iterator<Player> iterator =  whitePlayers.iterator();
        while(iterator.hasNext()) {
            whitePlayerNames.add(iterator.next().getName());
            blackPlayerNames.add(iterator.next().getName());
        }

        whiteNames = whitePlayerNames.toArray(whiteNames);
        blackNames = blackPlayerNames.toArray(blackNames);


        content = getContentPane();
        setSize(width, height);
        setTitle("Chess");
        content.setBackground(Color.black);
        content.setLayout(new BorderLayout());

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3,3));
        controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP, TitledBorder.CENTER,new Font("Lucida Calligraphy",Font.PLAIN,20), Color.ORANGE));

        whitePlayerPanel = new JPanel();
        whitePlayerPanel.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.RED));
        whitePlayerPanel.setLayout(new BorderLayout());

        blackPlayerPanel = new JPanel();
        blackPlayerPanel.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.BLUE));
        blackPlayerPanel.setLayout(new BorderLayout());

        JPanel whiteStats = new JPanel(new GridLayout(3,3));
        JPanel blackStats = new JPanel(new GridLayout(3,3));
        whiteComboBox = new JComboBox<String>(whiteNames);
        blackComboBox = new JComboBox<String>(blackNames);

        whiteScroll = new JScrollPane(whiteComboBox);
        blackScroll = new JScrollPane(blackComboBox);

        whiteComboPanel.setLayout(new FlowLayout());
        blackComboPanel.setLayout(new FlowLayout());

        whiteSelect = new Button("Select");
        blackSelect = new Button("Select");

        whiteSelect.addActionListener(new SelectPlayerHandler(0));
        blackSelect.addActionListener(new SelectPlayerHandler(1));
        whiteNewPlayer = new Button("New Player");
        blackNewPlayer = new Button("New Player");

        whiteNewPlayer.addActionListener(new NewPlayerHandler(0));
        blackNewPlayer.addActionListener(new NewPlayerHandler(1));

        whiteComboPanel.add(whiteScroll);
        whiteComboPanel.add(whiteSelect);
        whiteComboPanel.add(whiteNewPlayer);
        blackComboPanel.add(blackScroll);
        blackComboPanel.add(blackSelect);
        blackComboPanel.add(blackNewPlayer);
        whitePlayerPanel.add(whiteComboPanel, BorderLayout.NORTH);
        blackPlayerPanel.add(blackComboPanel, BorderLayout.NORTH);

        whiteStats.add(new JLabel("Name : "));
        whitePlayerPanel.add(whiteStats, BorderLayout.WEST);

        blackStats.add(new JLabel("Name : "));
        blackPlayerPanel.add(blackStats, BorderLayout.WEST);

        controlPanel.add(whitePlayerPanel);
        controlPanel.add(blackPlayerPanel);

        resetChessBoard();

        timeSlider.setMinimum(1);
        timeSlider.setMaximum(15);
        timeSlider.setValue(1);
        timeSlider.setMajorTickSpacing(2);
        timeSlider.setPaintLabels(true);
        timeSlider.setPaintTicks(true);
        timeSlider.addChangeListener(new TimeChange());

        showPlayer = new JPanel(new FlowLayout());
        showPlayer.add(timeSlider);


        startGame = new Button("Start");
        startGame.setBackground(Color.black);
        startGame.addActionListener(new Start());
        startGame.setPreferredSize(new Dimension(120,40));

        displayTime = new JPanel(new FlowLayout());
        displayTime.add(startGame);

        JLabel setTime=new JLabel("Set Timer(in mins):");
        setTime.setFont(new Font("Arial",Font.BOLD,16));
        timeLabel = new JLabel("Time Starts now", JLabel.CENTER);
        timeLabel.setFont(new Font("SERIF", Font.BOLD, 30));

        time = new JPanel(new GridLayout(3,3));
        time.add(setTime);
        time.add(showPlayer);
        time.add(displayTime);

        controlPanel.add(time);

        temp = new JPanel(){
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                try {
                    image = ImageIO.read(this.getClass().getResource("clash.jpg"));
                } catch (IOException ex) {
                    System.out.println("not found");
                }

                g.drawImage(image, 0, 0, null);
            }
        };
        temp.setMinimumSize(new Dimension(800,700));
        controlPanel.setMinimumSize(new Dimension(285,700));
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,temp, controlPanel);

        content.add(split);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }


    @Override
    public void mouseClicked(MouseEvent e) {

        current = (Cell) e.getSource();
        if(previous == null) {

            if(current.getPiece() != null) {
                if(current.getPiece().getColor() != currentChance) {
                    return;
                }

                current.select();
                previous = current;
                destinationCells.clear();
                destinationCells = current.getPiece().move(chessBoard, current.x, current.y);
                System.out.println("destinationCells" + destinationCells);
                destinationCells = new ArrayList<Cell>(filterDestinationToPreventCheck(destinationCells, current));

//                if (chessBoard[getKing(currentChance).getCellX()][getKing(currentChance).getCellY()].isCheck())
//                else if (destinationCells.isEmpty() == false && willKingBeInDanger(c, destinationCells.get(0)))
//                    destinationlist.clear();)

            }
        }
        else {
            if(current.getCellX() == previous.getCellX() && current.getCellY() == previous.getCellY()) {
                current.deSelect();
                cleanDestinations(destinationCells);
                destinationCells.clear();
                previous = null;
            }
            else if (current.getPiece() != null || previous.getPiece().getColor() != current.getPiece().getColor()) {

                if(current.isPossibleDestination()) {
                    if(current.getPiece() != null) {
                        current.removePiece();
                    }
                    current.setPiece(previous.getPiece());
                    if(previous.isCheck()) {
                        previous.setCheck(false);
                    }
                    previous.removePiece();
                    if(getKing(currentChance ^ 1).isOnCheck(chessBoard)) {

                        chessBoard[getKing(currentChance ^ 1).getX()][getKing(currentChance ^ 1).getY()].setCheck(true);
                        if(isCheckMate(currentChance ^ 1)) {
                            previous.deSelect();
                        }
                    }
                    if(getKing(currentChance).isOnCheck(chessBoard) == false) {
                        chessBoard[getKing(currentChance).getX()][getKing(currentChance).getY()].setCheck(false);
                    }
                    if (current.getPiece() instanceof King) {
                        ((King) current.getPiece()).setX(current.x);
                        ((King) current.getPiece()).setY(current.y);
                    }

                    changeChance();
                    if(!end) {
                        timer.reset();
                        timer.start();
                    }


                }
                if(previous != null) {
                    previous.deSelect();
                    previous = null;
                }
                cleanDestinations(destinationCells);
                destinationCells.clear();
            }
            else if(previous.getPiece().getColor() == current.getPiece().getColor()) {
                previous.deSelect();
                cleanDestinations(destinationCells);
                destinationCells.clear();
                current.select();
                previous = current;
                destinationCells = current.getPiece().move(chessBoard, current.x, current.y);
                destinationCells = new ArrayList<Cell>(filterDestinationToPreventCheck(destinationCells, current));

            }
        }

        highlightDestinations(destinationCells);

        if(current.getPiece() != null && current.getPiece() instanceof King) {
            ((King) current.getPiece()).setX(current.x);
            ((King) current.getPiece()).setY(current.y);

        }

    }

    private boolean isCheckMate(int i) {

        return false;
    }

    //A function to clean the highlights of possible destination cells
    private void cleanDestinations(ArrayList<Cell> destinationCells)      //Function to clear the last move's destinations
    {
        ListIterator<Cell> it = destinationCells.listIterator();
        while (it.hasNext()) {
            it.next().removePossibleDestination();
        }
    }

    //A function that indicates the possible moves by highlighting the Cells
    private void highlightDestinations(ArrayList<Cell> destinationCells) {
        ListIterator<Cell> it = destinationCells.listIterator();
        while (it.hasNext()) {
            it.next().setPossibleDestination();
        }

    }

    public static void arrayCopy (Cell[][] chessBoard, Cell[][] tempBoardSet) {
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {

                try {
                    tempBoardSet[i][j] = new Cell(chessBoard[i][j]);
                }
                catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ArrayList<Cell> filterDestinationToPreventCheck(ArrayList<Cell> destinationCells, Cell current) {

        ArrayList<Cell> newlist = new ArrayList<Cell>();
        Iterator<Cell> cellIterator = destinationCells.iterator();

        System.out.println( "COORIDNIFN" + current.getCellX() + ' ' + current.getCellY());

        while(cellIterator.hasNext()) {
            Cell[][] tempBoardSet = new Cell[8][8];
            arrayCopy(chessBoard, tempBoardSet);


            Cell validMoveCell = cellIterator.next();

            int tempX = validMoveCell.getCellX(), tempY = validMoveCell.getCellY();
            if (tempBoardSet[tempX][tempY].getPiece() != null) {
                tempBoardSet[tempX][tempY].removePiece();
            }
            tempBoardSet[tempX][tempY].setPiece(tempBoardSet[current.getCellX()][current.getCellY()].getPiece());
            int x = getKing(currentChance).getX();
            int y = getKing(currentChance).getY();

            if (tempBoardSet[current.getCellX()][current.getCellY()].getPiece() instanceof King) {
                ((King) (tempBoardSet[tempX][tempY].getPiece())).setX(tempX);
                ((King) (tempBoardSet[tempX][tempY].getPiece())).setY(tempY);
                x = tempX;
                y = tempY;
            }

            tempBoardSet[current.x][current.y].removePiece();
            if (((King) tempBoardSet[x][y].getPiece()).isOnCheck(tempBoardSet) == false) {
                newlist.add(validMoveCell);
            }
        }
        return newlist;
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

    private class TimeChange implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            remaningTime = timeSlider.getValue() * 60;
        }
    }

    private class Start implements ActionListener {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent e) {

//            if(whitePlayer == null && blackPlayer == null) {
//                JOptionPane.showMessageDialog(controlPanel, "Fill in the details");
//                return;
//            }

//            whiteNewPlayer.disable();
//            blackNewPlayer.disable();
//            whiteSelect.disable();
//            blackSelect.disable();

            //Remove temporary Chess Clash Image and show ChessBoard
            split.remove(temp);
            split.add(board);

            moveLabel = new JLabel("Move : ");
            moveLabel.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
            moveLabel.setForeground(Color.red);

            chanceLabel = new JLabel(currentMove);
            chanceLabel.setFont(new Font("Comic Sans MS",Font.BOLD,20));
            chanceLabel.setForeground(Color.blue);

            showPlayer.remove(timeSlider);
            showPlayer.add(moveLabel);
            showPlayer.add(chanceLabel);

            displayTime.remove(startGame);
            displayTime.add(timeLabel);
            timer = new Time(timeLabel);
            timer.start();


        }
    }

    private class SelectPlayerHandler implements ActionListener {

        int color;

        public SelectPlayerHandler(int i) {
            this.color = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            tempPlayer = null;
            String playerName = (color == 0 ) ? whiteName : blackName;
            JComboBox<String> currentComboBox = (color == 0 ) ? whiteComboBox : blackComboBox;
            JComboBox<String> otherComboBox = (color == 0) ? blackComboBox : whiteComboBox;
            ArrayList<Player> playerArrayList = (color == 0) ? whitePlayers : blackPlayers;
            ArrayList<Player> allPlayersList = Player.fetch_players();
            if( allPlayersList.isEmpty()) {
                return;
            }

            JPanel detailsPanel = (color == 0) ? whitePlayerPanel : blackPlayerPanel;
            JPanel comboPanel = (color == 0) ? whiteComboPanel : whiteComboPanel;

            if(selected == true) {
                detailsPanel.removeAll();
            }
            playerName = (String) currentComboBox.getSelectedItem();
            Iterator<Player> iterator = playerArrayList.iterator();
            Iterator<Player> otherPlayerIterator = allPlayersList.iterator();
            while(iterator.hasNext()) {
                Player temp = iterator.next();
                if(temp.getName().equals(playerName)) {
                    tempPlayer = temp;
                }
            }

            while(otherPlayerIterator.hasNext()) {
                Player temp = otherPlayerIterator.next();
                if(temp.getName().equals(playerName)) {
                    allPlayersList.remove(temp);
                }
            }

            if(tempPlayer == null) {
                return;
            }
            if(color == 0) {
                whitePlayer = tempPlayer;
            }
            else {
                blackPlayer = tempPlayer;
            }

            blackPlayers = allPlayersList;
            otherComboBox.removeAllItems();
            for(Player p : allPlayersList) {
                otherComboBox.addItem(p.getName());
            }

            detailsPanel.add(new JLabel(" " + tempPlayer.getName()));
            detailsPanel.add(new JLabel(" " + tempPlayer.getGamesPlayed()));
            detailsPanel.add(new JLabel(" " + tempPlayer.getGamesWon()));

            comboPanel.revalidate();
            comboPanel.repaint();
            comboPanel.add(detailsPanel);
            selected = true;

        }
    }

    private class NewPlayerHandler implements ActionListener {

        int color;
        public NewPlayerHandler(int i) {
            this.color = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String playerName = (color == 0) ? whiteName : blackName;
            JPanel playerPanel = (color == 0) ? whitePlayerPanel : blackPlayerPanel;
            ArrayList<Player> allPlayers = Player.fetch_players();
            Iterator<Player> iterator = allPlayers.iterator();
            JPanel playerDetailsPanel = (color == 0) ? whiteComboPanel : blackComboPanel;

            playerName = JOptionPane.showInputDialog(playerPanel , "Enter Your Name ");
            if( playerName != null) {
                while(iterator.hasNext()) {
                    if(iterator.next().getName().equals(playerName)) {
                        JOptionPane.showMessageDialog(playerPanel, "Player exists");
                        return;
                    }
                }

                if(playerName.length() != 0) {
                    Player temp = new Player(playerName);

                    temp.Update_Player();
                    if(color == 0) {
                        whitePlayer = temp;
                    }
                    else {
                        blackPlayer = temp;
                    }
                }
            }
            else {
                return;
            }

            playerDetailsPanel.removeAll();
            playerDetailsPanel.add(new JLabel(" " + playerName));
            playerDetailsPanel.add(new JLabel("0"));
            playerDetailsPanel.add(new JLabel("0"));
            playerPanel.revalidate();
            playerPanel.repaint();
            playerPanel.add(playerDetailsPanel);
            selected = true;

        }
    }

    //A function to retrieve the Black King or White King
    private King getKing(int color)
    {
        if (color == 0) {
            return whiteKing;
        }
        else {
            return blackKing;
        }
    }


    public void changeChance() {


    }
}
