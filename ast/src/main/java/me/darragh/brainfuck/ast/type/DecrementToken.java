package me.darragh.brainfuck.ast.type;

import me.darragh.brainfuck.ast.Token;
import me.darragh.brainfuck.ast.TokenIdentifiers;
import me.darragh.brainfuck.ast.TokenType;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class DecrementToken extends Token {
    @Override
    public byte getIdentifier() {
        return TokenIdentifiers.DECREMENT;
    }

    @Override
    public @NotNull TokenType getType() {
        return TokenType.DECREMENT;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DecrementToken;
    }
}
