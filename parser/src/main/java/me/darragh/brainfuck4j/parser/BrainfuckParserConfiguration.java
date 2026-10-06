package me.darragh.brainfuck4j.parser;

/**
 * @author darraghd493
 * @since 1.0.0
 */
public record BrainfuckParserConfiguration(
        char moveLeftCharacter,
        char moveRightCharacter,
        char incrementCharacter,
        char decrementCharacter,
        char startLoopCharacter,
        char endLoopCharacter,
        char inputCharacter,
        char outputCharacter
) {
    public static final BrainfuckParserConfiguration DEFAULT = new BrainfuckParserConfiguration('<', '>', '+', '-', '[', ']', ',', '.');
}
