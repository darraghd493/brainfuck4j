package me.darragh.brainfuck.interpreter;

import lombok.RequiredArgsConstructor;
import me.darragh.brainfuck.ast.Token;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interprets a brainfuck program.
 *
 * @author darraghd493
 * @since 1.0.0
 */
@RequiredArgsConstructor
public abstract class BrainfuckInterpreterEngine {
    protected final @NotNull BrainfuckInterpreterConfiguration configuration;

    public abstract void execute(@NotNull List<Token> tokens);
}
