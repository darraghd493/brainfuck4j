package me.darragh.brainfuck4j.ast.type;

import me.darragh.brainfuck4j.ast.Token;
import me.darragh.brainfuck4j.ast.TokenIdentifiers;
import me.darragh.brainfuck4j.ast.TokenType;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class MoveLeftToken extends Token {
    @Override
    public byte getIdentifier() {
        return TokenIdentifiers.MOVE_LEFT;
    }

    @Override
    public @NotNull TokenType getType() {
        return TokenType.MOVE_LEFT;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MoveLeftToken;
    }
}
