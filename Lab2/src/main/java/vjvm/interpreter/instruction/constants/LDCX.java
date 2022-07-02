package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDCX extends Instruction {
  // load from the constant pool
  private final Constant constant;
  private final String name;
  public static final LDCX LDC(ProgramCounter pc, MethodInfo method) {
    // Decode
    var constantPool = method.jClass().constantPool();
    return new LDCX(constantPool.constant(pc.byte_()), "ldc");
  }

  public static final LDCX LDC_W(ProgramCounter pc, MethodInfo method) {
    // Decode
    var constantPool = method.jClass().constantPool();
    return new LDCX(constantPool.constant(pc.short_()), "ldc_w");
  }

  public static final LDCX LDC2_W(ProgramCounter pc, MethodInfo method) {
    // Decode
    var constantPool = method.jClass().constantPool();
    return new LDCX(constantPool.constant(pc.short_()), "ldc2_w");
  }
  @Override
  // run
  public void run(JThread thread) {
    var opStack = thread.top().stack();
    if (constant instanceof IntegerConstant) {
      opStack.pushInt(((IntegerConstant) constant).value());
    } else if (constant instanceof FloatConstant) {
      opStack.pushFloat(((FloatConstant) constant).value());
    } else if (constant instanceof LongConstant) {
      opStack.pushLong(((LongConstant) constant).value());
    } else if (constant instanceof DoubleConstant) {
      opStack.pushDouble(((DoubleConstant) constant).value());
    } else {
      // add more
      ;
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
