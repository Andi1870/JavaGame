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

        if(scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if(hud.getLevel() == 2) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.FastEnemy, 12, 12, handler));
            else if(hud.getLevel() == 3) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.FastEnemy, 12, 12, handler));
            else if(hud.getLevel() == 4) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.SmartEnemy, 12, 12, handler));
            else if(hud.getLevel() == 5) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.FastEnemy, 12, 12, handler));
            else if(hud.getLevel() == 6) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.SmartEnemy, 12, 12, handler));
            else if(hud.getLevel() == 7) handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.FastEnemy, 12, 12, handler));

            if(hud.getLevel() == 8) {
            handler.clearEnemys(); 
            handler.addObject(new BossEnemy(Game.WIDTH / 2 - 52, -100, ID.BossEnemy, 80, 80, handler));
            }
        }
    }
}
