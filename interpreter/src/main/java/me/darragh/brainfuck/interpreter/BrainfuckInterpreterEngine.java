package me.darragh.brainfuck.interpreter;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@RequiredArgsConstructor
public abstract class BrainfuckInterpreterEngine {
    private final @NotNull BrainfuckInterpreterConfiguration configuration;
}
