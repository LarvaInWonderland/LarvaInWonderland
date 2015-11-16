import javax.swing.*;

/**
 * Created by joft on 2015. 11. 9..
 */
public class mainFrame extends JFrame {

    private final int WIDTH = 604;
    private final int HEIGHT = 700;

    private gamePanel gp;
    private infoPanel ip;

    Thread th;

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
        start();

    }

    private void start() {

        gp.start();

        th = new Thread() {
            public void run() {
                while(true) {

                    ip.setValues(gp.getAppleX(), gp.getAppleY(), gp.getLarvars(), gp.getDelay(), gp.getFps());

                    try{ Thread.sleep(100); } catch (Exception e) {}

                }
            };
        };
        th.start();
    }

    public static void main(String [] args) {

        new mainFrame().setVisible(true);

    }

}
