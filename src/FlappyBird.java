import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel {
    int bWidth = 360;
    int bHeight = 640;

    //imgs

    Image bg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    FlappyBird() {
        setPreferredSize(new Dimension(bWidth, bHeight));
        
        //load imgs

        bg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(bg, 0, 0, bWidth, bHeight, null);
    }
}
