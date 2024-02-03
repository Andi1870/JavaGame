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

public class BossEnemy extends GameObject{

    private Handler handler;
    private int width, height;
    private int timer = 80;
    private int timer2 = 50;
    private Random r = new Random();

    public BossEnemy(int x, int y, ID id, int width, int height, Handler handler) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    //creating collision rectangle 
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(timer >= 0) timer--;
        else velY = 0;

        if(timer <= 0) timer2--;
        if(timer2 <= 0) {
            if(velX == 0)   velX = 2;
            int spawn = r.nextInt(10);
            if(spawn == 0) handler.addObject(new BossEnemyBullet((int)x + 35, (int) y + 70, ID.BasicEnemy, 16, 16, handler));
        }

        if (x <= 0 || x >= Game.WIDTH - 95) velX *= -1;

        handler.addObject(new Trail(x, y, ID.BossEnemy, Color.red, width, height, 0.26f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, width, height);
    }
}
