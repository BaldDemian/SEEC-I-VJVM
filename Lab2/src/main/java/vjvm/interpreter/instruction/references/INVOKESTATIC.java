package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JClass;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.RefConstant;
import vjvm.utils.UnimplementedError;

public class INVOKESTATIC extends Instruction {
  private final MethodInfo method;


  /**
   *
   * @param pc current pc
   * @param method current method
   */
  public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
    // decode invokestatic
    // get the constant pool of the current class
    var constantPool = method.jClass().constantPool();
    var indexbyte1 = pc.byte_();
    var indexbyte2 = pc.byte_();
    var index = (indexbyte1 << 8) | indexbyte2;
    RefConstant constant = (RefConstant) constantPool.constant(index); // CONSTANT_Methodref_info
    var thisClass = method.jClass();
    String classDescriptor = "L" + constant.className() + ";";
    var jClass = thisClass.classLoader().loadClass(classDescriptor);
    var nameAndType = constant.nameAndTypeConstant();
    var name = nameAndType.name();
    var descriptor = nameAndType.type();
    this.method = jClass.findMethod(name, descriptor);
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var args = stack.popSlots(method.argc());
    thread.context().interpreter().invoke(method, thread, args);
  }

  @Override
  public String toString() {
    return String.format("invokestatic %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
  }
}
