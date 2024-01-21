package JavaGame;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{

    //Konstruktor eines Spielers
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override   //geht einen bestimmten Code jeden tick durch
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override   //rendert die Grafik des Spielers
    public void render(Graphics g) {
        if(id == ID.Player)         g.setColor(Color.white);
        else if (id == ID.Player2)  g.setColor(Color.blue);
        g.fillRect(x, y, 40, 40);
    }
    
}
