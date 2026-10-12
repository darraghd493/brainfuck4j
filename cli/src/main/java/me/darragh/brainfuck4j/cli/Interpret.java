package me.darragh.brainfuck4j.cli;

import me.darragh.brainfuck4j.ast.Token;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterConfiguration;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterEngine;
import me.darragh.brainfuck4j.interpreter.BrainfuckInterpreterEngines;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngine;
import me.darragh.brainfuck4j.parser.BrainfuckParserEngines;
import me.darragh.brainfuck4j.parser.BrainfuckParserException;
import picocli.CommandLine;

import java.util.List;

/**
 * @author darraghd493
 * @since 0.0.1
 */
@CommandLine.Command(
        name = "interpret",
        description = "Interprets a Brainfuck program.",
        mixinStandardHelpOptions = true
)
public final class Interpret implements Runnable {
    @CommandLine.Option(names = { "-m", "--memory-size" }, description = "Memory size")
    int memorySize = 30_000;

    @CommandLine.Parameters(index = "0", paramLabel = "<program>", description = "The Brainfuck program to interpret")
    String program;

    @Override
    public void run() {
        BrainfuckParserEngine parser = BrainfuckParserEngines.javaBrainfuckParserEngine();
        BrainfuckInterpreterEngine interpreter = BrainfuckInterpreterEngines.javaBrainfuckInterpreterEngine(new BrainfuckInterpreterConfiguration(
                this.memorySize,
                BrainfuckInterpreterConfiguration.ConsoleIo.UNSAFE_SYSTEM_CONSOLE
        ));

        List<Token> tokens;
        try {
            tokens = parser.extractTokens(this.program);
        } catch (BrainfuckParserException e) {
            System.err.println("Error parsing program: " + e.getMessage());
            return;
        }

        interpreter.execute(tokens);
    }
}
