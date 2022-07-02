package vjvm.interpreter.instruction.comparisons;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IFCOND extends Instruction {
  private final String name;
  private final ProgramCounter pc;
  private final int pos; // 字节码的原始PC
  private final byte branchByte1;
  private final byte branchByte2;
  public static IFCOND IFEQ(ProgramCounter pc, MethodInfo method) {
    return new IFCOND("ifeq", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IFCOND IFNE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND("ifne", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IFCOND IFLT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND("iflt", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IFCOND IFGE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND("ifge", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IFCOND IFGT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND("ifgt", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  public static IFCOND IFLE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND("ifle", pc, pc.position() - 1, pc.byte_(), pc.byte_());
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var offset = (branchByte1 << 8) | branchByte2;
    if (name.equals("ifeq")) {
      int val = stack.popInt();
      if (val == 0) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("ifne")) {
      int val = stack.popInt();
      if (val != 0) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("iflt")) {
      int val = stack.popInt();
      if (val < 0) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("ifge")) {
      int val = stack.popInt();
      if (val >= 0) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("ifgt")) {
      int val = stack.popInt();
      if (val > 0) {
        this.pc.position(pos + offset);
      }
    }
    else if (name.equals("ifle")) {
      int val = stack.popInt();
      if (val <= 0) {
        this.pc.position(pos + offset);
      }
    }
    else {
      System.err.println("Wrong type in IFCOND!!!!");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
