package me.darragh.brainfuck4j.cli;

import me.darragh.brainfuck4j.ast.Token;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterConfiguration;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterEngine;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterEngines;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngine;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngines;
import me.darragh.brainfuck4j.parser.BrainfuckParserException;
import me.darragh.brainfuck4j.transpiler.BrainfuckClassGenerator;
import me.darragh.brainfuck4j.transpiler.BrainfuckClassGeneratorConfiguration;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import picocli.CommandLine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author darraghd493
 * @since 0.0.1
 */
@CommandLine.Command(
        name = "transpile",
        description = "Transpiles a Brainfuck program to a JVM class (targeting Java 8).",
        mixinStandardHelpOptions = true
)
public final class Transpile implements Runnable {
    @CommandLine.Option(names = { "-m", "--memory-size" }, description = "Memory size")
    int memorySize = 30_000;

    @CommandLine.Parameters(index = "0", paramLabel = "<program>", description = "The Brainfuck program to interpret")
    String program;

    @CommandLine.Parameters(index = "1", paramLabel = "<class-name>", description = "The JVM class name")
    String className;

    @Override
    public void run() {
        BrainfuckParserEngine parser = BrainfuckParserEngines.javaBrainfuckParserEngine();
        BrainfuckClassGenerator classGenerator = new BrainfuckClassGenerator(new BrainfuckClassGeneratorConfiguration(
                this.memorySize,
                this.className
        ));

        List<Token> tokens;
        try {
            tokens = parser.extractTokens(this.program);
        } catch (BrainfuckParserException e) {
            System.err.println("Error parsing program: " + e.getMessage());
            return;
        }

        ClassNode classNode = classGenerator.generateClass(tokens);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(classWriter);
        byte[] classBytecode = classWriter.toByteArray();

        Path path = Path.of(this.className + ".class");

        try {
            Files.write(path, classBytecode);
            System.out.println("JVM class written to " + path.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error writing JVM class: " + e.getMessage());
        }
    }
}
