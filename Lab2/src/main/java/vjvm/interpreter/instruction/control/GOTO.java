package vjvm.interpreter.instruction.control;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GOTO extends Instruction {
  private final String name;
  private final ProgramCounter pc;
  private final int pos;
  private final byte branchByte1;
  private final byte branchByte2;
  public static GOTO GOTO (ProgramCounter pc, MethodInfo methodInfo) {
    //System.out.println("Running goto!");
    //System.out.println(pc.position());
    return new GOTO("goto", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  @Override
  public void run(JThread thread) {
    var offset = (branchByte1 << 8) | branchByte2;
    //System.out.println("offset is " + offset);
    this.pc.position(pos + offset);
  }

  @Override
  public String toString() {
    return name;
  }
}
