package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import java.io.DataInput;

public class RefConstant extends Constant{
  private final String refType;
  private final int classIndex;
  private final int nameAndTypeIndex;
  private final JClass self;
  private String className;
  private NameAndTypeConstant nameAndTypeConstant;
  @SneakyThrows
  public RefConstant(DataInput input, JClass jClass, String type) {
    classIndex = input.readUnsignedShort();
    nameAndTypeIndex = input.readUnsignedShort();
    refType = type;
    self = jClass;
  }

  public String className() {
    if (className == null) {
       ClassConstant classConstant = (ClassConstant) self.constantPool().constant(classIndex);
       className = classConstant.name();
    }
    return className;
  }
  public NameAndTypeConstant nameAndTypeConstant() {
    if (nameAndTypeConstant == null) {
      nameAndTypeConstant = ((NameAndTypeConstant) self.constantPool().constant(nameAndTypeIndex));
    }
    return nameAndTypeConstant;
  }
  @Override
  public String toString() {
    className = className();
    nameAndTypeConstant = nameAndTypeConstant();
    String name = nameAndTypeConstant.name();
    String type = nameAndTypeConstant.type();
    return String.format("%sref: %s.%s:%s", refType,className, name, type);
  }
}
