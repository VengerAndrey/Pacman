import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Admin on 28.06.2019.
 */
public class PacmanEngine implements KeyListener, PacmanConstants, Runnable {
    private MapFilling map;
    private char lastKey;

    public PacmanEngine(MapFilling map) {
        this.map = map;
        (new Thread(this)).start();
    }


    void moveUp() {
        if (map.map[map.pacY / PAC_INCREMENT - 1][map.pacX / PAC_INCREMENT] != 1) {
            map.pacY -= PAC_INCREMENT;
            map.repaint();
        }
    }

    void moveDown() {
        if (map.map[map.pacY / PAC_INCREMENT + 1][map.pacX / PAC_INCREMENT] != 1) {
            map.pacY += PAC_INCREMENT;
            map.repaint();
        }
    }

    void moveRight() {
        if (map.map[map.pacY / PAC_INCREMENT][map.pacX / PAC_INCREMENT + 1] != 1) {
            map.pacX += PAC_INCREMENT;
            map.repaint();
        }
    }

    void moveLeft() {
        if (map.map[map.pacY / PAC_INCREMENT][map.pacX / PAC_INCREMENT - 1] != 1) {
            map.pacX -= PAC_INCREMENT;
            map.repaint();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (lastKey == 'w') moveUp();
                if (lastKey == 's') moveDown();
                if (lastKey == 'a') moveLeft();
                if (lastKey == 'd') moveRight();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();


        if ((key == 'w' || key == 'W') && (map.map[map.pacY / PAC_INCREMENT - 1][map.pacX / PAC_INCREMENT] != 1)) {
            lastKey = 'w';
        }
        if ((key == 's' || key == 'S') && (map.map[map.pacY / PAC_INCREMENT + 1][map.pacX / PAC_INCREMENT] != 1)) {
            lastKey = 's';
        }
        if ((key == 'a' || key == 'A') && (map.map[map.pacY / PAC_INCREMENT][map.pacX / PAC_INCREMENT - 1] != 1)) {
            lastKey = 'a';
        }
        if ((key == 'd' || key == 'D') && (map.map[map.pacY / PAC_INCREMENT][map.pacX / PAC_INCREMENT + 1] != 1)) {
            lastKey = 'd';
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
