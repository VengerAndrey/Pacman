import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 28.06.2019.
 */
public class PacmanEngine implements KeyListener, PacmanConstants, Runnable {
    private MapFilling map;
    static int lastKey;


    public PacmanEngine(MapFilling map) {
        this.map = map;
        (new Thread(this)).start();
    }


    void moveUp() {
        if (map.map[map.pacY / PAC_INCREMENT - 1][map.pacX / PAC_INCREMENT] != 1) {
            map.pacY -= PAC_INCREMENT;
            MapFilling.imgIndex = 6;
            map.repaint();
        }
    }

    void moveDown() {
        if (map.map[map.pacY / PAC_INCREMENT + 1][map.pacX / PAC_INCREMENT] != 1) {
            map.pacY += PAC_INCREMENT;
            MapFilling.imgIndex = 4;
            map.repaint();
        }
    }

    void moveRight() {
        if (map.map[map.pacY / PAC_INCREMENT][map.pacX / PAC_INCREMENT + 1] != 1) {
            map.pacX += PAC_INCREMENT;
            MapFilling.imgIndex = 0;
            map.repaint();
        }
    }

    void moveLeft() {
        if (map.map[map.pacY / PAC_INCREMENT][map.pacX / PAC_INCREMENT - 1] != 1) {
            map.pacX -= PAC_INCREMENT;
            MapFilling.imgIndex = 2;
            map.repaint();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (lastKey == KeyEvent.VK_W) moveUp();
                if (lastKey == KeyEvent.VK_S) moveDown();
                if (lastKey == KeyEvent.VK_A) moveLeft();
                if (lastKey == KeyEvent.VK_D) moveRight();

                Thread.sleep(100);

                if (MapFilling.imgIndex == 0) {
                    MapFilling.imgIndex = 1;
                    map.repaint();
                    Thread.sleep(100);
                    continue;
                }
                if (MapFilling.imgIndex == 2) {
                    MapFilling.imgIndex = 3;
                    map.repaint();
                    Thread.sleep(100);
                    continue;
                }
                if (MapFilling.imgIndex == 4) {
                    MapFilling.imgIndex = 5;
                    map.repaint();
                    Thread.sleep(100);
                    continue;
                }
                if (MapFilling.imgIndex == 6) {
                    MapFilling.imgIndex = 7;
                    map.repaint();
                    Thread.sleep(100);
                    continue;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();


        if ((key == KeyEvent.VK_W) && (map.map[map.pacY / PAC_INCREMENT - 1][map.pacX / PAC_INCREMENT] != 1)) {
            lastKey = KeyEvent.VK_W;
        }
        if ((key == KeyEvent.VK_S) && (map.map[map.pacY / PAC_INCREMENT + 1][map.pacX / PAC_INCREMENT] != 1)) {
            lastKey = KeyEvent.VK_S;
        }
        if ((key == KeyEvent.VK_A) && (map.map[map.pacY / PAC_INCREMENT][map.pacX / PAC_INCREMENT - 1] != 1)) {
            lastKey = KeyEvent.VK_A;
        }
        if ((key == KeyEvent.VK_D) && (map.map[map.pacY / PAC_INCREMENT][map.pacX / PAC_INCREMENT + 1] != 1)) {
            lastKey = KeyEvent.VK_D;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
