package Code.Overview;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import Code.Game;

public class Window extends Canvas{
    
    //erzeugt das ganze Fenster für das Spiel
    public Window (int width, int height, String title, Game game){
        JFrame frame = new JFrame(title); // neues JFrame erstellen

        frame.setPreferredSize(new Dimension(width, height));   
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sonst ist das Spiel nicht zu schliessen
        frame.setResizable(false);                  //Spiel ist nicht an der Größe zu ändern
        frame.setLocationRelativeTo(null);                  //Das Fenster soll in der Mitte spawnen 
        frame.add(game);                                      //Game instanz soll in das Fenster hinzugefügt werden
        frame.setVisible(true);                             //damit man das Fenster überhaupt sehen kann
        game.start();   
    }
}
