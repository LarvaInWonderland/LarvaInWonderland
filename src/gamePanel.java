import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by joft on 2015. 11. 2..
 */

public class gamePanel extends JPanel implements MouseListener, MouseMotionListener, Runnable{

    int panelSize = 600;

    Point mousePoint = new Point();

    gamePanel() {

        this.setBackground(new Color(200, 200, 200));
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    public void run() {

        try {

            while(true) {
                Thread.sleep(100);
                /*System.out.println(mousePoint);*/
            }


        } catch (Exception e) {
            System.out.println("\nThread Error\n");
        }

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        mousePoint.setLocation( e.getX(), e.getY());
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
