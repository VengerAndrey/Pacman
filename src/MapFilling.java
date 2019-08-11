import javax.swing.*;
import java.awt.*;

public class MapFilling extends JPanel implements PacmanConstants{
    public int pacX = X_PAC_START;
    public int pacY = Y_PAC_START;
    int[][] map = new int[X_MAP][Y_MAP];
    int score = 0;

    MapFilling() {
        PacmanEngine engine = new PacmanEngine(this);
        addKeyListener(engine);
    }

    int getScore() {
        return score;
    }

    void mapFilling (int rows, int columns, Graphics g) {
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

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map[i][j] != 1 && map[i][j] != 3) map[i][j] = 2;
                if (i ==  pacY/MAP_INCREMENT && j == pacX/MAP_INCREMENT) {
                    map[i][j] = 3;
                    score++;
                    pacmanVisual.score = score;
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 1) g.fillRect(j*MAP_INCREMENT, i*MAP_INCREMENT, MAP_INCREMENT,MAP_INCREMENT);
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 2) g.fillOval(j*MAP_INCREMENT+9, i*MAP_INCREMENT+9, 3,3);

            }
        }
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.BLUE);

        mapFilling(X_MAP,Y_MAP, g);

//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[i].length; j++) {
//                if(map[i][j] == 1) g.fillRect(j*MAP_INCREMENT, i*MAP_INCREMENT, MAP_INCREMENT,MAP_INCREMENT);
//            }
//        }


        g.setColor(Color.red);
        g.fillOval(pacX, pacY, MAP_INCREMENT,MAP_INCREMENT);

        requestFocus();
    }

}
