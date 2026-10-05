package me.darragh.brainfuck.ast;

import lombok.experimental.UtilityClass;
import me.darragh.brainfuck.ast.type.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author darraghd493
 * @since 1.0.0
 */
@UtilityClass
public final class Tokens {
    public static @NotNull MoveLeftToken moveLeftToken() {
        return new MoveLeftToken();
    }

    public static @NotNull MoveRightToken moveRightToken() {
        return new MoveRightToken();
    }

    public static @NotNull IncrementToken incrementToken() {
        return new IncrementToken();
    }

    public static @NotNull DecrementToken decrementToken() {
        return new DecrementToken();
    }

    public static @NotNull InputToken inputToken() {
        return new InputToken();
    }

    public static @NotNull OutputToken outputToken() {
        return new OutputToken();
    }

    public static @NotNull StartLoopToken startLoopToken() {
        return new StartLoopToken();
    }

    public static @NotNull EndLoopToken endLoopToken() {
        return new EndLoopToken();
    }
}
