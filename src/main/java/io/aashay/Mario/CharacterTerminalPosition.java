package io.aashay.Mario;

import com.googlecode.lanterna.TerminalPosition;

public class CharacterTerminalPosition extends TerminalPosition {
    int[] position = new int[2];

    public CharacterTerminalPosition(int coloumn, int row) {
        super(coloumn, row);

        position[0] = coloumn;
        position[1] = row;
    }

    

}