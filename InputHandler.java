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

		if (x == maze.SZW - 2 && y == maze.SZH - 2) {
/*
			JFrame newFrame = new JFrame();
			newFrame.setSize(30, 30);
			newFrame.setVisible(true);
*/
			JOptionPane.showMessageDialog(null, "Что-то пошло не так!","Ошибка!", JOptionPane.ERROR_MESSAGE);
			// JOptionPane.showMessageDialog(null, "Ура! Вы не отсталый!","Победа!", JOptionPane.INFORMATION_MESSAGE);
		}

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
