package me.darragh.brainfuck4j.interpreter;

import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public record BrainfuckInterpreterConfiguration(
        int allocatedBytes,
        @NotNull ConsoleIo console
) {
    public static final BrainfuckInterpreterConfiguration DEFAULT = new BrainfuckInterpreterConfiguration(
            30_000,
            ConsoleIo.UNSAFE_SYSTEM_CONSOLE
    );

    //region Console IO
    public interface ConsoleIo {
        ConsoleIo UNSAFE_SYSTEM_CONSOLE = new ConsoleIo() {
            @Override
            public char in() {
                try {
                    return (char) System.in.read();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void out(char c) {
                System.out.print(c);
            }
        };

        char in();

        void out(char c);
    }
    //endregion
}
