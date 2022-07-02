package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NOP extends Instruction {
  private final String name;
  public static final NOP NOP(ProgramCounter pc, MethodInfo methodInfo) {
    return new NOP("nop");
  }
  @Override
  public void run(JThread thread) {
    ;
    // do nothing
  }

  @Override
  public String toString() {
    return name;
  }
}
