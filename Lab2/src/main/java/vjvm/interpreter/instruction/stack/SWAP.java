package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SWAP extends Instruction {
  private final String name;
  public static final SWAP SWAP(ProgramCounter pc, MethodInfo method) {
    return new SWAP("swap");
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var val1 = stack.popSlots(1);
    var val2 = stack.popSlots(1);
    stack.pushSlots(val1);
    stack.pushSlots(val2);
  }

  @Override
  public String toString() {
    return name;
  }
}
