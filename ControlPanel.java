import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JLabel timeLabel = null;
    private JLabel scoreLabel = null;

    private Timer timer = null;
    private Score score = null;

    public ControlPanel(Main main, Maze maze, String text) {
        main.add(this);

        this.setBounds(maze.getWidth(), 0, 200, maze.getHeight());
        this.setBackground(Color.WHITE);
    }

    /*** Timer section***/

    public void addTimer() {
        this.timeLabel = new JLabel();

        this.timeLabel.setHorizontalAlignment(JLabel.CENTER);
        this.timeLabel.setVerticalAlignment(JLabel.TOP);
        this.timeLabel.setFont(new Font("Verdana",1,20));

        this.add(timeLabel);

        this.timer = new Timer();
    }

    public Timer timer() {
        return this.timer;
    }

    /*** Score section. ***/

    public void addScore() {
        // initialize the Score label and add it to the JPanel.
        this.scoreLabel = new JLabel();

        this.scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        this.scoreLabel.setVerticalAlignment(JLabel.TOP);
        this.scoreLabel.setFont(new Font("Verdana", 1, 20));

        this.add(this.scoreLabel);

        // create the actual Score object.
        this.score = new Score();
    }

    public Score score() {
        return this.score;
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

        public boolean isUp() {
            return this.getMillisTotalLeft() <= 0;
        }

        public String getString() {
            return String.format("Time: %s : %s : %s", this.getHoursTotalLeft(),
                    this.getMinutesTotalLeft() % 60,
                    this.getSecondsTotalLeft() % 60);
        }

        public void update() {
            if (this.isStarted) {
                this.amountMillis = this.endTimeMillis - System.currentTimeMillis();
            } else {
                System.out.println("You can't update the timer if it's not started.");
            }
        }

        public void draw() {
            timeLabel.setText(this.getString());
        }
    }

    public class Score {

        private int scoreCount = 0;

        public void scoreUp() {
            this.scoreCount += 25;
        }

        public int getScore() {
            return this.scoreCount;
        }

        public String getString() {
            return String.format("Score: %s", this.scoreCount);
        }

        public void draw() {
            scoreLabel.setText(this.getString());
        }
    }

}
