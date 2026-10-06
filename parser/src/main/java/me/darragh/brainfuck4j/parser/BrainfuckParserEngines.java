package me.darragh.brainfuck4j.parser;

import lombok.experimental.UtilityClass;
import me.darragh.brainfuck4j.parser.type.JavaBrainfuckParserEngine;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@UtilityClass
public final class BrainfuckParserEngines {
    public static @NotNull JavaBrainfuckParserEngine javaBrainfuckParserEngine(@NotNull BrainfuckParserConfiguration configuration) {
        return new JavaBrainfuckParserEngine(configuration);
    }

    public static @NotNull JavaBrainfuckParserEngine javaBrainfuckParserEngine() {
        return new JavaBrainfuckParserEngine(BrainfuckParserConfiguration.DEFAULT);
    }
}
