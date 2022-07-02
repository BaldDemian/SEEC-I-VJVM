package vjvm.interpreter.instruction.conversions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class X2Y extends Instruction {
  private final String name;
  public static final X2Y I2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2l");
  }
  public static final X2Y I2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2f");
  }
  public static final X2Y I2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2d");
  }
  public static final X2Y L2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y("l2i");
  }
  public static final X2Y L2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y("l2f");
  }
  public static final X2Y L2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y("l2d");
  }
  public static final X2Y F2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y("f2i");
  }
  public static final X2Y F2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y("f2l");
  }
  public static final X2Y F2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y("f2d");
  }
  public static final X2Y D2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y("d2i");
  }
  public static final X2Y D2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y("d2l");
  }
  public static final X2Y D2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y("d2f");
  }
  public static final X2Y I2B(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2b");
  }
  public static final X2Y I2C(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2c");
  }
  public static final X2Y I2S(ProgramCounter pc, MethodInfo method) {
    return new X2Y("i2s");
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (name.equals("i2l")) {
      int val = stack.popInt();
      stack.pushLong((long) val);
    } else if (name.equals("i2f")) {
      int val = stack.popInt();
      stack.pushFloat((float) val);
    } else if (name.equals("i2d")) {
      int val = stack.popInt();
      stack.pushDouble((double) val);
    } else if (name.equals("i2b")) {
      int val = stack.popInt();
      stack.pushByte((byte) val);
    } else if (name.equals("i2c")) {
      int val = stack.popInt();
      stack.pushChar((char) val);
    } else if (name.equals("i2s")) {
      int val = stack.popInt();
      stack.pushShort((short) val);
    } else if (name.equals("l2i")) {
      long val = stack.popLong();
      stack.pushInt((int) val);
    } else if (name.equals("l2f")) {
      long val = stack.popLong();
      stack.pushFloat((float) val);
    } else if (name.equals("l2d")) {
      long val = stack.popLong();
      stack.pushDouble((double) val);
    } else if (name.equals("f2i")) {
      float val = stack.popFloat();
      stack.pushInt((int) val);
    } else if (name.equals("f2l")) {
      float val = stack.popFloat();
      stack.pushLong((long) val);
    } else if (name.equals("f2d")) {
      float val = stack.popFloat();
      stack.pushDouble((double) val);
    } else if (name.equals("d2i")) {
      double val = stack.popDouble();
      stack.pushInt((int) val);
    } else if (name.equals("d2l")) {
      double val = stack.popDouble();
      stack.pushLong((long) val);
    } else if (name.equals("d2f")) {
      double val = stack.popDouble();
      stack.pushFloat((float) val);
    } else {
      System.err.println("Wrong type in X2Y!!!");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
