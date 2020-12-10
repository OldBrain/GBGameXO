import javax.swing.*;

public class Cells extends JButton {
  private int x;
  private int y;

  public Cells(int x, int y) {
    this.x = x;
    this.y = y;

  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }
}
