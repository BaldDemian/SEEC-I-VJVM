package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import java.io.DataInput;

public class ClassConstant extends Constant{
  private final int nameIndex;
  private final JClass self;
  private String name;
  @SneakyThrows
  public ClassConstant(DataInput input, JClass self) {
    nameIndex = input.readUnsignedShort();
    this.self = self;
  }

  // lazy loding
  public String name() {
    if (name == null) {
      name = ((UTF8Constant) self.constantPool().constant(nameIndex)).value();
    }
    return name;
  }

  @Override
  public String toString() {
    return String.format("Class: %s", name());
  }
}
