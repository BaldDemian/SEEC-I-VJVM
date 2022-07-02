package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LCMP extends Instruction {
  private final String name;
  public static final LCMP LCMP(ProgramCounter pc, MethodInfo method) {
    return new LCMP("lcmp");
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (name.equals("lcmp")) {
      long val2 = stack.popLong();
      long val1 = stack.popLong();
      if (val1 > val2) {
        stack.pushInt(1);
      } else if (val1 == val2) {
        stack.pushInt(0);
      } else {
        stack.pushInt(-1);
      }
    } else {
      System.err.println("Wrong type in LCMP!!!");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
