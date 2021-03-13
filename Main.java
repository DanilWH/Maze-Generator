import javax.swing.*;

public class Main extends JFrame {

	public boolean isActive = true;
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

		// create the Control Panel.
		ControlPanel controlPanel = new ControlPanel(main, maze, main.title);
		controlPanel.addTimer();
		controlPanel.timer().set(0, 2, 0);

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

                // start the timer.
                controlPanel.timer().start();

				while(true) {
					if (main.isActive) {
						try {
							// check if the time is out.
							if (controlPanel.timer().timeIsUp()) {
								JOptionPane.showMessageDialog(null, "Что-то пошло не так!","Ошибка!", JOptionPane.ERROR_MESSAGE);
								maze.drawMaze();
								main.isActive = false;
							}

							controlPanel.timer().update();

							main.setTitle(String.format(main.title,
									controlPanel.timer().getMinutes(),
									controlPanel.timer().getSeconds()));

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
