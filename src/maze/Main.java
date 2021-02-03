package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class Main extends JFrame {

    private int wall_len = 30;

    public Main() {
        super("simpleApp");
        setSize(700, 600);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setBackground(Color.green);
        // Draw lines.
        gr2d.setPaint(Color.BLUE);
        // gr2d.drawLine(10, 10, 10, 50);

        for (int x = 0; x < Maze.SZH; x++) {
            for (int y = 0; y < Maze.SZW; y++) {
                if ((Maze.maze[x][y] & 1) != 0) {
                    // top
                    gr2d.drawLine(x * wall_len, y * wall_len,             // starting point.
                                  x * wall_len + wall_len, y * wall_len); // ending point.
                }
                if ((Maze.maze[x][y] & 2) != 0) {
                    // bottom
                    gr2d.drawLine(x * wall_len, y * wall_len + wall_len,
                                  x * wall_len + wall_len, y * wall_len + wall_len);
                }
                if ((Maze.maze[x][y] & 4) != 0) {
                    gr2d.drawLine(x * wall_len, y * wall_len,
                                  x * wall_len, y * wall_len + wall_len);
                }
                if ((Maze.maze[x][y] & 8) != 0) {
                    gr2d.drawLine(x * wall_len + wall_len, y * wall_len,
                                  x * wall_len + wall_len, y * wall_len + wall_len);
                }
            }
        }



    }

    public static void main(String[] args) {
        for (int i = 0; i < Maze.SZW * Maze.SZH; i++) {
            Maze.todo[i] = new Point();
        }
        Maze.setup();

        Main app = new Main();
    }
}