package maze;

import java.awt.*;
import javax.swing.JFrame;

public class Main extends JFrame {

    private PaintPanel paintPan = new PaintPanel();
	public boolean isActive = true;

    public Main() {
        this.setTitle("test paint");
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());
        this.add(paintPan, BorderLayout.CENTER);
        this.addKeyListener(new InputHandler(this));

        this.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();

        // init the maze.
        Maze.setup();
        // update the maze and redraw it on the panel.
        new Thread(new Runnable() {
            public void run() {
				// animate the process of building the maze.
                while(Maze.getTodonum() > 0) {
					try {
						// update the maze.
						Maze.updateMaze();
						//
						// redraw the maze.
						main.paintPan.render();

						Thread.sleep(0); // delay
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
				
				// put the slider in the up-left corner.
				Maze.setX(1);
				Maze.setY(1);
				
				while(true) {
					if (main.isActive) {
						try {
							Thread.sleep(1000); // delay
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					// render the maze.
					main.paintPan.render();
				}
            }
        }).start();
    }
}
