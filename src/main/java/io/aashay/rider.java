package io.aashay;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class rider {

    public static void main(String[] args) {
        new rider().init();
    }

    private void init() {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        Screen screen = null;
        TextGraphics textGraphics = null;

        try {
            terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.startScreen();
            screen.setCursorPosition(null);

            textGraphics = screen.newTextGraphics();

            loop(screen);

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

    private String[] getRiderPoses() {
        String[] riderPosesT = new String[4];
        File file = new File("/Users/aashayshah/code/lanterna/src/main/java/io/aashay/riderPoses.txt");
        try {
            BufferedReader poses = new BufferedReader(new FileReader(file));
            if (poses != null) {
                for (int i = 0; i < 4; i++) {
                    riderPosesT[i] = poses.readLine();
                }
            }
            poses.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return riderPosesT;
    }

    private void loop(Screen screen) throws IOException {
        int i = 0;
        String[] riderPoses = getRiderPoses();
        long startTimeMillis = System.currentTimeMillis();
        TextGraphics textGraphics = screen.newTextGraphics();

        while (true) {

            final KeyStroke keyStroke = screen.pollInput();
            if (keyStroke != null
                    && (keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)) {
                break;

            }

            if (System.currentTimeMillis() - startTimeMillis >= 80) {
                textGraphics.putString(1, 1, riderPoses[i]);
                screen.refresh();
                i++;
                startTimeMillis = System.currentTimeMillis();
            }

            if (i == 4) {
                i = 0;

            }

        }
    }
}