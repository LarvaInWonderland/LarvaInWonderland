import javax.swing.*;
import java.awt.*;

/**
 * Created by joft on 2015. 11. 9..
 */
public class mainFrame extends JFrame {

    private final int WIDTH = 604;
    private final int HEIGHT = 700;

    private gamePanel gp;
    private infoPanel ip;

    mainFrame() {

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        init();

    }

    private void init() {

        gp = new gamePanel();
        ip = new infoPanel();

        addKeyListener(gp);
        add(gp);
        add(ip);

    }

    public static void main(String [] args) {

        new mainFrame().setVisible(true);

    }

}
