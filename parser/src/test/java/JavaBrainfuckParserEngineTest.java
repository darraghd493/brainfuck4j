import lombok.SneakyThrows;
import me.darragh.brainfuck4j.ast.Token;
import me.darragh.brainfuck4j.ast.Tokens;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngine;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngines;
import me.darragh.brainfuck4j.parser.BrainfuckParserException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class JavaBrainfuckParserEngineTest {
    static final BrainfuckParserEngine ENGINE = BrainfuckParserEngines.javaBrainfuckParserEngine();

    @Test
    public void testUnmatchedLoopBrackets() {
        assertThatThrownBy(() -> ENGINE.extractTokens("[+[]"))
                .isInstanceOf(BrainfuckParserException.class)
                .hasMessage("Unmatched start loop character at index 0");
        assertThatThrownBy(() -> ENGINE.extractTokens("[+]]"))
                .isInstanceOf(BrainfuckParserException.class)
                .hasMessage("Unexpected end loop character at index 3");
    }

    @SneakyThrows
    @Test
    public void testTokenExtraction() {
        List<Token> extractedTokens = ENGINE.extractTokens("++>--<.,[]");
        List<Token> expectedTokens = Arrays.asList(
                Tokens.incrementToken(),
                Tokens.incrementToken(),
                Tokens.moveRightToken(),
                Tokens.decrementToken(),
                Tokens.decrementToken(),
                Tokens.moveLeftToken(),
                Tokens.outputToken(),
                Tokens.inputToken(),
                Tokens.startLoopToken((byte) 9),
                Tokens.endLoopToken((byte) 8)
        );
        assertThatList(expectedTokens).isEqualTo(extractedTokens);
    }
}