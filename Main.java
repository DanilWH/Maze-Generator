import javax.swing.*;

public class Main extends JFrame {

	public boolean isActive = true;
	private String title = "Test maze | %s : %s";

	// create one maze.
	private Maze maze = new Maze(this);

	// create the Control Panel.
	private ControlPanel controlPanel = new ControlPanel(this, maze, this.title);

	// create one walker.
	private Walker walker = new Walker(this, maze, controlPanel);

	public Main() {
    	/*** Init the JFrame (window). ***/
        this.setTitle("test paint");
        this.setSize(this.maze.getWidth() + this.controlPanel.getWidth(),
					this.maze.getHeight() + this.controlPanel.getHeight());

        this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*** Init the ControlPanel. ***/
		// Timer.
		this.controlPanel.addTimer();
		this.controlPanel.timer().set(0, 2, 0);
		this.controlPanel.timer().draw();

		// Score.
		this.controlPanel.addScore();
		this.controlPanel.score().draw();
    }

    public static void main(String[] args) {
    	// create the window.
    	Main main = new Main();

        // update the maze and redraw it on the panel.
        new Thread(new Runnable() {
            public void run() {
				// animate the process of building the maze.
                while(main.maze.getTodonum() > 0) {
					try {
						// update the maze.
						main.maze.updateMaze();
						// redraw the maze.
						main.maze.drawMaze();

						Thread.sleep(10); // delay
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }

                // start the timer.
                main.controlPanel.timer().start();

				while(true) {
					if (main.isActive) {
						try {
							// check if the time is out.
							if (main.controlPanel.timer().isUp()) {
								JOptionPane.showMessageDialog(null, "Что-то пошло не так!","Ошибка!", JOptionPane.ERROR_MESSAGE);
								// JOptionPane.showMessageDialog(null, "Ура! Вы не отсталый!","Победа!", JOptionPane.INFORMATION_MESSAGE);
								// JOptionPane.showMessageDialog(null, "Ура! Вы нашли выход!","Победа!", JOptionPane.INFORMATION_MESSAGE);
								main.maze.drawMaze();

								// deactivate the game.
								main.isActive = false;
							}

							// update the timer.
							main.controlPanel.timer().update();
							// redraw the timer.
							main.controlPanel.timer().draw();

							// update the timer value in the title of the window.
							main.setTitle(String.format(main.title,
									main.controlPanel.timer().getMinutes(),
									main.controlPanel.timer().getSeconds()));

							// redraw the walker.
							main.walker.draw();

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
