import lombok.SneakyThrows;
import me.darragh.brainfuck.ast.Token;
import me.darragh.brainfuck.ast.Tokens;
import me.darragh.brainfuck.parser.BrainfuckParserEngine;
import me.darragh.brainfuck.parser.BrainfuckParserEngines;
import me.darragh.brainfuck.parser.BrainfuckParserException;
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
                .hasMessage("Mismatched loop characters: 2 start loops and 1 end loops.");
        assertThatThrownBy(() -> ENGINE.extractTokens("[+]]"))
                .isInstanceOf(BrainfuckParserException.class)
                .hasMessage("Mismatched loop characters: 1 start loops and 2 end loops.");
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
                Tokens.startLoopToken(),
                Tokens.endLoopToken()
        );
        assertThatList(expectedTokens).isEqualTo(extractedTokens);
    }
}