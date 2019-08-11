import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Admin on 28.06.2019.
 */
public class PacmanVisual implements PacmanConstants{
//    public int pacX = X_PAC_START;
//    public int pacY = Y_PAC_START;
//    int[][] map = new int[X_MAP][Y_MAP];

    JFrame frame = new JFrame("Pacman");
    JPanel mainPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JButton startButton = new JButton("Start a new game");
    JButton exitButton = new JButton("Exit");
    //JButton endButton = new JButton("Finish the game");
    CardLayout cl = new CardLayout();
    //GridLayout gl = new GridLayout(2,1, 0, 250);
    //Box buttonBox = Box.createVerticalBox();
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    JTextField scoreText;
    int score = 0;

    PacmanVisual() {
        MapFilling mapFilling = new MapFilling();
        mapFilling.setLayout(new BorderLayout());

        mapFilling.add(scoreText = new JTextField(String.valueOf(score),50), "South");
        mapFilling.repaint();

        mainPanel.setLayout(cl);
        menuPanel.setLayout(gbl);

        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(20, 5, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;

        gbl.setConstraints(startButton, c);
        menuPanel.add(startButton);

        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(10, 0, 0, 0);
        gbl.setConstraints(exitButton, c);
        menuPanel.add(exitButton);
        //menuPanel.add(buttonBox, BorderLayout.CENTER);
        //menuPanel.setLayout(new SpringLayout());

        mainPanel.add(menuPanel, "1");
        mainPanel.add(mapFilling, "2");

        cl.show(mainPanel, "1");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(mainPanel, "2");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(616,659);
        frame.setResizable(false);
        frame.setVisible(true);


    }

    PacmanVisual(int score) {
        this.score = score;
    }
//    void mapFilling (int rows, int columns) {
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                if (i == 0 || i == X_MAP-1) map[i][j] = 1;
//                if (j == 0 || j == Y_MAP-1) map[i][j] = 1;
//                if(j > 1 && j < 6 && i == 2) map[i][j] = 1;
//                if(j > 6 && j < 10 && i == 2) map[i][j] = 1;
//                if(i < 10 && j == 9) map[i][j] = 1;
//                if(i == 4 && j < 8) map[i][j] = 1;
//                if(i == 6 && j > 1 && j < 9) map[i][j] = 1;
//                if(i > 7 && i < 12 && j == 2) map[i][j] = 1;
//                if(i == 9 && j > 2 && j < 8) map[i][j] = 1;
//                if(i == 11 && j > 6 && j < 12) map[i][j] = 1;
//                if(i == 13 && j < 14) map[i][j] = 1;
//
//
//            }
//        }
//
//    }

//    public void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//        g.setColor(Color.BLUE);
//
//        mapFilling(X_MAP,Y_MAP);
//
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[i].length; j++) {
//                if(map[i][j] == 1) g.fillRect(j*MAP_INCREMENT, i*MAP_INCREMENT, MAP_INCREMENT,MAP_INCREMENT);
//            }
//        }
//
//        g.setColor(Color.red);
//        g.fillOval(pacX, pacY, MAP_INCREMENT,MAP_INCREMENT);
//
//        requestFocus();
//    }

//    void addPaneltoFrame(Container container) {
//        container.setLayout(new BorderLayout());
//        container.add(this);
//
//    }



    public static void main(String[] args) {
        PacmanVisual pacmanVisual = new PacmanVisual();

    }
}
