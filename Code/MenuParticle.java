package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Code.GameObjects.GameObject;
import Code.GameObjects.ID;
import Code.Overview.Trail;

public class MenuParticle extends GameObject{

    private Handler handler;
    private int width, height;

    Random r = new Random();

    private Color color;

    public MenuParticle(int x, int y, ID id, int width, int height, Handler handler) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.handler = handler;

        int dir = r.nextInt(2);
        if(dir == 0) {
            velX = 3;
            velY = 6;
        }
        
        else if(dir == 1) {
            velX = 6;
            velY = 3;
        }

        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    //creating collision rectangle 
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;    //Gegner soll an Waenden bouncen   
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        
        handler.addObject(new Trail(x, y, ID.Trail, color, width, height, 0.04f, handler));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x, (int)y, width, height);
    }
}
