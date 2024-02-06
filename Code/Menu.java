package Code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import Code.Game.STATE;
import Code.GameObjects.ID;
import Code.GameObjects.Player;
import Code.GameObjects.Enemys.BasicEnemy;

public class Menu extends MouseAdapter{

    private Game game;
    private Handler handler;
    private Random r = new Random();

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == STATE.Menu) {

            //play button
            if(mouseOver(mx, my, 218, 150, 200, 60)) {
                game.gameState = STATE.Game;
                handler.object.clear();    
                handler.addObject(new Player(game.WIDTH / 2 - 40, game.HEIGHT / 2 - 40, ID.Player, 40, 40, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.BasicEnemy, 16, 16, handler));
            }

            //stats button
            if(mouseOver(mx, my, 218, 230, 200, 60)) {
                game.gameState = STATE.Stats;
            }

            //quit button
            if(mouseOver(mx, my, 218, 310, 200, 60)) {
                System.exit(1);
            }
        }

        //back button for stats
        if(game.gameState == STATE.Stats) {
            if(mouseOver(mx, my, 218, 310, 200, 60)){
                game.gameState = STATE.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width) {
            if(my > y && my < y + height) {
                return true;
            } else { return false; }
        }   else { return false; }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        if(game.gameState == STATE.Menu) {
            Font fnt = new Font("arial", 1, 60);
            Font fnt2 = new Font("arial", 1, 40);
            g.setColor(Color.WHITE);
            g.setFont(fnt);

            g.drawString("Menu", 240, 100);
            g.setFont(fnt2);
        
            g.drawString("Play", 275, 193);
            g.drawRect(218, 150, 200, 60);

            g.drawString("Stats", 270, 273);
            g.drawRect(218, 230, 200, 60);

            g.drawString("Quit", 275, 353);
            g.drawRect(218, 310, 200, 60);
        } 

        else if (game.gameState == STATE.Stats) {
            Font fnt = new Font("arial", 1, 60);
            Font fnt2 = new Font("arial", 1, 40);
            g.setColor(Color.WHITE);
            
            g.setFont(fnt);
            g.drawString("Stats", 240, 100);

            g.setFont(fnt2);
            g.drawString("Back", 275, 353);
            g.drawRect(218, 310, 200, 60);
        }
    }
}
