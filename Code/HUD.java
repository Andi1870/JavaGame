package Code;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    
    public static int HEALTH = 100; 

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100); 
    }

    public void render(Graphics g) {        // einzelne Healthbar states
        g.setColor(Color.red);
        g.fillRect(15, 15, 200, 23);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 2, 23);
        g.setColor(Color.white);
        g.drawRect(15, 15, HEALTH * 2, 24);
    }
}
