import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BattleField extends JPanel {
  public Cells[][] cell = new Cells[SettingWindow.MAX_FIELD_SIZE][SettingWindow.MAX_FIELD_SIZE];
  static final int MODE_H_VS_AI = 0;
  static final int MODE_H_VS_H = 1;
  private Random random = new Random();
  private GameWindow gameWindow;
  private SettingWindow settingWindow;

  private int mode;
  int fieldSize;
  private int winningLength;

  public boolean isInit;
  private boolean isInitPanel;

  public boolean isMotionHuman = true;

  private final char DOT_X = 'X';
  private final char DOT_O = 'O';
  private final char DOT_EMPTY = 'N';

  public boolean gameFinished;



  public BattleField(LayoutManager layout, GameWindow gameWindow) {
    super(layout);
    this.gameWindow = gameWindow;

  }

  public String who_Won() {
    String who = null;
    if (isFull()) {
      return "Ничья.";

    }

    if (checkWinLines(DOT_X, winningLength)) {
      return " крестики";
    }
    if (checkWinLines(DOT_O, winningLength)) {
      return " нолики";
    }
    return who;
  }



  void computerTurn(int x, int y) {
    cell[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    cell[x][y].setFont(new Font("", Font.BOLD, getCntSize(fieldSize)));
    cell[x][y].setBackground(Color.darkGray);
    cell[x][y].setEnabled(false);
    cell[x][y].setContent(DOT_O);
    cell[x][y].setText("0");
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
  }

  private void clearingPanel() {
    for (int i = 0; i < this.fieldSize; i++) {
      for (int j = 0; j < this.fieldSize; j++) {
//        System.out.println(cell[i][j].toString());
        remove(cell[i][j]);

      }

    }
  }


  public boolean checkWinLines(char dot, int dotsToWin) {
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        if (checkLine(i, j, 0, 1, dot, dotsToWin) ||
            checkLine(i, j, 1, 0, dot, dotsToWin) ||
            checkLine(i, j, 1, 1, dot, dotsToWin) ||
            checkLine(i, j, -1, 1, dot, dotsToWin)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean checkLine(int cy, int cx, int vy, int vx, char dot, int dotsToWin) {
    if (cx + vx * (dotsToWin - 1) > fieldSize - 1 || cy + vy * (dotsToWin - 1) > fieldSize - 1 ||
        cy + vy * (dotsToWin - 1) < 0) {
      return false;
    }

    for (int i = 0; i < dotsToWin; i++) {
//      if (map[cy + i * vy][cx + i * vx] != dot) {
      if (cell[cy + i * vy][cx + i * vx].getContent() != dot) {
        return false;
      }
    }
    return true;
  }

  private boolean isCellValid(int y, int x) {
    if (y < 0 || x < 0 || y >= fieldSize || x >= fieldSize) {
      return false;
    }
    return cell[y][x].getContent() == DOT_EMPTY;
  }

  private boolean isFull() {
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        if (cell[i][j].getContent() == DOT_EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  public void humanTurn(int x, int y) {
    go();
  }

  public void aiTurn() {
    int x;
    int y;

    // Попытка победить самому
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        if (isCellValid(i, j)) {
          cell[i][j].setContent(DOT_O);

          if (checkWinLines(DOT_O, winningLength)) {
            computerTurn(i, j);
            return;
          }
          cell[i][j].setContent(DOT_EMPTY);
        }
      }
    }
// Сбить победную линии противника, если осталось 1 ход для победы
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        if (isCellValid(i, j)) {
          cell[i][j].setContent(DOT_X);
          if (checkWinLines(DOT_X, winningLength)) {

            cell[i][j].setContent(DOT_O);
            computerTurn(i, j);

            return;
          }
          cell[i][j].setContent(DOT_EMPTY);
        }
      }
    }

// Сбить победную линии противника, если осталось 2 хода для победы
    for (int i = 0; i < fieldSize; i++) {
      for (int j = 0; j < fieldSize; j++) {
        if (isCellValid(i, j)) {
          cell[i][j].setContent(DOT_X);

          if (checkWinLines(DOT_X, winningLength - 1) &&
              Math.random() < 0.5) { //  фактор случайности, чтобы сбивал не все время первый попавшийся путь.
            cell[i][j].setContent(DOT_O);
            computerTurn(i, j);
            return;
          }
          cell[i][j].setContent(DOT_EMPTY);

        }
      }
    }

// Сходить в произвольную не занятую ячейку

    do {
      x = random.nextInt(fieldSize);
      y = random.nextInt(fieldSize);
    } while (!isCellValid(y, x));
    cell[y][x].setContent(DOT_O);


    computerTurn(y, x);
  }


  public void go() {
    gameFinished = true;

    if (checkWinLines(DOT_X, winningLength)) {
      JOptionPane.showMessageDialog(null, "<html><h2>Вы выиграли!!!</h2><i>Нажмите кнопку //New Game// для продолжения игры</i>");
      return;
    }
    if (isFull()) {
      JOptionPane.showMessageDialog(null, "<html><h2>Ничья</h2><i>Нажмите кнопку //New Game// для продолжения игры</i>");
      return;
    }

    aiTurn();
    if (checkWinLines(DOT_O, winningLength)) {
      JOptionPane.showMessageDialog(null, "<html><h2>Комьютер победил</h2><i>Нажмите кнопку //New Game// для продолжения игры</i>");

      return;
    }

    gameFinished = false;
  }

  public int getCntSize(int fieldSize) {
    if (fieldSize > 7) {
      return 40;
    }
    if (fieldSize <= 7) {
      return 90;
    }
    return 90;
  }
}
