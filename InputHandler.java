import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class InputHandler implements KeyListener {

	private Maze maze;
	private Walker walker;
	private ControlPanel controlPanel;

	public InputHandler(Maze maze, Walker walker, ControlPanel controlPanel) {
		this.maze = maze;
		this.walker = walker;
		this.controlPanel = controlPanel;
	}

    @Override
    public void keyPressed(KeyEvent e) {
		int x = this.walker.getXPos();
		int y = this.walker.getYPos();


		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				// check the upper wall
				if ((this.maze.maze[x][y] & 1) == 0) this.walker.setYPos(--y);
				break;
			case KeyEvent.VK_DOWN:
				// check the lower wall
				if ((this.maze.maze[x][y] & 2) == 0) this.walker.setYPos(++y);
				break;
			case KeyEvent.VK_LEFT:
				// check the left wall
				if ((this.maze.maze[x][y] & 4) == 0) this.walker.setXPos(--x);
				break;
			case KeyEvent.VK_RIGHT:
				// check the right wall
				if ((this.maze.maze[x][y] & 8) == 0) this.walker.setXPos(++x);
				break;
		}

		//  if there is a collision between the walker and the ball.
		if (x == this.maze.getXMaze() && y == this.maze.getYMaze()) {
			// put the ball to another random place in the maze.
			this.maze.setXMaze(this.getRandom(1, this.maze.SZW - 2));
			this.maze.setYMaze(this.getRandom(1, this.maze.SZH - 2));
			this.maze.drawMaze();

			// increase the number of score.
			this.controlPanel.score().scoreUp();
			this.controlPanel.score().draw();
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

	private int getRandom(int min, int max){
		int x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x;
	}
}
