import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JLabel label = new JLabel("sdlkfj");

    private Timer timer = null;

    public ControlPanel(Main main, Maze maze, String text) {
        main.add(this);

        this.setBounds(maze.SZW * maze.getWallLen(), 0,
                100, maze.SZH * maze.getWallLen());
        this.setBackground(Color.WHITE);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        this.add(label);
    }

    /*** Timer section***/

    public void addTimer() {
        this.timer = new Timer();
    }

    public Timer timer() {
        return this.timer;
    }


    /*** Inner classes. ***/

    public class Timer {

        private long amountMillis = 0;
        private long endTimeMillis = 0;
        private boolean isStarted = false;

        public void set(int hours, int mins, int secs) {
            long hoursMillis = hours * 60 * 60 * 1000;
            long minsMillis = mins * 60 * 1000;
            long secsMillis = secs * 1000;

            this.amountMillis = hoursMillis + minsMillis + secsMillis;

            label.setText(this.getTimeString());
        }

        public void start() {
            this.endTimeMillis = System.currentTimeMillis() + this.amountMillis;
            this.isStarted = true;
        }


        public long getHoursTotalLeft() {
            return this.getMinutesTotalLeft() / 60;
        }

        public long getMinutesTotalLeft() {
            return this.getSecondsTotalLeft() / 60;
        }

        public long getSecondsTotalLeft() {
            return this.getMillisTotalLeft() / 1000;
        }

        public long getMillisTotalLeft() {
            return this.amountMillis;
        }

        public int getMinutes() {
            return (int) this.getMinutesTotalLeft() % 60;
        }

        public int getSeconds() {
            return (int) this.getSecondsTotalLeft() % 60;
        }

        public boolean timeIsUp() {
            return this.getMillisTotalLeft() <= 0;
        }

        public String getTimeString() {
            return String.format("%s : %s : %s", this.getHoursTotalLeft(),
                    this.getMinutesTotalLeft() % 60,
                    this.getSecondsTotalLeft() % 60);
        }

        public void update() {
            if (this.isStarted) {
                this.amountMillis = this.endTimeMillis - System.currentTimeMillis();
                label.setText(this.getTimeString());
            } else {
                System.out.println("You can't update the timer if it's not started.");
            }
        }
    }

}
