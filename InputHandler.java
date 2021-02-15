import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class InputHandler implements KeyListener {

	private Main main;
	private Maze maze;
	
	public InputHandler(Main main, Maze maze) {
		this.main = main;
		this.maze = maze;
	}

    @Override
    public void keyPressed(KeyEvent e) {
		int x = this.maze.getXMaze();
		int y = this.maze.getYMaze();
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				// check the upper wall
				if ((this.maze.maze[x][y] & 1) == 0) this.maze.setYMaze(--y);
				break;
			case KeyEvent.VK_DOWN:
				// check the lower wall
				if ((this.maze.maze[x][y] & 2) == 0) this.maze.setYMaze(++y);
				break;
			case KeyEvent.VK_LEFT:
				// check the left wall
				if ((this.maze.maze[x][y] & 4) == 0) this.maze.setXMaze(--x);
				break;
			case KeyEvent.VK_RIGHT:
				// check the right wall
				if ((this.maze.maze[x][y] & 8) == 0) this.maze.setXMaze(++x);
				break;
				
			case KeyEvent.VK_SPACE:
				main.isActive = !main.isActive;
				break;
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
