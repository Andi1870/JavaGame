package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
    BasicEnemy(int x, int y, ID id) {
        super(x, y, id);

        velX = 10;
        velY = 10;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;    //Gegner soll an Waenden bouncen   
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}