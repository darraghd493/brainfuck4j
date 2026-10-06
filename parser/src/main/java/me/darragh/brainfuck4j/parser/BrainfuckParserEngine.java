package me.darragh.brainfuck4j.parser;

import lombok.RequiredArgsConstructor;
import me.darragh.brainfuck4j.ast.Token;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Tokenizes and links a brainfuck program.
 *
 * @author darraghd493
 * @since 1.0.0
 */
@RequiredArgsConstructor
public abstract class BrainfuckParserEngine {
    protected final @NotNull BrainfuckParserConfiguration configuration;

    public abstract @NotNull List<Token> extractTokens(@NotNull String source) throws BrainfuckParserException;
}
