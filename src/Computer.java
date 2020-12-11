import java.util.Random;

public class Computer {
  private static char[][] map;
  static int size;
  static int dotsToWin;

  public static boolean gameFinished;

 public static int turnX;
  public static int turnY;
//  public char[][] map;
  private static final char DOT_X = 'X';
  private static final char DOT_O = 'O';
  private static final char DOT_EMPTY = 'N';

  private static Random random = new Random();
//  BattleField battleField = new BattleField();


  public Computer() {
//    initMap();
//    go();
  }

  public static void humanTurn(int x, int y) {
    if (isCellValid(y, x)) {
      map[y][x] = DOT_X;
      go();
    }
  }


  public int getTurnX() {
    return turnX;
  }
  public int getTurnY() {
    return turnY;
  }

  public static void go() {
    gameFinished = true;

    printMap();
    if (checkWinLines(DOT_X, dotsToWin)) {
      System.out.println("Вы выиграли!!!");
      return;
    }
    if (isFull()) {
      System.out.println("Ничья");
      return;
    }

    aiTurn();
    printMap();
    if (checkWinLines(DOT_O, dotsToWin)) {
      System.out.println("Комьютер победил");
      return;
    }
    if (isFull()) {
      System.out.println("Ничья");
      return;
    }

    gameFinished = false;
  }



  public void initMap() {

    map = new char[size][size];
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map.length; j++) {
        map[i][j] = DOT_EMPTY;
      }
    }
  }

  public static void aiTurn() {
    int x;
    int y;

    // Попытка победить самому
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (isCellValid(i, j)) {
          map[i][j] = DOT_O;

          if (checkWinLines(DOT_O, dotsToWin)) {
            computerTurn(i, j);
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
            computerTurn(i, j);

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
            computerTurn(i, j);
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
    computerTurn(y, x);
  }




  public static boolean checkWinLines(char dot, int dotsToWin) {
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

  public static boolean checkLine(int cy, int cx, int vy, int vx, char dot, int dotsToWin) {
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


  public static boolean isCellValid(int y, int x) {
    if (y < 0 || x < 0 || y >= size || x >= size) {
      return false;
    }
    return map[y][x] == DOT_EMPTY;
  }

   static boolean isFull() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (map[i][j] == DOT_EMPTY) {
          return false;
        }
      }
    }
    return true;
  }


   static void printMap() {
    System.out.print("  ");
    for (int i = 0; i < size; i++) {
      System.out.print(i + 1 + " ");
    }
    System.out.println();
    for (int i = 0; i < size; i++) {
      System.out.print(i + 1 + " ");
      for (int j = 0; j < size; j++) {
        System.out.printf("%c ", map[i][j]);
      }
      System.out.println();
    }
  }

  public static void computerTurn(int i, int j) {
    turnX = i;
    turnY = j;
  }

}
