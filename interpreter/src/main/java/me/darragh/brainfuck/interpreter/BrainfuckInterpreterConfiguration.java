package me.darragh.brainfuck.interpreter;

import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.io.Writer;

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
            final Writer writer = System.console().writer();
            final Reader reader = System.console().reader();

            @Override
            public char in() {
                try {
                    // wait for the reader to be ready
                    while (!reader.ready()) {
                        //noinspection BusyWait
                        Thread.sleep(50L);
                    }

                    // consume input
                    char[] chars = new char[1];
                    //noinspection ResultOfMethodCallIgnored
                    reader.read(chars); // should be 1
                    return chars[0];
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void out(char c) {
                try {
                    writer.append(c);
                    writer.flush();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        char in();

        void out(char c);
    }
    //endregion
}
