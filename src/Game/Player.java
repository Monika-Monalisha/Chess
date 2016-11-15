package Game;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Player implements Serializable{

    private static final long serialVersionUID = 1L;
    private String name;
    private Integer gamesPlayed;
    private Integer gamesWon;

    public Player(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    public static ArrayList<Player> fetch_players()         //Function to fetch the list of the players
    {
        Player tempplayer;
        ObjectInputStream input = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try
        {
            File infile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
            System.out.println(infile);

            input = new ObjectInputStream(new FileInputStream(infile));
            try
            {
                while(true)
                {
                    tempplayer = (Player) input.readObject();
                    players.add(tempplayer);
                }
            }
            catch(EOFException e)
            {
                input.close();
            }
        }
        catch (FileNotFoundException e)
        {
            players.clear();
            return players;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            try {input.close();} catch (IOException e1) {}
            JOptionPane.showMessageDialog(null, "Unable to read the required Game files !!");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return players;
    }

    public void Update_Player()            //Function to update the statistics of a player
    {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        Player temp_player;
        File inputfile=null;
        File outputfile=null;
        try
        {
            inputfile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
            outputfile = new File(System.getProperty("user.dir")+ File.separator + "tempfile.dat");
        } catch (SecurityException e)
        {
            JOptionPane.showMessageDialog(null, "Read-Write Permission Denied !! Program Cannot Start");
            System.exit(0);
        }
        boolean playerdonotexist;
        try
        {
            if(outputfile.exists()==false)
                outputfile.createNewFile();
            if(inputfile.exists()==false)
            {
                output = new ObjectOutputStream(new java.io.FileOutputStream(outputfile,true));
                output.writeObject(this);
            }
            else
            {
                input = new ObjectInputStream(new FileInputStream(inputfile));
                output = new ObjectOutputStream(new FileOutputStream(outputfile));
                playerdonotexist=true;
                try
                {
                    while(true)
                    {
                        temp_player = (Player)input.readObject();
                        if (temp_player.name.equals(name))
                        {
                            output.writeObject(this);
                            playerdonotexist = false;
                        }
                        else
                            output.writeObject(temp_player);
                    }
                }
                catch(EOFException e){
                    input.close();
                }
                if(playerdonotexist)
                    output.writeObject(this);
            }
            inputfile.delete();
            output.close();
            File newf = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
            if(outputfile.renameTo(newf)==false)
                System.out.println("File Renameing Unsuccessful");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to read/write the required Game files !! Press ok to continue");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        }
        catch (Exception e)
        {

        }
    }


}
