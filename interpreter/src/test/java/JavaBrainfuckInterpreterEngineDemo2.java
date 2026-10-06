import lombok.SneakyThrows;
import me.darragh.brainfuck.ast.Token;
import me.darragh.brainfuck.interpreter.BrainfuckInterpreterEngine;
import me.darragh.brainfuck.interpreter.BrainfuckInterpreterEngines;
import me.darragh.brainfuck.parser.BrainfuckParserEngine;
import me.darragh.brainfuck.parser.BrainfuckParserEngines;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SuppressWarnings({"resource"})
@SneakyThrows
void main() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://brainfuck.org/e.b"))
            .build();
    String program = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

    BrainfuckParserEngine parser = BrainfuckParserEngines.javaBrainfuckParserEngine();
    BrainfuckInterpreterEngine interpreter = BrainfuckInterpreterEngines.javaBrainfuckInterpreterEngine();

    List<Token> tokens = parser.extractTokens(program.stripIndent().trim());
    interpreter.execute(tokens);
}