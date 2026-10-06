package me.darragh.brainfuck4j.ast;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class TokenIdentifiers {
    public static final byte MOVE_LEFT = (byte) 0;
    public static final byte MOVE_RIGHT = (byte) 1;
    public static final byte INCREMENT = (byte) 2;
    public static final byte DECREMENT = (byte) 3;
    public static final byte START_LOOP = (byte) 4;
    public static final byte END_LOOP = (byte) 5;
    public static final byte INPUT = (byte) 6;
    public static final byte OUTPUT = (byte) 7;
}
