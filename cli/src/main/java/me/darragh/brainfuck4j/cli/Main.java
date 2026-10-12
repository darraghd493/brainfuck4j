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
        name = "brainfuck4j",
        description = "Interprets/transpiles Brainfuck programs.",
        subcommands = { Interpret.class, Transpile.class },
        mixinStandardHelpOptions = true
)
public final class Main implements Runnable {
    @Override
    public void run() {
        System.out.println("For help, run brainfuck4j --help");
    }

    static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
