package io.aashay;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class screenTest {
    static final Random random = new Random();

    // setup variables to change animations
    final static int numLines = 7; // **Use this to set length of file
    final static int numCharacters = 42 - 1; // **Use this to set number of characters (do not remove '-1')

    public static void main(final String[] args) throws IOException {
        final DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Screen screen = null;
        Terminal terminal = null;

        // ArrayList<int[]> name = new ArrayList<>();
        final File file = new File("/Users/aashayshah/code/lanterna/src/main/java/io/aashay/a.txt");
        final BufferedReader artInFile = new BufferedReader(new FileReader(file));

        final String[] art = new String[numLines];
        for (int i = 0; i < numLines; i++) {
            art[i] = artInFile.readLine();
        }
        artInFile.close();

        try {
            terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.startScreen();
            screen.setCursorPosition(null);

            TerminalSize terminalSize = screen.getTerminalSize();
            drawScreen(terminalSize, screen);

            while (true) {
                final KeyStroke keyStroke = screen.pollInput();
                if (keyStroke != null
                        && (keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF)) {
                    break;

                }

                final TerminalSize newSize = screen.doResizeIfNecessary();
                if (newSize != null) {
                    terminalSize = newSize;
                    drawScreen(newSize, screen);
                }

                final int[] cellToRemove = getCellToRemove(terminalSize, art);

                screen.setCharacter(cellToRemove[0], cellToRemove[1], new TextCharacter(' '));

                screen.refresh();

            }

        } catch (final IOException e) {

            e.printStackTrace();
        } finally {
            if (terminal != null) {
                try {
                    terminal.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void drawScreen(final TerminalSize terminalSize, final Screen screen) throws IOException {
        for (int j = 0; j < terminalSize.getRows(); j++) {
            for (int i = 0; i < terminalSize.getColumns(); i++) {
                screen.setCharacter(i, j, new TextCharacter('#', TextColor.ANSI.CYAN, TextColor.ANSI.WHITE));
            }
        }
        screen.refresh();
    }

    private static int[] getCellToRemove(final TerminalSize terminalSize, final String[] art) {
        final int[] cellToRemove = { random.nextInt(terminalSize.getColumns()),
                random.nextInt(terminalSize.getRows()) };
        if (isArtHere(art, cellToRemove)) {
            return cellToRemove;
        } else if (isArtHere(art, cellToRemove)) {
            getCellToRemove(terminalSize, art);
        }
        return new int[2];

    }

    private static boolean isArtHere(final String[] art, final int[] cellToRemove) {
        if (cellToRemove[1] < numLines && cellToRemove[0] < numCharacters) {
            if (art[cellToRemove[1]].charAt(cellToRemove[0]) == ('#')) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}