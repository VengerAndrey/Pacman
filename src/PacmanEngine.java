import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Admin on 28.06.2019.
 */
public class PacmanEngine implements KeyListener, PacmanConstants{
    private PacmanVisual map;

    public PacmanEngine(PacmanVisual map) {
         this.map = map;
    }



    void moveUp() {
        if (map.map[map.pacY/PAC_INCREMENT - 1][map.pacX/PAC_INCREMENT] != 1) {
            map.pacY -= PAC_INCREMENT;
            map.repaint();
        }
    }
    void moveDown() {
        if (map.map[map.pacY/PAC_INCREMENT + 1][map.pacX/PAC_INCREMENT] != 1) {
            map.pacY += PAC_INCREMENT;
            map.repaint();
        }
    }
    void moveRight() {
        if (map.map[map.pacY/PAC_INCREMENT][map.pacX/PAC_INCREMENT + 1] != 1) {
            map.pacX += PAC_INCREMENT;
            map.repaint();
        }
    }
    void moveLeft() {
        if (map.map[map.pacY/PAC_INCREMENT][map.pacX/PAC_INCREMENT - 1] != 1) {
            map.pacX -= PAC_INCREMENT;
            map.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();

        if(key == 'w' || key == 'W') {
            moveUp();
        }
        if(key == 's' || key == 'S') {
            moveDown();
        }
        if(key == 'a' || key == 'A') {
            moveLeft();
        }
        if(key == 'd' || key == 'D') {
            moveRight();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
