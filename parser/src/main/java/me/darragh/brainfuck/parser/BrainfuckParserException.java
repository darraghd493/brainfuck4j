package me.darragh.brainfuck.parser;

import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class BrainfuckParserException extends Exception {
    public BrainfuckParserException(@NotNull String message) {
        super(message);
    }
}
