package io.aashay.reflectiveLantern;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class Runner {

    static annotated annotated = new annotated();

    public static void main(String[] args)
            throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        try {
            terminal = defaultTerminalFactory.createTerminal();

            Class<?> class1 = annotated.getClass();
            Method[] methods = class1.getMethods();

            methods[0].invoke(annotated, terminal);

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
}