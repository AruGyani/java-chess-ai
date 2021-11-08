package src.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.pieces.Piece;

public class App extends JPanel {
    // Setup
    public static int WIDTH = 8 * Piece.TILE_SIZE + 80, HEIGHT = 8 * Piece.TILE_SIZE + 80;
    private static final int UPS = 60, FPS = 560;
    private static JFrame frame = new JFrame();

    public Game game = new Game();

    private static final boolean RENDER_TIME = false;
    private static final boolean running = true;

    public App() {
        setFocusable(true);
               
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
        
            }

            public void keyTyped(KeyEvent e) {

            }
        });

        addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {
                game.mouseClicked(e);
            }   

            public void mouseClicked(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                game.mouseMoved(e);
            }
            
        });
    }

    public void paint(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;

        g.setColor(new Color(31, 31, 31));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        game.render(g);
    }

    public void update() {
        WIDTH = frame.getWidth();
        HEIGHT = frame.getHeight();

    }

    @SuppressWarnings("unused")
    public void run() {
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;

        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                repaint();
                update();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                if (RENDER_TIME) {
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                }

                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();

        app.setDoubleBuffered(true);
        app.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame.add(app);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Chess - Aru Gyani");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        app.run();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}
