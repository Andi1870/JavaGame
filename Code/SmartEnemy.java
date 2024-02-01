package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;

    private int width, height;
   

    SmartEnemy(int x, int y, ID id, int width, int height, Handler handler) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.handler = handler;

        for(int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }
    }

    //creating collision rectangle 
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velX = (float) ((-1.5 / distance) * diffX);
        velY = (float) ((-1.5 / distance) * diffY);
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.green, width, height, 0.04f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, width, height);
    }
}
