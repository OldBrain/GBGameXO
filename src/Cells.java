import javax.swing.*;
import java.awt.*;

public class Cells extends JButton {
  private int x;
  private int y;

  public enum DOT {O, X, N,}

  @Override
  public String toString() {
    return "Cells{" +
        "x=" + x +
        ", y=" + y +
        ", content=" + content +
        '}';
  }

  private DOT content;

  public void setContent(DOT content) {
    this.content = content;
  }

  public Cells(int x, int y, DOT content) {
    this.x = x;
    this.y = y;
    this.content = content;
    addActionListener(e -> {
      KeyPressed(x, y, this.content);
    });
  }

  private void KeyPressed(int x, int y, DOT content) {
    if (content == DOT.N) {
      setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
      setFont(new Font("", Font.BOLD, 90));
      setText("X");
//      setForeground(Color.blue);
      setContent(DOT.X);
      setBackground(Color.lightGray);
      setEnabled(false);
    } else {
      System.out.println("Занято!");

    }


  }

  public void setX(int x) {
    this.x = x;

  }

  public void setY(int y) {
    this.y = y;
  }

  public int getXCoordinate() {
    return x;
  }


  public int getYCoordinate() {
    return y;
  }


}
