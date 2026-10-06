package me.darragh.brainfuck.transpiler;

import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public record BrainfuckClassGeneratorConfiguration(
        int allocatedBytes,
        @NotNull String className
) {
    public static final BrainfuckClassGeneratorConfiguration DEFAULT = new BrainfuckClassGeneratorConfiguration(30_000, "Main");
}
