package me.darragh.brainfuck.interpreter;

import lombok.experimental.UtilityClass;
import me.darragh.brainfuck.interpreter.type.JavaBrainfuckInterpreterEngine;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@UtilityClass
public final class BrainfuckInterpreterEngines {
    public static JavaBrainfuckInterpreterEngine javaBrainfuckInterpreterEngine(BrainfuckInterpreterConfiguration configuration) {
        return new JavaBrainfuckInterpreterEngine(configuration);
    }

    public static JavaBrainfuckInterpreterEngine javaBrainfuckInterpreterEngine() {
        return new JavaBrainfuckInterpreterEngine(BrainfuckInterpreterConfiguration.DEFAULT);
    }
}
