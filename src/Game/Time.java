package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shubham.goyal on 11/10/16.
 */
public class Time {

    private JLabel label;
    Timer timer;
    int remainingTime;

    public Time(JLabel label, int remainingTime) {

        timer = new Timer(1000, new TimerListner());

        this.label = label;
        this.remainingTime = remainingTime;
    }

    public void start() {
        timer.start();
    }

    public void reset() {
        remainingTime = remainingTime;
    }

    private class TimerListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int minute, second;
            if( remainingTime > 0) {
                minute = remainingTime / 60;
                second = remainingTime % 10;
                label.setText(String.valueOf(minute) + ":" + (second >= 10 ? String.valueOf(second) : "0" + String.valueOf(second)));
            }
            else {
                label.setText("Time's up!");
                reset();
                start();
            }
        }
    }
}
