import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapEditor implements PacmanConstants {
    static JButton buttonMap[][] = new JButton[X_MAP][Y_MAP];
    static JPanel editorPanel = new JPanel();
    JPanel buttonMapPanel = new JPanel();
    JPanel saveButtonPanel = new JPanel();
    JButton saveButton = new JButton("Save map");
    GridLayout gl = new GridLayout(X_MAP, Y_MAP);
    BorderLayout bl = new BorderLayout();
    static int coins = 5;

    MapEditor() {
        buttonMapPanel.setLayout(gl);
        editorPanel.setLayout(bl);

        createButtonsMap();
        setColorOnButtons(Color.WHITE);
        addButtonsOnPanel(buttonMapPanel);
        addActionListenerOnButtons();

        saveButtonPanel.add(saveButton);

        editorPanel.add(buttonMapPanel, "Center");
        editorPanel.add(saveButtonPanel, "South");


    }

    void addButtonsOnPanel(JPanel panel) {
        for (int i = 0; i < X_MAP; i++) {
            for (int j = 0; j < Y_MAP; j++) {
                panel.add(buttonMap[i][j]);
            }
        }
    }

    void setColorOnButtons(Color color) {
        for (int i = 0; i < X_MAP; i++) {
            for (int j = 0; j < Y_MAP; j++) {
                if (i == 0 || i == X_MAP - 1 || j == 0 || j == Y_MAP - 1) buttonMap[i][j].setBackground(Color.BLUE);
                else buttonMap[i][j].setBackground(color);
            }
        }
    }

    void createButtonsMap() {
        for (int i = 0; i < X_MAP; i++) {
            for (int j = 0; j < Y_MAP; j++) {

                buttonMap[i][j] = new JButton();
            }
        }
    }

    void addActionListenerOnButtons() {
        for (int i = 0; i < X_MAP; i++) {
            for (int j = 0; j < Y_MAP; j++) {
                int finalI = i;
                int finalJ = j;
                if (!(i == 0 || i == X_MAP - 1) && !(j == 0 || j == Y_MAP - 1)) {
                    buttonMap[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if (buttonMap[finalI][finalJ].getBackground() == Color.WHITE)
                                buttonMap[finalI][finalJ].setBackground(Color.BLUE);
                            else buttonMap[finalI][finalJ].setBackground(Color.WHITE);
                        }
                    });
                }
            }
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createMap();
                PacmanVisual.cl.show(PacmanVisual.mainPanel, "1");
            }
        });
    }

    void createMap() {
        for (int i = 0; i < X_MAP; i++) {
            for (int j = 0; j < Y_MAP; j++) {
                if (buttonMap[i][j].getBackground() == Color.WHITE) {
                    MapFilling.map[i][j] = 2;
                } else {
                    MapFilling.map[i][j] = 1;
                    if (!(i == 0 || i == X_MAP - 1) && !(j == 0 || j == Y_MAP - 1)) coins--;
                }
            }
        }
    }
}
