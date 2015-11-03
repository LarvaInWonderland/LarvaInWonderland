import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by joft on 2015. 11. 2..
 */

public class frame extends JFrame {

    int frame_width = 600;
    int frame_height = 700;

    JPanel mp = new JPanel();
    gamePanel gp;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


    frame(String argString) {

        this.setSize(this.frame_width, this.frame_height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setTitle(argString);
        this.setLocation( (dim.width/2)-(this.getWidth()/2), (dim.height/2)-(this.getHeight()/2) );
        mp.setLayout(null);
        init();
        this.add(mp);
        this.setVisible(true);
        start();

    }

    void start() {

        Thread t = new Thread(gp);
        t.start();

    }

    void init() {

        mp.setBounds(0, 0, this.frame_width, this.frame_height);

        gp = new gamePanel();

        gp.setBounds(0, 80, gp.panelSize, gp.panelSize);

        mp.add(gp);

    }
}
