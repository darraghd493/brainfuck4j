import lombok.SneakyThrows;
import me.darragh.brainfuck4j.ast.Token;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngine;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngines;
import me.darragh.brainfuck4j.transpiler.BrainfuckClassGenerator;
import me.darragh.brainfuck4j.transpiler.BrainfuckClassGeneratorConfiguration;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SuppressWarnings({"resource", "JavaPrintToLogpoint"})
@SneakyThrows
void main() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://brainfuck.org/tictactoe.b"))
            .build();
    String program = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

    BrainfuckParserEngine parser = BrainfuckParserEngines.javaBrainfuckParserEngine();
    List<Token> tokens = parser.extractTokens(program.stripIndent().trim());

    BrainfuckClassGenerator generator = new BrainfuckClassGenerator(BrainfuckClassGeneratorConfiguration.DEFAULT);
    // BrainfuckClassGenerator generator = new BrainfuckClassGenerator(new BrainfuckClassGeneratorConfiguration(3_000_000, "Main"));
    ClassNode classNode = generator.generateClass(tokens);
    classNode.version = Opcodes.V1_8;

    ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    classNode.accept(classWriter);
    byte[] bytecode = classWriter.toByteArray();
    Path path = Path.of("Main.class");
    Files.write(path, bytecode);
    System.out.println("Bytecode written to " + path.toAbsolutePath());
}