package vjvm.runtime.classdata.constant;

import lombok.var;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import vjvm.runtime.JClass;

import java.io.DataInput;

import static vjvm.classfiledefs.ConstantTags.*;

public abstract class Constant {
  @SneakyThrows
  public static Pair<Constant, Integer> constructFromData(DataInput input, JClass jClass) {
    // tag --> u1, only 1 byte
    var tag = input.readByte();
    var count = 1;
    Constant result;
    switch (tag) {
      case CONSTANT_Class:
        result = new ClassConstant(input, jClass);
        break;

      case CONSTANT_Fieldref:
        result = new RefConstant(input, jClass, "Field");
        break;

      case CONSTANT_Methodref:
        result = new RefConstant(input, jClass, "Method");
        break;

      case CONSTANT_InterfaceMethodref:
        result = new RefConstant(input, jClass, "Interface");
        break;

      case CONSTANT_Integer:
        result = new IntegerConstant(input);
        break;

      case CONSTANT_NameAndType:
        result = new NameAndTypeConstant(input, jClass);
        break;

      case CONSTANT_Utf8:
        result = new UTF8Constant(input);
        break;

      case CONSTANT_Double:
        result = new DoubleConstant(input);
        count = 2;
        // A Constant_Double_info takes up 2 indexes of the constant pool
        break;

      case CONSTANT_Long:
        result = new LongConstant(input);
        count = 2;
        // A Constant_Long_info takes up 2 indexes of the constant pool
        break;

      case CONSTANT_String:
        result = new StringConstant(input, jClass);
        break;

      case CONSTANT_Float:
        result = new FloatConstant(input);
        break;

      case CONSTANT_MethodHandle:
        result = new UnknownConstant(input, 3, "MethodHandle");
        break;

      case CONSTANT_MethodType:
        result = new UnknownConstant(input, 2, "MethodType");
        break;

      case CONSTANT_InvokeDynamic:
        result = new UnknownConstant(input, 4, "InvokeDynamic");
        break;

      default:
        throw new ClassFormatError();
    }
    return Pair.of(result, count);
  }
}
