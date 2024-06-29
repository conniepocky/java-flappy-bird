import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int bWidth = 360;
        int bHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(bWidth, bHeight);

        FlappyBird flappyBird = new FlappyBird();

        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);

    }
}
