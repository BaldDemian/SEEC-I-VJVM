package vjvm.runtime.classdata.attribute;

import lombok.var;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.utils.UnimplementedError;

import java.io.DataInput;

@Getter
public class Code extends Attribute {
  private final int maxStack; // max depth of the oprand stack of this method
  private final int maxLocals; // max length of the slots
  private final byte[] code; // the bytecode represented as raw bytes
  private final Attribute[] attributes;

  @SneakyThrows
  Code(DataInput input, ConstantPool constantPool) {

    // construct Code
    maxStack = input.readUnsignedShort(); // u2
    maxLocals = input.readUnsignedShort(); // u2

    // read byte codes
    int codeLength = input.readInt(); // u4. Number of bytes in the "code" array
    code = new byte[codeLength];
    for (int i = 0; i < codeLength; i++) {
      code[i] = input.readByte();
    }
    // We will not handle the exception table
    int exceptionTableLength = input.readUnsignedShort(); // u2
    for (int i = 0; i < exceptionTableLength; i++) {
      input.readUnsignedShort(); // u2
      input.readUnsignedShort(); // u2
      input.readUnsignedShort(); // u2
      input.readUnsignedShort(); // u2
    }

    // read attributes
    int attributesCount = input.readUnsignedShort(); // u2
    attributes = new Attribute[attributesCount];
    for (int i = 0; i < attributesCount; i++) {
      attributes[i] = Attribute.constructFromData(input, constantPool, null);
    }
  }
}
