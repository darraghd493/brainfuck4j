package me.darragh.brainfuck4j.ast;

import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public abstract class Token {
    public abstract byte getIdentifier();

    public abstract @NotNull TokenType getType();
}
