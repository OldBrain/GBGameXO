import primer.Logic;

import javax.swing.*;
import java.awt.*;

public class BattleField extends JPanel {
  static final int MODE_H_VS_AI = 0;
  static final int MODE_H_VS_H = 1;
//  private  GridLayout layout;
  private GameWindow gameWindow;

  private int mode;
  private int fieldSize=3;
  private int winningLength;

  private boolean isInit;

  public BattleField(LayoutManager layout, GameWindow gameWindow) {
    super(layout);
//    this.layout = new GridLayout(3, 3);
    this.gameWindow = gameWindow;
    initMap();



//    repaint();
//    revalidate();
   }





  public void initMap() {
      Cells[][] cell = new Cells[fieldSize][fieldSize];
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
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
  initMap();
  repaint();
  }
}
