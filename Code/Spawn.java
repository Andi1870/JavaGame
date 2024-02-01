package Code;

import java.util.Random;

public class Spawn {
    
    private Handler handler;
    private HUD hud;

    private Random r = new Random();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    //spawning an enemy when the level/score goes up (difficulty is rising)
    public void tick() {
        scoreKeep++;

        if(scoreKeep >= 250) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.BasicEnemy, 16, 16, handler));

            if(hud.getLevel() == 3) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.FastEnemy, 14, 14, handler));
            if(hud.getLevel() == 4) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.SmartEnemy, 14, 14, handler));
        }
    }
}
