import primer.BattleMap;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
  static final int MAIN_WINDOW_X = 450;
  static final int MAIN_WINDOW_Y = 200;
  static final int MAIN_WINDOW_WIDTH = 450;
  static final int MAIN_WINDOW_HEIGHT = 400;
  static int SIZE = 3;
  JPanel panelForButtons;
  JPanel panelForFields;
  JButton buttonNewGame;
  JButton buttonExit;
  int fieldSize;

  private SettingWindow settingWindow;
  private BattleField battleField;
  private int mode;
  private int winningLength;

  public GameWindow() {


    setBounds(MAIN_WINDOW_X, MAIN_WINDOW_Y, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("XO GAME");

    this.settingWindow = new SettingWindow(this);

    battleField = new BattleField(new GridLayout(3,3),this);
    add(battleField, BorderLayout.CENTER);

    panelForButtons = new JPanel(new GridLayout(1,2));
    panelForFields = new JPanel(new GridLayout(SIZE, SIZE));
    buttonNewGame = new JButton("NewGame");
    buttonExit = new JButton("Exit");
    buttonExit.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
    buttonNewGame.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
    panelForButtons.add(buttonNewGame);
    panelForButtons.add(buttonExit);
    add(panelForButtons, BorderLayout.SOUTH);




//    revalidate();
//    add(panelForFields, BorderLayout.CENTER);



    setVisible(true);

    buttonExit.addActionListener(e ->{
      System.exit(0);
          });
    buttonNewGame.addActionListener(e ->{
       settingWindow.setVisible(true);

    });


  }
private void InitPanel() {

}
  public void startNewGame(int mode, int fieldSize, int winningLength) {
    this.fieldSize = fieldSize;

    battleField.startNewGame(mode, fieldSize, winningLength);
//

  }
}
