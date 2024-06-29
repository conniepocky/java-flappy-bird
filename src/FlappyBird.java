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
    int birdY = bHeight * 1/4;
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

    Bird bird;

    //pipes

    int pipeX = bWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;

        boolean passed = false;

        Pipe(Image img) {
            this.img = img;
        }
    }

    ArrayList<Pipe> pipes;
    Random random = new Random();

    // game logic

    int velocityY = 0;
    int velocityX = -4;
    int gravity = 1;

    Timer gameLoop;
    Timer pipeLoop;

    boolean gameOver = false;
    double score = 0;

    FlappyBird() { //constructor
        setPreferredSize(new Dimension(bWidth, bHeight));

        setFocusable(true);
        addKeyListener(this);
        
        //load imgs

        bg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
    
        // objects

        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        // pipe timer

        pipeLoop = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        pipeLoop.start();

        // game loop

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void placePipes() {

        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace = bHeight/4;

        Pipe topPipe = new Pipe(topPipeImg);
        Pipe bottomPipe = new Pipe(bottomPipeImg);

        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(bg, 0, 0, bWidth, bHeight, null);

        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
    
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        //score 

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        if (gameOver) {
            g.drawString("Game over: " + String.valueOf((int) score), 10, 35);
        } else {
            g.drawString("Score: " + String.valueOf((int) score), 10, 35);
        }
    }

    public void move() {
        velocityY += gravity;

        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (!pipe.passed && pipe.x+pipe.width < bird.x) {
                pipe.passed = true;
                score += 0.5;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }
        }

        if (bird.y > bHeight) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            gameLoop.stop();
            pipeLoop.stop();

            System.out.println("Game Over :(");
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width && 
            a.x + a.width > b.x && 
            a.y < b.y + b.height && 
            a.y + a.height > b.y;
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
