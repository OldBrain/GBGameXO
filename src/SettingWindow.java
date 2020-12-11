import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame {
    private static final int WINDOW_X = GameWindow.MAIN_WINDOW_X + 50;
    private static final int WINDOW_Y = GameWindow.MAIN_WINDOW_Y + 50;
    private static final int WINDOW_WIDTH = GameWindow.MAIN_WINDOW_WIDTH - 100;
    private static final int WINDOW_HEIGHT = 400;

    private final int MIN_FIELD_SIZE = 3;
    static final int MAX_FIELD_SIZE = 10;

    private GameWindow gameWindow;
    private Computer computer;

    private JRadioButton rbHumVsAi;
    private JRadioButton rbHumVsHum;
    private ButtonGroup gameMode;

    private JSlider slFieldSize;
    private JSlider slWiningLength;



    public SettingWindow(GameWindow gameWindow,Computer computer) {
        this.gameWindow = gameWindow;
        this.computer = computer;
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);

        setTitle("Settings");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLayout(new GridLayout(8, 1));

        add(new JLabel("Choose game mode:"));

        rbHumVsAi = new JRadioButton("HumVsAi", true);
        add(rbHumVsAi);
        rbHumVsHum = new JRadioButton("HumVsHum");
        rbHumVsHum.setEnabled(false);
        add(rbHumVsHum);
        gameMode = new ButtonGroup();
        gameMode.add(rbHumVsAi);
        gameMode.add(rbHumVsHum);

        add(new JLabel("Choose size:"));
        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSize.setMajorTickSpacing(1);
        slFieldSize.setPaintTicks(true);
        slFieldSize.setPaintLabels(true);
        add(slFieldSize);

        add(new JLabel("Choose winning length:"));
        slWiningLength = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        slWiningLength.setMajorTickSpacing(1);
        slWiningLength.setPaintTicks(true);
        slWiningLength.setPaintLabels(true);
        add(slWiningLength);

        slFieldSize.addChangeListener(e -> {
            slWiningLength.setMaximum(slFieldSize.getValue());
        });

        JButton btnStartAGame = new JButton("Start a game");
        add(btnStartAGame);
        btnStartAGame.addActionListener(e -> {
            int mode;
            if (rbHumVsAi.isSelected()) {
                mode = BattleField.MODE_H_VS_AI;
            } else {
                mode = BattleField.MODE_H_VS_H;
            }

            int fieldSize = slFieldSize.getValue();
            int winningLength = slWiningLength.getValue();

            computer.size = fieldSize;
            computer.dotsToWin = winningLength;
            computer.initMap();
            computer.gameFinished = false;


            gameWindow.startNewGame(mode, fieldSize, winningLength);

            setVisible(false);
        });


        setVisible(false);
    }
}
