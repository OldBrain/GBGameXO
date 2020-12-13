import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
  static final int MAIN_WINDOW_X = 250;
  static final int MAIN_WINDOW_Y = 100;
  static final int MAIN_WINDOW_WIDTH = 660;
  static final int MAIN_WINDOW_HEIGHT = 600;
  static int SIZE = 3;
  JPanel panelForButtons;
  JButton buttonNewGame;
  JButton buttonExit;
  private int fieldSize = 3;
  private int oldFieldSize;
  private SettingWindow settingWindow;
  private BattleField battleField;


  public GameWindow() {


    setResizable(false);
    setBounds(MAIN_WINDOW_X, MAIN_WINDOW_Y, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("XO GAME");

    this.settingWindow = new SettingWindow(this);

    battleField = new BattleField(new GridLayout(fieldSize, fieldSize), this);

    panelForButtons = new JPanel(new GridLayout(1, 2));
    buttonNewGame = new JButton("<html><h2>New game</h2><i><center> and  settings</center></i>");
    buttonExit = new JButton("<html><h1>EXIT</h1>");
    buttonExit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    buttonNewGame.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    panelForButtons.add(buttonNewGame);
    panelForButtons.add(buttonExit);
    add(panelForButtons, BorderLayout.SOUTH);

    JLabel jLabel = new JLabel();
    jLabel.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel.setIcon(new ImageIcon(getClass().getResource("img/fon.jpg")));
    getContentPane().add(jLabel, BorderLayout.CENTER);

    setVisible(true);

    buttonExit.addActionListener(e -> {
      System.exit(0);
    });
    buttonNewGame.addActionListener(e -> {

      settingWindow.setVisible(true);

    });


  }

  public void startNewGame(int mode, int fieldSize, int winningLength) {

    battleField.repaint();
    this.fieldSize = fieldSize;

    if (battleField.isInit) {
      remove(battleField);
    }
    battleField = new BattleField(new GridLayout(fieldSize, fieldSize), this);
    add(battleField, BorderLayout.CENTER);
    battleField.startNewGame(mode, fieldSize, winningLength);

  }
}
