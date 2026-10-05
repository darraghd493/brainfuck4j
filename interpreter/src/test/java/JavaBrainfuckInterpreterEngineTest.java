import me.darragh.brainfuck.interpreter.BrainfuckInterpreterConfiguration;
import me.darragh.brainfuck.interpreter.BrainfuckInterpreterEngine;
import me.darragh.brainfuck.interpreter.BrainfuckInterpreterEngines;
import me.darragh.brainfuck.parser.BrainfuckParserEngine;
import me.darragh.brainfuck.parser.BrainfuckParserEngines;
import me.darragh.brainfuck.parser.BrainfuckParserException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JavaBrainfuckInterpreterEngineTest {
    static final BrainfuckParserEngine PARSER = BrainfuckParserEngines.javaBrainfuckParserEngine();

    //region Standard Programs
    //region Hello World
    // from https://gist.github.com/roachhd/dce54bec8ba55fb17d3a
    String HELLO_WORLD_SOURCE = """
        >++++++++[<+++++++++>-]<.>++++[<+++++++>-]<+.+++++++..+++.>>++++++[<+++++++>-]<+
        +.------------.>++++++[<+++++++++>-]<+.<.+++.------.--------.>>>++++[<++++++++>-
        ]<+.
        """;

    @Test
    public void testHelloWorld() throws BrainfuckParserException {
        StringBuilder output = new StringBuilder();

        BrainfuckInterpreterEngine interpreter = BrainfuckInterpreterEngines.javaBrainfuckInterpreterEngine(new BrainfuckInterpreterConfiguration(
                30_000,
                new BrainfuckInterpreterConfiguration.ConsoleIo() {
                    @Override
                    public char in() {
                        return 0;
                    }

                    @Override
                    public void out(char c) {
                        output.append(c);
                    }
                }
        ));

        interpreter.execute(PARSER.extractTokens(HELLO_WORLD_SOURCE));
        assertThat(output.toString()).isEqualTo("Hello, World!");
    }
    //endregion
    //endregion
}
