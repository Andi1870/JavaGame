package Code.GameObjects.Enemys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Code.Game;
import Code.Handler;
import Code.GameObjects.GameObject;
import Code.GameObjects.ID;
import Code.Overview.Trail;

public class BossEnemyBullet extends GameObject{

    private Handler handler;
    private int width, height;
    private Random r = new Random();

    public BossEnemyBullet(int x, int y, ID id, int width, int height, Handler handler) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.handler = handler;

        velX = r.nextInt(5 - -5) + -5;
        velY = 4;
    }

    //creating collision rectangle 
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public void tick() {
        x += velX;
        y += velY;

        //if (y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;    //Gegner soll an Waenden bouncen   
        //if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        if(y >= Game.HEIGHT) handler.removeObject(this);
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, width, height, 0.06f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, width, height);
    }
}
