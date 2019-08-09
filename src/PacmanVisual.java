import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 28.06.2019.
 */
public class PacmanVisual extends JPanel implements PacmanConstants{
    public int pacX = X_PAC_START;
    public int pacY = Y_PAC_START;
    int[][] map = new int[X_MAP][Y_MAP];

    PacmanVisual() {
        PacmanEngine engine = new PacmanEngine(this);
        addKeyListener(engine);
    }

    void mapFilling (int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || i == X_MAP-1) map[i][j] = 1;
                if (j == 0 || j == Y_MAP-1) map[i][j] = 1;
                if(j > 1 && j < 6 && i == 2) map[i][j] = 1;
                if(j > 6 && j < 10 && i == 2) map[i][j] = 1;
                if(i < 10 && j == 9) map[i][j] = 1;
                if(i == 4 && j < 8) map[i][j] = 1;
                if(i == 6 && j > 1 && j < 9) map[i][j] = 1;
                if(i > 7 && i < 12 && j == 2) map[i][j] = 1;
                if(i == 9 && j > 2 && j < 8) map[i][j] = 1;
                if(i == 11 && j > 6 && j < 12) map[i][j] = 1;
                if(i == 13 && j < 14) map[i][j] = 1;


            }
        }



        /*boolean flag = true;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
               if (flag) {
                   map[i][j] = 1;
                   flag = !flag;
               } else {
                   map[i][j] = 0;
                   flag = !flag;
               }
            }
        }*/
    }

    int numofWalls (int[][] map) {
        int num = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) num++;
            }
        }

        return num;
    }



    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.BLUE);

        mapFilling(X_MAP,Y_MAP);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 1) g.fillRect(j*MAP_INCREMENT, i*MAP_INCREMENT, MAP_INCREMENT,MAP_INCREMENT);
            }
        }

        g.setColor(Color.red);
        g.fillOval(pacX, pacY, MAP_INCREMENT,MAP_INCREMENT);

        requestFocus();
    }

    void addPaneltoFrame(Container container) {
        container.setLayout(new BorderLayout());
        container.add(this);

    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Pacman");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PacmanVisual pacmanVisual = new PacmanVisual();
        pacmanVisual.addPaneltoFrame(frame.getContentPane());
        frame.setSize(616,639);
        frame.setVisible(true);
    }
}
