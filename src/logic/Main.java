package logic;//Для создания кода игры с полем более чем 3х3
// надо вызывать initLine(arrLines, map[i][j],k),
// где k ,будет принимать значения от 0 до SIZE*2+2
// SIZE вертикальных линий + SIZE горизонтальных линий + 2 диагонали.
// Для увелеченя читабельности кода и облегчения создания
// интеллекта бота объявлены константы и пока поле будет 3x3, т.к. это классический
// вариант игры. В дальнейшем думаю можно будет доработать на
// любой разумный размер поля.




import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Random rand = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static char[][] map;
    public static final int SIZE = 3;
    public static final char DOT_EMPTY='•';
    public static final char DOT_X='X';
    public static final char DOT_O='0';

    public static final int COUNT_EMPTY=0;
    public static final int COUNT_O=1;
    public static final int COUNT_X=2;

    public static final int COORDINATES_X = 0;
    public static final int COORDINATES_Y = 1;

    public static final int I_HLINE1=0;
    public static final int I_HLINE2=1;
    public static final int I_HLINE3=2;
    public static final int J_VLINE1=3;
    public static final int J_VLINE2=4;
    public static final int J_VLINE3=5;
    public static final int UP_LEFT_DOWN_RIGHT=6;
    public static final int UP_RIGHT_DOWN_LEFT=7;



    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

//          *******  Для отладки

            System.out.println("Масив КООРДИНАТ ");
            for (int i = 0; i <8 ; i++) {
                for (int j = 0; j < 3; j++) {

                    for (int k = 0; k <2 ; k++) {
//       System.out.print(i+initArrayLines(map)[i][j][0]);
       System.out.print("arr[" + i + "][" + j + "][" + k + "] = " + initArrayLines(map)[i][j][k] + "\t");

                    }
                    System.out.println();
                }
            }
//            *************
        }
        System.out.println("Игра закончена");
    }




    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }


    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }



    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }


    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }



    public static boolean checkWin(char symb) {
        if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if(map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if(map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if(map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if(map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if(map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if(map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        return false;
    }


    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static int[][][] initLine(int[][][] arrLines,char symbol,int linesID,int x,int y) {
        switch (symbol) {
            case DOT_EMPTY:
                arrLines[linesID][COUNT_EMPTY][0]++;
                arrLines[linesID][COUNT_EMPTY][COORDINATES_X]=  x;
                arrLines[linesID][COUNT_EMPTY][COORDINATES_Y]=  y;

                break;
            case DOT_O:
                arrLines[linesID][COUNT_O][0]++;
                arrLines[linesID][COUNT_O][COORDINATES_X]=  x;
                arrLines[linesID][COUNT_O][COORDINATES_Y]=  y;
                break;
            case  DOT_X:
                arrLines[linesID][COUNT_X][0]++;
                arrLines[linesID][COUNT_X][COORDINATES_X]=  x;
                arrLines[linesID][COUNT_X][COORDINATES_Y]=  y;
                break;
        }
        return arrLines;
    }

    public static int[][][] initArrayLines(char[][] map ) {
//      arrLines[linesID][COUNT][COORDINATES];
        int[][][] arrLines = new int[8][3][2];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j <SIZE ; j++) {
//                UP_LEFT_DOWN_RIGHT
                if (i == j) {
                    initLine(arrLines, map[i][j],UP_LEFT_DOWN_RIGHT,i,j);
                }

//                UP_RIGHT_DOWN_LEFT
                if (i + j==SIZE-1) {
                    initLine(arrLines, map[i][j],UP_RIGHT_DOWN_LEFT,i,j);
                }
//                I_HLINE
                if (i ==0) initLine(arrLines, map[i][j], I_HLINE1,i,j);
                if (i ==1)initLine(arrLines, map[i][j],I_HLINE2,i,j);
                if (i ==2)initLine(arrLines, map[i][j], I_HLINE3,i,j);
//                 J_VLINE
                if (j ==0)initLine(arrLines, map[i][j], J_VLINE1,i,j);
                if (i ==2)initLine(arrLines, map[i][j], J_VLINE2,i,j);
                if (i ==2)initLine(arrLines, map[i][j], J_VLINE3,i,j);

            }
        }


        return arrLines;
    }

    public static void getGoodTurn(int[][] arrLines) {
        for (int i = 0; i < SIZE*2+2; i++) {
            if (arrLines[i][DOT_O] == 2 & arrLines[i][DOT_EMPTY] == 1) {
                // Это победа
//                map[y][x] = DOT_O;
//                return;
            }
        }
    }


}
