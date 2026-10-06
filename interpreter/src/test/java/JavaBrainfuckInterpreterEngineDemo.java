import lombok.SneakyThrows;
import me.darragh.brainfuck4j.ast.Token;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterEngine;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterEngines;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngine;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngines;

// from https://gist.github.com/roachhd/dce54bec8ba55fb17d3a
String HELLO_WORLD_SOURCE = """
        >++++++++[<+++++++++>-]<.>++++[<+++++++>-]<+.+++++++..+++.>>++++++[<+++++++>-]<+
        +.------------.>++++++[<+++++++++>-]<+.<.+++.------.--------.>>>++++[<++++++++>-
        ]<+.
        """;

@SneakyThrows
void main() {
    BrainfuckParserEngine parser = BrainfuckParserEngines.javaBrainfuckParserEngine();
    BrainfuckInterpreterEngine interpreter = BrainfuckInterpreterEngines.javaBrainfuckInterpreterEngine();

    List<Token> tokens = parser.extractTokens(HELLO_WORLD_SOURCE);
    interpreter.execute(tokens);
}