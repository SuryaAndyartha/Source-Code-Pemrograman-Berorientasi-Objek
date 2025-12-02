import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH, HEIGHT;
    private final int PADDLE_WIDTH = 10;
    private final int PADDLE_HEIGHT = 80;
    private final int BALL_SIZE = 12;
    private final int FPS = 60;

    private Paddle leftPaddle, rightPaddle;
    private Ball ball;
    private Timer timer;

    private int leftScore = 0;
    private int rightScore = 0;

    private boolean paused = false;

    public GamePanel(int w, int h) {
        this.WIDTH = w;
        this.HEIGHT = h;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        leftPaddle = new Paddle(20, HEIGHT/2 - PADDLE_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle = new Paddle(WIDTH - 20 - PADDLE_WIDTH, HEIGHT/2 - PADDLE_HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = new Ball(WIDTH/2 - BALL_SIZE/2, HEIGHT/2 - BALL_SIZE/2, BALL_SIZE, BALL_SIZE);

        timer = new Timer(1000 / FPS, this);
    }

    public void startGame() {
        resetPositions();
        timer.start();
    }

    private void resetPositions() {
        leftPaddle.setY(HEIGHT/2 - PADDLE_HEIGHT/2);
        rightPaddle.setY(HEIGHT/2 - PADDLE_HEIGHT/2);
        ball.reset(WIDTH/2 - BALL_SIZE/2, HEIGHT/2 - BALL_SIZE/2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.GRAY);
        for (int y = 0; y < HEIGHT; y += 20) {
            g2.fillRect(WIDTH/2 - 2, y, 4, 12);
        }

        g2.setColor(Color.WHITE);
        g2.fillRect(leftPaddle.x, leftPaddle.y, leftPaddle.width, leftPaddle.height);
        g2.fillRect(rightPaddle.x, rightPaddle.y, rightPaddle.width, rightPaddle.height);
        g2.fillOval(ball.x, ball.y, ball.width, ball.height);

        g2.setFont(new Font("SansSerif", Font.BOLD, 36));
        String scoreText = leftScore + "    " + rightScore;
        FontMetrics fm = g2.getFontMetrics();
        int tx = WIDTH/2 - fm.stringWidth(scoreText)/2;
        g2.drawString(scoreText, tx, 50);

        if (paused) {
            g2.setFont(new Font("SansSerif", Font.BOLD, 28));
            String p = "PAUSED - Press P to resume";
            int px = WIDTH/2 - g2.getFontMetrics().stringWidth(p)/2;
            g2.drawString(p, px, HEIGHT/2 - 20);
        }

        g2.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!paused) {
            leftPaddle.update(0, HEIGHT);
            rightPaddle.update(0, HEIGHT);
            ball.move();
            checkCollision();
        }
        repaint();
    }

    private void checkCollision() {
        if (ball.y <= 0) {
            ball.y = 0;
            ball.vy *= -1;
        } else if (ball.y + ball.height >= HEIGHT) {
            ball.y = HEIGHT - ball.height;
            ball.vy *= -1;
        }

        if (ball.getRect().intersects(leftPaddle.getRect())) {
            ball.x = leftPaddle.x + leftPaddle.width;
            ball.vx = Math.abs(ball.vx);
            int hitPos = ball.getCenterY() - leftPaddle.getCenterY();
            ball.vy = hitPos / 8;
            ball.vx *= 1.05;
        } else if (ball.getRect().intersects(rightPaddle.getRect())) {
            ball.x = rightPaddle.x - ball.width;
            ball.vx = -Math.abs(ball.vx);
            int hitPos = ball.getCenterY() - rightPaddle.getCenterY();
            ball.vy = hitPos / 8;
            ball.vx *= 1.05;
        }

        if (ball.x < -50) {
            rightScore++;
            resetPositions();
        } else if (ball.x > WIDTH + 50) {
            leftScore++;
            resetPositions();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_W -> leftPaddle.setUpPressed(true);
            case KeyEvent.VK_S -> leftPaddle.setDownPressed(true);
            case KeyEvent.VK_UP -> rightPaddle.setUpPressed(true);
            case KeyEvent.VK_DOWN -> rightPaddle.setDownPressed(true);
            case KeyEvent.VK_P -> paused = !paused;
            case KeyEvent.VK_R -> { leftScore = 0; rightScore = 0; resetPositions(); }
        }
        updatePaddleVelByKeys(leftPaddle);
        updatePaddleVelByKeys(rightPaddle);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_W -> leftPaddle.setUpPressed(false);
            case KeyEvent.VK_S -> leftPaddle.setDownPressed(false);
            case KeyEvent.VK_UP -> rightPaddle.setUpPressed(false);
            case KeyEvent.VK_DOWN -> rightPaddle.setDownPressed(false);
        }
        updatePaddleVelByKeys(leftPaddle);
        updatePaddleVelByKeys(rightPaddle);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void updatePaddleVelByKeys(Paddle p) {
        if (p.isUpPressed() && !p.isDownPressed()) p.setVelY(-6);
        else if (p.isDownPressed() && !p.isUpPressed()) p.setVelY(6);
        else p.setVelY(0);
    }
}
