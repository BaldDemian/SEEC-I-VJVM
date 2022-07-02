package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XNEG extends Instruction {
  private final String name;

  public static final XNEG INEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("ineg");
  }
  public static final XNEG LNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("lneg");
  }
  public static final XNEG FNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("fneg");
  }
  public static final XNEG DNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("dneg");
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (name.equals("ineg")) {
      int i = stack.popInt();
      stack.pushInt(-i);
    } else if (name.equals("lneg")) {
      long l = stack.popLong();
      stack.pushLong(-l);
    } else if (name.equals("fneg")) {
      float f = stack.popFloat();
      stack.pushFloat(-f);
    } else if (name.equals("dneg")) {
      double d = stack.popDouble();
      stack.pushDouble(-d);
    } else {
      System.err.println("Wrong type in XNEG!!!");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
