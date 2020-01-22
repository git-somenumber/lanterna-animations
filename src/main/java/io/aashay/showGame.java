package io.aashay;

import com.googlecode.lanterna.TerminalPosition;
import java.io.IOException;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalSize;

/**
 * start2
 */
public class showGame {
  static int[] Xpose = {11,11};

  public static int[] getXpose(){
    return Xpose;
  }

  public static void main(String[] args) throws InterruptedException{
    DefaultTerminalFactory defaultTerminalFactory =  new DefaultTerminalFactory();
    Terminal terminal = null;

    try {
      terminal = defaultTerminalFactory.createTerminal();
      terminal.enterPrivateMode();
      terminal.clearScreen();
      terminal.setCursorVisible(false);

      final TextGraphics textGraphics =  terminal.newTextGraphics();
      textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
      textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);

      textGraphics.putString(2, 3, "Aashay made this");
      textGraphics.putString(2, 4, "Key Pressed: ");
      textGraphics.drawRectangle(new TerminalPosition(10, 10), new TerminalSize(10, 10), '#');
      int[] Xpose = {11,11};
      textGraphics.putString(Xpose[0], Xpose[1], "X");

      terminal.flush();

      KeyStroke keyStroke  = terminal.readInput();

      while(keyStroke.getKeyType() != KeyType.Escape){
        keyStroke = terminal.readInput();
        textGraphics.putString(2+"Key Pressed: ".length(), 4, keyStroke.toString());
        terminal.flush();

        if(keyStroke.getKeyType() == KeyType.ArrowUp){
          textGraphics.putString(Xpose[0], Xpose[1], " ");
          Xpose[1]--;
          textGraphics.putString(Xpose[0], Xpose[1], "X");
          terminal.flush();
        }
        else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
          textGraphics.putString(Xpose[0], Xpose[1], " ");
          Xpose[1]++;
          textGraphics.putString(Xpose[0], Xpose[1], "X");
          terminal.flush();
        }
        else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
          textGraphics.putString(Xpose[0], Xpose[1], " ");
          Xpose[0]--;
          textGraphics.putString(Xpose[0], Xpose[1], "X");
          terminal.flush();
        }
        else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
          textGraphics.putString(Xpose[0], Xpose[1], " ");
          Xpose[0]++;
          textGraphics.putString(Xpose[0], Xpose[1], "X");
          terminal.flush();
        }
      }

      terminal.exitPrivateMode();
      terminal.close();

    } catch(IOException e) {
      e.printStackTrace();
    }
    finally {
        if(terminal != null) {
            try {
                /*
                The close() call here will exit private mode
                 */
                terminal.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
  }
}

}
