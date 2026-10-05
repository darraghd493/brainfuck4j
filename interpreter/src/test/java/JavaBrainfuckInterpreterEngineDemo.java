import lombok.SneakyThrows;
import me.darragh.brainfuck.ast.Token;
import me.darragh.brainfuck.interpreter.BrainfuckInterpreterEngine;
import me.darragh.brainfuck.interpreter.BrainfuckInterpreterEngines;
import me.darragh.brainfuck.parser.BrainfuckParserEngine;
import me.darragh.brainfuck.parser.BrainfuckParserEngines;

// from https://gist.github.com/roachhd/dce54bec8ba55fb17d3a
String HELLO_WORLD_SOURCE = """
        >++++++++[<+++++++++>-]<.>++++[<+++++++>-]<+.+++++++..+++.>>++++++[<+++++++>-]<+
        +.------------.>++++++[<+++++++++>-]<+.<.+++.------.--------.>>>++++[<++++++++>-
        ]<+.
        """;

@SneakyThrows
void main() {
    BrainfuckParserEngine PARSER = BrainfuckParserEngines.javaBrainfuckParserEngine();
    BrainfuckInterpreterEngine INTERPRETER = BrainfuckInterpreterEngines.javaBrainfuckInterpreterEngine();

    List<Token> tokens = PARSER.extractTokens(HELLO_WORLD_SOURCE);
    INTERPRETER.execute(tokens);
}