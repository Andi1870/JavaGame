package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

    private Handler handler;
    private int width, height;

    FastEnemy(int x, int y, ID id, int width, int height, Handler handler) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.handler = handler;

        velX = 6;
        velY = 8;
    }

    //creating collision rectangle 
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;    //Gegner soll an Waenden bouncen   
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, width, height, 0.04f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, width, height);
    }
}
