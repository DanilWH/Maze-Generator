package maze;

import java.util.Random;

public class Maze {

    public static final int SZW = 80; // the size of the maze (width)
    public static final int SZH = 80; // the size of the maze (height)
    public static int[][] maze = new int[SZW][SZH];

    private static int todonum;
    private static Point[] todo = new Point[SZW * SZH];
    private static int[] dx = new int[4];
    private static int[] dy = new int[4];

    private static final Random random = new Random();
    private static int x, y, n, d;

    public static void setup() {
        // init the "todo" list.
        for (int i = 0; i < SZW * SZH; i++) {
            todo[i] = new Point();
        }

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

        x = random.nextInt(SZW - 2) + 1;
        y = random.nextInt(SZH - 2) + 1;

        maze[x][y] &= ~48;

        // put all the unprocessed cells into the "todo" list.
        for (d = 0; d < 4; d++) {
            if ((maze[x + dx[d]][y + dy[d]] & 16) != 0) {
                todo[todonum].x = x + dx[d];
                todo[todonum].y = y + dy[d];
                todonum++;

                maze[x + dx[d]][y + dy[d]] &= ~16;
            }
        }

    }

    public static void updateMaze() {
        if (todonum <= 0) {
			Maze.x = 1;
			Maze.y = 1;
			return;
		}

        // choose a random cell from the todo list.
        n = random.nextInt(todonum);
        x = todo[n].x;
        y = todo[n].y;
		// System.out.println("n = " + n); TODO
		// System.out.println("x = " + x); TODO
		// System.out.println("y = " + y); TODO

        // remove the processed cell from the list.
        todonum--;
        todo[n] = new Point(todo[todonum].x, todo[todonum].y);

        // choose the direction that leads to the exit.
        do {
            d = random.nextInt(4);
        } while ((maze[x + dx[d]][y + dy[d]] & 32) != 0);
		// System.out.println("d = " + d); TODO

        // join the chosen cell to the maze.
        maze[x][y] &= ~(1 * (intOf(Math.pow(2, d))) | 32);
        maze[x + dx[d]][y + dy[d]] &= ~(1 * (intOf(Math.pow(2, d ^ 1))));
		
		// System.out.println("maze[x][y] = " + maze[x][y]); TODO
        // System.out.println("maze[x + dx[d]][y + dy[d]] = " + maze[x + dx[d]][y + dy[d]]); TODO

        // put all the unprocessed cells into the todo list.
        for (d = 0; d < 4; d++) {
            if ((maze[x + dx[d]][y + dy[d]] & 16) != 0) {
                todo[todonum].x = x + dx[d];
                todo[todonum].y = y + dy[d];
                todonum++;
                maze[x + dx[d]][y + dy[d]] &= ~16;
            }
        }

        maze[1][1] &= ~1; // the beginning of the maze is at the left-top corner.
        maze[SZW - 2][SZH - 2] &= ~2; // the end of the maze is at the right-bottom corner.
    }
	
	public static int getTodonum() {
		return todonum;
	}

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }
	
	public static void setX(int x) {
		Maze.x = x;
	}
	
	public static void setY(int y) {
		Maze.y = y;
	}

    private static void setDxDy() {
        dx[0] = 0; dx[1] = 0; dx[2] = -1; dx[3] = 1;
        dy[0] = -1; dy[1] = 1; dy[2] = 0; dy[3] = 0;
    }

    private static int intOf(double x) {
        String[] x_parts = String.valueOf(x).split("[.]");

        return Integer.parseInt(x_parts[0]);
    }
}
