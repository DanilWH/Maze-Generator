import javax.swing.*;

public class Main extends JFrame {

	public boolean isActive = true;
	private long endingTime;
	private String title = "Test maze | %s : %s";

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

						Thread.sleep(10); // delay
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }

                main.endingTime = System.currentTimeMillis() + 2 * 60 * 1000;
				
				while(true) {
					if (main.isActive) {
						try {
							long millisLeft = main.endingTime - System.currentTimeMillis();
							// check if the time is out.
							if (millisLeft <= 0) {
								JOptionPane.showMessageDialog(null, "Что-то пошло не так!","Ошибка!", JOptionPane.ERROR_MESSAGE);
								maze.drawMaze();
								main.isActive = false;
							}

							// show the time in the title bar.
							long secLeft = millisLeft / 1000;
							long minLeft = secLeft / 60;

							main.setTitle(String.format(main.title, minLeft % 60, secLeft % 60));

							// redraw the walker.
							walker.draw();

							// make a pause.
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
