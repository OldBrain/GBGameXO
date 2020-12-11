import primer.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

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



  public BattleField(LayoutManager layout, GameWindow gameWindow) {
    super(layout);
    this.gameWindow = gameWindow;
   }





  public void initMap(int fieldSize) {
//      Cells[][] cell = new Cells[fieldSize][fieldSize];
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        cell[i][j] = new Cells(i,j,Cells.DOT.N);
        add(cell[i][j]);
        isInitPanel = true;

      }
    }

  }





//  public void startNewGame(int mode, int fieldSize, int winningLength) {
//  }




  public void startNewGame(int mode, int fieldSize, int winningLength) {
    if (isInitPanel) clearingPanel();
    this.mode = mode;
    this.fieldSize = fieldSize;
    this.winningLength = winningLength;
    isInit = true;
//    System.out.println(toString());


//    System.out.println(cell[1][1].toString());
    initMap(fieldSize);
  revalidate();
//  repaint();

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
