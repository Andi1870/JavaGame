package Code.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Code.Game;
import Code.Handler;
import Code.Overview.HUD;
import Code.Overview.Trail;

public class Player extends GameObject{

    private Handler handler;
    private int width, height;

    //Konstruktor eines Spielers
    public Player(int x, int y, ID id, int width, int height, Handler handler) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.handler = handler;   
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    @Override   //geht einen bestimmten Code jeden tick durch
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp((int)x, 0, Game.WIDTH - 58);
        y = Game.clamp((int)y, 0, Game.HEIGHT - 80);

        collision();
        handler.addObject(new Trail(x, y, ID.Trail, Color.white, width, height, 0.06f, handler));
    }

    private void collision() {
        for(int i = 0; i < handler.object.size(); i++) {
            
            GameObject tempObject = handler.object.get(i); 
            
            if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    @Override   //rendert die Grafik des Spielers
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, width, height);
    }
}
