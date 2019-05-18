package Mainpack;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;



public class MainMidlet
    extends MIDlet
    implements CommandListener {




  public MainGameCanvas GameObjeto;
  

  public void startApp() {
    if (GameObjeto == null) {
      try {
        GameObjeto = new MainGameCanvas();
        GameObjeto.start();
        Command exitCommand = new Command("Sair", Command.EXIT, 0);
        GameObjeto.addCommand(exitCommand);
        GameObjeto.setCommandListener(this);
      }
      catch (IOException ioe) {
        System.out.println(ioe);
      }
    }
    System.out.println(GameObjeto.getWidth());
    Display.getDisplay(this).setCurrent(GameObjeto);
  }

  public void pauseApp() {}

  public void destroyApp(boolean unconditional) {
    if (GameObjeto != null)
      GameObjeto.stop();
  }

  public void commandAction(Command c, Displayable s) {
    if (c.getCommandType() == Command.EXIT) {
      destroyApp(true);
      notifyDestroyed();
    }
  }
}