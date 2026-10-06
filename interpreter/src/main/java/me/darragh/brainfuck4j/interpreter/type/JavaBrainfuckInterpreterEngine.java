package me.darragh.brainfuck4j.interpreter.type;

import me.darragh.brainfuck4j.ast.Token;
import me.darragh.brainfuck4j.ast.type.EndLoopToken;
import me.darragh.brainfuck4j.ast.type.StartLoopToken;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterConfiguration;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterEngine;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class JavaBrainfuckInterpreterEngine extends BrainfuckInterpreterEngine {
    public JavaBrainfuckInterpreterEngine(@NotNull BrainfuckInterpreterConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void execute(@NotNull List<Token> tokens) {
        byte[] memory = new byte[configuration.allocatedBytes()];
        int pointer = 0;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            switch (token.getType()) {
                case MOVE_LEFT -> pointer--;
                case MOVE_RIGHT -> pointer++;
                case INCREMENT -> memory[pointer]++;
                case DECREMENT -> memory[pointer]--;
                case START_LOOP -> {
                    if (memory[pointer] == 0) {
                        i = ((StartLoopToken) token).getNextIndex();
                    }
                }
                case END_LOOP -> {
                    if (memory[pointer] != 0) {
                        i = ((EndLoopToken) token).getPreviousIndex();
                    }
                }
                case INPUT -> memory[pointer] = (byte) configuration.console().in();
                case OUTPUT -> configuration.console().out((char) memory[pointer]);
            }
        }
    }
}
