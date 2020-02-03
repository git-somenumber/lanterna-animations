package io.aashay.reflectiveLantern;

import java.io.IOException;

import com.googlecode.lanterna.terminal.Terminal;

public class annotated {

    @use
    public annotated() {

    }

    public static void run(Terminal terminal) throws IOException {
        terminal.putCharacter('A');
        terminal.putCharacter('A');
        terminal.putCharacter('S');
        terminal.putCharacter('H');
        terminal.putCharacter('A');
        terminal.putCharacter('Y');
        terminal.flush();
    }

    private static void main(String[] args) {
        annotated annotated = new annotated();

        runTest.setTest(annotated);

    }
}
