package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XCMPCOND extends Instruction {
  private final String name;
  public static final XCMPCOND FCMPL(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("fcmpl");
  }
  public static final XCMPCOND FCMPG(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("fcmpg");
  }
  public static final XCMPCOND DCMPL(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("dcmpl");
  }
  public static final XCMPCOND DCMPG(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("dcmpg");
  }
  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (name.equals("fcmpl")) {
      float val2 = stack.popFloat();
      float val1 = stack.popFloat();
      if (val1 == Float.NaN || val2 == Float.NaN) {
        stack.pushInt(-1);
      } else if (val1 > val2) {
        stack.pushInt(1);
      } else if (val1 == val2) {
        stack.pushInt(0);
      } else {
        stack.pushInt(-1);
      }
    } else if (name.equals("fcmpg")) {
      float val2 = stack.popFloat();
      float val1 = stack.popFloat();
      if (val1 == Float.NaN || val2 == Float.NaN) {
        stack.pushInt(1);
      } else if (val1 > val2) {
        stack.pushInt(1);
      } else if (val1 == val2) {
        stack.pushInt(0);
      } else {
        stack.pushInt(-1);
      }
    } else if (name.equals("dcmpl")) {
      double val2 = stack.popDouble();
      double val1 = stack.popDouble();
      if (val1 == Double.NaN || val2 == Double.NaN) {
        stack.pushInt(-1);
      } else if (val1 > val2) {
        stack.pushInt(1);
      } else if (val1 == val2) {
        stack.pushInt(0);
      } else {
        stack.pushInt(-1);
      }
    } else if (name.equals("dcmpg")) {
      double val2 = stack.popDouble();
      double val1 = stack.popDouble();
      if (val1 == Double.NaN || val2 == Double.NaN) {
        stack.pushInt(1);
      } else if (val1 > val2) {
        stack.pushInt(1);
      } else if (val1 == val2) {
        stack.pushInt(0);
      } else {
        stack.pushInt(-1);
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
