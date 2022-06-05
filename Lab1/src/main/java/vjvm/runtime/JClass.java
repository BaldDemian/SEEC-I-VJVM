package vjvm.runtime;

import vjvm.classloader.JClassLoader;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.FieldInfo;
import vjvm.runtime.classdata.InterfaceInfo;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.attribute.Attribute;
import vjvm.runtime.classdata.constant.*;
import vjvm.utils.UnimplementedError;
import java.io.DataInput;
import java.io.InvalidClassException;
import java.util.Optional;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;

import static vjvm.classfiledefs.ClassAccessFlags.*;

public class JClass {
  @Getter
  private final JClassLoader classLoader;
  @Getter
  private final int minorVersion;
  @Getter
  private final int majorVersion;
  @Getter
  private final ConstantPool constantPool;
  @Getter
  private final int accessFlags;
  private final FieldInfo[] fields;
  private final MethodInfo[] methods;
  private final Attribute[] attributes;

  private InterfaceInfo[] interfaces;
  private String thisClass;
  private String superClass;

  @SneakyThrows
  public JClass(DataInput dataInput, JClassLoader classLoader) {
    this.classLoader = classLoader;

    // check magic number
    // readInt() --> read 4 bytes, 8 Hexidecimal bit
    var magic = dataInput.readInt();
    if (magic != 0xcafebabe) {
      throw new InvalidClassException(String.format(
        "Wrong magic number, expected: 0xcafebabe, got: 0x%x", magic));
    }

    // readUnsignedShort() --> read 2 bytes, in decimal form
    minorVersion = dataInput.readUnsignedShort();
    majorVersion = dataInput.readUnsignedShort();

    constantPool = new ConstantPool(dataInput, this);
    accessFlags = dataInput.readUnsignedShort();

    // read thisClass
    int thisClassIndex = dataInput.readUnsignedShort();
    ClassConstant classConstant = (ClassConstant) this.constantPool.constant(thisClassIndex);
    thisClass = classConstant.name();
    // read superClass
    int superClassIndex = dataInput.readUnsignedShort();
    if (superClassIndex < 1) {
      superClass = "";
    } else {
      classConstant = (ClassConstant) this.constantPool.constant(superClassIndex);
      superClass = classConstant.name();
    }
    int interfacesCount = dataInput.readUnsignedShort();
    interfaces = new InterfaceInfo[interfacesCount];
    for (int i = 0; i < interfacesCount; i++) {
      interfaces[i] = new InterfaceInfo(dataInput, this);
    }
    int filedsCount = dataInput.readUnsignedShort();
    fields = new FieldInfo[filedsCount];
    for (int i = 0; i < filedsCount; i++) {
      fields[i] = new FieldInfo(dataInput, this);
    }
    int methodsCount = dataInput.readUnsignedShort();
    methods = new MethodInfo[methodsCount];
    for (int i = 0; i < methodsCount; i++) {
      methods[i] = new MethodInfo(dataInput, this);
    }
    attributes = null; // We will not print attributes in Lab1.1
    System.out.println();
    System.out.println("class name: " + thisClass);
    System.out.println("minor version: " + minorVersion);
    System.out.println("major version: " + majorVersion);
    System.out.println("flags: " + String.format("0x%x", accessFlags));
    System.out.println("this class: " + thisClass);
    System.out.println("super class: " + superClass);
    System.out.println();
    System.out.println();
    System.out.println("constant pool:");
    for (int i = 1; i < constantPool.size(); i++) {

      System.out.print("#" + i + " = ");
      // Be careful with Long and Double
      System.out.println(constantPool.constant(i).toString());
      if (constantPool.constant(i) instanceof DoubleConstant ||
        constantPool.constant(i) instanceof LongConstant) {
        i = i + 1;
      }
    }

    System.out.println();

    System.out.println("interfaces:");
    for (int i = 0; i < interfacesCount; i++) {
      System.out.println(interfaces[i].toString());
    }

    System.out.println();

    System.out.println("fields:");
    for (int i = 0; i < filedsCount; i++) {
      System.out.println(fields[i].toString());
    }

    System.out.println();

    System.out.println("methods:");
    for (int i = 0; i < methodsCount; i++) {
      System.out.println(methods[i].toString());
    }
  }

  public boolean public_() {
    return (accessFlags & ACC_PUBLIC) != 0;
  }

  public boolean final_() {
    return (accessFlags & ACC_FINAL) != 0;
  }

  public boolean super_() {
    return (accessFlags & ACC_SUPER) != 0;
  }

  public boolean interface_() {
    return (accessFlags & ACC_INTERFACE) != 0;
  }

  public boolean abstract_() {
    return (accessFlags & ACC_ABSTRACT) != 0;
  }

  public boolean synthetic() {
    return (accessFlags & ACC_SYNTHETIC) != 0;
  }

  public boolean annotation() {
    return (accessFlags & ACC_ANNOTATION) != 0;
  }

  public boolean enum_() {
    return (accessFlags & ACC_ENUM) != 0;
  }

  public boolean module() {
    return (accessFlags & ACC_MODULE) != 0;
  }

  public int fieldsCount() {
    return fields.length;
  }

  public FieldInfo field(int index) {
    return fields[index];
  }

  public int methodsCount() {
    return methods.length;
  }

  public MethodInfo method(int index) {
    return methods[index];
  }

}
