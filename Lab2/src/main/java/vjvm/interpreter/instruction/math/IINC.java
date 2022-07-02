package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IINC extends Instruction {
  private final String name;
  private final int index;
  private final int offset;
  public static final IINC IINC(ProgramCounter pc, MethodInfo method) {
    // Increment local variable by constant
    return new IINC("iinc", pc.byte_(), pc.byte_());
  }
  @Override
  public void run(JThread thread) {
    var slots = thread.top().vars();
    int i = slots.int_(this.index);
    slots.int_(this.index, i + this.offset);
  }

  @Override
  public String toString() {
    return null;
  }
}
