package me.darragh.brainfuck.ast;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public enum TokenType {
    MOVE_LEFT,
    MOVE_RIGHT,
    INCREMENT,
    DECREMENT,
    START_LOOP,
    END_LOOP,
    INPUT,
    OUTPUT
}
