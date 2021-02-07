package maze;

import javax.swing.*;
import java.awt.*;

class PaintPanel extends JPanel {

    private int wall_len = 30;
    private Color color = null;

    public PaintPanel() {
        this.setBackground(Color.GRAY);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setPaint(Color.BLUE);

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

    public void redrawMaze() {
        this.repaint();
    }
}