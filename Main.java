import java.awt.*;
import javax.swing.JFrame;

public class Main extends JFrame {

	public boolean isActive = true;

    public Main() {
    	/*** Init the JFrame (window). ***/
        this.setTitle("test paint");
        this.setSize(1000, 1000);
        //this.addKeyListener(new InputHandler(this, maze));

        this.setVisible(true);
    }

    public static void main(String[] args) {
    	// create the window.
    	Main main = new Main();

    	// create one maze.
    	Maze maze = new Maze(main);

    	// create one walker.
		// Walker walker = new Walker(maze);


        // update the maze and redraw it on the panel.
        new Thread(new Runnable() {
            public void run() {
				// animate the process of building the maze.
                while(maze.getTodonum() > 0) {
					try {
						// update the maze.
						maze.updateMaze();
						//
						// redraw the maze.
						maze.drawMaze();

						Thread.sleep(1); // delay
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
				
				// put the slider in the up-left corner.
				maze.setXMaze(1);
				maze.setYMaze(1);
				
				while(true) {
					if (main.isActive) {
						try {
							Thread.sleep(10); // delay
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					// render the maze.
					maze.drawMaze();
				}
            }
        }).start();
    }
}
