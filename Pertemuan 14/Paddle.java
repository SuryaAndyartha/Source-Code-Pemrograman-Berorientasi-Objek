import java.awt.*;

public class Paddle {
    int x, y, width, height;
    private int velY = 0;
    private boolean upPressed = false;
    private boolean downPressed = false;

    public Paddle(int x, int y, int w, int h) {
        this.x = x; this.y = y; this.width = w; this.height = h;
    }

    public void update(int minY, int maxY) {
        y += velY;
        if (y < minY) y = minY;
        if (y + height > maxY) y = maxY - height;
    }

    public Rectangle getRect() { return new Rectangle(x, y, width, height); }
    public int getCenterY() { return y + height/2; }
    public void setY(int ny) { y = ny; }
    public void setVelY(int v) { velY = v; }
    public int getVelY() { return velY; }
    public void setUpPressed(boolean b) { upPressed = b; }
    public void setDownPressed(boolean b) { downPressed = b; }
    public boolean isUpPressed() { return upPressed; }
    public boolean isDownPressed() { return downPressed; }
}
