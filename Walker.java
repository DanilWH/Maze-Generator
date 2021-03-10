import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

class Walker extends JPanel {
	
	private int xPos = 1, yPos = 1;
	private int xPosOld = xPos, yPosOld = yPos;
	private Maze maze;
	
	public Walker(Main main, Maze maze) {
		this.maze = maze;
		
		main.add(this);
		main.addKeyListener(new InputHandler(maze, this));

		this.setBounds(0, 0, maze.SZW * maze.getWallLen(), maze.SZH * maze.getWallLen());
		this.setBackground(new Color(0, 0, 0, 0));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr2d = (Graphics2D) g;

		// redraw the previous position of the walker with another color.
		//gr2d.setColor(new Color(0, 100, 0));
		gr2d.setColor(new Color(255, 91, 0));
		gr2d.fillRect(this.xPosOld * maze.getWallLen() + 1, this.yPosOld * maze.getWallLen() + 1,
				maze.getWallLen() - (int) maze.getStroke(), maze.getWallLen() - (int) maze.getStroke());

		// draw the walker at the new position.
		//gr2d.setColor(Color.BLUE);
		gr2d.setColor(new Color(58, 117, 196));
		gr2d.fillRect(this.xPos * maze.getWallLen() + 1, this.yPos * maze.getWallLen() + 1,
					maze.getWallLen() - (int) maze.getStroke(), maze.getWallLen() - (int) maze.getStroke());

		// reassign the old position with the new one.
		this.xPosOld = xPos;
		this.yPosOld = yPos;
	}
	
	public void draw() {
		this.repaint();
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	
}