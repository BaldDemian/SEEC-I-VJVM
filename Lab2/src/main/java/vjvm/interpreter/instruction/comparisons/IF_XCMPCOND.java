package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IF_XCMPCOND extends Instruction {
  private final String name;
  private final ProgramCounter pc;
  private final int pos;
  private final byte branchByte1;
  private final byte branchByte2;
  public static IF_XCMPCOND IF_ICMPEQ(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND("if_icmpeq", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IF_XCMPCOND IF_ICMPNE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND("if_icmpne", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IF_XCMPCOND IF_ICMPLT(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND("if_icmplt", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IF_XCMPCOND IF_ICMPLE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND("if_icmple", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IF_XCMPCOND IF_ICMPGT(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND("if_icmpgt", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IF_XCMPCOND IF_ICMPGE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND("if_icmpge", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var offset = (branchByte1 << 8) | branchByte2;
    int val2 = stack.popInt();
    int val1 = stack.popInt();
    if (name.equals("if_icmpeq")) {
      if (val1 == val2) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("if_icmpne")) {
      if (val1 != val2) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("if_icmplt")) {
      if (val1 < val2) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("if_icmple")) {
      if (val1 <= val2) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("if_icmpgt")) {
      if (val1 > val2) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("if_icmpge")) {
      if (val1 >= val2) {
        this.pc.position(pos + offset);
      }
    }
    else {
      System.err.println("Wrong type in IF_XCMPCOND!!!");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
