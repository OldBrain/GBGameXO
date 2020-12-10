import javax.swing.*;
import java.awt.*;

public class BattleField extends JPanel {
  static final int MODE_H_VS_AI = 0;
  static final int MODE_H_VS_H = 1;
  private GameWindow gameWindow;

  private int mode;
  private int fieldSize;
  private int winningLength;
  Cells[][] cell =new Cells[3][3];

  private boolean isInit;

  public BattleField(LayoutManager layout, GameWindow gameWindow) {
    super(layout);
    this.gameWindow = gameWindow;
    initMap();
   }

  public void initMap() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        cell[i][j] = new Cells(i,j);
        add(cell[i][j]);


      }

    }

  }

//  public void startNewGame(int mode, int fieldSize, int winningLength) {
//  }

  public void startNewGame(int mode, int fieldSize, int winningLength) {
    this.mode = mode;
    this.fieldSize = fieldSize;
    this.winningLength = winningLength;

    isInit = true;
    repaint();
  }
}
