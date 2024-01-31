package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{

    Handler handler;

    //Konstruktor eines Spielers
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;   
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }

    @Override   //geht einen bestimmten Code jeden tick durch
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 58);
        y = Game.clamp(y, 0, Game.HEIGHT - 80);

        collision();
    }

    private void collision() {
        for(int i = 0; i < handler.object.size(); i++) {
            
            GameObject tempObject = handler.object.get(i); 
            
            if(tempObject.getID() == ID.BasicEnemy) {
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
        g.fillRect(x, y, 40, 40);
    }
}
