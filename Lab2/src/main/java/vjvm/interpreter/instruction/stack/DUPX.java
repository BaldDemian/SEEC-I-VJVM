package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX extends Instruction {
  // duplicate the value on the top of the stack and then push it back
  private final String name;

  public static final DUPX DUP(ProgramCounter pc, MethodInfo method) {
    return new DUPX("dup");
  }
  public static final DUPX DUP_X1(ProgramCounter pc, MethodInfo method) {
    return new DUPX("dup_x1");
  }
  public static final DUPX DUP_X2(ProgramCounter pc, MethodInfo method) {
    return new DUPX("dup_x2");
  }
  public static final DUPX DUP2(ProgramCounter pc, MethodInfo method) {
    return new DUPX("dup2");
  }
  public static final DUPX DUP2_X1(ProgramCounter pc, MethodInfo method) {
    return new DUPX("dup2_x1");
  }
  public static final DUPX DUP2_X2(ProgramCounter pc, MethodInfo method) {
    return new DUPX("dup2_x2");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    if (name.equals("dup")) {
      // make sure the value of top of the stack is NOT a double or long
      var val1 = stack.popSlots(1);
      stack.pushSlots(val1);
      stack.pushSlots(val1);
    } else if (name.equals("dup_x1")) {
      var val1 = stack.popSlots(1);
      var val2 = stack.popSlots(1);
      stack.pushSlots(val1);
      stack.pushSlots(val2);
      stack.pushSlots(val1);
    } else if (name.equals("dup_x2")) {
      var val1 = stack.popSlots(1);
      var val2 = stack.popSlots(1);
      var val3 = stack.popSlots(1);
      stack.pushSlots(val1);
      stack.pushSlots(val3);
      stack.pushSlots(val2);
      stack.pushSlots(val1);
    } else if (name.equals("dup2")) {
      var val1 = stack.popSlots(1);
      var val2 = stack.popSlots(1);
      stack.pushSlots(val2);
      stack.pushSlots(val1);
      stack.pushSlots(val2);
      stack.pushSlots(val1);
    } else if (name.equals("dup2_x1")) {
      var val1 = stack.popSlots(1);
      var val2 = stack.popSlots(1);
      var val3 = stack.popSlots(1);
      stack.pushSlots(val2);
      stack.pushSlots(val1);
      stack.pushSlots(val3);
      stack.pushSlots(val2);
      stack.pushSlots(val1);
    } else if (name.equals("dup2_x2")) {
      var val1 = stack.popSlots(1);
      var val2 = stack.popSlots(1);
      var val3 = stack.popSlots(1);
      var val4 = stack.popSlots(1);
      stack.pushSlots(val2);
      stack.pushSlots(val1);
      stack.pushSlots(val4);
      stack.pushSlots(val3);
      stack.pushSlots(val2);
      stack.pushSlots(val1);
    } else {
      System.err.println("Type err in dup!!!");
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
