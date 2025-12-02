import java.awt.*;

public class Ball {
    int x, y, width, height;
    double vx, vy;

    public Ball(int x, int y, int w, int h) {
        this.x = x; this.y = y; this.width = w; this.height = h;
        initVelocity();
    }

    private void initVelocity() {
        vx = 4 * (Math.random() < 0.5 ? -1 : 1);
        vy = 2 * (Math.random() * 2 - 1);
    }

    public void reset(int nx, int ny) {
        x = nx; y = ny;
        initVelocity();
    }

    public void move() {
        x += (int) Math.round(vx);
        y += (int) Math.round(vy);
    }

    public Rectangle getRect() { return new Rectangle(x, y, width, height); }
    public int getCenterY() { return y + height/2; }
}
