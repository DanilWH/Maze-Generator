package interfaces;

import java.awt.*;

class Point {
    public int x;
    public int y;
}

public class Main {

    private static final int SZW = 5; // the size of the maze (width)
    private static final int SZH = 5; // the size of the maze (height)
    private static final int DXY = 2; // ??
    private static final int COUNT_TRUE = 20; // ??
    private static final int MSH = 50; // ??
    private static final int COLOR_X = 9; // ??
    private static final int COLOR_X_BLOCK_1 = 2; // ??
    private static final int COLOR_X_BLOCK_2 = -4105; // ??

    private boolean timerstop; // ??
    private Object color_x_block; // ??

    private int gvopros, vtrue, count_true_ok; // ??
    private int r, c; // ??
    private int mr, mc; // ??

    private static int todonum;
    private static int[][] maze = new int[SZW][SZH];
    private static Point[] todo = new Point[SZW * SZH];
    private static int[] dx = new int[4];
    private static int[] dy = new int[4];


    public static void main(String[] args) {
        // TODO
    }

    public static void setup() {
        /*
         */

        int x, y, n, d;
        setDxDy();

        // set the borders.
        for (x = 0; x < SZW; x++) {
            for (y = 0; y < SZH; y++) {
                if (x == 0 || x == SZW - 1 || y == 0 || y == SZH - 1) {
                    maze[x][y] = 32;
                } else {
                    maze[x][y] = 63;
                }
            }
        }

        x = (int) (Math.random() * (SZW - 2)) + 1;
        y = (int) (Math.random() * (SZH - 2)) + 1;

        maze[x][y] = maze[x][y] & ~48;

        // put all the unprocessed cells into the "todo" list.
        for (d = 0; d < 4; d++) {
            if ((maze[x + dx[d]][y + dy[d]] & 16) != 0) {
                todo[todonum].x = x + dx[d];
                todo[todonum].y = y + dy[d];
                todonum++;

                maze[x + dx[d]][y + dy[d]] = maze[x + dx[d]][y + dy[d]] & ~16;
            }
        }
    }

    public static void setDxDy() {
        /*
            dx(0) = 0: dx(1) = 0: dx(2) = -1: dx(3) = 1
            dy(0) = -1: dy(1) = 1: dy(2) = 0: dy(3) = 0
         */

        dx[0] = 0; dx[1] = 0; dx[2] = -1; dx[3] = 1;
        dy[0] = -1; dy[1] = 1; dy[2] = 0; dy[3] = 0;
    }
}
