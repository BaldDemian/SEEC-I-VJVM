package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LIOPR extends Instruction {

  private final String name;

  public static final LIOPR ISHL(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("ishl");
  }
  public static final LIOPR LSHL(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("lshl");
  }
  public static final LIOPR ISHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("ishr");
  }
  public static final LIOPR LSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("lshr");
  }
  public static final LIOPR IUSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("iushr");
  }
  public static final LIOPR LUSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("lushr");
  }
  public static final LIOPR IAND(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("iand");
  }
  public static final LIOPR LAND(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("land");
  }
  public static final LIOPR IOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("ior");
  }
  public static final LIOPR LOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("lor");
  }
  public static final LIOPR IXOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("ixor");
  }
  public static final LIOPR LXOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR("lxor");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (name.equals("ishl")) {
      // Shift left int
      int val2 = stack.popInt();
      int val1 = stack.popInt();
      // An int result is calculated by shifting value1 left by s bit positions,
      // where s is the value of the low 5 bits of value2
      int moveBits = val2 & 0x1f;
      val1 = val1 << moveBits;
      stack.pushInt(val1);
    } else if (name.equals("lshl")) {
      int val2 = stack.popInt();
      long val1 = stack.popLong();
      // A long result is calculated by shifting value1 left by s bit positions,
      // where s is the low 6 bits of value2.
      int moveBits = val2 & 0x3f;
      val1 = val1 << moveBits;
      stack.pushLong(val1);
    } else if (name.equals("ishr")) {
      int val2 = stack.popInt();
      int val1 = stack.popInt();
      // An int result is calculated by shifting value1 right by s bit positions, with sign extension,
      // where s is the value of the low 5 bits of value2.
      int moveBits = val2 & 0x1f;
      val1 = val1 >> moveBits;
      stack.pushInt(val1);
    } else if (name.equals("lshr")) {
      int val2 = stack.popInt();
      long val1 = stack.popLong();
      int moveBits = val2 & 0x3f;
      val1 = val1 >> moveBits;
      stack.pushLong(val1);
    } else if (name.equals("iushr")) {
      int val2 = stack.popInt();
      int val1 = stack.popInt();
      int moveBits = val2 & 0x1f;
      val1 = val1 >>> moveBits;
      stack.pushInt(val1);
    } else if (name.equals("lushr")) {
      int val2 = stack.popInt();
      long val1 = stack.popLong();
      int moveBits = val2 & 0x3f;
      val1 = val1 >>> moveBits;
      stack.pushLong(val1);
    } else if (name.equals("iand")) {
      int val2 = stack.popInt();
      int val1 = stack.popInt();
      stack.pushInt(val1 & val2);
    } else if (name.equals("land")) {
      long val2 = stack.popLong();
      long val1 = stack.popLong();
      stack.pushLong(val1 & val2);
    } else if (name.equals("ior")) {
      int val2 = stack.popInt();
      int val1 = stack.popInt();
      stack.pushInt(val1 | val2);
    } else if (name.equals("lor")) {
      long val2 = stack.popLong();
      long val1 = stack.popLong();
      stack.pushLong(val1 | val2);
    } else if (name.equals("ixor")) {
      int val2 = stack.popInt();
      int val1 = stack.popInt();
      stack.pushInt(val1 ^ val2);
    } else if (name.equals("lxor")) {
      long val2 = stack.popLong();
      long val1 = stack.popLong();
      stack.pushLong(val1 ^ val2);
    } else {
      System.err.println("Wrong type in LIOPR!!!");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
