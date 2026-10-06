package me.darragh.brainfuck.transpiler;

import lombok.RequiredArgsConstructor;
import me.darragh.brainfuck.ast.Token;
import me.darragh.brainfuck.ast.TokenType;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.List;
import java.util.Stack;

/**
 * @author darraghd493
 * @since 0.0.1
 */
@RequiredArgsConstructor
public final class BrainfuckClassGenerator implements Opcodes {
    private final BrainfuckClassGeneratorConfiguration configuration;

    @SuppressWarnings("RedundantLabeledSwitchRuleCodeBlock")
    public @NotNull ClassNode generateClass(@NotNull List<Token> tokens) {
        ClassNode classNode = new ClassNode();
        classNode.name = this.configuration.className();
        classNode.version = V1_8;
        classNode.superName = "java/lang/Object";
        classNode.access = ACC_PUBLIC;

        // public static void main(String[] args)
        MethodNode methodNode = new MethodNode(
                ACC_STATIC | ACC_PUBLIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                new String[] {
                        "java/io/IOException"
                }
        );
        classNode.methods.add(methodNode);

        // init memory (byte array)
        methodNode.instructions.add(new LdcInsnNode(configuration.allocatedBytes()));
        methodNode.instructions.add(new IntInsnNode(NEWARRAY, T_BYTE));
        methodNode.instructions.add(new VarInsnNode(ASTORE, 0));

        // init pointer (int)
        methodNode.instructions.add(new InsnNode(ICONST_0));
        methodNode.instructions.add(new VarInsnNode(ISTORE, 1));

        // generate tokens
        Stack<LabelNode> loopLabels = new Stack<>();
        Stack<JumpInsnNode> startJumpNodes = new Stack<>();

        for (Token token : tokens) {
            switch (token.getType()) {
                case MOVE_LEFT, MOVE_RIGHT -> {
                    // pointer++/--
                    methodNode.instructions.add(new IincInsnNode(1, token.getType() == TokenType.MOVE_RIGHT ? 1 : -1));
                }
                case INCREMENT, DECREMENT -> {
                    // memory[pointer]++/--
                    methodNode.instructions.add(new VarInsnNode(ALOAD, 0)); // load array
                    methodNode.instructions.add(new VarInsnNode(ILOAD, 1)); // load pointer
                    methodNode.instructions.add(new InsnNode(DUP2));
                    methodNode.instructions.add(new InsnNode(BALOAD)); // load byte
                    methodNode.instructions.add(new InsnNode(ICONST_1)); // + 1
                    methodNode.instructions.add(new InsnNode(token.getType() == TokenType.INCREMENT ? IADD : ISUB));
                    methodNode.instructions.add(new InsnNode(BASTORE)); // store byte
                }
                case START_LOOP -> {
                    LabelNode startLabelNode = new LabelNode();
                    methodNode.instructions.add(startLabelNode);
                    loopLabels.push(startLabelNode);

                    methodNode.instructions.add(new VarInsnNode(ALOAD, 0)); // load array
                    methodNode.instructions.add(new VarInsnNode(ILOAD, 1)); // load pointer
                    methodNode.instructions.add(new InsnNode(BALOAD)); // load byte

                    JumpInsnNode jumpNode = new JumpInsnNode(IFEQ, null);
                    methodNode.instructions.add(jumpNode);
                    startJumpNodes.push(jumpNode);
                }
                case END_LOOP -> {
                    LabelNode startLabelNode = loopLabels.pop(),
                            endLabelNode = new LabelNode();
                    methodNode.instructions.add(endLabelNode);

                    methodNode.instructions.add(new VarInsnNode(ALOAD, 0)); // load array
                    methodNode.instructions.add(new VarInsnNode(ILOAD, 1)); // load pointer
                    methodNode.instructions.add(new InsnNode(BALOAD)); // load byte
                    methodNode.instructions.add(new JumpInsnNode(IFNE, startLabelNode));

                    startJumpNodes.pop().label = endLabelNode;
                }
                case INPUT -> {
                    // invokevirtual: objectref, [arg1, arg2, ...] → result
                    methodNode.instructions.add(new VarInsnNode(ALOAD, 0)); // load array
                    methodNode.instructions.add(new VarInsnNode(ILOAD, 1)); // load pointer
                    methodNode.instructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"));
                    methodNode.instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false));
                    methodNode.instructions.add(new InsnNode(I2B)); // convert to byte
                    methodNode.instructions.add(new InsnNode(BASTORE)); // store byte
                }
                case OUTPUT -> {
                    methodNode.instructions.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
                    methodNode.instructions.add(new VarInsnNode(ALOAD, 0)); // load array
                    methodNode.instructions.add(new VarInsnNode(ILOAD, 1)); // load pointer
                    methodNode.instructions.add(new InsnNode(BALOAD)); // load byte
                    methodNode.instructions.add(new InsnNode(I2C)); // convert to char
                    methodNode.instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false));
                }
            }
        }

        methodNode.instructions.add(new InsnNode(RETURN));

        return classNode;
    }
}
