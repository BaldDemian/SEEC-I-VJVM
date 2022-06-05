package vjvm.runtime.classdata;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.constant.ClassConstant;

import java.io.DataInput;

public class InterfaceInfo {
  private final String name;
  @SneakyThrows
  public InterfaceInfo(DataInput input, JClass jClass) {
    int nameIndex = input.readUnsignedShort();
    this.name = ((ClassConstant) jClass.constantPool().constant(nameIndex)).name();
  }

  @Override
  public String toString() {
    return this.name;
  }
}
