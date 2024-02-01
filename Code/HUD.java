package Code;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    
    public static int HEALTH = 100; 

    private int level = 1;
    private int score = 0;

    public void tick() {
        HEALTH = (int) Game.clamp(HEALTH, 0, 100); 

        score++;
    }

    public void render(Graphics g) {        // einzelne Healthbar states
        g.setColor(Color.red);
        g.fillRect(15, 15, 200, 23);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 2, 23);
        g.setColor(Color.white);
        g.drawRect(15, 15, HEALTH * 2, 24);

        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    } 
}
