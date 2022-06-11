package vjvm.runtime.classdata.attribute;

import lombok.var;
import lombok.SneakyThrows;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.UTF8Constant;

import java.io.DataInput;

public abstract class Attribute {

  @SneakyThrows
  public static Attribute constructFromData(DataInput input, ConstantPool constantPool, MethodInfo method) {
    // treat the attribute "Code" specially
    // detect and construct "Code" attribute
    var nameIndex = input.readUnsignedShort(); // u2
    var attrLength = Integer.toUnsignedLong(input.readInt()); // u4
    String name = ((UTF8Constant) constantPool.constant(nameIndex)).value();
    if (name.equals("Code")) {
      Code code = new Code(input, constantPool);
      method.setCode(code);
      return code;
    } else {
      // treat other all attributes with no difference
      return new UnknownAttribute(input, attrLength);
    }
  }
}
