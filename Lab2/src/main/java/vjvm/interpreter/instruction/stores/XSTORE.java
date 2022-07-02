package vjvm.interpreter.instruction.stores;

import lombok.var;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XSTORE extends Instruction {
  // pop one operand from the operand stack
  // and then set the slot of the given index to this operand
  private final int index;
  private final String name;
  private final String type;

  public static final XSTORE ISTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.byte_(), "istore", "int");
  }
  public static final XSTORE ISTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0, "istore", "int");
  }
  public static final XSTORE ISTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1, "istore", "int");
  }
  public static final XSTORE ISTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2, "istore", "int");
  }
  public static final XSTORE ISTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3, "istore", "int");
  }

  public static final XSTORE LSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.byte_(), "lstore", "long");
  }
  public static final XSTORE LSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0, "lstore", "long");
  }
  public static final XSTORE LSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1, "lstore", "long");
  }
  public static final XSTORE LSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2, "lstore", "long");
  }
  public static final XSTORE LSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3, "lstore", "long");
  }

  public static final XSTORE FSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.byte_(), "fstore", "float");
  }
  public static final XSTORE FSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0, "fstore", "float");
  }
  public static final XSTORE FSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1, "fstore", "float");
  }
  public static final XSTORE FSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2, "fstore", "float");
  }
  public static final XSTORE FSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3, "fstore", "float");
  }

  public static final XSTORE DSTORE(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(pc.byte_(), "dstore", "double");
  }
  public static final XSTORE DSTORE_0(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(0, "dstore", "double");
  }
  public static final XSTORE DSTORE_1(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(1, "dstore", "double");
  }
  public static final XSTORE DSTORE_2(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(2, "dstore", "double");
  }
  public static final XSTORE DSTORE_3(ProgramCounter pc, MethodInfo method) {
    return new XSTORE(3, "dstore", "double");
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var slots = thread.top().vars();
    if (type.equals("int")) {
      Integer slot = stack.popInt();
      slots.setSlot(slot, this.index);
    } else if (type.equals("double")) {
      Double slot = stack.popDouble();
      slots.setSlot(slot, this.index);
    } else if (type.equals("float")) {
      Float slot = stack.popFloat();
      slots.setSlot(slot, this.index);
    }
    else if (type.equals("long")) {
      Long slot = stack.popLong();
      slots.setSlot(slot, this.index);
    }
    else {
      System.err.println("Check the type in XSTORE!!!");
      // add more
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
