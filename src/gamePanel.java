import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.geom.Arc2D;

/**
 * 게임 화면
 */
public class gamePanel extends JPanel implements KeyListener {

    private final int WIDTH = 600;
    private final int HEIGHT = 600;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;
    private boolean game;
    private int appleX;
    private int appleY;
    private long delay;
    private int fps = 60;
    private int fpsStr;

    private Larva larvas;
    private Thread th;

    gamePanel() {

        setBounds(2, 76, WIDTH, HEIGHT);
        setBackground(Color.lightGray);
        init();

    }

    void init() {

        larvas = new Larva(5*5);
        moveRight = true;
        moveLeft = false;
        moveUp = false;
        moveDown = false;
        game = true;
        appleCreate();

    }

    void start() {

        th = new Thread() {

            @Override
            public void run() {

                long lastLoopTime = System.nanoTime();
                long lastFpsTime = 0;
                final int TARGET_FPS = 60;
                final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

                while(game) {

                    long now = System.nanoTime();
                    long updateLength = now - lastLoopTime;
                    lastLoopTime = now;
                    /*double delta = updateLength / ((double)OPTIMAL_TIME);*/

                    lastFpsTime += updateLength;
                    fps++;

                    if (lastFpsTime >= 1000000000)
                    {
                        setFps(fps);
                        lastFpsTime = 0;
                        fps = 0;
                    }

                    if(game) process();
                    repaint();

                    try{
                        delay = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;
                        Thread.sleep( delay );
                    } catch (Exception e) {}

                }

            }

        };

        th.start();

    }

    void process(){

        larvas.overCheck();
        larvas.moveLarvas();
        checkApple();
    }

    void appleCreate() {

        boolean check = true;

        while (check) {
            for( int cnt = 0 ; cnt < larvas.larvas ; cnt++) {
                appleX = (int) (Math.random() * 28) * larvas.larvaSize + 20;
                appleY = (int) (Math.random() * 28) * larvas.larvaSize + 20;
                if (appleX+10 != larvas.x[cnt] || appleX-10 != larvas.x[cnt] ||
                        appleY+10 != larvas.y[cnt] || appleY-10 != larvas.y[cnt]) {
                    check = false;
                }
            }
        }
    }

    void checkApple() {

        if( larvas.x[0] >= appleX-10 && larvas.x[0] <= appleX+10
                && larvas.y[0] >= appleY-10 && larvas.y[0] <= appleY+10 ) {
            larvas.larvaIncrement();
            appleCreate();
        }

    }

    void gameOver(Graphics g) {

        String msg = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 30);
        FontMetrics metr = getFontMetrics(font);
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.green);
        g.fillRoundRect(appleX-10, appleY-10, 20, 20, 20, 20);
        larvas.drawLarvas(g);
        if(!game) gameOver(g);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_LEFT && !moveRight ) {
            moveLeft = true;
            moveUp = moveDown = false;
        }
        if ( key == KeyEvent.VK_RIGHT && !moveLeft ) {
            moveRight = true;
            moveUp = moveDown = false;
        }
        if ( key == KeyEvent.VK_UP && !moveDown ) {
            moveUp = true;
            moveLeft = moveRight = false;
        }
        if ( key == KeyEvent.VK_DOWN && !moveUp ) {
            moveDown = true;
            moveLeft = moveRight = false;
        }
        if( key == KeyEvent.VK_SPACE && !game ) {
            game = true;
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public int getAppleX() { return appleX; }
    public int getAppleY() { return appleY; }
    public int getLarvars() { return larvas.larvas; }
    public long getDelay() { return delay; }
    public int getFps() { return fpsStr; }
    public void setFps(int arg) { fpsStr = arg; }

    private class Larva {

        private final int larvaSize = 20;

        private int larvas;
        private int delay;
        private int x[] = new int[999];
        private int y[] = new int[999];

        Larva(int larvasNum) {
            larvas = larvasNum;
            delay = 1;
            for( int cnt = 0 ; cnt < larvas ; cnt++ ) {
                x[cnt] = (int)WIDTH/3 - cnt * larvaSize;
                y[cnt] = (int)HEIGHT/2;
            }
        }

        void larvaIncrement() {
            larvas+=5;
            if(larvas%35==0) delay++;
        }

        void moveLarvas() {

            for( int cnt = larvas ; cnt > 0 ; cnt-- ) {
                x[cnt] = x[ cnt - 1 ];
                y[cnt] = y[ cnt - 1 ];
            }

            if(moveLeft) {x[0] -= 2;}
            if(moveRight) {x[0] += 2;}
            if(moveUp) {y[0] -= 2;}
            if(moveDown) {y[0] += 2;}

        }

        void drawLarvas(Graphics g) {

            for( int cnt = 0 ; cnt < larvas ; cnt++ ) {

                if(cnt < 6) {
                    g.setColor(Color.blue);
                    g.fillRoundRect(x[cnt]-larvaSize/2, y[cnt]-larvaSize/2, larvaSize, larvaSize, larvaSize, larvaSize);
                } else if(x[cnt]!=0 && y[cnt]!=0){
                    g.setColor(Color.black);
                    g.fillRoundRect(x[cnt]-larvaSize/2, y[cnt]-larvaSize/2, larvaSize, larvaSize, larvaSize, larvaSize);
                }

            }

            Toolkit.getDefaultToolkit().sync();

        }

        void overCheck() {

            for( int cnt = larvas ; cnt > 0 ; cnt-- ) {
                if( x[0] == x[cnt] && y[0] == y[cnt]) {
                    game = false;
                }
            }

            if( x[0] < 0 || x[0] > WIDTH || y[0] < 0 || y[0] > HEIGHT ) {
                game = false;
            }

        }

    }

}