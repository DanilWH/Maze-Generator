package interfaces;

import java.awt.*;
import java.util.Random;

class Point {
    public int x;
    public int y;
}

public class Main {

    private static final int SZW = 80; // the size of the maze (width)
    private static final int SZH = 80; // the size of the maze (height)

    private static int todonum = 0;
    private static int[][] maze = new int[SZW][SZH];
    private static Point[] todo = new Point[SZW * SZH];
    private static int[] dx = new int[4];
    private static int[] dy = new int[4];

    private static final Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < SZW * SZH; i++) {
            todo[i] = new Point();
        }

        setup();
        for (int i = 0; i < SZW; i++) {
            for (int j = 0; j < SZH; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(intOf(Math.pow(2, 5)));
    }

    public static void setup() {
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

        /*
        x = (int) (Math.random() * (SZW - 2)) + 1;
        y = (int) (Math.random() * (SZH - 2)) + 1;
         */
        x = random.nextInt(SZW - 2) + 1;
        y = random.nextInt(SZH - 2) + 1;

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

        // while all the cells aren't processed
        while (todonum > 0) {
            // choose a random cell from the todo list.
            n = random.nextInt(todonum);
            x = todo[n].x;
            y = todo[n].y;

            // remove the processed cell from the list.
            todonum = todonum - 1;
            todo[n] = todo[todonum];

            // choose the direction that leads to the exit.
            do {
                d = random.nextInt(4);
                System.out.println(d);
            } while ((maze[x + dx[d]][y + dy[d]] & 32) != 0);

            // join the chosen cell to the maze.
            maze[x][y] = maze[x][y] & ~(1 * (intOf(Math.pow(2, d))) | 32);
            maze[x + dx[d]][y + dy[d]] = maze[x + dx[d]][y + dy[d]] & ~(1 * (intOf(Math.pow(2, d ^ 1))));

            // put all the unprocessed cells into the todo list.
            for (d = 0; d < 4; d++) {
                if ((maze[x + dx[d]][y + dy[d]] & 16) != 0) {
                    todo[todonum].x = x + dx[d];
                    todo[todonum].y = y + dy[d];
                    todonum++;
                    maze[x + dx[d]][y + dy[d]] = maze[x + dx[d]][y + dy[d]] & ~16;
                }
            }

            maze[1][1] = maze[1][1] & ~1; // the beginning of the maze is at the left-top corner.
            maze[SZW - 2][SZH - 2] = maze[SZW - 2][SZH - 2] & ~2; // the end of the maze is at the right-bottom corner.
        }
    }

    public static void setDxDy() {
        dx[0] = 0; dx[1] = 0; dx[2] = -1; dx[3] = 1;
        dy[0] = -1; dy[1] = 1; dy[2] = 0; dy[3] = 0;
    }

    public static int intOf(double x) {
        String[] x_parts = String.valueOf(x).split("[.]");

        return Integer.parseInt(x_parts[0]);
    }
}
