package Code;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Taskbar.State;
import java.awt.image.BufferStrategy;
import java.util.Random;

import Code.GameObjects.ID;
import Code.GameObjects.Player;
import Code.GameObjects.Spawn;
import Code.GameObjects.Enemys.BasicEnemy;
import Code.Overview.HUD;
import Code.Overview.Window;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; 
    private Thread thread;
    private Random r;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    public enum STATE {
        Menu,
        Stats,
        Game
    };

    public STATE gameState = STATE.Menu;
     
    public Game() {

        handler = new Handler();
        menu = new Menu(this, handler);
        this.addKeyListener(new KeyInput(handler));   
        this.addMouseListener(menu);                                 //damit KeyInputs erkannt werden und im KeyInput gehandlet werden


        new Window(WIDTH, HEIGHT, "First Game", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        r = new Random();


        if(gameState == STATE.Game) {
            handler.addObject(new Player(WIDTH / 2 - 40, HEIGHT / 2 - 40, ID.Player, 40, 40, handler));     //erstellen von GameObjects in die LinkedList    
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH) - 50, r.nextInt(HEIGHT) - 50, ID.BasicEnemy, 16, 16, handler));     //erstellen eines Gegners
        }
        else {
            for(int i = 0; i < 10; i++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH) - 50, r.nextInt(HEIGHT) - 50, ID.MenuParticle, 16, 16, handler));     //erstellen eines Gegners
            }
        }
    }

    //startet den Thread des Programms
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //beendet den Thread des Programms
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override   //Methode, damit das Programm läuft und die GameObjects geladen, angezeigt und aktualisiert werden
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 100;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    //GameObjects aktualisieren sich stetig
    private void tick() {
        handler.tick();
        
        if(gameState == STATE.Game) {
            hud.tick();
            spawner.tick();
        } else if(gameState == STATE.Menu || gameState == STATE.Stats) {
            menu.tick();
        }
    }

    //BufferStrategy wird erstellt, Graphics kriegt das Fenster (damit sich das Bild dauerhaft aktualiert)
    private void render() {
        this.requestFocus();
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();  //in Graphics das Bild gespeichert, um änderungen vorzunehmen
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);                  //Graphics von den GameObjects werden dauerhaft aktualisiert

        if(gameState == STATE.Game) {
            hud.render(g);
        } else if(gameState == STATE.Menu || gameState == STATE.Stats) {
            menu.render(g);
        }

        g.dispose();
        bs.show();                          //zeigt zum schluss das Bild
    }


    public static float clamp(float var, float min, float max) {    //clamp Methode verhindert, dass der Spieler ueber die Grenzen des Windows geht
        if(var >= max) 
            return var = max;
        else if(var <= min) 
            return var = min;
        else 
            return var;
    }

    public static void main(String[] args) {
        new Game();
    }
}