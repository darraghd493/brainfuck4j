package me.darragh.brainfuck.transpiler;

import org.jetbrains.annotations.NotNull;

public record BrainfuckClassGeneratorConfiguration(
        int allocatedBytes,
        @NotNull String className
) {
    public static final BrainfuckClassGeneratorConfiguration DEFAULT = new BrainfuckClassGeneratorConfiguration(30_000, "Main");
}
