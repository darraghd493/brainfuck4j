package me.darragh.brainfuck4j.ast.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.darragh.brainfuck4j.ast.Token;
import me.darragh.brainfuck4j.ast.TokenIdentifiers;
import me.darragh.brainfuck4j.ast.TokenType;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class StartLoopToken extends Token {
    private int nextIndex;

    @Override
    public byte getIdentifier() {
        return TokenIdentifiers.START_LOOP;
    }

    @Override
    public @NotNull TokenType getType() {
        return TokenType.START_LOOP;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StartLoopToken o && o.nextIndex == this.nextIndex;
    }
}
