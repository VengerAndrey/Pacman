import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 28.06.2019.
 */

public class PacmanVisual implements PacmanConstants, Runnable {

    JFrame frame = new JFrame("Pacman");
    static JPanel mainPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JButton startButton = new JButton("Start a new game");
    JButton exitButton = new JButton("Exit");
    JButton mapEditorButton = new JButton("Map Editor");
    JButton gameFinishButton = new JButton("Finish the game");
    static JLabel timeLabel = new JLabel();
    static JLabel scoreLabel = new JLabel();
    JLabel emptyLabel1 = new JLabel("     ");
    JLabel emptyLabel2 = new JLabel("     ");
    static CardLayout cl = new CardLayout();
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    Box box = Box.createHorizontalBox();

    static int score = -1;

    Timer timer = new Timer();
    private int time;
    private long startTime;

    PacmanVisual() {
        MapFilling mapFilling = new MapFilling();
        mapFilling.setLayout(new BorderLayout());

        MapEditor mapEditor = new MapEditor();

        time = MapEditor.coins;

        box.add(scoreLabel);
        box.add(emptyLabel1);
        box.add(timeLabel);
        box.add(emptyLabel2);
        box.add(gameFinishButton);
        mapFilling.add(box, "South");

        gameFinishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(mainPanel, "1");
                //MapFilling.isGame = false;
                mapFilling.repaint();
            }
        });

        mapFilling.repaint();

        mainPanel.setLayout(cl);
        menuPanel.setLayout(gbl);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(20, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;

        gbl.setConstraints(startButton, c);
        menuPanel.add(startButton);

        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(10, 0, 0, 0);
        gbl.setConstraints(mapEditorButton, c);
        menuPanel.add(mapEditorButton);

        gbl.setConstraints(exitButton, c);
        menuPanel.add(exitButton);

        mainPanel.add(menuPanel, "1");
        mainPanel.add(mapFilling, "2");
        mainPanel.add(mapEditor.editorPanel, "3");

        cl.show(mainPanel, "1");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(mainPanel, "2");
                newGame();
                mapFilling.repaint();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        mapEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(mainPanel, "3");
            }
        });

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(616, 679);
        frame.setResizable(false);
        frame.setVisible(true);


    }

    @Override
    public void run() {
        while (true) {
            try {
                if ((time * 1000 - (System.currentTimeMillis() - startTime) > 0)) {
                    PacmanVisual.timeLabel.setText("Time left: " + Long.toString((time * 1000 - (System.currentTimeMillis() - startTime)) / 1000));
                    Thread.sleep(1000);
                } else PacmanVisual.timeLabel.setText("Time is over!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setScore() {
        scoreLabel.setText("Score: " + Integer.toString(score));
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
    void newGame() {
        MapFilling.pacX = X_PAC_START;
        MapFilling.pacY = Y_PAC_START;

        MapFilling.isGame = true;

        PacmanEngine.lastKey = -1;

        score = 0;
        setScore();

        startTime = System.currentTimeMillis();
        (new Thread(PacmanVisual.this::run)).start();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (PacmanVisual.score == MapEditor.coins - 750) {
                    timer.cancel();
                }
            }
        }, time * 1000);
    }

    public static void main(String[] args) {
        PacmanVisual pacmanVisual = new PacmanVisual();

    }
}
