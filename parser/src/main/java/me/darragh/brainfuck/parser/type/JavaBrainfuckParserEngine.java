package me.darragh.brainfuck.parser.type;

import me.darragh.brainfuck.ast.Token;
import me.darragh.brainfuck.ast.TokenType;
import me.darragh.brainfuck.ast.Tokens;
import me.darragh.brainfuck.ast.type.EndLoopToken;
import me.darragh.brainfuck.ast.type.StartLoopToken;
import me.darragh.brainfuck.parser.BrainfuckParserConfiguration;
import me.darragh.brainfuck.parser.BrainfuckParserEngine;
import me.darragh.brainfuck.parser.BrainfuckParserException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public final class JavaBrainfuckParserEngine extends BrainfuckParserEngine {
    public JavaBrainfuckParserEngine(@NotNull BrainfuckParserConfiguration configuration) {
        super(configuration);
    }

    @Override
    public @NotNull List<Token> extractTokens(@NotNull String source) throws BrainfuckParserException {
        List<Token>  tokens = source.chars().mapToObj(c -> mapCharToToken((char) c))
                .filter(Objects::nonNull)
                .toList();

        // match loop brackets
        Stack<Integer> startLoops = new Stack<>();


        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.getType() == TokenType.START_LOOP) {
                startLoops.push(i);
            } else if (token.getType() == TokenType.END_LOOP) {
                if (startLoops.isEmpty()) {
                    throw new BrainfuckParserException("Unexpected end loop character at index " + i);
                }
                int startLoopIndex = startLoops.pop();
                if (tokens.get(startLoopIndex) instanceof StartLoopToken startLoopToken) {
                    startLoopToken.setNextIndex(i);
                }
                if (token instanceof EndLoopToken endLoopToken) {
                    endLoopToken.setPreviousIndex(startLoopIndex);
                }
            }
        }

        if (!startLoops.isEmpty()) {
            throw new BrainfuckParserException("Unmatched start loop character at index " + startLoops.peek());
        }

        return tokens;
    }

    private @Nullable Token mapCharToToken(char ch) {
        if (ch == configuration.moveLeftCharacter()) return Tokens.moveLeftToken();
        else if (ch == configuration.moveRightCharacter()) return Tokens.moveRightToken();
        else if (ch == configuration.incrementCharacter()) return Tokens.incrementToken();
        else if (ch == configuration.decrementCharacter()) return Tokens.decrementToken();
        else if (ch == configuration.inputCharacter()) return Tokens.inputToken();
        else if (ch == configuration.outputCharacter()) return Tokens.outputToken();
        else if (ch == configuration.startLoopCharacter()) return Tokens.startLoopToken();
        else if (ch == configuration.endLoopCharacter()) return Tokens.endLoopToken();
        else return null;
    }
}
