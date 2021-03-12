import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class InputHandler implements KeyListener {
	
	private Maze maze;
	private Walker walker;

	public InputHandler(Maze maze, Walker walker) {
		this.maze = maze;
		this.walker = walker;
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

		if (x == this.maze.getXMaze() && y == this.maze.getYMaze()) {
			this.maze.setXMaze(this.getRandom(1, this.maze.SZW - 2));
			this.maze.setYMaze(this.getRandom(1, this.maze.SZH - 2));
			this.maze.drawMaze();
		}

		if (x == this.maze.SZW - 2 && y == this.maze.SZH - 2) {
			JOptionPane.showMessageDialog(null, "Что-то пошло не так!","Ошибка!", JOptionPane.ERROR_MESSAGE);
			// JOptionPane.showMessageDialog(null, "Ура! Вы не отсталый!","Победа!", JOptionPane.INFORMATION_MESSAGE);
			// JOptionPane.showMessageDialog(null, "Ура! Вы нашли выход!","Победа!", JOptionPane.INFORMATION_MESSAGE);
			this.maze.drawMaze();
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
