import javax.swing.*;
import java.awt.*;

/**
 * Created by joft on 2015. 11. 9..
 */
public class infoPanel extends JPanel {

    private final int WIDTH = 600;
    private final int HEIGHT = 70;

    private JLabel appleX;
    private JLabel appleXvalue;
    private JLabel appleY;
    private JLabel appleYvalue;
    private JLabel larvaNum;
    private JLabel larvasValue;
    private JLabel delay;
    private JLabel delayValue;
    private JLabel fps;
    private JLabel fpsValue;

    infoPanel() {

        setBounds(2, 4, WIDTH, HEIGHT);
        setBackground(Color.red);
        setLayout(null);
        init();

    }

    void init(){

        appleX = new JLabel("appleX");
        appleXvalue = new JLabel();
        appleY = new JLabel("appleY");
        appleYvalue = new JLabel();
        larvaNum = new JLabel("larvas");
        larvasValue = new JLabel();
        delay = new JLabel("delay");
        delayValue = new JLabel();
        fps = new JLabel("FPS");
        fpsValue = new JLabel();

        appleX.setBounds(10, 30, 50, 20);
        appleXvalue.setBounds(70, 30, 50, 20);
        appleY.setBounds(130, 30, 50, 20);
        appleYvalue.setBounds(190, 30, 50, 20);
        larvaNum.setBounds(250, 30, 50, 20);
        larvasValue.setBounds(310, 30, 50, 20);
        delay.setBounds(370, 30, 50, 20);
        delayValue.setBounds(430, 30, 50, 20);
        fps.setBounds(490, 30, 50, 20);
        fpsValue.setBounds(550, 30, 50, 20);


        add(appleX);
        add(appleXvalue);
        add(appleY);
        add(appleYvalue);
        add(larvaNum);
        add(larvasValue);
        add(delay);
        add(delayValue);
        add(fps);
        add(fpsValue);


    }

    public void setValues(int x, int y, int larvas, long delay, int fps) {
        appleXvalue.setText(String.valueOf(x));
        appleYvalue.setText(String.valueOf(y));
        larvasValue.setText(String.valueOf(larvas));
        delayValue.setText(String.valueOf(delay));
        fpsValue.setText(String.valueOf(fps));
    }

}