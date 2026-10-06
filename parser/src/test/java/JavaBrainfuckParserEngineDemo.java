import lombok.SneakyThrows;
import me.darragh.brainfuck.parser.BrainfuckParserEngine;
import me.darragh.brainfuck.parser.BrainfuckParserEngines;

@SneakyThrows
void main() {
    BrainfuckParserEngine engine = BrainfuckParserEngines.javaBrainfuckParserEngine();
    engine.extractTokens("++>--<.,[]").forEach(token -> System.out.println(token.getClass().getSimpleName()));
}