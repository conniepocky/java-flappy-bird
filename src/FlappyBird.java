import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int bWidth = 360;
    int bHeight = 640;

    //imgs

    Image bg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //bird

    int birdX = bWidth/8;
    int birdY = bHeight/2;
    int birdWidth = 35;
    int birdHeight = 25;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }

    }

    // game logic

    Bird bird;
    int velocityY = 0;
    int gravity = 1;

    Timer gameLoop;

    FlappyBird() { //constructor
        setPreferredSize(new Dimension(bWidth, bHeight));

        setFocusable(true);
        addKeyListener(this);
        
        //load imgs

        bg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
    
        bird = new Bird(birdImg);

        // game loop

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(bg, 0, 0, bWidth, bHeight, null);

        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
    }

    public void move() {
        velocityY += gravity;

        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
