import java.util.Random;

public class Computer {
  private int size;
  private int dotsToWin;

  private int turnX;
  private int turnY;
  public char[][] map;
  private final char DOT_X = 'X';
  private final char DOT_O = 'O';
  private final char DOT_EMPTY = '.';

  private Random random = new Random();
//  BattleField battleField = new BattleField();



  public int getTurnX() {
    return turnX;
  }
  public int getTurnY() {
    return turnY;
  }

  public void initMap() {
    char[][] map = new char[SettingWindow.MAX_FIELD_SIZE][SettingWindow.MAX_FIELD_SIZE];
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map.length; j++) {
        map[i][j] = DOT_EMPTY;
      }
    }
  }

  public void aiTurn() {
    int x;
    int y;

    // Попытка победить самому
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (isCellValid(i, j)) {
          map[i][j] = DOT_O;

          if (checkWinLines(DOT_O, dotsToWin)) {
            return;
          }
          map[i][j] = DOT_EMPTY;
        }
      }
    }
// Сбить победную линии противника, если осталось 1 ход для победы
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (isCellValid(i, j)) {
          map[i][j] = DOT_X;
          if (checkWinLines(DOT_X, dotsToWin)) {
            map[i][j] = DOT_O;


            return;
          }
          map[i][j] = DOT_EMPTY;
        }
      }
    }

// Сбить победную линии противника, если осталось 2 хода для победы
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (isCellValid(i, j)) {
          map[i][j] = DOT_X;
          if (checkWinLines(DOT_X, dotsToWin - 1) &&
              Math.random() < 0.5) { //  фактор случайности, чтобы сбивал не все время первый попавшийся путь.
            map[i][j] = DOT_O;
            return;
          }
          map[i][j] = DOT_EMPTY;
        }
      }
    }

// Сходить в произвольную не занятую ячейку

    do {
      x = random.nextInt(size);
      y = random.nextInt(size);
    } while (!isCellValid(y, x));
    map[y][x] = DOT_O;
  }



  public boolean checkWinLines(char dot, int dotsToWin) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
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

  public boolean checkLine(int cy, int cx, int vy, int vx, char dot, int dotsToWin) {
    if (cx + vx * (dotsToWin - 1) > size - 1 || cy + vy * (dotsToWin - 1) > size - 1 ||
        cy + vy * (dotsToWin - 1) < 0) {
      return false;
    }

    for (int i = 0; i < dotsToWin; i++) {
      if (map[cy + i * vy][cx + i * vx] != dot) {
        return false;
      }
    }
    return true;
  }


  public boolean isCellValid(int y, int x) {
    if (y < 0 || x < 0 || y >= size || x >= size) {
      return false;
    }
    return map[y][x] == DOT_EMPTY;
  }
}
