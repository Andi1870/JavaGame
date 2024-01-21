package JavaGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; 
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    
    public Game() {
        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));                                    //damit KeyInputs erkannt werden und im KeyInput gehandlet werden

        new Window(WIDTH, HEIGHT, "First Game", this);

        handler.addObject(new Player(WIDTH / 2 - 40, HEIGHT / 2 - 40, ID.Player));     //erstellen von GameObjects in die LinkedList
        handler.addObject(new Player(WIDTH / 2 + 40, HEIGHT / 2 + 40, ID.Player2));    
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
    }

    //BufferStrategy wird erstellt, Graphics kriegt das Fenster (damit sich das Bild dauerhaft aktualiert)
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();  //in Graphics das Bild gespeichert, um änderungen vorzunehmen
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);                  //Graphics von den GameObjects werden dauerhaft aktualisiert

        g.dispose();
        bs.show();                          //zeigt zu schluss das Bild
    }


    public static void main(String[] args) {
        new Game();
    }
}