package vjvm.interpreter.instruction.loads;

import lombok.AccessLevel;
import lombok.var;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.IntegerConstant;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XLOAD extends Instruction {
  private final int index;
  private final String name;

  public static final XLOAD ILOAD(ProgramCounter pc, MethodInfo method) {
    // load int from local variable and then push it to the operand stack
    // todo: where can we find the slots(local variable array) of this method?
    return new XLOAD(pc.byte_(), "iload");
  }
  public static final XLOAD ILOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0, "iload_0");
  }
  public static final XLOAD ILOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1, "iload_1");
  }
  public static final XLOAD ILOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2, "iload_2");
  }
  public static final XLOAD ILOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3, "iload_3");
  }

  public static final XLOAD FLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.byte_(), "fload");
  }
  public static final XLOAD FLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0, "fload_0");
  }
  public static final XLOAD FLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1, "fload_1");
  }
  public static final XLOAD FLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2, "fload_2");
  }
  public static final XLOAD FLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3, "fload_3");
  }

  public static final XLOAD LLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.byte_(), "lload");
  }
  public static final XLOAD LLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0, "lload_0");
  }
  public static final XLOAD LLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1, "lload_1");
  }
  public static final XLOAD LLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2, "lload_2");
  }
  public static final XLOAD LLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3, "lload_3");
  }

  public static final XLOAD DLOAD(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(pc.byte_(), "dload");
  }
  public static final XLOAD DLOAD_0(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(0, "dload_0");
  }
  public static final XLOAD DLOAD_1(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(1, "dload_1");
  }
  public static final XLOAD DLOAD_2(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(2, "dload_2");
  }
  public static final XLOAD DLOAD_3(ProgramCounter pc, MethodInfo method) {
    return new XLOAD(3, "dload_3");
  }
  @Override
  public void run(JThread thread) {
    var slots = thread.top().vars();
    var stack = thread.top().stack();
    Object slot = slots.getSlot(this.index);
    if (slot instanceof Integer) {
      stack.pushInt((Integer) slot);
    } else if (slot instanceof Float) {
      stack.pushFloat((Float) slot);
    } else if (slot instanceof Double) {
      stack.pushDouble((Double) slot);
    } else if (slot instanceof Long) {
      stack.pushLong((Long) slot);
    } else {
      ;
      // add more
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
