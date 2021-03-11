import javax.swing.JFrame;

public class Main extends JFrame {

	public boolean isActive = true;

    public Main() {
    	/*** Init the JFrame (window). ***/
        this.setTitle("test paint");
        this.setSize(800, 800);

        this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    	// create the window.
    	Main main = new Main();

    	// create one maze.
    	Maze maze = new Maze(main);

    	// create one walker.
		Walker walker = new Walker(main, maze);


        // update the maze and redraw it on the panel.
        new Thread(new Runnable() {
            public void run() {
				// animate the process of building the maze.
                while(maze.getTodonum() > 0) {
					try {
						// update the maze.
						maze.updateMaze();
						// redraw the maze.
						maze.drawMaze();

						Thread.sleep(0); // delay
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
				
				while(true) {
					if (main.isActive) {
						try {
							walker.draw();
							Thread.sleep(10); // delay
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
            }
        }).start();
    }
}
