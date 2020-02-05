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
    Terminal terminal = null;
    Screen screen = null;

    GameCharacter mario = new GameCharacter("src/main/resources/mario.txt");

    public static void main(String[] args) {
        new app().init();
    }

    private void init() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

        try {
            terminal = defaultTerminalFactory.createTerminal();
            terminal.setCursorVisible(false);

            screen = new TerminalScreen(terminal);

            screen.startScreen();

            pathMaker(screen, terminal.getTerminalSize());
            screen.refresh();

            loop();

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

    private void loop() throws IOException {
        Pose currentPose = Pose.STAND;
        long poseTime = System.currentTimeMillis();

        TerminalPosition CharacterPosition = new TerminalPosition(5, terminal.getTerminalSize().getRows()-5);
        while (true) {

            final KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null
                    && (keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)) {
                break;

            } else if (keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowRight)) {
                CharacterPosition = CharacterPosition.withRelativeColumn(1);
                placeCharacter(CharacterPosition, Pose.RIGHT);
                currentPose = Pose.RIGHT;
                poseTime = System.currentTimeMillis();
            } else if (keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowLeft)) {
                CharacterPosition = CharacterPosition.withRelativeColumn(-1);
                placeCharacter(CharacterPosition, Pose.LEFT);
                currentPose = Pose.LEFT;
                poseTime = System.currentTimeMillis();
            } else if(keyStroke != null && (keyStroke.getKeyType() == KeyType.ArrowDown)){
                placeCharacter(CharacterPosition, Pose.DOWN);
                currentPose = Pose.DOWN;
                poseTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - poseTime >= 300 && (currentPose != Pose.STAND)) {
                placeCharacter(CharacterPosition, Pose.STAND);
            }
            screen.refresh();
        }
    }

    private void placeCharacter(TerminalPosition characterPosition, Pose pose_enum) {
        TextGraphics textGraphics = screen.newTextGraphics();
        String[] pose = mario.move(pose_enum);
        for (int i = 0;i<3;i++) {
            textGraphics.putString(characterPosition.withRelativeRow(i), pose[i]);
        }
    }
}