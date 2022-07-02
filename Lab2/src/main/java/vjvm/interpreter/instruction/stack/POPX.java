package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class POPX extends Instruction {

  private final String name;

  public static final POPX POP(ProgramCounter pc, MethodInfo method) {
    return new POPX("pop");
  }
  public static final POPX POP2(ProgramCounter pc, MethodInfo method) {
    return new POPX("pop2");
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (name.equals("pop")) {
      stack.popInt();
    } else if (name.equals("pop2")) {
      stack.popDouble();
    } else {
      System.err.println("Type err in POP!!!!");
    }
  }

  @Override
  public String toString() {
    return null;
  }
}
