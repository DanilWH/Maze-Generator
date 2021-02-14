package maze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class InputHandler implements KeyListener {
	
	private Main main;
	
	public InputHandler(Main main) {
		this.main = main;
	}

    @Override
    public void keyPressed(KeyEvent e) {
		int x = Maze.getX();
		int y = Maze.getY();
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if ((Maze.maze[x][y] & 1) == 0) { // check the upper wall
					Maze.setY(--y);
				}
				break;
			case KeyEvent.VK_DOWN:
				if ((Maze.maze[x][y] & 2) == 0) { // check the lower wall
					Maze.setY(++y);
				}
				break;
			case KeyEvent.VK_LEFT:
				if ((Maze.maze[x][y] & 4) == 0) { // check the left wall
					Maze.setX(--x);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if ((Maze.maze[x][y] & 8) == 0) { // check the right wall
					Maze.setX(++x);
				}
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
