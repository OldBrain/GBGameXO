import javax.swing.*;
import java.awt.*;

public class BattleField extends JPanel {
  public Cells[][] cell = new Cells[SettingWindow.MAX_FIELD_SIZE][SettingWindow.MAX_FIELD_SIZE];
  static final int MODE_H_VS_AI = 0;
  static final int MODE_H_VS_H = 1;

  private GameWindow gameWindow;

  private int mode;
  private int fieldSize;
  private int winningLength;

  public boolean isInit;
  private boolean isInitPanel;

  public boolean isMotionHuman = true;

  private final char DOT_X = 'X';
  private final char DOT_O = 'O';
  private final char DOT_EMPTY = 'N';

  public BattleField() {

  }

  public BattleField(LayoutManager layout, GameWindow gameWindow) {
    super(layout);
    this.gameWindow = gameWindow;
  }

  void computerTurn() {
    cell[Computer.turnX][Computer.turnY].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    cell[Computer.turnX][Computer.turnY].setFont(new Font("", Font.BOLD, 90));
    cell[Computer.turnX][Computer.turnY].setBackground(Color.darkGray);
    cell[Computer.turnX][Computer.turnY].setEnabled(false);
    cell[Computer.turnX][Computer.turnY].setContent(DOT_O);
    cell[Computer.turnX][Computer.turnY].setText("0");
    revalidate();
    isMotionHuman = true;
  }


  public void initMap(int fieldSize) {
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        cell[i][j] = new Cells(i, j, DOT_EMPTY, this);
        add(cell[i][j]);
        isInitPanel = true;

      }
    }

  }


  public void startNewGame(int mode, int fieldSize, int winningLength) {
    isMotionHuman = true;
    if (isInitPanel) clearingPanel();
    this.mode = mode;
    this.fieldSize = fieldSize;
    this.winningLength = winningLength;
    isInit = true;
    initMap(fieldSize);
    revalidate();
    Computer.size = fieldSize;
  }

  private void clearingPanel() {
    for (int i = 0; i < this.fieldSize; i++) {
      for (int j = 0; j < this.fieldSize; j++) {
//        System.out.println(cell[i][j].toString());
        remove(cell[i][j]);

      }

    }
  }
}
