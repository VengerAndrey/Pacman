import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapFilling extends JPanel implements PacmanConstants {
    static public int pacX = X_PAC_START;
    static public int pacY = Y_PAC_START;

    static public boolean isGame = false;

    static int[][] map = new int[X_MAP][Y_MAP];

    ArrayList<String> pacmanImgSrcs = new ArrayList<String>();
    static int imgIndex = 0;

    MapFilling() {
        PacmanEngine engine = new PacmanEngine(this);
        addPacmanImgSrcs();
        addKeyListener(engine);
    }

    void mapFilling(int rows, int columns, Graphics g) {
        if(!isGame) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == 3) {
                        map[i][j] = 2;
                        g.setColor(Color.ORANGE);
                        g.fillOval(j * MAP_INCREMENT + 9, i * MAP_INCREMENT + 9, 3, 3);
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 || i == X_MAP - 1) map[i][j] = 1;
                if (j == 0 || j == Y_MAP - 1) map[i][j] = 1;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map[i][j] != 1 && map[i][j] != 3) map[i][j] = 2;
                if (i == pacY / MAP_INCREMENT && j == pacX / MAP_INCREMENT && map[i][j] == 2) {
                    map[i][j] = 3;
                    PacmanVisual.score++;
                    PacmanVisual.setScore();
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j * MAP_INCREMENT, i * MAP_INCREMENT, MAP_INCREMENT, MAP_INCREMENT);
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 2) {
                    g.setColor(Color.ORANGE);
                    g.fillOval(j * MAP_INCREMENT + 9, i * MAP_INCREMENT + 9, 3, 3);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        mapFilling(X_MAP, Y_MAP, g);

        g.drawImage(new ImageIcon(pacmanImgSrcs.get(imgIndex)).getImage(), pacX, pacY, null);

        requestFocus();
    }

    void addPacmanImgSrcs() {
        pacmanImgSrcs.add("pics/pacman1.png");
        pacmanImgSrcs.add("pics/pacman2.png");
        pacmanImgSrcs.add("pics/pacman1l.png");
        pacmanImgSrcs.add("pics/pacman2l.png");
        pacmanImgSrcs.add("pics/pacman1d.png");
        pacmanImgSrcs.add("pics/pacman2d.png");
        pacmanImgSrcs.add("pics/pacman1u.png");
        pacmanImgSrcs.add("pics/pacman2u.png");
    }


}
