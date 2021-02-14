package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {

    private int wall_len = 10;

    public PaintPanel() {
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setPaint(Color.BLACK);
        // gr2d.setStroke(new BasicStroke(2.0f));

        for (int x = 0; x < Maze.SZH; x++) {
            for (int y = 0; y < Maze.SZW; y++) {
                if ((Maze.maze[x][y] & 1) != 0) { // upper wall
                    gr2d.drawLine(x * wall_len, y * wall_len,             // starting point.
                            x * wall_len + wall_len, y * wall_len); // ending point.
                }
                if ((Maze.maze[x][y] & 2) != 0) { // lower wall
                    gr2d.drawLine(x * wall_len, y * wall_len + wall_len,
                            x * wall_len + wall_len, y * wall_len + wall_len);
                }
                if ((Maze.maze[x][y] & 4) != 0) { // left wall
                    gr2d.drawLine(x * wall_len, y * wall_len,
                            x * wall_len, y * wall_len + wall_len);
                }
                if ((Maze.maze[x][y] & 8) != 0) { // right wall
                    gr2d.drawLine(x * wall_len + wall_len, y * wall_len,
                            x * wall_len + wall_len, y * wall_len + wall_len);
                }

                if (Maze.maze[x][y] == 63) {
                    gr2d.fillRect(x * wall_len, y * wall_len, wall_len, wall_len);
                    continue;
                } else if (Maze.maze[x][y] == 47) {
                    gr2d.setPaint(Color.GREEN);
                    gr2d.fillRect(x * wall_len + 1, y * wall_len + 1, wall_len - 1, wall_len - 1);
                    gr2d.setPaint(Color.BLACK);
                    continue;
                }
                // draw the red rect slider.
                gr2d.setPaint(Color.RED);
                gr2d.fillRect(Maze.getX() * wall_len + 1, Maze.getY() * wall_len + 1, wall_len - 1, wall_len - 1);
                gr2d.setPaint(Color.BLACK);
            }
        }
    }

    public void render() {
        this.repaint();
    }
	
	public int getWallLen() {
		return this.wall_len;
	}
}
