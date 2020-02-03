package io.aashay.Mario;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;


public class app {
    public static void main(String[] args) {
        new app().init();
    }

    private void init() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        Screen screen = null;

        try {
            terminal = defaultTerminalFactory.createTerminal();
            terminal.setCursorVisible(false);

            screen = new TerminalScreen(terminal);

            screen.startScreen();

            pathMaker(screen, terminal.getTerminalSize());
            screen.refresh();

            loop(screen, terminal);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void pathMaker(Screen screen, TerminalSize size) throws IOException {

        TextGraphics textGraphics = screen.newTextGraphics();
        for (int i = 0; i <= size.getColumns(); i++) {
            textGraphics.putString(i, size.getRows() - 1, "#");
            textGraphics.putString(i, size.getRows() - 2, "\u2500");
        }
    }

    private void loop(Screen screen, Terminal terminal) throws IOException {
        int currentPose = 0;
        long poseTime = System.currentTimeMillis();

        TerminalPosition CharacterPosition = new TerminalPosition(5, terminal.getTerminalSize().getRows()-5);
        while (true) {

            final KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null
                    && (keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)) {
                break;

            } else if (keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowRight)) {
                CharacterPosition = CharacterPosition.withRelativeColumn(1);
                placeCharacter(screen, CharacterPosition, 3);
                currentPose = 3;
                poseTime = System.currentTimeMillis();
            } else if (keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowLeft)) {
                CharacterPosition = CharacterPosition.withRelativeColumn(-1);
                placeCharacter(screen, CharacterPosition, 6);
                currentPose = 6;
                poseTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - poseTime >= 300 && (currentPose == 3 || currentPose == 6)) {
                placeCharacter(screen,CharacterPosition, 0);
            }
            screen.refresh();
        }
    }

    private void placeCharacter(Screen screen, TerminalPosition characterPosition, int characterPose) {
        TextGraphics textGraphics = screen.newTextGraphics();
        String[] pose = Poses.getPoseI().getPose(characterPose);
        for (int i = 0;i<3;i++) {
            textGraphics.putString(characterPosition.withRelativeRow(i), pose[i]);
        }
    }
}