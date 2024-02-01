package Code;

import java.awt.Graphics;
import java.util.LinkedList;

//Handlet die einen GameObjects
public class Handler {

    //Liste von GameObejcts wird erstellt
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);      //jedes GameObject wird in eine temporäre Instanz gespeichert

            tempObject.tick();                          //eigene tick Methode jedes GameObjects wird aufgerufen (je nachdem ob Player o.ä.)
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);      //jede Grafik des G.O. wird in eine temporäre Instanz gespeichert

            tempObject.render(g);                       //eigene render Methode jedes G.O. wird aufgerufen
        }
    }

    public void clearEnemys() {
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if(tempObject.getID() == ID.Player) {
                object.clear();
                addObject(tempObject);
            }
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);                        //erstellt ein GameObject des übergebenen Typens
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);                     //entfernt ein GameObject des übergebenen Typens
    }
}
