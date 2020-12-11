import javax.swing.*;
import java.awt.*;

public class Cells extends JButton {
  private int x;
  private int y;

  private final char DOT_X = 'X';
  private final char DOT_O = 'O';
  private final char DOT_EMPTY = 'N';
  //  Computer computer;
  BattleField battleField;


  private char content;


  public void setContent(char content) {
    this.content = content;
  }

  public char getContent() {
    return content;
  }

  public Cells(int x, int y, char content, BattleField battleField) {
    this.x = x;
    this.y = y;
    this.content = content;
    this.battleField = battleField;
    addActionListener(e -> {

      playerTurn(x, y, DOT_X);
//        battleField.computerTurn();
      battleField.isMotionHuman = true;
    });
  }

  public void playerTurn(int x, int y, char turnContent) {
    if (battleField.isMotionHuman && !battleField.gameFinished) {

      if (content == DOT_EMPTY) {
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        setFont(new Font("", Font.BOLD, 90));
        setText("X");
//      setForeground(Color.blue);
        setContent(DOT_X);
        setBackground(Color.lightGray);
        setEnabled(false);
//      Computer.humanTurn(y, x);
        battleField.humanTurn(y, x);
        battleField.isMotionHuman = false;

      } else {
        System.out.println("Занято!");
      }

    }


  }


}
