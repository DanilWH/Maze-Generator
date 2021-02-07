package maze;

import java.awt.*;
import javax.swing.JFrame;

public class Main extends JFrame {

    private static PaintPanel paintPan = new PaintPanel();
    private static Main main = new Main();
    public Main() {
        this.setTitle("test paint");
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());
        this.add(paintPan, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        Maze.setup();

        new Thread(new Runnable() {
            public void run() {
                while(true) { //бесконечно крутим
                    try {
                        Maze.updateMaze();
                        drawMaze();

                        Thread.sleep(10); // 4 секунды в милисекундах
                        // System.out.println("Hi!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void drawMaze() {
        paintPan.redrawMaze();
        main.repaint();
    }

}