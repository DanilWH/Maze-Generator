import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.Random;

import javax.swing.JPanel;

public class Maze extends JPanel {
    public final int SZW = 30; // the size of the maze (width)
    public final int SZH = 30; // the size of the maze (height)
    public int[][] maze = new int[SZW][SZH];

    private int todonum;
    private Point[] todo = new Point[SZW * SZH];
    private int[] dx = new int[4];
    private int[] dy = new int[4];

    private final Random random = new Random();
    private int xMaze, yMaze, n, d;

    private final int wall_len = 15;
	private final float stroke = 2.0f;

    public Maze(Main main) {
        /*** Init the JPanel ***/
        // add the JPanel object to the JFrame object.
        main.add(this);

        this.setBounds(0, 0, SZW * wall_len, SZH * wall_len);
        this.setBackground(Color.WHITE);

        /*** init the maze. ***/
        this.initMaze();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr2d = (Graphics2D) g;
        gr2d.setPaint(Color.BLACK);
        gr2d.setStroke(new BasicStroke(this.stroke));

        for (int x = 0; x < this.SZH; x++) {
            for (int y = 0; y < this.SZW; y++) {
                if ((this.maze[x][y] & 1) != 0) { // upper wall
                    gr2d.drawLine(x * this.wall_len, y * this.wall_len,            // starting point.
                            x * this.wall_len + this.wall_len, y * this.wall_len); // ending point.
                }
                if ((this.maze[x][y] & 2) != 0) { // lower wall
                    gr2d.drawLine(x * this.wall_len, y * this.wall_len + this.wall_len,
                            x * this.wall_len + this.wall_len, y * this.wall_len + this.wall_len);
                }
                if ((this.maze[x][y] & 4) != 0) { // left wall
                    gr2d.drawLine(x * this.wall_len, y * this.wall_len,
                            x * this.wall_len, y * this.wall_len + this.wall_len);
                }
                if ((this.maze[x][y] & 8) != 0) { // right wall
                    gr2d.drawLine(x * this.wall_len + this.wall_len, y * this.wall_len,
                            x * this.wall_len + this.wall_len, y * this.wall_len + this.wall_len);
                }

                if (this.maze[x][y] == 63) {
                    gr2d.fillRect(x * this.wall_len, y * this.wall_len, this.wall_len, this.wall_len);
                    continue;
                } else if (this.maze[x][y] == 47) {
                    gr2d.setPaint(Color.GREEN);
                    gr2d.fillRect(x * this.wall_len + 1, y * this.wall_len + 1,
                            this.wall_len - 1, this.wall_len - 1);
                    gr2d.setPaint(Color.BLACK);
                    continue;
                }
                // draw the red rect slider.
                gr2d.setPaint(Color.RED);
                gr2d.fillRect(this.getXMaze() * wall_len + 1, this.getYMaze() * this.wall_len + 1,
                        this.wall_len - (int) this.stroke, this.wall_len - (int) this.stroke);
                gr2d.setPaint(Color.BLACK);
            }
        }
    }

    public void initMaze() {
        // initMaze the "todo" list.
        for (int i = 0; i < this.SZW * this.SZH; i++) {
            this.todo[i] = new Point();
        }

        this.setDxDy();

        // set the borders.
        for (this.xMaze = 0; this.xMaze < this.SZW; this.xMaze++) {
            for (this.yMaze = 0; this.yMaze < this.SZH; this.yMaze++) {
                if (this.xMaze == 0 || this.xMaze == this.SZW - 1 || this.yMaze == 0 || this.yMaze == this.SZH - 1) {
                    this.maze[this.xMaze][this.yMaze] = 32;
                } else {
                    this.maze[this.xMaze][this.yMaze] = 63;
                }
            }
        }

        this.xMaze = this.random.nextInt(this.SZW - 2) + 1;
        this.yMaze = this.random.nextInt(this.SZH - 2) + 1;

        this.maze[this.xMaze][this.yMaze] &= ~48;

        // put all the unprocessed cells into the "todo" list.
        this.putCellsInTodo();
    }

    public void updateMaze() {
        if (this.todonum <= 0) return;

        // choose a random cell from the todo list.
        this.n = this.random.nextInt(this.todonum);
        this.xMaze = this.todo[this.n].x;
        this.yMaze = this.todo[this.n].y;
        // System.out.println("n = " + this.n); TODO
        // System.out.println("x = " + this.x); TODO
        // System.out.println("y = " + this.y); TODO

        // remove the processed cell from the list.
        this.todonum--;
        this.todo[this.n] = new Point(this.todo[this.todonum].x, this.todo[this.todonum].y);

        // choose the direction that leads to the exit.
        do {
            this.d = this.random.nextInt(4);
        } while ((this.maze[this.xMaze + this.dx[this.d]][this.yMaze + this.dy[this.d]] & 32) != 0);
        // System.out.println("d = " + this.d); TODO

        // join the chosen cell to the maze.
        this.maze[this.xMaze][this.yMaze] &= ~(1 * (this.intOf(Math.pow(2, this.d))) | 32);
        this.maze[this.xMaze + this.dx[this.d]][this.yMaze + this.dy[this.d]] &= ~(1 * (this.intOf(Math.pow(2, this.d ^ 1))));

        // System.out.println("maze[x][y] = " + this.maze[this.x][this.y]); TODO
        // System.out.println("maze[x + dx[d]][y + dy[d]] = " + this.maze[this.x + this.dx[this.d]][this.y + this.dy[this.d]]); TODO

        // put all the unprocessed cells into the todo list.
        this.putCellsInTodo();

        /*
        this.maze[1][1] &= ~1; // the beginning of the maze is at the left-top corner.
        this.maze[this.SZW - 2][this.SZH - 2] &= ~2; // the end of the maze is at the right-bottom corner.
         */
    }

    public void drawMaze() {
        this.repaint();
    }

    public int getTodonum() {
        return this.todonum;
    }

    public int getWallLen() {
        return this.wall_len;
    }

    public int getXMaze() {
        return this.xMaze;
    }

    public int getYMaze() {
        return this.yMaze;
    }

    public void setXMaze(int xMaze) {
        this.xMaze = xMaze;
    }

    public void setYMaze(int yMaze) {
        this.yMaze = yMaze;
    }
	
	public float getStroke() {
		return this.stroke;
	}

    private void setDxDy() {
        this.dx[0] = 0; this.dx[1] = 0; this.dx[2] = -1; this.dx[3] = 1;
        this.dy[0] = -1; this.dy[1] = 1; this.dy[2] = 0; this.dy[3] = 0;
    }

    private int intOf(double x) {
        String[] x_parts = String.valueOf(x).split("[.]");

        return Integer.parseInt(x_parts[0]);
    }

    private void putCellsInTodo() {
        // put all the unprocessed cells into the todo list.
        for (this.d = 0; this.d < 4; this.d++) {
            if ((this.maze[this.xMaze + this.dx[this.d]][this.yMaze + this.dy[this.d]] & 16) != 0) {
                this.todo[this.todonum].x = this.xMaze + this.dx[this.d];
                this.todo[this.todonum].y = this.yMaze + this.dy[this.d];
                this.todonum++;
                this.maze[this.xMaze + this.dx[this.d]][this.yMaze + this.dy[this.d]] &= ~16;
            }
        }
    }

}
